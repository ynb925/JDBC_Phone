package co.example;

public class SqlConstants {

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
}
