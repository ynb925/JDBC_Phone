package co.example;

public class RunPhone {

    public static void main(String[] args) {
        PhoneDAO phoneDAO = new PhoneDAO();

        Phone phone1 = new Phone();
        phone1.setUserName("p1");
        phone1.setPhoneNumber("123456");

        var createPhone = phoneDAO.create(phone1);
        System.out.println(createPhone);

        phone1.setUserName("otherPhone");
        phone1.setPhoneNumber("77777777");
        var updatePhone = phoneDAO.update(phone1);

        System.out.println(updatePhone);
        System.out.println(phoneDAO.delete(phone1.getId()));

        var foundedPhone = phoneDAO.findById(97);
        System.out.println(foundedPhone);

        System.out.println(phoneDAO.findAll());

        phoneDAO.close();
    }
}

/*

// active this code to test jdbc connection is it work

    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin5555");

        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.createStatement()) {
                var resultSet = statement.executeQuery("SELECT * FROM phone;");
                System.out.println("id |" + " userName   |" + " phoneNumber  |");
                while (resultSet.next()) {
                    var idName = resultSet.getString("id");
                    var userName = resultSet.getString("userName");
                    var phoneNumber = resultSet.getString("phoneNumber");
                    System.out.println(idName + " | " + userName + " | " + phoneNumber);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

*/
