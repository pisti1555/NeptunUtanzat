package hu.nye.rft.neptun.login;

import hu.nye.rft.neptun.database.SQLServer;
import hu.nye.rft.neptun.user.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    User user;
    SQLServer server;

    public Login(User user, SQLServer server) {
        this.user = user;
        this.server = server;
    }

    private static final int NEPTUNKODHOSSZ = 6;
    private static int i = 0;

    public void login() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        if(i==5) {
            System.out.println("Túl sokszor adott meg hibás adatokat");
            System.exit(0);
        }
        System.out.println("Adja meg az adatait\nNEPTUN-kód: ");
        user.setNeptunKod(scanner.next().toUpperCase());
        if(user.getNeptunKod().length()!=NEPTUNKODHOSSZ) {
            System.out.println("Hibás neptun azonosító\nKérem írja be újra figyelmesen\n");
            i++;
            login();
        }
        System.out.println("Jelszó: ");
        user.setJelszo(scanner.next());

        if(!user.findUser(user.getNeptunKod())) {
            System.out.println("Nem létezik ilyen profil");
            login();
        }

        if(!user.isPwCorrect(user.getNeptunKod(), user.getJelszo())) {
            System.out.println("Hibás jelszó");
            login();
        }else {
            System.out.println("Belépés...");
        }
    }

    public void regisztracio() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adja meg az adatait!\nVezetéknév: ");
        user.setVnev(scanner.next());
        System.out.println("Keresztnév: ");
        user.setKnev(scanner.next());
        System.out.println("Neptun-kód: ");
        user.setNeptunKod(scanner.next().toUpperCase());
        System.out.println("Jelszó: ");
        user.setJelszo(scanner.next());
        if(!user.findUser(user.getNeptunKod())) {
            server.insertUser(user.getVnev(), user.getKnev(), user.getNeptunKod(), user.getJelszo());
        } else {
            System.out.println("Már létezik profil ilyen neptun kóddal");
        }
    }

    public void belepes() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Belépés | 2: Regisztráció");
        int valasz = scanner.nextInt();
        switch (valasz) {
            case 1: {
                login();
            }
            break;
            case 2: {
                regisztracio();
            }
            break;
            default: {
                System.out.println("Hiba a választás folyamán");
            }
            break;
        }
    }
}