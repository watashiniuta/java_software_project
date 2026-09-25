package project6;

import java.io.PrintWriter;
import java.io.IOException;

public class Test6 {
    public static void main(String[] args) throws IOException {
        // GUI frame 출력
        PlannerMainFrame frame = new PlannerMainFrame();
        frame.setVisible(true);
        
        new ProcessBuilder("cmd", "/c", "echo %date%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "echo %time%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "whoami").inheritIO().start();
    }
}
