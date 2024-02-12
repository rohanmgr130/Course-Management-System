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

public class EditCourse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField coursename;
	private JTextField years;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCourse frame = new EditCourse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void editCourse1(int ID, String Name, int years) {
	    String url = "jdbc:mysql://localhost:3306/Course";
	    String dbUsername = "root";
	    String dbPassword = "";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
	        // Use a PreparedStatement with a WHERE clause to specify the record to update
	        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE courses SET name=?, years=? WHERE id=?");
	        preparedStatement.setString(1, Name);
	        preparedStatement.setInt(2, years);
	        preparedStatement.setInt(3, ID); // Add the ID parameter to the WHERE clause

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Updated successfully");
	        } else {
	            JOptionPane.showMessageDialog(null, "No records updated. ID not found.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Create the frame.
	 */
	public EditCourse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Coursename:");
		lblNewLabel.setBounds(123, 119, 87, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblNewLabel);
		
		coursename = new JTextField();
		coursename.setBounds(123, 144, 164, 20);
		contentPane.add(coursename);
		coursename.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Years :");
		lblNewLabel_1.setBounds(123, 178, 87, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);
		
		years = new JTextField();
		years.setBounds(123, 197, 164, 20);
		contentPane.add(years);
		years.setColumns(10);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.setBounds(98, 228, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IID = id.getText();
				String CourseM = coursename.getText();
				String YearM = years.getText();
				if(!IID.isEmpty()&&!CourseM.isEmpty() && !YearM.isEmpty()){
					int Yrs = Integer.parseInt(YearM);
					int Id = Integer.parseInt(IID);
					editCourse1(Id,CourseM,Yrs);
				}else {
					JOptionPane.showMessageDialog(null,"This message can't be print");
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(228, 228, 89, 23);
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
		
		JLabel lblNewLabel_2 = new JLabel("Edit Course");
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setBackground(new Color(255, 175, 175));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(191, 11, 130, 26);
		panel.add(lblNewLabel_2);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(111, 62, 32, 14);
		contentPane.add(lblId);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(142, 59, 61, 23);
		contentPane.add(id);
	}
}
