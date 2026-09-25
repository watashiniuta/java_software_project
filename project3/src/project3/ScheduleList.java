package project3;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

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

	public int numSchedules() {
		return index;
	}

	public Schedule getSchedule(int i) {		
		return schedule[i - 1];
	}

	public void add(Schedule s) {
		schedule[index] = s;
		index++;
	}
	
	public void saveToFile() {
		try (PrintWriter writer = new PrintWriter("./src/schedule-normal2.data")) {
			for (Schedule s : schedule) {
				if (s.title != null) {
					writer.println("TITLE = " + s.title);
				}
				if (s.start != null) {
					writer.println("START = " + s.start);
				}
				if (s.end != null) {
					writer.println("END = " + s.end);
				}
				if (s.place != null) {
					writer.println("PLACE = " + s.place);
				}
				if (s.memo != null) {
					writer.println("MEMO = " + s.memo);
				}
				writer.println(";");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void loadFromFile() {
		// garbage collection 사용
		schedule = new Schedule[100];
		index = 0;
		
		try (Scanner scanner = new Scanner(this.file, "MS949")) {
			String[] result = {null, null, null, null, null};
			
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] str = line.split("=");
				
				if(line.length() == 0 || line.startsWith("//")) {
					continue;
				}
				
				if(line.equals(";")) {
					// 여기서 추후에 DateTime 사용해야함..
					add(new Schedule(result[0], result[1], result[2], result[3], result[4]));
					
					// schedule에 객체를 생성후 result 전부 null로 초기화
					result[0] = null;
					result[1] = null;
					result[2] = null;
					result[3] = null;
					result[4] = null;
					continue;
				}
				
				try {
					// result에 알맞는 index 값에 저장
					if(str[0].trim().equals("TITLE")) {
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
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
