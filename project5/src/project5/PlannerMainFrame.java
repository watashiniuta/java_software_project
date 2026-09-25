package project5;

import javax.swing.*;
import java.awt.*;

public class PlannerMainFrame extends JFrame {
    private MonthSchedulePanel monthPanel;
    private DaySchedulePanel dayPanel;

    public PlannerMainFrame() {
        setTitle("Schedule Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 650);
        setLayout(new BorderLayout(0, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        JButton btnPrev = new JButton("<");
        JButton btnNext = new JButton(">");
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        JLabel labelYear = new JLabel("2026", JLabel.CENTER);
        JLabel labelMonth = new JLabel("5", JLabel.CENTER);
        titlePanel.add(labelYear);
        titlePanel.add(labelMonth);
        topPanel.add(btnPrev, BorderLayout.WEST);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        topPanel.add(btnNext, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        monthPanel = new MonthSchedulePanel();
        add(monthPanel, BorderLayout.CENTER);

        dayPanel = new DaySchedulePanel();
        dayPanel.setPreferredSize(new Dimension(550, 150));
        add(dayPanel, BorderLayout.SOUTH);
    }

    public MonthSchedulePanel getMonthPanel() { return monthPanel; }
    public DaySchedulePanel getDayPanel() { return dayPanel; }
}
