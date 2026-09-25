package project5;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class DaySchedulePanel extends JPanel {
    private JTextField txtTitle, txtStart, txtEnd, txtPlace, txtMemo;
    private JButton btnCancel, btnClose, btnSave;
    public DaySchedulePanel() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 5, 5, 5));   
        formPanel.add(new JLabel("Title", JLabel.CENTER));
        formPanel.add(new JLabel("Start (hh:mm)", JLabel.CENTER));
        formPanel.add(new JLabel("End", JLabel.CENTER));
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
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnClose);
        buttonPanel.add(btnSave);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    public void setScheduleDetails(Schedule s) {
        if (s != null) {
            txtTitle.setText(s.getTitle());      
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          
            if (s.getStart() != null) {
                txtStart.setText(s.getStart().format(timeFormatter));
            } else {
                txtStart.setText("");
            }
            
            if (s.getEnd() != null) {
                txtEnd.setText(s.getEnd().format(dateTimeFormatter));
            } else {
                txtEnd.setText("");
            }
            
            txtPlace.setText(s.getPlace());
            txtMemo.setText(s.getMemo());
        }
    }
}


