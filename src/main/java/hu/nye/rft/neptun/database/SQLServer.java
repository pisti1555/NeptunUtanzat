package hu.nye.rft.neptun.database;

import java.sql.*;

public class SQLServer {
    public Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/neptun", "sa", "pass");

    public SQLServer() throws SQLException {
    }

    public void createDatabaseIfNotExists() throws SQLException {
        String command = "CREATE TABLE IF NOT EXISTS USERS " +
                "(VNEV VARCHAR(40), " +
                "KNEV VARCHAR(40), " +
                "NEPTUNKOD VARCHAR(10) PRIMARY KEY, " +
                "JELSZO VARCHAR(20))";

        Statement st = connection.createStatement();
        st.executeUpdate(command);
    }

    public void createTantargyakIfNotExists() throws SQLException {
        String command = "CREATE TABLE IF NOT EXISTS TARGYAK " +
                "(TARGYNEV VARCHAR(20) PRIMARY KEY, ELOADONEV VARCHAR (40), IDOPONT VARCHAR(10), SZABADHELYEK INTEGER)";
        Statement st = connection.createStatement();
        st.executeUpdate(command);
    }

    public void insertUser(String vnev, String knev, String neptunKod, String jelszo) throws SQLException {
        String insert = "INSERT INTO USERS (VNEV, KNEV, NEPTUNKOD, JELSZO) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(insert);
        pst.setString(1, vnev);
        pst.setString(2, knev);
        pst.setString(3, neptunKod);
        pst.setString(4, jelszo);
        pst.executeUpdate();
    }

    public void insertTantargy(String targy, String eloado, String idopont, int helyek) throws SQLException {
        String insert = "INSERT INTO TARGYAK (TARGYNEV, ELOADONEV, IDOPONT, SZABADHELYEK) VALUES(?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(insert);
        pst.setString(1, targy);
        pst.setString(2, eloado);
        pst.setString(3, idopont);
        pst.setInt(4, helyek);
        pst.executeUpdate();
    }

    public boolean letezikEIlyenTargy(String targy) throws SQLException {
        String find = "SELECT TARGYNEV FROM TARGYAK";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(find);
        String nevv;
        int i = 0;
        while (resultSet.next()) {
            nevv = resultSet.getString("TARGYNEV");
            if (nevv.equals(targy)) {
                i++;
            }
        }
        return i != 0;
    }

    public String miAVNeve(String neptunKod) throws SQLException {
        String find = "SELECT * FROM USERS";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(find);
        String toReturn = "Üres";
        while (resultSet.next()) {
            String vnev = resultSet.getString("VNEV");
            String knev = resultSet.getString("KNEV");
            String neptun = resultSet.getString("NEPTUNKOD");
            String pw = resultSet.getString("JELSZO");
            if (neptun.equals(neptunKod)) {
                toReturn = vnev;
            }
        }
        return toReturn;
    }

    public String miAKNeve(String neptunKod) throws SQLException {
        String find = "SELECT * FROM USERS";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(find);
        String toReturn = "Üres";
        while (resultSet.next()) {
            String vnev = resultSet.getString("VNEV");
            String knev = resultSet.getString("KNEV");
            String neptun = resultSet.getString("NEPTUNKOD");
            String pw = resultSet.getString("JELSZO");
            if (neptun.equals(neptunKod)) {
                toReturn = knev;
            }
        }
        return toReturn;
    }

    public void listazas() throws SQLException {
        System.out.println("Tárgyak listája");
        System.out.println("Tárgynév | Előadó neve | Időpont | Szabad helyek");
        Statement st = connection.createStatement();
        String command = "SELECT * FROM TARGYAK";
        ResultSet rs = st.executeQuery(command);
        int i=1;
        while(rs.next()) {
            String targy = rs.getString("TARGYNEV");
            String eloado = rs.getString("ELOADONEV");
            String idopont = rs.getString("IDOPONT");
            int helyek = rs.getInt("SZABADHELYEK");
            System.out.println(i+". "+targy+" | "+eloado+" | "+idopont+" | "+helyek);
            i++;
        }
    }

    public void targyFelvetel(String targy) throws SQLException {
        String command = "SELECT * FROM TARGYAK";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(command);
        int van = 0;
        while(rs.next()) {
            String ttargy = rs.getString("TARGYNEV");
            if(ttargy.equals(targy)) {
                van++;
            }
            String eloado = rs.getString("ELOADONEV");
            String idopont = rs.getString("IDOPONT");
            int helyek = rs.getInt("SZABADHELYEK");
            if(van == 0) {
                System.out.println("Nincs ilyen tárgy");
                break;
            }
            if(ttargy.equals(targy)) {
                if(helyek>0) {
                    System.out.println("Tantárgy felvéve");
                    String comm = "UPDATE TARGYAK SET SZABADHELYEK = "+(helyek-1)+" WHERE TARGYNEV = '"+targy+"'";
                    st.executeUpdate(comm);
                    break;
                } else {
                    System.out.println("Nincs több szabad hely");
                    break;
                }
            }
        }
    }
}