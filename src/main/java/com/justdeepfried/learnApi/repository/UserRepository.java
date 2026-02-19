package com.justdeepfried.learnApi.repository;

import com.justdeepfried.learnApi.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository  {
    private final String url = "jdbc:sqlite:./application.db";
    private final String username = "";
    private final String password = "";


    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected!");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserEntity user = new UserEntity();
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
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

    public UserEntity findById(int id) {
        UserEntity user = new UserEntity();

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected!");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));

            rs.close();
            ps.close();
            conn.close();
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void createIfNotCreated() {
        String sql = """
        CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            username TEXT NOT NULL,
            password TEXT NOT NULL
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
