package project5;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthSchedulePanel extends JPanel {
    private int daysInMonth; 
    private int startOffset;
    private JButton[] dayBtns;

    public MonthSchedulePanel() {
        setLayout(new BorderLayout());
        int targetYear = 2026;
        int targetMonth = 5;
        this.daysInMonth = calcDaysInMonth(targetYear, targetMonth);
        this.startOffset = calcStartOffset(targetYear, targetMonth);
        
        createHeaderPanel();
        createCalendarStructure();
    }

    public void createHeaderPanel() {
    	String[] days = {"SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT"};
    	JPanel headerPanel = new JPanel(new GridLayout(1, 7));
        for (String day : days) {
            JLabel lbl = new JLabel(day, JLabel.CENTER);
            headerPanel.add(lbl);
        }
        add(headerPanel, BorderLayout.NORTH);
    }

    public int calcStartOffset(int year, int month) {
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int DayValue = firstDay.getDayOfWeek().getValue();
        
        if (DayValue == 7) {
            return 0;
        }
        return DayValue;
    }

    public int calcDaysInMonth(int year, int month) {
        LocalDate firstDay = LocalDate.of(year, month, 1);
        return firstDay.lengthOfMonth();
    }

    public void createCalendarStructure() {
        JPanel gridPanel = new JPanel(new GridLayout(6, 7, 2, 2));
        dayBtns = new JButton[42];

        for (int i = 0; i < 42; i++) {
            int dayNum = i - this.startOffset + 1;
            
            if (dayNum > 0 && dayNum <= this.daysInMonth) {
                dayBtns[i] = new JButton();
                dayBtns[i].setLayout(new BorderLayout());

                JTextArea txtArea = new JTextArea(String.valueOf(dayNum) + "\n");
                txtArea.setEditable(false);       
                txtArea.setHighlighter(null);  
                txtArea.setOpaque(false);       
                txtArea.setLineWrap(true);      
                txtArea.setWrapStyleWord(true); 
                
                dayBtns[i].add(txtArea, BorderLayout.CENTER);
            } else {
                dayBtns[i] = new JButton("");
                dayBtns[i].setEnabled(false);
            }
            gridPanel.add(dayBtns[i]);
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    public int parseDayFromString(LocalDateTime startDateTime) {
        return startDateTime.getDayOfMonth();
    }

    public void loadSchedule(ScheduleList list) {
        if (list == null) return;
        int totalSchedules = list.numSchedules();

        for (int i = 0; i < totalSchedules; i++) {
            Schedule s = list.getSchedule(i);
            if (s == null || s.getTitle() == null) continue;

            LocalDateTime startDateTime = s.getStart();
            if (startDateTime != null && startDateTime.getYear() == 2026 && startDateTime.getMonthValue() == 5) {
                int day = parseDayFromString(startDateTime);
                int buttonIndex = day + this.startOffset - 1;
                
                if (buttonIndex >= 0 && buttonIndex < 42 && dayBtns[buttonIndex] != null) {
                    JTextArea targetArea = (JTextArea) dayBtns[buttonIndex].getComponent(0);
                    targetArea.setText(day + "\n" + s.getTitle());
                }
            }
        }
    }
}