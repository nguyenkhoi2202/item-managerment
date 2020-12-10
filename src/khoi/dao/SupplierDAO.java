/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import khoi.connection.DBcontext;
import khoi.dto.SupplierDTO;

/**
 *
 * @author Nguyen Khoi
 */
public class SupplierDAO {

    public List<SupplierDTO> getAllSup() throws SQLException, Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBcontext.openConnection();
            if (con != null) {
                String sql = "select supCode,supName,address,collaborating "
                        + "from tblSuppliers";
                List<SupplierDTO> list = new ArrayList<>();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    SupplierDTO s = new SupplierDTO(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3), rs.getBoolean(4));
                    list.add(s);
                }
                return list;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void saveSup(String supCode, String supName, String address, boolean collborating) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBcontext.openConnection();
            if (con != null) {
                String sql = "INSERT  tblSuppliers VALUES(?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, supCode);
                ps.setString(2, supName);
                ps.setString(3, address);
                ps.setBoolean(4, collborating);
                ps.executeUpdate();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteSup(String supCode) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBcontext.openConnection();
            if (con != null) {
                String sql = "delete from tblSuppliers where supCode = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, supCode);
                ps.executeUpdate();
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void updateSup(String code, String name, String address, boolean collborating) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBcontext.openConnection();
            if (con != null) {
                String sql = "Update tblSuppliers Set supCode = ? , supName = ?, address = ?, collaborating = ?\n"
                        + "                Where supCode = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, code);
                ps.setString(2, name);
                ps.setString(3, address);
                ps.setBoolean(4, collborating);
                ps.setString(5, code);
                ps.executeUpdate();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

//    public SupplierDTO checkSupCode(String supCode) throws SQLException, Exception {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            con = DBcontext.openConnection();
//            if (con != null) {
//                String sql = "Select supCode,supName,address,collaborating"
//                        + " from tblSuppliers Where supCode = ?";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, supCode);
//                rs = ps.executeQuery();
//                if (rs.next()) {
//                    return new SupplierDTO(rs.getString("supCode"), rs.getString("supName"),
//                            rs.getString("address"), rs.getBoolean("collaborating"));
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
}
