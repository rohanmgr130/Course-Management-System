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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

public class ADDstudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentname;
	private JTextField lastname;
	private JTextField email;
	private JPasswordField passwordField;
	private JComboBox comboBox;
	private JComboBox comboBox_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADDstudent frame = new ADDstudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void addstudent( String firstname, String lastname,String email,String password,int level,String course) {
        String url = "jdbc:mysql://localhost:3306/course";
         String dbUsername = "root";
         String dbPassword = "";

         try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
        	 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users ( firstname, lastname, email, password, std_level, std_course, role) VALUES (?, ?, ?, ?, ?, ?, 'Student')");
             preparedStatement.setString(1,firstname);
             preparedStatement.setString(2, lastname);
             preparedStatement.setString(3,email);
             preparedStatement.setString(4, password);
             preparedStatement.setInt(5, level);
             preparedStatement.setString(6, course);
              preparedStatement.executeUpdate();
              
             JOptionPane.showMessageDialog(null, "Added successfully");
             
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
	/**
	 * Create the frame.
	 */
	public ADDstudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 672, 48);
		panel.setBackground(Color.BLACK);
		panel.setForeground(new Color(255, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("ADD Student");
		lblNewLabel_2.setForeground(Color.PINK);
		lblNewLabel_2.setBackground(new Color(255, 175, 175));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(268, 11, 130, 26);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(160, 82, 45));
		panel_1.setBounds(92, 79, 480, 274);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(56, 223, 89, 23);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 224));
		panel_1.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(297, 223, 89, 23);
		btnNewButton_1.setForeground(new Color(255, 255, 224));
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(269, 172, 164, 22);
		comboBox_1.setBackground(new Color(255, 255, 255));
		panel_1.add(comboBox_1);
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"BIT", "BIBM", "BBA"}));
		
		comboBox = new JComboBox();
		comboBox.setBounds(27, 172, 164, 22);
		comboBox.setBackground(new Color(255, 255, 240));
		panel_1.add(comboBox);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6"}));
		
		JLabel lblNewLabel_3 = new JLabel("Level :");
		lblNewLabel_3.setBounds(76, 147, 56, 14);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_4 = new JLabel("Course :");
		lblNewLabel_4.setBounds(314, 147, 72, 14);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(269, 116, 164, 20);
		panel_1.add(passwordField);
		
		email = new JTextField();
		email.setBounds(27, 116, 164, 20);
		panel_1.add(email);
		email.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(76, 94, 50, 14);
		panel_1.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setBounds(299, 91, 87, 14);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lastname = new JTextField();
		lastname.setBounds(269, 60, 164, 20);
		panel_1.add(lastname);
		lastname.setColumns(10);
		
		studentname = new JTextField();
		studentname.setBounds(27, 60, 164, 20);
		panel_1.add(studentname);
		studentname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Firstname:");
		lblNewLabel.setBounds(76, 35, 87, 14);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Lastname:");
		lblNewLabel_1.setBounds(299, 34, 87, 14);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstN = studentname.getText();
				String lastN = lastname.getText();
				String emailN = email.getText();
				String password = passwordField.getText();
      			String level = (String)comboBox.getSelectedItem();
      			String course = (String)comboBox.getSelectedItem();
				if(!firstN.isEmpty() && !lastN.isEmpty() && !emailN.isEmpty()&& !password.isEmpty()&& !level.isEmpty()&& !course.isEmpty()){
					int lvl = Integer.parseInt(level);
				addstudent(firstN,lastN,emailN,password,lvl,course);
				}else {
					JOptionPane.showMessageDialog(null,"This message can't be print");
				}
			}
		});
	}
}
