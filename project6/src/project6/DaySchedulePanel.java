package project6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DaySchedulePanel extends JPanel implements ActionListener {
    private JTextField txtTitle, txtStart, txtEnd, txtPlace, txtMemo;
    private JButton btnCancel, btnClose, btnSave;
    private Schedule currentSchedule;   // 현재 열린 일정 상태값 저장
    private PlannerMainFrame mainFrame;
    
    public DaySchedulePanel(PlannerMainFrame frame) {
        this.mainFrame = frame;
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(2, 5, 5, 5));   
        formPanel.add(new JLabel("Title", JLabel.CENTER));
        formPanel.add(new JLabel("Start (hh:mm)", JLabel.CENTER));
        formPanel.add(new JLabel("End (yyyy-MM-dd hh:mm)", JLabel.CENTER));
        formPanel.add(new JLabel("Place", JLabel.CENTER));
        formPanel.add(new JLabel("Memo", JLabel.CENTER));
        txtTitle = new JTextField();
        txtStart = new JTextField();
        txtEnd = new JTextField();
        txtPlace = new JTextField();
        txtMemo = new JTextField();
        formPanel.add(txtTitle);
        formPanel.add(txtStart);
        formPanel.add(txtEnd);
        formPanel.add(txtPlace);
        formPanel.add(txtMemo);
        add(formPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnCancel = new JButton("CANCEL");
        btnClose = new JButton("CLOSE");
        btnSave = new JButton("SAVE");
        btnCancel.addActionListener(this);
        btnClose.addActionListener(this);
        btnSave.addActionListener(this);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnClose);
        buttonPanel.add(btnSave);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void setScheduleDetails(Schedule s) {
        this.currentSchedule = s;
        if (s != null) {
            txtTitle.setText(s.getTitle());      
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (s.getEnd() != null) {
                txtEnd.setText(s.getEnd().format(dateTimeFormatter));
            } else {
                txtEnd.setText("");
            }
            if (s.getStart() != null) {
                txtStart.setText(s.getStart().format(timeFormatter));
            } else {
                txtStart.setText("");
            }
            txtMemo.setText(s.getMemo());
            txtPlace.setText(s.getPlace());
        } else {
            clearFields();
        }
    }
    
    public void clearFields() {
        this.currentSchedule = null;
        txtTitle.setText("");
        txtStart.setText("");
        txtEnd.setText("");
        txtPlace.setText("");
        txtMemo.setText("");
    }

    // 버튼별 동작 로직 처리 구현
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == btnClose) {
            clearFields();
        } else if (source == btnCancel) {
            if (currentSchedule != null) {
                mainFrame.getScheduleList().delete(currentSchedule); 
                mainFrame.getScheduleList().saveToFile();            
                mainFrame.updateTheScreen();                        
            }
            clearFields();
        } else if (source == btnSave) {
            if (txtTitle.getText().trim().isEmpty()) return;
            
            try {
                int year = mainFrame.getCurrentYear();
                int month = mainFrame.getCurrentMonth();
                int day = mainFrame.getCurrentDay();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String startStr = String.format("%04d-%02d-%02d %s", year, month, day, txtStart.getText().trim());
                LocalDateTime startDateTime = LocalDateTime.parse(startStr, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(txtEnd.getText().trim(), formatter);
                
                if (mainFrame.getScheduleList().isTimeConflict(startDateTime, endDateTime)) {
                    System.out.println("Time Conflict Error");
                    return;
                }
                
                if (currentSchedule == null) {
                    Schedule newSchedule = new Schedule(txtTitle.getText().trim(), startDateTime, endDateTime, txtPlace.getText().trim(), txtMemo.getText().trim());
                    mainFrame.getScheduleList().add(newSchedule);
                } else {
                    currentSchedule.setTitle(txtTitle.getText().trim());
                    currentSchedule.setStart(startDateTime);
                    currentSchedule.setEnd(endDateTime);
                    currentSchedule.setPlace(txtPlace.getText().trim());
                    currentSchedule.setMemo(txtMemo.getText().trim());
                }
                
                mainFrame.getScheduleList().saveToFile();
                mainFrame.updateTheScreen();
                clearFields();
            } catch (Exception except) {
                System.out.println("Input Format Error: " + except.getMessage());
            }
        }
    }
}
