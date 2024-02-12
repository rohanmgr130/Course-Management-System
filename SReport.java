package java10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class SReport extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private static int stdId;
	private int getStdId() {
		return stdId;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SReport frame = new SReport(stdId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


//get std data
    private Map<String, String> getStudentData(int id) {
        Map<String, String> studentData = new HashMap<>();

        String url = "jdbc:mysql://localhost:3306/course";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.firstname, u.lastname, u.email,u.std_course,u.std_level, r.module_1, r.mark_1,r.module_2, r.mark_2,r.module_3, r.mark_3, r.percentage, r.result FROM users u JOIN results r ON r.result_id=r.std_id WHERE u.user_id = ?")) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Getting data from the result set and putting it into the HashMap
                    studentData.put("firstname", resultSet.getString("firstname"));
                    studentData.put("lastname", resultSet.getString("lastname"));
                    studentData.put("email", resultSet.getString("email"));
                    studentData.put("std_course", resultSet.getString("std_course"));
                    studentData.put("std_level", resultSet.getString("std_level"));
                    studentData.put("module_1", resultSet.getString("module_1"));
                    studentData.put("mark_1", resultSet.getString("mark_1"));
                    studentData.put("module_2", resultSet.getString("module_2"));
                    studentData.put("mark_2", resultSet.getString("mark_2"));
                    studentData.put("module_3", resultSet.getString("module_3"));
                    studentData.put("mark_3", resultSet.getString("mark_3"));
                    studentData.put("percentage", resultSet.getString("percentage"));
                    studentData.put("result", resultSet.getString("result"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentData;
    }

	/**
	 * Create the frame.
	 */
		public SReport(int stdId) {
	        

	        Map<String, String> studentData = getStudentData(stdId);

	        String fname = studentData.get("firstname");
	        String lname = studentData.get("lastname");
	        String email = studentData.get("email");
	        String course = studentData.get("std_course");
	        String module1 = studentData.get("module_1");
	        String mark1 = studentData.get("mark_1");
	        String module2 = studentData.get("module_2");
	        String mark2 = studentData.get("mark_2");
	        String module3 = studentData.get("module_3");
	        String mark3 = studentData.get("mark_3");
	        String percent = studentData.get("percentage");
	        String result = studentData.get("result");
	        String lvl = studentData.get("std_level");
	        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 399);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 659, 53);
		panel.setBackground(new Color(139, 0, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Report");
		lblNewLabel.setForeground(new Color(255, 222, 173));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(246, 11, 146, 19);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(0, 64, 80, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(0, 95, 80, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Course :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(0, 127, 80, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Lastname :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3.setBounds(322, 67, 80, 20);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Level :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_4.setBounds(322, 95, 80, 20);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel(fname);
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_5.setBounds(79, 65, 80, 20);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel(email);
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_6.setBounds(79, 95, 160, 20);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel(course);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_7.setBounds(79, 127, 80, 20);
		contentPane.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel(lname);
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_8.setBounds(412, 68, 80, 20);
		contentPane.add(lblNewLabel_1_8);
		
		JLabel level = new JLabel(lvl);
		level.setFont(new Font("Tahoma", Font.BOLD, 13));
		level.setBounds(412, 96, 80, 20);
		contentPane.add(level);
		
		JLabel lblNewLabel_1_10 = new JLabel("Modules :");
		lblNewLabel_1_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_10.setBounds(79, 175, 80, 20);
		contentPane.add(lblNewLabel_1_10);
		
		JLabel lblNewLabel_1_11 = new JLabel("Marks :");
		lblNewLabel_1_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_11.setBounds(340, 175, 80, 20);
		contentPane.add(lblNewLabel_1_11);
		
		JLabel lblNewLabel_1_12 = new JLabel("Percentage :");
		lblNewLabel_1_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_12.setBounds(0, 256, 111, 20);
		contentPane.add(lblNewLabel_1_12);
		
		JLabel lblNewLabel_1_13 = new JLabel("Result :");
		lblNewLabel_1_13.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_13.setBounds(0, 287, 80, 20);
		contentPane.add(lblNewLabel_1_13);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.setBackground(new Color(105, 105, 105));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(282, 326, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel m1 = new JLabel(module1);
		m1.setForeground(new Color(0, 0, 0));
		m1.setBackground(new Color(255, 218, 185));
		m1.setFont(new Font("Tahoma", Font.BOLD, 13));
		m1.setBounds(10, 195, 52, 30);
		contentPane.add(m1);
		
		JLabel m2 = new JLabel(module2);
		m2.setBackground(new Color(255, 218, 185));
		m2.setFont(new Font("Tahoma", Font.BOLD, 13));
		m2.setBounds(79, 195, 52, 30);
		contentPane.add(m2);
		
		JLabel m3 = new JLabel(module3);
		m3.setBackground(new Color(255, 218, 185));
		m3.setFont(new Font("Tahoma", Font.BOLD, 13));
		m3.setBounds(146, 195, 52, 30);
		contentPane.add(m3);
		
		JLabel mar1 = new JLabel(mark1);
		mar1.setBackground(new Color(255, 218, 185));
		mar1.setFont(new Font("Tahoma", Font.BOLD, 13));
		mar1.setBounds(282, 195, 52, 30);
		contentPane.add(mar1);
		
		JLabel mar2 = new JLabel(mark2);
		mar2.setBackground(new Color(255, 218, 185));
		mar2.setFont(new Font("Tahoma", Font.BOLD, 13));
		mar2.setBounds(350, 195, 52, 30);
		contentPane.add(mar2);
		
		JLabel mar3 = new JLabel(mark3);
		mar3.setBackground(new Color(255, 218, 185));
		mar3.setFont(new Font("Tahoma", Font.BOLD, 13));
		mar3.setBounds(427, 195, 52, 30);
		contentPane.add(mar3);
		
		JLabel percent1 = new JLabel(percent);
		percent1.setFont(new Font("Tahoma", Font.BOLD, 13));
		percent1.setBounds(120, 256, 52, 20);
		contentPane.add(percent1);
		
		JLabel res = new JLabel(result);
		res.setFont(new Font("Tahoma", Font.BOLD, 13));
		res.setBounds(79, 287, 80, 20);
		contentPane.add(res);
	

		
		}

}
