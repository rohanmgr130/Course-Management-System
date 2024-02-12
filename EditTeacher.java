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
import javax.swing.JPasswordField;

public class EditTeacher extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField adminname;
	private JTextField lastname;
	private JTextField email;
	private JPasswordField passwordField;
	private JTextField ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTeacher frame = new EditTeacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void editteacher(String TeacherID, String firstname, String lastname, String email, String password) {
	    String url = "jdbc:mysql://localhost:3306/course";
	    String dbUsername = "root";
	    String dbPassword = "";

	    try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
	        PreparedStatement preparedStatement = connection.prepareStatement(
	                "UPDATE users SET firstname=?, lastname=?, email=?, password=? WHERE user_id=?");
	        preparedStatement.setString(1, firstname);
	        preparedStatement.setString(2, lastname);
	        preparedStatement.setString(3, email);
	        preparedStatement.setString(4, password);
	        preparedStatement.setString(5, TeacherID);
	        preparedStatement.executeUpdate();

	        JOptionPane.showMessageDialog(null, "Edit successfully");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Create the frame.
	 */
	public EditTeacher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Firstname:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(123, 110, 87, 14);
		contentPane.add(lblNewLabel);
		
		adminname = new JTextField();
		adminname.setBounds(123, 127, 164, 20);
		contentPane.add(adminname);
		adminname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Lastname:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(123, 167, 87, 14);
		contentPane.add(lblNewLabel_1);
		
		lastname = new JTextField();
		lastname.setBounds(123, 184, 164, 20);
		contentPane.add(lastname);
		lastname.setColumns(10);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ED = ID.getText();
				String firstN = adminname.getText();
				String lastN = lastname.getText();
				String emailN = email.getText();
				String password = passwordField.getText();
				if(!ED.isEmpty()&&!firstN.isEmpty() && !lastN.isEmpty() && !emailN.isEmpty()&& !password.isEmpty()){
					editteacher(ED,firstN,lastN,emailN,password);
				}else {
					JOptionPane.showMessageDialog(null,"This message can't be print");
				}
			}
		});
		btnNewButton.setBounds(121, 321, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(274, 321, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setForeground(new Color(255, 128, 128));
		panel.setBounds(0, 0, 662, 48);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Edit Course");
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setBackground(new Color(255, 175, 175));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(268, 11, 130, 26);
		panel.add(lblNewLabel_2);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(123, 215, 87, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(123, 263, 87, 14);
		contentPane.add(lblNewLabel_1_1);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(123, 231, 164, 20);
		contentPane.add(email);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(123, 280, 164, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("TeacherID :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(123, 59, 87, 14);
		contentPane.add(lblNewLabel_3);
		
		ID = new JTextField();
		ID.setColumns(10);
		ID.setBounds(123, 79, 164, 20);
		contentPane.add(ID);
	}
}
