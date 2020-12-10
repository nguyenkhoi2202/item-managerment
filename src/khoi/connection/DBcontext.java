/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Khoi
 */
public class DBcontext {
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=ItemManagement";
    static String user = "sa";
    static String pass = "123";

    public static java.sql.Connection openConnection() throws Exception {
        Connection con=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not connnect to database!!!");  
        }
       return con;
    }
}
