package es.iesmz.dam.pro;

import javax.swing.*;
import java.sql.*;

public class DBManager {
    private static Connection conn = null;
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "marcosfit";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";


    // Conf for logins
    private static final String DB_LOGIN = "Login";
    private static final String DB_LOGIN_SELECT = "Select * from "+ DB_LOGIN;
    private static final String DB_LOGIN_USR = "Username";
    private static final String DB_LOGIN_PSWD = "Password";
    private static final String DB_LOGIN_USRLVL = "UserLevel";

    // Configuracion tarjetas
    private static final String DB_TARJETAS = "Tarjetas";
    private static final String DB_TARJETAS_SELECT = "Select * from "+ DB_TARJETAS;
    private static final String DB_TARJETAS_NUM = "numero";
    private static final String DB_TARJETAS_CVV = "CVV";
    private static final String DB_TARJETAS_NOM = "nombre_titular";
    private static final String DB_TARJETAS_CAD = "caducidad";
    private static final String DB_TARJETAS_TIPO = "tipo";




    // Loading the jdbc driver
    public static boolean loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("OK!");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Conecting with the db
    public static boolean connect() {
        try {
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // closing the connection with the db.
    public static void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // Methods for login table

    // Method that returns the user lvl for an specific username + password, returns -1 if it doesn't exists
    public static int getLoginLvl(String userName, String password){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_LOGIN_SELECT + " WHERE " + DB_LOGIN_USR + "='" + userName +"' AND " + DB_LOGIN_PSWD + "='"+ password + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // if there's not records, we return -1
            if (!rs.first()) {
                return -1;
            }
            int userLvl = rs.getInt(DB_LOGIN_USRLVL);
            rs.close();
            return userLvl;


        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    //BASE DE DATOS DE TARJETAS
    public static ResultSet getTablaTarjetas(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_TARJETAS_SELECT);
            //stmt.close();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static boolean insertTarjeta(String numero, String CVV, String nombre, String caducidad, String tipo) {
        try {
            // Obtenemos la tabla tarjetas
            System.out.print("Insertando tarjeta de " + nombre + "...");
            ResultSet rs = getTablaTarjetas(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            // Insertamos el nuevo registro
            rs.moveToInsertRow();
            rs.updateString(DB_TARJETAS_NUM, numero);
            rs.updateString(DB_TARJETAS_CVV, CVV);
            rs.updateString(DB_TARJETAS_NOM, nombre);
            rs.updateString(DB_TARJETAS_CAD, caducidad);
            rs.updateString(DB_TARJETAS_TIPO, tipo);
            rs.insertRow();

            // Todo bien, cerramos ResultSet y devolvemos true
            rs.close();
            System.out.println("¡Tarjeta guardada!");

            return true;

        } catch (SQLException ex) {
            return false;
        }
    }
}
