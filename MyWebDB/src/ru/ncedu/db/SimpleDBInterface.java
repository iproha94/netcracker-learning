package ru.ncedu.db;

import ru.ncedu.model.State;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.pool.*;


/**
 * Created by Ilya on 01.07.2015.
 */
public class SimpleDBInterface {

    public static List<State> loadStates(String query) {
        List<State> result = new ArrayList<>(100);

//        String query = "select * from demo_states";
        Connection conn = connectViaDS();

        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)){

            if (rs != null) {
                while (rs.next()) {
                    State state = new State();
                    state.setSt(rs.getString(1));
                    state.setName(rs.getString(2));

                    result.add(state);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<State> loadStates() {
        List<State> result = new ArrayList<>(100);

        String query = "select * from demo_states";
        Connection conn = connectViaDS();

        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(query)){

            if (rs != null) {
                while (rs.next()) {
                    State state = new State();
                    state.setSt(rs.getString(1));
                    state.setName(rs.getString(2));

                    result.add(state);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Connection connectViaDS() {
        Connection cn = null;
        try {
            OracleDataSource ds = new OracleDataSource();
            ds.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            ds.setUser("ADMIN2");
            ds.setPassword("1");
            System.out.println("connectViaDS working");

            cn = ds.getConnection();
            System.out.println(cn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cn;
    }

    private static Connection connect() {
        //Load driver
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("No driver found");
            e.printStackTrace();
            return null;
        }

        System.out.println("Driver loaded!");

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ADMIN2", "1");
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return null;
        }

        System.out.println("Connection created.");

        return conn;
    }

    private static void close(AutoCloseable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (Exception e) {
            System.out.println("Closing FAILED: " + resource);
            e.printStackTrace();
        }
        System.out.println("Closing SUCCESS: " + resource);
    }
}
