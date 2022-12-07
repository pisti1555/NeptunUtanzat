package hu.nye.rft.neptun.database;

import hu.nye.rft.neptun.database.SQLServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private final Connection connection;
    private final SQLServer server;

    private String vnev;
    private String knev;
    private String neptunKod;
    private String jelszo;

    public User(Connection connection, SQLServer server) {
        this.connection = connection;
        this.server = server;
    }

    public boolean findUser(String neptunKod) throws SQLException {
        String find = "SELECT NEPTUNKOD FROM USERS";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(find);
        String nk;
        boolean valid = false;
        while (resultSet.next()) {
            nk = resultSet.getString("NEPTUNKOD");
            if (nk.equals(neptunKod)) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public boolean isPwCorrect(String neptunKod, String pw) throws SQLException {
        String find = "SELECT NEPTUNKOD, JELSZO FROM USERS";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(find);
        String nk;
        String pass;
        boolean valid = false;
        while (resultSet.next()) {
            nk = resultSet.getString("NEPTUNKOD");
            pass = resultSet.getString("JELSZO");
            if (nk.equals(neptunKod) && pass.equals(pw)) {
                valid = true;
                break;
            }
        }
        return valid;
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
