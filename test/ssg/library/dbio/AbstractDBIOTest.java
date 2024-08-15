package ssg.library.dbio;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
class AbstractDBIOTest {

    private Connection connection = null;
    @Test
    void getDatabaseConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("resource/database.properties");
        properties.load(fis);

        String driver = properties.getProperty("driver");
        String db_url = properties.getProperty("url");
        String db_id = properties.getProperty("username");
        String db_pwd = properties.getProperty("password");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(db_url, db_id, db_pwd);
        assertNotNull(connection);
    }


    void open() {

    }

    void close() {

    }


    @Test
    void create() {
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}