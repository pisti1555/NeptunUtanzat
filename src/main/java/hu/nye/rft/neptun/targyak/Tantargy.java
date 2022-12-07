package hu.nye.rft.neptun.targyak;

import hu.nye.rft.neptun.database.SQLServer;
import hu.nye.rft.neptun.database.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Tantargy {
    SQLServer server;
    User user;

    public Tantargy(SQLServer server, User user) {
        this.server = server;
        this.user = user;
    }

    public void hozzaad() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tantárgy hozzáadása\nTárgy neve: ");
        String targy = scanner.nextLine().toUpperCase();
        String vnev = server.miAVNeve(user.getNeptunKod());
        String knev = server.miAKNeve(user.getNeptunKod());
        String nev = vnev + " " + knev;
        server.miAVNeve(user.getNeptunKod());
        System.out.println("Időpont: (Hétfőtől-Vasárnapig)");
        String idopont = scanner.next();
        System.out.println("Még szabad helyek száma: ");
        int helyek = scanner.nextInt();
        if(!server.letezikEIlyenTargy(targy)) {
            server.insertTantargy(targy, nev, idopont, helyek);
        } else {
            System.out.println("Már létezik a tantárgy");
        }
    }


}
