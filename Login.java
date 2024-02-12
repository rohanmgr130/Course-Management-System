package java10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private boolean checkLogin(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/course";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	private String UserEmail;
	
	public String getemail() {
		return UserEmail;
	}
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(64, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(241, 114, 118, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBackground(new Color(240, 240, 240));
		lblPassword.setForeground(new Color(0, 64, 0));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(239, 191, 104, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setBackground(new Color(255, 128, 0));
		textField.setBounds(241, 139, 168, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(419, 240, 0, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(new Color(0, 0, 0));
		passwordField_1.setBackground(new Color(255, 128, 0));
		passwordField_1.setBounds(241, 216, 168, 20);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(new Color(0, 0, 64));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = textField.getText();
				String Password = passwordField_1.getText();
				
				if(checkLogin(Username,Password)) {
					JOptionPane.showMessageDialog(null,"Login Successful !!!");
					UserEmail = Username;
					Dashboard db = new Dashboard(UserEmail);
					db.setVisible(true);
					dispose();
					}else {
						JOptionPane.showMessageDialog(null,"Invalid");
					}
				
			}
		});
		btnNewButton.setBounds(254, 286, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton toRegister = new JButton("Sign Up");
		toRegister.setForeground(new Color(128, 64, 64));
		toRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		toRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				project prj = new project();
				prj.setVisible(true);
				dispose();
			}
		});
		toRegister.setBounds(254, 347, 89, 23);
		contentPane.add(toRegister);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 709, 452);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\javaimg\\party-color-fragment-geometric-border-offer-black-special_138282_wh1200.png"));
		lblNewLabel_1.setBounds(0, 0, 709, 452);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("New label");
		label.setBounds(460, 113, 46, 14);
		panel.add(label);
	}
}
