package ua.com.alevel.db_all_circle.dao;

import org.springframework.stereotype.Service;
import ua.com.alevel.db_all_circle.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service("jdbcUserDao")
public class JdbcUserDao implements UserDao {

    private Connection connection;
    private Statement statement;

    private static final String CREATE_USER_QUERY = "insert into users values (default,?,?,?)";
    private final static String UPDATE_BY_ID_QUERY = "update users set email = ?, first_name = ?, last_name = ? where id = ?";
    private static final String FIND_ALL_USER_QUERY = "select * from users";
    private static final String FIND_USER_BY_ID_QUERY = "select * from users where id = ";
    private static final String DELETE_USER_BY_ID_QUERY = "delete from users where id = ";

    public JdbcUserDao() {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/db_all_circle?useSSL=false&serverTimezone=UTC",
                    "jdbc:postgresql://localhost:5432/db_all_circle",
//                    "root", "root");
                    "postgres", "root");
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(User user) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection
                    .prepareStatement(CREATE_USER_QUERY);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection
                    .prepareStatement(UPDATE_BY_ID_QUERY);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection
                    .prepareStatement(DELETE_USER_BY_ID_QUERY + id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User find(Integer id) {
        try {
            ResultSet resultSet = this.statement.executeQuery(FIND_USER_BY_ID_QUERY + id);
            while (resultSet.next()) {
                return initUserByResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            System.out.println("throwables = " + throwables.getMessage());
        }
        return null;
    }

    @Override
    public List<User> find() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = this.statement.executeQuery(FIND_ALL_USER_QUERY);
            while (resultSet.next()) {
                users.add(initUserByResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            System.out.println("throwables = " + throwables.getMessage());
        }
        return users;
    }

    private User initUserByResultSet(ResultSet resultSet) throws SQLException {
        String email = resultSet.getString("email");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int id = resultSet.getInt("id");
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
