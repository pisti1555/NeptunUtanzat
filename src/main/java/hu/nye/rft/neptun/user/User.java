package hu.nye.rft.neptun.user;

import hu.nye.rft.neptun.database.ListOfUsers;
import hu.nye.rft.neptun.database.SQLServer;

import java.sql.Connection;

public class User extends ListOfUsers{

    private String vnev;
    private String knev;
    private String neptunKod;
    private String jelszo;

    public User(SQLServer server, Connection connection) {
        super(server, connection);
    }

    public String getVnev() {
        return vnev;
    }

    public void setVnev(String vnev) {
        this.vnev = vnev;
    }

    public String getKnev() {
        return knev;
    }

    public void setKnev(String knev) {
        this.knev = knev;
    }

    public String getNeptunKod() {
        return neptunKod;
    }

    public void setNeptunKod(String neptunKod) {
        this.neptunKod = neptunKod;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }
}
