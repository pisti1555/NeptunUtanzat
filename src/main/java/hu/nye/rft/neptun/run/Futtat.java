package hu.nye.rft.neptun.run;


import hu.nye.rft.neptun.database.SQLServer;
import hu.nye.rft.neptun.login.Login;
import hu.nye.rft.neptun.menu.Menu;
import hu.nye.rft.neptun.user.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Futtat {
    Menu menu;
    SQLServer server;
    Login login;

    public Futtat(Menu menu, SQLServer server, Login login) {
        this.menu = menu;
        this.server = server;
        this.login = login;
    }

    public void futtat() throws SQLException {
        server.createDatabaseIfNotExists();
        server.createTantargyakIfNotExists();
        login.belepes();
        menu.menu();
    }
}
