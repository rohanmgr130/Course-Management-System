package java10;

import java.awt.BorderLayout;

import java.util.regex.*;
import java.awt.EventQueue;

import javax.swing.JOptionPane;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class project extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel confirmPassw;
    private JTextField firstName;
    private JTextField surname;
    private JTextField email;
    private JPasswordField password;
    private JPasswordField passwordConfi;
    private JComboBox comboBox;
    private JComboBox comboBox_1;
    private JComboBox comboBox_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    project frame = new  project();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void registerUser(String email, String password, String role, String firstname, String lastname, int std_level, String std_course) {
        String url = "jdbc:mysql://localhost:3306/course";
        String dbUsername = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            switch (role) {
                case "Student":
                    try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname, lastname, email, password, role, std_level, std_course) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                        preparedStatement.setString(1, firstname);
                        preparedStatement.setString(2, lastname);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, password);
                        preparedStatement.setString(5, role);
                        preparedStatement.setInt(6, std_level);
                        preparedStatement.setString(7, std_course);
                        preparedStatement.executeUpdate();
                    }
                    break;
                case "Teacher":
                case "Admin":
                    try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname, lastname, email, password, role) VALUES (?, ?, ?, ?, ?)")) {
                        preparedStatement.setString(1, firstname);
                        preparedStatement.setString(2, lastname);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, password);
                        preparedStatement.setString(5, role);
                        preparedStatement.executeUpdate();
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid role during registration");
            }
            JOptionPane.showMessageDialog(null,"signed in as" + role);
        } catch (SQLException e) {
            // Handle database errors, log or show an appropriate message
            e.printStackTrace();
        }
    }

public  project() {
	setFont(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 890, 532);
        confirmPassw = new JPanel();
        confirmPassw.setBackground(Color.WHITE);
        confirmPassw.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(confirmPassw);
        confirmPassw.setLayout(null);
        
        JLabel lblRegistration = new JLabel("Sign Up");
        lblRegistration.setBounds(104, 37, 189, 35);
        lblRegistration.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 25));
        confirmPassw.add(lblRegistration);
        
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(240, 95, 101, 20);
        lblFirstName.setFont(new Font("Cambria", Font.BOLD, 14));
        confirmPassw.add(lblFirstName);
        
        JLabel lblSurname = new JLabel("Last Name");
        lblSurname.setBounds(240, 148, 101, 25);
        lblSurname.setFont(new Font("Cambria", Font.BOLD, 14));
        confirmPassw.add(lblSurname);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(240, 196, 101, 25);
        lblEmail.setFont(new Font("Cambria", Font.BOLD, 14));
        confirmPassw.add(lblEmail);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(240, 251, 101, 25);
        lblPassword.setFont(new Font("Cambria", Font.BOLD, 14));
        confirmPassw.add(lblPassword);
        
        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setBounds(237, 303, 164, 25);
        lblConfirmPassword.setFont(new Font("Cambria", Font.BOLD, 14));
        confirmPassw.add(lblConfirmPassword);
        
        firstName = new JTextField();
        firstName.setBounds(240, 119, 206, 25);
        confirmPassw.add(firstName);
        firstName.setColumns(10);
        
        surname = new JTextField();
        surname.setBounds(240, 170, 206, 23);
        surname.setColumns(10);
        confirmPassw.add(surname);
        
        email = new JTextField();
        email.setBounds(240, 223, 206, 23);
        email.setColumns(10);
        confirmPassw.add(email);
        
        password = new JPasswordField();
        password.setBounds(240, 274, 206, 23);
        confirmPassw.add(password);
        
        passwordConfi = new JPasswordField();
        passwordConfi.setBounds(240, 328, 206, 23);
        confirmPassw.add(passwordConfi);
JButton registerBtn = new JButton("Sign Up");
registerBtn.setBounds(504, 201, 128, 35);
registerBtn.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 18));
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstname = firstName.getText();
                String lastname = surname.getText();
                String eMail = email.getText();
                String newPass = password.getText();
                String confirmPass = passwordConfi.getText();
                String mode=(String) comboBox.getSelectedItem();
                int selectLevel = Integer.parseInt((String) comboBox_1.getSelectedItem());;
                String select_course =(String) comboBox_2.getSelectedItem();
                
                  // Regex for FirstName
                String regexFN = "[a-zA-Z]+";
                Pattern Fname = Pattern.compile(regexFN);
                
                Matcher FN = Fname.matcher(firstname);
                boolean fname = FN.matches();
                
                // Regex for LastName
                String regexLN = "[a-zA-Z]+";
                Pattern Lname = Pattern.compile(regexLN);
                
                Matcher LN = Fname.matcher(lastname);
                boolean lname = LN.matches();
                
                //for emailverification
             
                String regexEmail =  "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern verifyEmail = Pattern.compile(regexEmail);
                
                Matcher eM = verifyEmail.matcher(eMail);
                boolean checkEmail = eM.matches();
                
                // Regex for New password
                String regexP = "[a-zA-Z]+";
                Pattern passN = Pattern.compile(regexP);
                
                Matcher pN = passN.matcher(newPass);
                boolean passNew = pN.matches();
                
                // Regex for Confirm password
                String regexPC = "[a-zA-Z]+";
                Pattern passC = Pattern.compile(regexPC);
                Matcher pC = passC.matcher(confirmPass);
                boolean passConfirm = pC.matches();
                
                
                if(!firstname.equals("") && !lastname.equals("") && !eMail.equals("") && !newPass.equals("") && !confirmPass.equals(" ")) {
                    if(fname==true && lname==true && checkEmail == true &&  passNew== true && newPass.equals(confirmPass) ) {
         
                        registerUser(eMail,newPass,mode,firstname,lastname,selectLevel,select_course);    
                        JOptionPane.showMessageDialog(null, "Register sucessful");
                    }else {
                        JOptionPane.showMessageDialog(null, "Failed...");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Inputs cannot be empty !!");
                }
                
                }       
        });
        confirmPassw.add(registerBtn);
        
        JLabel lblItsQuickAnd = new JLabel("It's quick and easy.");
        lblItsQuickAnd.setBounds(104, 69, 179, 25);
        lblItsQuickAnd.setFont(new Font("Cambria", Font.BOLD, 13));
        confirmPassw.add(lblItsQuickAnd);
        
        comboBox = new JComboBox();
        comboBox.setBounds(99, 435, 347, 22);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Student", "Admin", "Teacher"}));
        confirmPassw.add(comboBox);
        
        JButton toLogin = new JButton("Login");
        toLogin.setBounds(521, 292, 89, 23);
        toLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
        toLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login lgn = new Login();
                lgn.setVisible(true);
                dispose();
            }
        });
        confirmPassw.add(toLogin);
        
        JLabel lblWelcomeToCourse = new JLabel("Welcome to Course Management System");
        lblWelcomeToCourse.setBackground(Color.BLACK);
        lblWelcomeToCourse.setBounds(143, 11, 478, 35);
        lblWelcomeToCourse.setForeground(new Color(128, 64, 0));
        lblWelcomeToCourse.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 25));
        confirmPassw.add(lblWelcomeToCourse);
        
        comboBox_1 = new JComboBox();
        comboBox_1.setBounds(106, 387, 138, 22);
        comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6"}));
        confirmPassw.add(comboBox_1);
        
        comboBox_2 = new JComboBox();
        comboBox_2.setBounds(295, 387, 138, 22);
        comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"BIT", "BIBM", "IMBA"}));
        confirmPassw.add(comboBox_2);
        
        JLabel lblNewLabel = new JLabel("Register as..");
        lblNewLabel.setBounds(99, 411, 101, 21);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        confirmPassw.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Level");
        lblNewLabel_1.setBounds(137, 362, 83, 22);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        confirmPassw.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Course");
        lblNewLabel_2.setBounds(336, 362, 65, 23);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        confirmPassw.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\icons8-name-100 (1).png"));
        lblNewLabel_3.setBounds(143, 95, 72, 49);
        confirmPassw.add(lblNewLabel_3);
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\email.png"));
        lblNewLabel_5.setBounds(153, 196, 77, 57);
        confirmPassw.add(lblNewLabel_5);
        
        JLabel lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\icons8-key-48.png"));
        lblNewLabel_7.setBounds(154, 309, 66, 42);
        confirmPassw.add(lblNewLabel_7);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\4305535 (1).png"));
        lblNewLabel_4.setBounds(149, 251, 60, 55);
        confirmPassw.add(lblNewLabel_4);
        
        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\profile_12965436 (1).png"));
        lblNewLabel_6.setBounds(153, 148, 57, 45);
        confirmPassw.add(lblNewLabel_6);
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String selectedMode = (String) comboBox.getSelectedItem();

                // Check if the selected mode is Student
                if ("Student".equals(selectedMode)) {
                    comboBox_1.setVisible(true); // Show Level comboBox
                    comboBox_2.setVisible(true); // Show Course comboBox
                    lblNewLabel_1.setVisible(true);
                    lblNewLabel_2.setVisible(true);
                } else {
                    comboBox_1.setVisible(false); // Hide Level comboBox
                    comboBox_2.setVisible(false); // Hide Course comboBox
                    lblNewLabel_1.setVisible(false);
                    lblNewLabel_2.setVisible(false);
                }
            }
        });
   }
}
