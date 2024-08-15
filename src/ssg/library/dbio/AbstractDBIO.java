package ssg.library.dbio;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractDBIO {
    private Connection connection = null;

    private Connection getDatabaseConnection() throws IOException, ClassNotFoundException, SQLException {


        Properties properties = new Properties();

        FileInputStream fis = new FileInputStream("resource/database.properties");

        properties.load(fis);

        fis.close();

        String driver = properties.getProperty("driver");
        String db_url = properties.getProperty("url");
        String db_id = properties.getProperty("username");
        String db_pwd = properties.getProperty("password");

        Class.forName(driver);
        connection = DriverManager.getConnection(db_url, db_id, db_pwd);
        return connection;
    }


    private void open() {
        try {
            connection = getDatabaseConnection();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void close() {
        try {
            connection.close();
        } catch (SQLException se) {
            System.err.println(se.getMessage());
        }
    }

    protected abstract void create(Object o);

    protected abstract Object read();

    protected abstract void update(Object o );

    protected abstract void delete(Object o);
}
