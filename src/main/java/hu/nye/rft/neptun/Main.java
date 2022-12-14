package hu.nye.rft.neptun;

import hu.nye.rft.neptun.database.SQLServer;
import hu.nye.rft.neptun.database.User;
import hu.nye.rft.neptun.login.Login;
import hu.nye.rft.neptun.menu.Menu;
import hu.nye.rft.neptun.run.Futtat;
import hu.nye.rft.neptun.targyak.Tantargy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main class.
 */
public class Main {
    /**
     * Futtat.
     */
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/neptun", "sa", "pass");

        SQLServer server = new SQLServer();
        User user = new User(connection, server);
        Login login = new Login(user, server);
        Tantargy targy = new Tantargy(server, user);
        Menu menu = new Menu(targy, server);
        Futtat futtat = new Futtat(menu, server, login);

        futtat.futtat();
    }
}