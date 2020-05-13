/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nam.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import nam.conns.MyConnection;
import nam.dtos.FacultyDTO;


public class FacultyDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public FacultyDAO() {

    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<FacultyDTO> getFacultyList() throws Exception {
        List<FacultyDTO> result = null;
        int id;
        String name;
        int age;
        String address;
        FacultyDTO dto = null;
        try {
            String sql = "SELECT FacultyID, FullName, Age, Address FROM Faculty";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("FacultyID");
                name = rs.getString("FullName");
                age = rs.getInt("Age");
                address = rs.getString("Address");

                dto = new FacultyDTO(id, name, age, address);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public FacultyDTO findByPrimaryKey(int id) throws Exception {
        String name;
        int age;
        String address;
        FacultyDTO dto = null;
        try {

            String sql = "SELECT FacultyID, FullName, Age, Address FROM Faculty Where FacultyID = ?";
            //1 va 2
            conn = (Connection) MyConnection.getMyConnection();
            //3
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            //4
            if (rs.next()) {
                id = rs.getInt("FacultyID");
                name = rs.getString("FullName");
                age = rs.getInt("Age");
                address = rs.getString("Address");
                dto = new FacultyDTO(id, name, age, address);

            }

        } finally {
            //5
            closeConnection();
        }
        return dto;
    }
    
    public List<FacultyDTO> findByName(String search) throws Exception {
        List<FacultyDTO> result = null;
        int id;
        String name;
        int age;
        String address;
        FacultyDTO dto = null;
        try {

            String sql = "SELECT FacultyID, FullName, Age, Address FROM Faculty Where (FullName like ?)";
            //1 va 2
            conn = (Connection) MyConnection.getMyConnection();
            //3
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            //4
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("FacultyID");
                name = rs.getString("FullName");
                age = rs.getInt("Age");
                address = rs.getString("Address");
                dto = new FacultyDTO(id, name, age, address);
                result.add(dto);
            }

        } finally {
            //5
            closeConnection();
        }
        return result;
    }

    public boolean insert(int id, String name, int age, String address) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Faculty(FacultyID, FullName, Age, Address) values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.setString(2, name);
            preStm.setInt(3, age);
            preStm.setString(4, address);

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean delete(int id) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete FROM Faculty Where FacultyID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(int id, String name, int age, String address) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Faculty set FullName = ?, Age = ?, Address = ? where FacultyID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, name);
            preStm.setInt(2, age);
            preStm.setString(3, address);
            preStm.setInt(4, id);

            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

}
