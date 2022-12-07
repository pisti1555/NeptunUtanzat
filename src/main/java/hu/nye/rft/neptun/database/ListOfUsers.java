package hu.nye.rft.neptun.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListOfUsers {
    SQLServer server;
    Connection connection;

    public ListOfUsers(SQLServer server, Connection connection) {
        this.server = server;
        this.connection = connection;
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
}