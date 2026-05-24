package config;

public class Constants {
    public static final String USER     = "postgres";
    public static final String PASSWORD = "your_password";  // use env var ideally
    public static final String DATABASE = "book_db";
    public static final int    PORT     = 5432;
    public static final String URL      = "jdbc:postgresql://localhost:" + PORT + "/" + DATABASE;
    public static final String DRIVER   = "org.postgresql.Driver";
}
