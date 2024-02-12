// CoursesDisplay.java

package java10;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.sql.*;

public class RS extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public RS() {
        setLayout(null);

        // Getting the data
        DefaultTableModel model = new DefaultTableModel();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/course", "root", "");
            Statement stmt = con.createStatement();
            String query = "SELECT u.user_id, u.firstname, u.lastname, u.email,u.std_course,u.std_level, r.module_1, r.mark_1,r.module_2, r.mark_2,r.module_3, r.mark_3, r.percentage, r.result FROM users u JOIN results r ON u.user_id=r.std_id";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 596, 300);
        add(scrollPane);
        
        table = new JTable(model);
        scrollPane.setViewportView(table);

        TableColumnModel columnModel = table.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);

        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);

        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setVisible(true);
        table.setIntercellSpacing(new java.awt.Dimension(7, 7));
    }
}
