package project5;
import java.io.IOException;

public class ScheduleTest {
    public static void main(String[] args) throws IOException {
        PlannerMainFrame frame = new PlannerMainFrame();
        ScheduleList list = new ScheduleList("./src/project5/schedule-file.data");
        
        frame.getMonthPanel().loadSchedule(list);
        frame.getDayPanel().setScheduleDetails(list.getSchedule(8));
        frame.setVisible(true);
        
        new ProcessBuilder("cmd", "/c", "echo %date%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "echo %time%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "whoami").inheritIO().start();
    }
}
