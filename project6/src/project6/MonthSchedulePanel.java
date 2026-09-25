package project6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthSchedulePanel extends JPanel implements ActionListener {
    private int daysInMonth, startOffset;
    private JButton[] dayBtns;
    private JPanel gridPanel;
    private PlannerMainFrame mainFrame;

    public MonthSchedulePanel(PlannerMainFrame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());
        createHeaderPanel();
        
        gridPanel = new JPanel(new GridLayout(6, 7, 2, 2));
        add(gridPanel, BorderLayout.CENTER);
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
        if (DayValue == 7) { return 0; }
        return DayValue;
    }

    public int calcDaysInMonth(int year, int month) {
        LocalDate firstDay = LocalDate.of(year, month, 1);
        return firstDay.lengthOfMonth();
    }

    public void updateTheCalendar(int year, int month, ScheduleList list) {
        this.daysInMonth = calcDaysInMonth(year, month);
        this.startOffset = calcStartOffset(year, month);
        gridPanel.removeAll();
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
                dayBtns[i].setActionCommand(String.valueOf(dayNum));
                dayBtns[i].addActionListener(this);
            } else {
                dayBtns[i] = new JButton("");
                dayBtns[i].setEnabled(false);
            }
            gridPanel.add(dayBtns[i]);
        }
        loadSchedule(list, year, month);
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public void loadSchedule(ScheduleList list, int year, int month) {
        if (list == null) return;
        int totalSchedules = list.numSchedules();

        for (int i = 0; i < totalSchedules; i++) {
            Schedule s = list.getSchedule(i);
            if (s.getTitle() == null || s == null ) continue;

            LocalDateTime startDateTime = s.getStart();
            if (startDateTime.getYear() == year && startDateTime.getMonthValue() == month && startDateTime != null) {
                int day = parseDayFromString(startDateTime), buttonIndex = day + this.startOffset - 1;
                
                if (buttonIndex >= 0 && dayBtns[buttonIndex] != null && buttonIndex < 42) {
                    JTextArea targetArea = (JTextArea) dayBtns[buttonIndex].getComponent(0);
                    targetArea.setText(targetArea.getText() + s.getTitle() + "\n");
                }
            }
        }
    }

    // 날짜 버튼 동작 구현
    public void actionPerformed(ActionEvent e) {
        int selectedDay = Integer.parseInt(e.getActionCommand());
        mainFrame.handleOfDaySelected(selectedDay);
    }
    public int parseDayFromString(LocalDateTime startDateTime) { return startDateTime.getDayOfMonth(); }
}
