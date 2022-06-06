package es.iesmz.dam.pro;

import java.sql.*;
import java.time.LocalDate;

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

    // Conf for Monitors
    private static final String DB_MONITORES = "monitores";
    private static final String DB_MONITOR_SELECT = "Select * from "+ DB_MONITORES;
    private static final String DB_MONITOR_CODIGO ="codigo_monitores";
    private static final String DB_MONITOR_NOMBRE ="Nombre";
    private static final String DB_MONITOR_APELLIDOS ="apellidos";
    private static final String DB_MONITOR_NACIMIENTO ="Fecha_nac";
    private static final String DB_MONITOR_GENERO ="Genero";

    // Conf for Users
    private static final String DB_USUARIOS = "usuarios";
    private static final String DB_USUARIOS_SELECT = "Select * from "+ DB_USUARIOS;
    private static final String DB_USUARIOS_CODIGO ="codigo_usuarios";
    private static final String DB_USUARIOS_DNI ="DNI";
    private static final String DB_USUARIOS_NOMBRE ="nombre";
    private static final String DB_USUARIOS_APELLIDOS ="apellidos";
    private static final String DB_USUARIOS_TELEFONO ="telefono";
    private static final String DB_USUARIOS_METODOPAGO ="metodo_pago";
    private static final String DB_USUARIOS_CORREO ="correo";
    private static final String DB_USUARIOS_CODIGOSUSCRIPCION ="codigo_suscripcion";




    // Loading the jdbc driver
    public static boolean loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    // Metodo insertar monitor

    public static ResultSet getTablaMonitores(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_MONITOR_SELECT);
            //stmt.close();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static boolean insertMonitor(String nombre, String apellidos, LocalDate fechaNacimiento, String genero){

        try {
            // Obtenemos la tabla monitores
            System.out.print("Insertando monitor " + nombre + "...");
            ResultSet rs = getTablaMonitores(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            Statement smtm = conn.createStatement();
            ResultSet rst = smtm.executeQuery("SELECT MAX(codigo_monitores) FROM monitores");


            rs.moveToInsertRow();
            if (rst.next()){
                int codigo = (rst.getInt(1)+1);
                rs.updateInt(DB_MONITOR_CODIGO, codigo);
            }
            rs.updateString(DB_MONITOR_NOMBRE, nombre);
            rs.updateString(DB_MONITOR_APELLIDOS, apellidos);
            rs.updateDate(DB_MONITOR_NACIMIENTO, Date.valueOf(fechaNacimiento));
            rs.updateString(DB_MONITOR_GENERO, genero);
            rs.insertRow();

            // Todo bien, cerramos ResultSet y devolvemos true
            rs.close();
            rst.close();
            System.out.println("OK!");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public static ResultSet getTablaUsuarios(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_USUARIOS_SELECT);
            //stmt.close();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static boolean insertUsuario(String dni, String nombre, String apellidos, String telefono, String correo, String metodo){

        try {
            // Obtenemos la tabla usuarios
            System.out.print("Insertando usuario " + nombre + "...");
            ResultSet rs = getTablaUsuarios(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            Statement smtm = conn.createStatement();
            ResultSet rst = smtm.executeQuery("SELECT MAX(codigo_usuarios) FROM usuarios");

            rs.moveToInsertRow();
            if (rst.next()){
                int codigo = (rst.getInt(1)+1);
                rs.updateInt(DB_USUARIOS_CODIGO, codigo);
            }
            rs.updateString(DB_USUARIOS_DNI, dni);
            rs.updateString(DB_USUARIOS_NOMBRE, nombre);
            rs.updateString(DB_USUARIOS_APELLIDOS, apellidos);
            rs.updateString(DB_USUARIOS_TELEFONO, telefono);
            rs.updateString(DB_USUARIOS_CORREO, correo);
            rs.updateString(DB_USUARIOS_METODOPAGO, metodo);
            rs.updateInt(DB_USUARIOS_CODIGOSUSCRIPCION, 210);
            rs.insertRow();

            // Todo bien, cerramos ResultSet y devolvemos true
            rs.close();
            System.out.println("OK!");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }


}
