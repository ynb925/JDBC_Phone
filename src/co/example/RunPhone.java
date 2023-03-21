package co.example;

/**
 *
 */

public class RunPhone {

    public static void main(String[] args) {

    }
}



/*

// active this code to make sure your self what connection is work

    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUser("postgres");
        dataSource.setPassword("admin5555");

        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.createStatement()) {
                var resultSet = statement.executeQuery("SELECT * FROM doctor WHERE age>25;");
                System.out.println("id |" + " fullName |" + " spez  |" + " age  |");
                System.out.println("_________________________________");
                while (resultSet.next()) {
                    var idName = resultSet.getString("id");
                    var fullName = resultSet.getString("fullname");
                    var spez = resultSet.getString("spez");
                    var age = resultSet.getString("age");
                    System.out.println(idName + " | " + fullName + " | " + spez + " | " + age);
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
