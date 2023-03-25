package co.example;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Створити клас PhoneDAO з наступними методами:
 * Phone findById(int id), який повертає об’єкт Phone з бази даних
 * List<Phone> findAll(), який повертає усі об’єкти Phone з бази даних
 * Boolean delete(int id), який видаляє Phone з бази за id
 * Boolean create(Phone phone) який додає новий Phone до бази даних
 * Phone update(Phone phone) який оновлює об’єкт.
 */

public class PhoneDAO {

    private final Connection connection;

    public PhoneDAO() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin5555");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String createSQL = """
            INSERT INTO phone(
            	 "userName", "phoneNumber")
            	VALUES ( ?, ?);
            """;
    public static final String deleteSQL = """
            DELETE FROM phone
            WHERE id=?;
            """;
    public static final String updateSQL = """
             UPDATE phone
             SET "userName"=?,
            "phoneNumber"=?
             WHERE id=?;
             """;
    public static final String findAll = """
            SELECT *
            FROM phone
            """;
    public static final String findByIdSQL = findAll + """                           
            WHERE id=?
            """;


    public Phone create(Phone phone) {
        try (var prepareStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, phone.getUserName());
            prepareStatement.setString(2, phone.getPhoneNumber());

            prepareStatement.executeUpdate();
            var generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next())
                phone.setId(generatedKeys.getLong("id"));

            return phone;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean delete(long id) {
        try (var prepareStatement = connection.prepareStatement(deleteSQL)) {
            prepareStatement.setLong(1, id);

            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Phone update(Phone phone) {
        try (var prepareStatement = connection.prepareStatement(updateSQL)) {
            prepareStatement.setString(1, phone.getUserName());
            prepareStatement.setString(2, phone.getPhoneNumber());
            prepareStatement.setInt(3, (int) phone.getId());

            var resultSet = prepareStatement.executeUpdate();

            return phone;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Phone> findById(int id) {
        try (var preparedStatement = connection.prepareStatement(findByIdSQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();

            Phone phone = null;
            if (resultSet.next()) {
                phone = phoneBuilder(resultSet);
            }
            return Optional.ofNullable(phone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Phone phoneBuilder(ResultSet resultSet) {
        Phone phone;
        phone = new Phone();
        try {
            phone.setId(resultSet.getLong("id"));
            phone.setUserName(resultSet.getString("userName"));
            phone.setPhoneNumber(resultSet.getString("phoneNumber"));
            return phone;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Phone> findAll() {
        try (var preparedStatement = connection.prepareStatement(findAll)) {
            var resultSet = preparedStatement.executeQuery();
            List<Phone> phones = new ArrayList<>();
            while (resultSet.next()) {
                phones.add(phoneBuilder(resultSet));
            }

            return phones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

