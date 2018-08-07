package db.connect;

import db.dao.UsersDao;
import db.dao.UsersDaoJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Connect {

    private static UsersDao usersDao;

    static {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            properties.load(new FileInputStream("resources/WEB-INF/db.properties"));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            usersDao = new UsersDaoJdbcTemplate(dataSource);

        } catch (IOException e) { e.printStackTrace(); }
    }

    public static UsersDao getUserDao() {
        return usersDao;
    }
}
