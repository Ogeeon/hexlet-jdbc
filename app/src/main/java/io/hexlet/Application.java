package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Application {
    // Нужно указывать базовое исключение,
    // потому что выполнение запросов может привести к исключениям
    public static void main(String[] args) throws SQLException {
        // long termId;
        try (java.sql.Connection conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {
            var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (java.sql.Statement statement = conn.createStatement()) {
                statement.execute(sql);
            }
            /*
            sql = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, "Sarah");
                preparedStatement.setString(2, "333333333");
                preparedStatement.executeUpdate();
                var generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("Inserted with ID=" + generatedKeys.getLong(1));
                } else {
                    throw new SQLException("DB have not returned an id after saving the entity");
                }
                preparedStatement.setString(1, "John");
                preparedStatement.setString(2, "222222");
                preparedStatement.executeUpdate();
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("Inserted with ID=" + generatedKeys.getLong(1));
                } else {
                    throw new SQLException("DB have not returned an id after saving the entity");
                }
                preparedStatement.setString(1, "T-1000");
                preparedStatement.setString(2, "1111");
                preparedStatement.executeUpdate();
                generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    termId = generatedKeys.getLong(1);
                    System.out.println("Inserted with ID=" + termId);
                } else {
                    throw new SQLException("DB have not returned an id after saving the entity");
                }
            }
            sql = "DELETE FROM users WHERE id = ?";
            try (var preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setLong(1, termId);
                preparedStatement.executeUpdate();
            }
            var sql3 = "SELECT * FROM users";
            try (java.sql.Statement statement3 = conn.createStatement()) {
                var resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.print(resultSet.getString("username") + " ");
                    System.out.println(resultSet.getString("phone"));
                }
            }*/
            var dao = new UserDAO(conn);

            var user = new User("Maria", "888888888");
            user.getId(); // null
            dao.save(user);
            System.out.println("Saved with ID=" + user.getId());

            // Возвращается Optional<User>
            var user2 = dao.find(user.getId()).get();
            System.out.println("Are IDs equal? " + (Objects.equals(user2.getId(), user.getId())));
            // user2.getId() == user.getId(); // true
            System.err.println("Deleting 1: " + dao.delete(1l));
            System.err.println("Deleting 2: " + dao.delete(2l));
        }
    }
}