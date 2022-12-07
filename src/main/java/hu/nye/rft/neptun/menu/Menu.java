package hu.nye.rft.neptun.menu;


import hu.nye.rft.neptun.database.SQLServer;
import hu.nye.rft.neptun.targyak.Tantargy;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class Menu {
    Tantargy targy;
    SQLServer server;
    Scanner scanner = new Scanner(System.in);

    public Menu(Tantargy targy, SQLServer server) {
        this.targy = targy;
        this.server = server;
    }

    public void menu() throws SQLException {
        boolean eloado = eloadoE();
        System.out.println("1: Tárgyfelvétel | 2: Tárgy hozzáadása(Ha előadó vagy) | 9: Kilépés");
        int valasztas = scanner.nextInt();
        switch (valasztas) {
            case 1: {
                server.listazas();
                System.out.println("Melyik tárgyat veszed fel?");
                String targy = scanner.next();
                server.targyFelvetel(targy);
            }break;
            case 2: {
                if(eloado) {
                    targy.hozzaad();
                } else {
                    System.out.println("Ehhez nincs jogosultságod");
                }
            }break;
            case 9: {
                System.exit(0);
            }break;
            default: {
                System.out.println("Vmi nem jó menu switch");
            }break;
        }
    }

    private boolean eloadoE() {
        Scanner scanner = new Scanner(System.in);
        boolean eloado = false;
        System.out.println("Előado(1) vagy diák(2) vagy?");
        int valasz = scanner.nextInt();
        switch (valasz) {
            case 1: {
                eloado = true;
            }break;
            case 2: {
                eloado = false;
            }break;
            default: {
                System.out.println("Ismeretlen parancs");
                eloadoE();
            }break;
        }
        return eloado;
    }
}
