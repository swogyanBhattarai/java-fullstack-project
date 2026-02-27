package com.justdeepfried.learnApi.repository;

import com.justdeepfried.learnApi.model.UserModel;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository  {
    private final String url = "jdbc:sqlite:./application.db";
    private final String username = "";
    private final String password = "";


    public List<UserModel> findAll() {
        List<UserModel> users = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setId(rs.getInt("id"));
                users.add(user);
            }
            rs.close();
            ps.close();
            conn.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserModel findById(int id) {
        UserModel user = new UserModel();

        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setId(rs.getInt("id"));
            } else {
                System.out.printf("No user found with id: %d", id);
                return null;
            }

            rs.close();
            ps.close();
            conn.close();
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addUser(UserModel user) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);

            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, age) VALUES (?, ?)");
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());

            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateUser(int id, UserModel updateUser) {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement getUser = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            getUser.setInt(1, id);
            ResultSet rs = getUser.executeQuery();

            if (rs.next()) {
                PreparedStatement update = conn.prepareStatement("UPDATE users SET name = ?, age = ? WHERE id = ?");
                update.setString(1, updateUser.getName());
                update.setInt(2, updateUser.getAge());
                update.setInt(3, id);
                update.executeUpdate();
            } else {
                System.out.println("No user with id: " + id);
            }
            rs.close();
            getUser.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteUser (int id) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
            deleteStmt.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createIfNotCreated() {
        String sql = """
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            age INTEGER NOT NULL
        )
    """;

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            st.close();
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
