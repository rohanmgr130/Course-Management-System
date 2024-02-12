package java10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DeleteStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStudent frame = new DeleteStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void deletestudent(int ID) {
	    String url = "jdbc:mysql://localhost:3306/Course";
	    String dbUsername = "root";
	    String dbPassword = "";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
	        // First, delete related records from the results table
	        PreparedStatement deleteResults = connection.prepareStatement("DELETE FROM results WHERE std_id = ?");
	        deleteResults.setInt(1, ID);
	        deleteResults.executeUpdate();

	        // Then, delete the record from the users table
	        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE user_id = ?");
	        preparedStatement.setInt(1, ID);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Deleted successfully");
	        } else {
	            JOptionPane.showMessageDialog(null, "No records deleted. ID not found.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	/**
	 * Create the frame.
	 */
	public DeleteStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(255, 128, 128));
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 501, 48);
		contentPane.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Delete Course");
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBackground(Color.PINK);
		lblNewLabel_2.setBounds(177, 11, 148, 26);
		panel.add(lblNewLabel_2);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(120, 103, 32, 14);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(162, 103, 176, 23);
		contentPane.add(textField);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IID = textField.getText();
				if(!IID.isEmpty()){
					
					int Id = Integer.parseInt(IID);
					deletestudent(Id);
				}else {
					JOptionPane.showMessageDialog(null,"This message can't be print");
				}
			}
		});
		btnNewButton.setBounds(120, 149, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(249, 149, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
