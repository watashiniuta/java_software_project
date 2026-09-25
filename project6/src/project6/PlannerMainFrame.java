package project6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlannerMainFrame extends JFrame implements ActionListener {
    private MonthSchedulePanel monthPanel;
    private DaySchedulePanel dayPanel;
    private ScheduleList scheduleList;
    private int currentYear = 2026, currentMonth = 5, currentDay = 1;
    private JButton btnPrev, btnNext;
    private JLabel labelYear, labelMonth;

    public PlannerMainFrame() {
        scheduleList = new ScheduleList("./src/project6/schedule-normal.data");

        setTitle("Schedule Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 650);
        setLayout(new BorderLayout(0, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        btnPrev = new JButton("<");
        btnNext = new JButton(">");
        btnPrev.addActionListener(this);
        btnNext.addActionListener(this);
        
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        labelYear = new JLabel(String.valueOf(currentYear), JLabel.CENTER);
        labelMonth = new JLabel(String.valueOf(currentMonth), JLabel.CENTER);
        titlePanel.add(labelYear);
        titlePanel.add(labelMonth);
        topPanel.add(btnPrev, BorderLayout.WEST);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        topPanel.add(btnNext, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        monthPanel = new MonthSchedulePanel(this);
        add(monthPanel, BorderLayout.CENTER);
        dayPanel = new DaySchedulePanel(this);
        dayPanel.setPreferredSize(new Dimension(550, 150));
        add(dayPanel, BorderLayout.SOUTH);
        
        updateTheScreen();
    }

    public void updateTheScreen() {
        labelYear.setText(String.valueOf(currentYear));
        labelMonth.setText(String.valueOf(currentMonth));
        monthPanel.updateTheCalendar(currentYear, currentMonth, scheduleList);
    }

    public void handleOfDaySelected(int day) {  // 특정 날짜 버튼 클릭에 따른 동기화 처리 구현
        this.currentDay = day;
        Schedule target = null;
        
        for (int i = 0; i < scheduleList.numSchedules(); i++) {
            Schedule s = scheduleList.getSchedule(i);
            LocalDateTime start = s.getStart();
            if (start != null && start.getYear() == currentYear && start.getMonthValue() == currentMonth && start.getDayOfMonth() == day) {
                target = s;
                break;
            }
        }
        dayPanel.setScheduleDetails(target); // 하단 화면에 출력 혹은 없으면 빈칸 처리
    }

    // 달 이동 버튼 동작 구현
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPrev) {
            currentMonth--;
            if (currentMonth < 1) {
                currentMonth = 12;
                currentYear--;
            }
        } else if (e.getSource() == btnNext) {
            currentMonth++;
            if (currentMonth > 12) {
                currentMonth = 1;
                currentYear++;
            }
        }
        updateTheScreen();
        dayPanel.clearFields();
    }

    public ScheduleList getScheduleList() { return scheduleList; }
    public int getCurrentYear() { return currentYear; }
    public int getCurrentMonth() { return currentMonth; }
    public int getCurrentDay() { return currentDay; }
}