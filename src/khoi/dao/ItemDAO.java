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
import khoi.dto.ItemDTO;

/**
 *
 * @author Nguyen Khoi
 */
public class ItemDAO {

    public List<ItemDTO> getAllItem() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select  itemCode, itemName,unit,price,supplying,supCode from tblItems";
        try {
            List<ItemDTO> list = new ArrayList<>();
            con = DBcontext.openConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ItemDTO i = new ItemDTO(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getFloat(4),
                            rs.getBoolean(5),
                            rs.getString(6));
                    list.add(i);
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

    public void saveItem(String itemCode, String itemName, String unit,
            float price, boolean supplying, String supCode) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBcontext.openConnection();
            if (con != null) {
                String sql = "INSERT tblItems VALUES(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, itemCode);
                ps.setString(2, itemName);
                ps.setString(3, unit);
                ps.setFloat(4, price);
                ps.setBoolean(5, supplying);
                ps.setString(6, supCode);
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

    public boolean deleteItem(String itemCode) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBcontext.openConnection();
            if (con != null) {
                String sql = "delete from tblItems where itemCode = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, itemCode);
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

    public void updateItem(String itemCode, String itemName,
            String iunit, float iprice, boolean isupplying,String supplier) throws SQLException, Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBcontext.openConnection();
            if (con != null) {
                String sql = "Update tblItems Set itemName = ?, unit = ?"
                        + ",price= ?, supplying =?,supCode =? Where itemCode = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, itemName);
                ps.setString(2, iunit);
                ps.setFloat(3, iprice);
                ps.setBoolean(4, isupplying);
                ps.setString(5, supplier);
                ps.setString(6, itemCode);
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

    public ItemDTO checkItemCode(String itemCode) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBcontext.openConnection();
            if (con != null) {
                String sql = "Select itemCode,itemName,unit,price,supplying,supCode"
                        + " from tblItems Where itemCode = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, itemCode);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return new ItemDTO(rs.getString("itemCode"), rs.getString("itemName"),
                            rs.getString("unit"), rs.getFloat("price"), rs.getBoolean("supplying"), rs.getString("supCode"));
                }
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
}
