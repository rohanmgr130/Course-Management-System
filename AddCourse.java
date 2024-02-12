package java10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddCourse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField coursename;
	private JTextField years;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void addCourse1( String Name, int years) {
        String url = "jdbc:mysql://localhost:3306/Course";
         String dbUsername = "root";
         String dbPassword = "";

         try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO courses (Name, years) VALUES (?, ?);");
             preparedStatement.setString(1,Name);
              preparedStatement.setInt(2, years);
              
              preparedStatement.executeUpdate();
              
             JOptionPane.showMessageDialog(null, "Added successfully");
             
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
	/**
	 * Create the frame.
	 */
	public AddCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Coursename:");
		lblNewLabel.setBounds(118, 119, 97, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		coursename = new JTextField();
		coursename.setBounds(90, 144, 164, 20);
		contentPane.add(coursename);
		coursename.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Years :");
		lblNewLabel_1.setBounds(332, 119, 87, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		years = new JTextField();
		years.setBounds(278, 143, 164, 20);
		contentPane.add(years);
		years.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(126, 198, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CourseM = coursename.getText();
				String YearM = years.getText();
				if(!CourseM.isEmpty() && !YearM.isEmpty()){
					int Yrs = Integer.parseInt(YearM);
					addCourse1(CourseM,Yrs);
				}else {
					JOptionPane.showMessageDialog(null,"This message can't be print");
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(308, 198, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 501, 48);
		panel.setBackground(Color.BLACK);
		panel.setForeground(new Color(255, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("ADD Course");
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setBackground(new Color(255, 175, 175));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(191, 11, 130, 26);
		panel.add(lblNewLabel_2);
	}
}
