package java10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addMarks extends JFrame {

private static final long serialVersionUID = 1L;
private JPanel contentPane;
private JTextField textField;
private JTextField textField_2;
private JTextField textField_3;
private JTextField textField_4;
private JTextField textField_5;
private JTextField textField_6;
private JTextField textField_7;
private JTextField textField_8;
private JTextField textField_9;

/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
addMarks frame = new addMarks();
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

//adding result
    private void addMarks1(int std_id, String module1,int mark1,  String module2,int mark2, String module3,  int mark3, int percent, String result) {
        String url = "jdbc:mysql://localhost:3306/course";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO results (std_id, module_1, mark_1, module_2, mark_2, module_3, mark_3, percentage, result) VALUES (?, ?, ?, ?,?, ?, ?,?,?)")) {

            preparedStatement.setInt(1, std_id);
            preparedStatement.setString(2, module1);
            preparedStatement.setInt(3, mark1);
            preparedStatement.setString(4, module2);
            preparedStatement.setInt(5, mark2);
            preparedStatement.setString(6, module3);
            preparedStatement.setInt(7, mark3);
            preparedStatement.setInt(8, percent);
            preparedStatement.setString(9, result);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Result Successfully Added");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while Adding Result.");
        }
    }
/**
* Create the frame.
*/
public addMarks() {
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 770, 547);
contentPane = new JPanel();
contentPane.setBackground(SystemColor.textHighlightText);
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

setContentPane(contentPane);
contentPane.setLayout(null);

JPanel panel_9 = new JPanel();
panel_9.setLayout(null);
panel_9.setBackground(SystemColor.controlShadow);
panel_9.setBounds(0, 0, 764, 58);
contentPane.add(panel_9);

JLabel lblNewLabel_1 = new JLabel("Add Marks");
lblNewLabel_1.setFont(new Font("Dyuthi", Font.BOLD, 35));
lblNewLabel_1.setBounds(289, 12, 217, 36);
panel_9.add(lblNewLabel_1);

JLabel lblNewLabel = new JLabel("Student Id :");
lblNewLabel.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblNewLabel.setBounds(211, 80, 144, 25);
contentPane.add(lblNewLabel);

textField = new JTextField();
textField.setBounds(406, 77, 68, 31);
contentPane.add(textField);
textField.setColumns(10);

JLabel lblModule = new JLabel("1 :");
lblModule.setForeground(Color.BLUE);
lblModule.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblModule.setBounds(107, 184, 51, 25);
contentPane.add(lblModule);

textField_2 = new JTextField();
textField_2.setColumns(10);
textField_2.setBounds(176, 181, 101, 31);
contentPane.add(textField_2);

JLabel lblModule_2 = new JLabel("2 :");
lblModule_2.setForeground(Color.RED);
lblModule_2.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblModule_2.setBounds(349, 184, 51, 25);
contentPane.add(lblModule_2);

textField_3 = new JTextField();
textField_3.setColumns(10);
textField_3.setBounds(418, 181, 101, 31);
contentPane.add(textField_3);
JLabel lblModule_1_1 = new JLabel("3 :");
lblModule_1_1.setForeground(UIManager.getColor("OptionPane.questionDialog.titlePane.foreground"));
lblModule_1_1.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblModule_1_1.setBounds(578, 184, 51, 25);
contentPane.add(lblModule_1_1);

textField_4 = new JTextField();
textField_4.setColumns(10);
textField_4.setBounds(647, 181, 101, 31);
contentPane.add(textField_4);

JLabel lblMarks = new JLabel("1 :");
lblMarks.setForeground(Color.BLUE);
lblMarks.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblMarks.setBounds(107, 299, 56, 25);
contentPane.add(lblMarks);

textField_5 = new JTextField();
textField_5.setColumns(10);
textField_5.setBounds(176, 296, 101, 31);
contentPane.add(textField_5);

JLabel lblModule_2_1 = new JLabel("2:");
lblModule_2_1.setForeground(Color.RED);
lblModule_2_1.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblModule_2_1.setBounds(349, 302, 51, 25);
contentPane.add(lblModule_2_1);

textField_6 = new JTextField();
textField_6.setColumns(10);
textField_6.setBounds(418, 299, 101, 31);
contentPane.add(textField_6);

JLabel lblModule_1_1_1 = new JLabel("3 :");
lblModule_1_1_1.setForeground(UIManager.getColor("OptionPane.questionDialog.titlePane.foreground"));
lblModule_1_1_1.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblModule_1_1_1.setBounds(578, 302, 42, 25);
contentPane.add(lblModule_1_1_1);

textField_7 = new JTextField();
textField_7.setColumns(10);
textField_7.setBounds(647, 299, 101, 31);
contentPane.add(textField_7);
JLabel lblPercentage = new JLabel("Percentage :");
lblPercentage.setForeground(UIManager.getColor("OptionPane.questionDialog.titlePane.foreground"));
lblPercentage.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblPercentage.setBounds(177, 359, 135, 25);
contentPane.add(lblPercentage);

textField_8 = new JTextField();
textField_8.setColumns(10);
textField_8.setBounds(330, 356, 78, 31);
contentPane.add(textField_8);

JLabel lblResult = new JLabel("Result :");
lblResult.setForeground(Color.RED);
lblResult.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblResult.setBounds(176, 417, 101, 25);
contentPane.add(lblResult);

textField_9 = new JTextField();
textField_9.setColumns(10);
textField_9.setBounds(330, 414, 78, 31);
contentPane.add(textField_9);

JLabel lblModules = new JLabel("Modules ");
lblModules.setForeground(Color.BLUE);
lblModules.setFont(new Font("Dyuthi", Font.BOLD, 30));
lblModules.setBounds(315, 128, 135, 31);
contentPane.add(lblModules);

JLabel lblMarks_1 = new JLabel("Marks");
lblMarks_1.setForeground(SystemColor.desktop);
lblMarks_1.setFont(new Font("Dyuthi", Font.BOLD, 30));
lblMarks_1.setBounds(327, 235, 101, 31);
contentPane.add(lblMarks_1);

JButton btnNewButton = new JButton("Back");
btnNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
dispose();
}
});
btnNewButton.setFont(new Font("Dyuthi", Font.BOLD, 20));
btnNewButton.setBounds(657, 469, 101, 41);
contentPane.add(btnNewButton);

JButton btnNewButton_1 = new JButton("Add");
btnNewButton_1.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
				String iD = textField.getText();
                String module1 = textField_2.getText();
                String module2 = textField_3.getText();
                String module3 = textField_4.getText();
                String mark1 = textField_5.getText();
                String mark2 = textField_6.getText();
                String mark3 = textField_7.getText();
                String per = textField_8.getText();
                String res = textField_9.getText();
                if(!(iD.isEmpty()&&  module1.isEmpty() && module2.isEmpty() && module3.isEmpty() &
                    mark1.isEmpty() & mark2.isEmpty() & mark3.isEmpty())) {
                    int stdId = Integer.parseInt(iD);
                    int mark1Value = Integer.parseInt(mark1);
                    int mark2Value = Integer.parseInt(mark2);
                    int mark3Value = Integer.parseInt(mark3);
                    int perc =Integer.parseInt(per);
                    addMarks1( stdId,module1,mark1Value,module2,mark2Value,module3, mark3Value,perc,res);
                   
                }else {
                    JOptionPane.showMessageDialog(null,"Inputs cannot be empty!!");
                   
                }
}
});
btnNewButton_1.setFont(new Font("Dyuthi", Font.BOLD, 20));
btnNewButton_1.setBounds(330, 457, 117, 41);
contentPane.add(btnNewButton_1);

JLabel lblModule_1_1_1_1 = new JLabel("->");
lblModule_1_1_1_1.setForeground(UIManager.getColor("OptionPane.questionDialog.titlePane.foreground"));
lblModule_1_1_1_1.setFont(new Font("Dyuthi", Font.BOLD, 25));
lblModule_1_1_1_1.setBounds(615, 481, 42, 25);
contentPane.add(lblModule_1_1_1_1);
}

}

