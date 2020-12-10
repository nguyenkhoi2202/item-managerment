/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import khoi.connection.DBcontext;

/**
 *
 * @author Nguyen Khoi
 */
public class UserDAO {
    public static String checkLogin(String ID, String PW){
        Connection cn = null;
        try {
            cn = DBcontext.openConnection();
            if (cn != null) {
                String sql = "select fullName from tblUsers where userID=? and password=?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, ID);
                ps.setString(2, PW);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name=rs.getString("fullname");
                    return name;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(cn!=null){
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
