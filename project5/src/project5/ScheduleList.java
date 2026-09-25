package project5;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ScheduleList {
	// ArrayList<Schedule> schedule = new ArrayList<Schedule>(); 이걸로 향후 갱신
	Schedule[] schedule = new Schedule[100];
	File file;
	String fileName;
	int index = 0;
	
	public ScheduleList(String fileName) {
		this.fileName = fileName;
		this.file = new File(fileName);

		loadFromFile();
	}

	public int numSchedules() { return index; }
	public Schedule getSchedule(int i) { return schedule[i]; }
	public void add(Schedule s) {
		schedule[index] = s;
		index++;
	}
	
	public boolean isTimeConflict(LocalDateTime start, LocalDateTime end) {
		return end.isBefore(start);
	}
	
	public void saveToFile() {
		try (PrintWriter writer = new PrintWriter(fileName)) {
			for (Schedule s : schedule) {
				String title = s.getTitle();
				LocalDateTime start = s.getStart();
				LocalDateTime end = s.getEnd();
				String place = s.getPlace();
				String memo = s.getMemo();
				
				if (title != null) {
					writer.println("TITLE = " + title);
				}
				if (start != null) {
					writer.println("START = " + start);
				}
				if (end != null) {
					writer.println("END = " + end);
				}
				if (place != null) {
					writer.println("PLACE = " + place);
				}
				if (memo != null) {
					writer.println("MEMO = " + memo);
				}
				writer.println(";");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void loadFromFile() {
		schedule = new Schedule[100];
		index = 0;
		
		try (Scanner scanner = new Scanner(this.file, "MS949")) {
			String[] result = {null, null, null, null, null};		
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] str = line.split("=");
				
				if (line.length() == 0 || line.startsWith("//")) { continue; }
				if(line.equals(";")) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					LocalDateTime dateTimeStart = null, dateTimeEnd = null;
					
					if (result[1] != null) {
						try {
							dateTimeStart = LocalDateTime.parse(result[1], formatter);
						} catch (Exception e) { System.out.println("Date Format Error"); }
					}
					if (result[2] != null) {
						try {
							dateTimeEnd = LocalDateTime.parse(result[2], formatter);
						} catch (Exception e) { System.out.println("Date Format Error"); }
					}
					if (dateTimeStart != null && dateTimeEnd != null && isTimeConflict(dateTimeStart, dateTimeEnd)) {
						result[0] = null;
						result[1] = null;
						result[2] = null;
						result[3] = null;
						result[4] = null;
						System.out.println("The end time cannot be earlier than the start time");
						continue;
					}					
					add(new Schedule(result[0], dateTimeStart, dateTimeEnd, result[3], result[4]));
					
					result[0] = null;
					result[1] = null;
					result[2] = null;
					result[3] = null;
					result[4] = null;
					continue;
				}			
				try {
					if (str[0].trim().equals("TITLE")) {	
						result[0] = str[1].trim();
					} else if (str[0].trim().equals("START")) {
						result[1] = str[1].trim();
					} else if (str[0].trim().equals("END")) {
						result[2] = str[1].trim();
					} else if (str[0].trim().equals("PLACE")) {
						result[3] = str[1].trim();
					} else if (str[0].trim().equals("MEMO")) {
						result[4] = str[1].trim();
					}
				} catch (Exception e) { System.out.println("no schedule " + str[0].trim().toLowerCase());}
			}
		} catch (Exception e) { System.out.println("unknown file name"); }
	}
}
