package java10;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class resultid1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField studentid;

    private static int stdId; // Corrected declaration

    public int getstdId() {
        return stdId;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    resultid1 frame = new resultid1(); // Corrected class name
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
    public resultid1() { // Corrected class name
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Enter student ID:");
        lblNewLabel.setBounds(156, 26, 113, 14);
        contentPane.add(lblNewLabel);
        
        studentid = new JTextField();
        studentid.setBounds(156, 72, 96, 68);
        contentPane.add(studentid);
        studentid.setColumns(10);
        
        JButton btnNewButton = new JButton("show res");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = studentid.getText();
                if (!id.isEmpty()) {
                    stdId = Integer.parseInt(id); // Assign the value to stdId
                    SReport result = new SReport(stdId);
                    result.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty!!");
                }
            }
        });
        btnNewButton.setBounds(60, 202, 89, 23);
        contentPane.add(btnNewButton);
        
        JButton btnBack = new JButton("back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnBack.setBounds(233, 202, 89, 23);
        contentPane.add(btnBack);
    }
}