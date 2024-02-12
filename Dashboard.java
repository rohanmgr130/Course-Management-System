package java10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java10.addMarks;

import javax.swing.JTabbedPane;
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
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JTextField textField;
	private course coursedisplay;
	private JTextField textField_1;
	private AMD adm;
	private JTextField textField_2;
	private JTextField textField_3;
	private STD std;
	private TC tc;
	private JTextField newname;
	private JTextField lastname;
	private RS rs;
	private RS displayresult;
	private JTextField email;
    private JPasswordField passwordField;
	private JPasswordField passwordField_1;
    private JTextField textField_4;
	private JTextField studentid;
	
	    
	  
	
	private static String userEmail;
	public String geteUserEmail() {
		return userEmail;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard(userEmail);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String url = "jdbc:mysql://localhost:3306/course";
    String dbUsername = "root";
    String dbPassword = "";
   
	//fetch userdat
	public Map<String, String> getUserData(String userEmail) {
        Map<String, String> userData = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?")) {

            preparedStatement.setString(1, userEmail);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Getting data from the result set
                    userData.put("firstname", resultSet.getString("firstname"));
                    userData.put("lastname", resultSet.getString("lastname"));
                    userData.put("email", resultSet.getString("email"));
                    userData.put("password", resultSet.getString("password"));
                    userData.put("role", resultSet.getString("role"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print the retrieved data
//        System.out.println("UserData: " + userData);

        return userData;
    }
	//total number of admin,teacher,student
    public int getTotalCount(String role) {
        String url = "jdbc:mysql://localhost:3306/course";
        String dbUsername = "root";
        String dbPassword = "";

        String query = "SELECT COUNT(*) FROM users WHERE role = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, role);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return 0; // Return 0 if something went wrong
    }
    
    //to get total courses
    public class DatabaseOperations {

        // Your existing database connection parameters
        private String url = "jdbc:mysql://localhost:3306/course";
        private String dbUsername = "root";
        private String dbPassword = "";

        // Method to get the total number of courses
        public int getTotalCourseCount() {
            String query = "SELECT COUNT(*) FROM courses";

            try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                
            }

            return 0; // Return 0 if something went wrong
        }
    }
	//setting
	public int updateUserData(String oldEmail, String newFirstName, String newSurname, String newEmail, String newPassword) {
        String url = "jdbc:mysql://localhost:3306/course";
        String dbUsername = "root";
        String dbPassword = "";

        String query = "UPDATE users SET firstname = ?, lastname = ?, email = ?, password = ? WHERE  email = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newFirstName);
            preparedStatement.setString(2, newSurname);
            preparedStatement.setString(3, newEmail);
            preparedStatement.setString(4, newPassword);
            preparedStatement.setString(5, oldEmail);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Display success message
                JOptionPane.showMessageDialog(null,"Profile updated successfully. Please re-login !!");
                Login logIn = new Login();;
                logIn.setVisible(true);
                dispose();
            }else {
                JOptionPane.showMessageDialog(null,"Failed to update profile !!");
            }

            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return 0; // Return 0 if something went wrong
    }
	


	/**
	 * Create the frame.
	 */
	public Dashboard(String UserEmail) {
		this.userEmail = UserEmail;
//      System.out.println(userEmail);
      Map<String, String> userData = getUserData(userEmail);
      
      String fname = userData.get("firstname");
      String role = userData.get("role");
      String lname = userData.get("lastname");
      String password = userData.get("password");
      String Email = userData.get("email");
      
      DatabaseOperations databaseOperations = new DatabaseOperations();
      int totalCourses = databaseOperations.getTotalCourseCount();
      String totalCourse = String.valueOf(totalCourses);

      int totalStds = getTotalCount("Student");
      int totalA = getTotalCount("Admin");
      int totalT = getTotalCount("Teacher");

      String totalAdmins = String.valueOf(totalA);
      String totalTeachers = String.valueOf(totalT);
      String totalStudents = String.valueOf(totalStds);


      
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 101, 441);
		panel.setBackground(new Color(102, 0, 0));
		panel.setForeground(new Color(51, 0, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNewButton.setBounds(10, 89, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Teacher");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(10, 125, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Student");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton_2.setBounds(10, 159, 89, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Course");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setBounds(10, 227, 86, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Admin");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_4.setBounds(10, 193, 89, 23);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_4_2 = new JButton("Log Out");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
                l.setVisible(true);
                dispose();
			}
		});
		btnNewButton_4_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_4_2.setBounds(10, 407, 89, 23);
		panel.add(btnNewButton_4_2);
		
		JButton btnSetting = new JButton("Setting");
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);
			}
		});
		btnSetting.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSetting.setBounds(10, 377, 89, 23);
		panel.add(btnSetting);
		
		JButton btnNewButton_8 = new JButton("Result");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					tabbedPane.setSelectedIndex(5);
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_8.setBounds(10, 268, 89, 23);
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_8_1 = new JButton("Show res");
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultid1 rID = new resultid1();
				rID.setVisible(true);
					
				}
			
		});
		btnNewButton_8_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_8_1.setBounds(10, 305, 89, 23);
		panel.add(btnNewButton_8_1);
		
		JButton btnNewButton_1_1 = new JButton(role);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_1.setBounds(10, 62, 89, 23);
		panel.add(btnNewButton_1_1);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(111, 5, 651, 441);
		contentPane.add(tabbedPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(new Color(204, 102, 51));
		tabbedPane.addTab("Home", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.DARK_GRAY);
		panel_9.setBounds(159, 95, 96, 86);
		panel_4.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel(totalAdmins);
		lblNewLabel_6.setForeground(new Color(255, 128, 0));
		lblNewLabel_6.setBackground(new Color(64, 0, 0));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(5, 21, 76, 29);
		panel_9.add(lblNewLabel_6);
		
		JLabel lblNewLabel_2 = new JLabel(totalStudents);
		lblNewLabel_2.setBounds(73, 12, 0, 0);
		panel_9.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\9218716.png"));
		
		JLabel lblNewLabel_8 = new JLabel("Admin :");
		lblNewLabel_8.setForeground(new Color(64, 0, 64));
		lblNewLabel_8.setBounds(26, 8, 46, 14);
		panel_9.add(lblNewLabel_8);
		
		JPanel panel_9_1 = new JPanel();
		panel_9_1.setBackground(Color.LIGHT_GRAY);
		panel_9_1.setBounds(434, 95, 96, 86);
		panel_4.add(panel_9_1);
		panel_9_1.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBounds(48, 5, 0, 0);
		panel_9_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_6_1 = new JLabel(totalAdmins);
		lblNewLabel_6_1.setForeground(new Color(0, 0, 64));
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6_1.setBounds(10, 26, 76, 29);
		panel_9_1.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("Student :");
		lblNewLabel_8_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_8_1.setBounds(20, 5, 66, 14);
		panel_9_1.add(lblNewLabel_8_1);
		
		JPanel panel_9_2 = new JPanel();
		panel_9_2.setBackground(Color.PINK);
		panel_9_2.setBounds(159, 208, 96, 86);
		panel_4.add(panel_9_2);
		panel_9_2.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2.setBounds(48, 5, 0, 0);
		panel_9_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_6_2 = new JLabel(totalTeachers);
		lblNewLabel_6_2.setForeground(new Color(64, 0, 0));
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6_2.setBounds(10, 28, 76, 29);
		panel_9_2.add(lblNewLabel_6_2);
		
		JLabel lblNewLabel_8_2 = new JLabel("Teacher :");
		lblNewLabel_8_2.setForeground(new Color(255, 255, 0));
		lblNewLabel_8_2.setBounds(25, 4, 61, 14);
		panel_9_2.add(lblNewLabel_8_2);
		
		JPanel panel_9_3 = new JPanel();
		panel_9_3.setBackground(Color.BLACK);
		panel_9_3.setBounds(434, 208, 96, 86);
		panel_4.add(panel_9_3);
		panel_9_3.setLayout(null);
		
		JLabel lblNewLabel_2_3 = new JLabel("");
		lblNewLabel_2_3.setBounds(48, 5, 0, 0);
		panel_9_3.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_6_3 = new JLabel(totalCourse);
		lblNewLabel_6_3.setForeground(new Color(255, 255, 0));
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6_3.setBounds(10, 30, 76, 29);
		panel_9_3.add(lblNewLabel_6_3);
		
		JLabel lblNewLabel_8_3 = new JLabel("Courses :");
		lblNewLabel_8_3.setForeground(new Color(255, 128, 128));
		lblNewLabel_8_3.setBounds(21, 5, 54, 14);
		panel_9_3.add(lblNewLabel_8_3);
		
		JLabel lblNewLabel_7 = new JLabel("WELCOME TO HOME PAGE");
		lblNewLabel_7.setForeground(new Color(0, 0, 128));
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 24));
		lblNewLabel_7.setBounds(189, 11, 447, 38);
		panel_4.add(lblNewLabel_7);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\administrator (1).png"));
		lblNewLabel_9.setBounds(72, 95, 77, 75);
		panel_4.add(lblNewLabel_9);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\graduated.png"));
		label.setBounds(347, 106, 77, 59);
		panel_4.add(label);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\teacher.png"));
		lblNewLabel_10.setBounds(72, 208, 64, 75);
		panel_4.add(lblNewLabel_10);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\online-learning.png"));
		label_1.setBounds(347, 208, 77, 66);
		panel_4.add(label_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("Teacher", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_6_1_1_1 = new JPanel();
		panel_6_1_1_1.setLayout(null);
		panel_6_1_1_1.setForeground(Color.WHITE);
		panel_6_1_1_1.setBackground(new Color(51, 0, 0));
		panel_6_1_1_1.setBounds(0, 0, 626, 56);
		panel_5.add(panel_6_1_1_1);
		
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setForeground(new Color(255, 255, 204));
		lblTeacher.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblTeacher.setBounds(258, 11, 95, 20);
		panel_6_1_1_1.add(lblTeacher);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Admin name :");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(10, 67, 105, 20);
		panel_5.add(lblNewLabel_1_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(114, 69, 169, 20);
		panel_5.add(textField_3);
		
		JButton btnNewButton_5_3_1_1 = new JButton("ADD");
		btnNewButton_5_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTeacher AD = new AddTeacher();
				AD.setVisible(true);
			}
		});
		btnNewButton_5_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_3_1_1.setBounds(311, 67, 105, 23);
		panel_5.add(btnNewButton_5_3_1_1);
		
		JButton btnNewButton_5_1_1_1_1 = new JButton("Edit");
		btnNewButton_5_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditTeacher editA  = new EditTeacher();
				editA.setVisible(true);
			}
		});
		btnNewButton_5_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_1_1_1_1.setBounds(439, 68, 118, 23);
		panel_5.add(btnNewButton_5_1_1_1_1);
		
		JButton btnNewButton_5_2_1_1_1 = new JButton("Del");
		btnNewButton_5_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deleteteacher DA = new Deleteteacher();
				DA.setVisible(true);
			}
		});
		btnNewButton_5_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_2_1_1_1.setBounds(367, 106, 118, 23);
		panel_5.add(btnNewButton_5_2_1_1_1);
		
		tc = new TC();
		tc.setBounds(10,170,624,161);
		tc.setForeground(new Color(0,0,0));
		panel_5.add(tc);
		tc.setLayout(null);
		
		JButton btnNewButton_6_1_1_1 = new JButton("Refresh");
		btnNewButton_6_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tc = new TC();
				tc.setBounds(12,170,624,161);
				tc.setForeground(new Color(0,0,0));
				panel_5.add(tc);
				tc.setLayout(null);
			}
		});
		btnNewButton_6_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_6_1_1_1.setBounds(265, 359, 118, 23);
		panel_5.add(btnNewButton_6_1_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(205, 133, 63));
		tabbedPane.addTab("Student", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_6_1_1 = new JPanel();
		panel_6_1_1.setLayout(null);
		panel_6_1_1.setForeground(Color.WHITE);
		panel_6_1_1.setBackground(new Color(51, 0, 0));
		panel_6_1_1.setBounds(0, 0, 626, 56);
		panel_1.add(panel_6_1_1);
		
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setForeground(new Color(255, 255, 204));
		lblStudent.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblStudent.setBounds(258, 11, 95, 20);
		panel_6_1_1.add(lblStudent);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(125, 69, 169, 20);
		panel_1.add(textField_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Admin name :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 67, 105, 20);
		panel_1.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton_5_3_1 = new JButton("ADD");
		btnNewButton_5_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADDstudent AD = new ADDstudent();
				AD.setVisible(true);
			}
		});
		btnNewButton_5_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_3_1.setBounds(313, 68, 105, 23);
		panel_1.add(btnNewButton_5_3_1);
		
		JButton btnNewButton_5_1_1_1 = new JButton("Edit");
		btnNewButton_5_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminEdit editA  = new AdminEdit();
				editA.setVisible(true);
			}
		});
		btnNewButton_5_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_1_1_1.setBounds(448, 68, 118, 23);
		panel_1.add(btnNewButton_5_1_1_1);
		
		JButton btnNewButton_5_2_1_1 = new JButton("Del");
		btnNewButton_5_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deleteadmin DA = new Deleteadmin();
				DA.setVisible(true);
			}
		});
		btnNewButton_5_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_2_1_1.setBounds(373, 103, 118, 23);
		panel_1.add(btnNewButton_5_2_1_1);
		
		std = new STD();
		std.setBounds(0,137,670,219);
		std.setForeground(new Color(0,0,0));
		panel_1.add(std);
		std.setLayout(null);
		
		JButton btnNewButton_6_1_1 = new JButton("Refresh");
		btnNewButton_6_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				std = new STD();
				std.setBounds(0,137,670,219);
				std.setForeground(new Color(0,0,0));
				panel_1.add(std);
				std.setLayout(null);
			}
		});
		btnNewButton_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_6_1_1.setBounds(259, 368, 118, 23);
		panel_1.add(btnNewButton_6_1_1);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(238, 232, 170));
		tabbedPane.addTab("Admin", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setForeground(Color.WHITE);
		panel_6_1.setBackground(new Color(51, 0, 0));
		panel_6_1.setBounds(0, 0, 626, 56);
		panel_3.add(panel_6_1);
		panel_6_1.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(258, 11, 95, 20);
		lblAdmin.setForeground(new Color(255, 255, 204));
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 23));
		panel_6_1.add(lblAdmin);
		
		JLabel lblNewLabel_1_1 = new JLabel("Admin name :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 79, 105, 20);
		panel_3.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(125, 81, 169, 20);
		panel_3.add(textField_1);
		
		JButton btnNewButton_5_3 = new JButton("ADD");
		btnNewButton_5_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADDadmin AD = new ADDadmin();
				AD.setVisible(true);
				}
		});
		btnNewButton_5_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_3.setBounds(300, 80, 105, 23);
		panel_3.add(btnNewButton_5_3);
		
		JButton btnNewButton_5_1_1 = new JButton("Edit");
		btnNewButton_5_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminEdit editA  = new AdminEdit();
				editA.setVisible(true);
			}
		});
		btnNewButton_5_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_1_1.setBounds(415, 80, 118, 23);
		panel_3.add(btnNewButton_5_1_1);
		
		JButton btnNewButton_5_2_1 = new JButton("Del");
		btnNewButton_5_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deleteadmin DA = new Deleteadmin();
				DA.setVisible(true);
			}
		});
		btnNewButton_5_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_2_1.setBounds(356, 114, 118, 23);
		panel_3.add(btnNewButton_5_2_1);
		
		adm = new AMD();
		adm.setBounds(0,179,636,164);
		adm.setVisible(true);		
		panel_3.add(adm);
		
		JButton btnNewButton_6_1 = new JButton("Refresh");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adm = new AMD();
				adm.setBounds(0,179,636,164);
				adm.setVisible(true);		
				panel_3.add(adm);
			}
		});
		btnNewButton_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_6_1.setBounds(273, 351, 118, 23);
		panel_3.add(btnNewButton_6_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(255, 204, 102));
		tabbedPane.addTab("Course", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Course name :");
		lblNewLabel_1.setBounds(10, 78, 105, 20);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(113, 80, 169, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Add course");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourse Add = new AddCourse();
				Add.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(301, 79, 105, 23);
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnNewButton_5);
		
		JButton btnNewButton_5_1 = new JButton("Edit Course");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditCourse courseE = new EditCourse();
				courseE.setVisible(true);
			}
		});
		btnNewButton_5_1.setBounds(433, 78, 118, 23);
		btnNewButton_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnNewButton_5_1);
		
		JButton btnNewButton_5_2 = new JButton("Delete Course");
		btnNewButton_5_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteCourse courseD = new DeleteCourse();
				courseD.setVisible(true);
			}
		});
		btnNewButton_5_2.setBounds(301, 106, 118, 23);
		btnNewButton_5_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnNewButton_5_2);
		
		coursedisplay = new course();
		coursedisplay.setBackground(new Color(240, 128, 128));
		coursedisplay.setBounds(10,130,626,198);
		coursedisplay.setForeground(new Color(0, 0, 0));
		panel_2.add(coursedisplay);
		coursedisplay.setLayout(null);
		
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 11, 626, 56);
		panel_6.setForeground(new Color(255, 255, 255));
		panel_6.setBackground(new Color(51, 0, 0));
		panel_2.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Courses");
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(258, 11, 95, 20);
		panel_6.add(lblNewLabel);
		
		JButton btnNewButton_6 = new JButton("Refresh");
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coursedisplay = new course();
				coursedisplay.setBackground(new Color(240, 128, 128));
				coursedisplay.setBounds(10,130,626,198);
				coursedisplay.setForeground(new Color(0, 0, 0));
				panel_2.add(coursedisplay);
				coursedisplay.setLayout(null);
			}
		});
		btnNewButton_6.setBounds(260, 353, 118, 23);
		panel_2.add(btnNewButton_6);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Result", null, panel_8, null);
		panel_8.setLayout(null);
		
		JPanel panel_6_1_2 = new JPanel();
		panel_6_1_2.setLayout(null);
		panel_6_1_2.setForeground(Color.WHITE);
		panel_6_1_2.setBackground(new Color(51, 0, 0));
		panel_6_1_2.setBounds(0, 0, 646, 56);
		panel_8.add(panel_6_1_2);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setForeground(new Color(255, 255, 204));
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblResult.setBounds(270, 11, 95, 20);
		panel_6_1_2.add(lblResult);
		
		JLabel lblNewLabel_1_2 = new JLabel("Search :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(10, 67, 105, 20);
		panel_8.add(lblNewLabel_1_2);
		
		JButton btnNewButton_5_4 = new JButton("Add result\r\n");
		btnNewButton_5_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMarks Add = new addMarks();
				Add.setVisible(true);
			}
		});
		btnNewButton_5_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_4.setBounds(294, 67, 105, 23);
		panel_8.add(btnNewButton_5_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(93, 69, 169, 20);
		panel_8.add(textField_4);
		
		JButton btnNewButton_5_1_2 = new JButton("Edit Result");
		btnNewButton_5_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editresult edit = new Editresult();
				edit.setVisible(true);
			}
		});
		btnNewButton_5_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_5_1_2.setBounds(445, 67, 118, 23);
		panel_8.add(btnNewButton_5_1_2);
		
		rs = new RS();
		rs.setBounds(20,180,601,142);
		rs.setVisible(true);		
		panel_8.add(rs);
		
		JButton btnNewButton_6_2 = new JButton("Refresh");
		btnNewButton_6_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rs = new RS();
				rs.setBounds(20,180,601,142);
				rs.setVisible(true);		
				panel_8.add(rs);
			}
		});
		btnNewButton_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_6_2.setBounds(276, 357, 118, 23);
		panel_8.add(btnNewButton_6_2);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Setting", null, panel_7, null);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\javaimg\\9218716.png"));
		lblNewLabel_3.setBounds(362, 67, 128, 120);
		panel_7.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Setting :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_4.setBounds(10, 25, 149, 32);
		panel_7.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("First Name :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 82, 99, 21);
		panel_7.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Last Name :");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(10, 110, 99, 21);
		panel_7.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Email :");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2.setBounds(10, 142, 84, 21);
		panel_7.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("Change Password :");
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_1.setBounds(10, 243, 142, 21);
		panel_7.add(lblNewLabel_5_2_1);
		
		JLabel lblNewLabel_5_2_2 = new JLabel("Old Password :");
		lblNewLabel_5_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5_2_2.setBounds(10, 211, 112, 21);
		panel_7.add(lblNewLabel_5_2_2);
		
		newname = new JTextField();
		newname.setBounds(106, 84, 163, 20);
		panel_7.add(newname);
		newname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(106, 114, 163, 20);
		panel_7.add(lastname);
		
		JButton btnNewButton_7 = new JButton("Update");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Ofname = newname.getText();
                String Olname = lastname.getText();
                String Oemail = email.getText();
                String Opass = passwordField.getText();
                String Npass = passwordField_1.getText();
                
                if(!Ofname.isEmpty() && !Olname.isEmpty() && !Oemail.isEmpty() && !Opass.isEmpty() && !Npass.isEmpty()) {
                    if(Opass.equals(password)) {
                        updateUserData(Email,Ofname,Olname,Oemail,Npass);
                        
                    }
                }
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_7.setBounds(127, 308, 89, 23);
		panel_7.add(btnNewButton_7);
		
		email = new JTextField();
		email.setBounds(106, 144, 163, 20);
		panel_7.add(email);
		email.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 213, 149, 20);
		panel_7.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(155, 245, 149, 20);
		panel_7.add(passwordField_1);
		System.out.println(role);
		if("Admin".equals(role)) {
			btnNewButton_8.setVisible(true);
			btnNewButton_8_1.setVisible(true);
			btnNewButton_5_3_1_1.setVisible(true);
			btnNewButton_5_1_1_1_1.setVisible(true);
			btnNewButton_5_2_1_1_1.setVisible(true);
			btnNewButton_5_3_1.setVisible(true);
			btnNewButton_5_1_1_1.setVisible(true);
			btnNewButton_5_2_1_1.setVisible(true);
			btnNewButton_5_3.setVisible(true);
			btnNewButton_5_1_1.setVisible(true);
			btnNewButton_5_2_1.setVisible(true);
			btnNewButton_5.setVisible(true);
			btnNewButton_5_1.setVisible(true);
			btnNewButton_5_2.setVisible(true);
			
		}else {
			btnNewButton_8.setVisible(false);
			btnNewButton_8_1.setVisible(false);
			btnNewButton_5_3_1_1.setVisible(false);
			btnNewButton_5_1_1_1_1.setVisible(false);
			btnNewButton_5_2_1_1_1.setVisible(false);
			btnNewButton_5_3_1.setVisible(false);
			btnNewButton_5_1_1_1.setVisible(false);
			btnNewButton_5_2_1_1.setVisible(false);
			btnNewButton_5_3.setVisible(false);
			btnNewButton_5_1_1.setVisible(false);
			btnNewButton_5_2_1.setVisible(false);
			btnNewButton_5.setVisible(false);
			btnNewButton_5_1.setVisible(false);
			btnNewButton_5_2.setVisible(false);

		}
		if("Teacher".equals(role)) {
			btnNewButton_8.setVisible(true);
			btnNewButton_5_4.setVisible(true);
			btnNewButton_5_1_2.setVisible(true);
			
		}else {
			btnNewButton_8.setVisible(false);
			btnNewButton_5_4.setVisible(false);
			btnNewButton_5_1_2.setVisible(false);

		}
	}
}

