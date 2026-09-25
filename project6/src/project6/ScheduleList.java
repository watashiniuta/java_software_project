package project6;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduleList {
    private ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
    private String fileName;
    private File file;
    
    public ScheduleList(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
        loadFromFile();
    }
    
    public boolean isTimeConflict(LocalDateTime start, LocalDateTime end) { return end.isBefore(start); }
    public int numSchedules() { return scheduleList.size(); }
    public void add(Schedule s) { scheduleList.add(s); }
    public void delete(Schedule s) { scheduleList.remove(s); }
    public Schedule getSchedule(int i) { return scheduleList.get(i); }
   
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(fileName, "MS949")) {
            for (int i = 0; i < scheduleList.size(); i++) {
                Schedule s = scheduleList.get(i);
                if (s.getTitle() != null) writer.println("TITLE = " + s.getTitle());
                if (s.getStart() != null) writer.println("START = " + s.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                if (s.getEnd() != null) writer.println("END = " + s.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                if (s.getPlace() != null) writer.println("PLACE = " + s.getPlace());
                if (s.getMemo() != null) writer.println("MEMO = " + s.getMemo());
                writer.println(";");
            }
        } catch (Exception except) {
            System.out.println(except);
        }
    }
    
    public void loadFromFile() {
        scheduleList.clear();
        if (!file.exists()) return;
        
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
                        try { dateTimeStart = LocalDateTime.parse(result[1], formatter); } 
                        catch (Exception e) { System.out.println("Date Format Error"); }
                    }
                    if (result[2] != null) {
                        try { dateTimeEnd = LocalDateTime.parse(result[2], formatter); } 
                        catch (Exception e) { System.out.println("Date Format Error"); }
                    }
                    if (dateTimeStart != null && dateTimeEnd != null && isTimeConflict(dateTimeStart, dateTimeEnd)) {
                        result[0] = null; result[1] = null; result[2] = null; result[3] = null; result[4] = null;
                        System.out.println("The end time cannot be earlier than the start time");
                        continue;
                    }                    
                    add(new Schedule(result[0], dateTimeStart, dateTimeEnd, result[3], result[4]));
                    
                    result[0] = null; result[1] = null; result[2] = null; result[3] = null; result[4] = null;
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
                } catch (Exception except) { System.out.println("no schedule " + str[0].trim().toLowerCase());}
            }
        } catch (Exception except) { System.out.println("unknown file name"); }
    }
}