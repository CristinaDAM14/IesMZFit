package es.iesmz.dam.pro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Conf for Activities
    private static final String DB_ACTIVIDADES = "Actividades";
    private static final String DB_ACTIVIDADES_SELECT = "Select * from "+ DB_ACTIVIDADES;
    private static final String DB_ACT_ID = "codigo_actividades";
    private static final String DB_ACT_NOMBRE = "nombre";
    private static final String DB_ACT_DURACION = "duracion";
    private static final String DB_ACT_HORARIO = "horario";
    private static final String DB_ACT_TURNO = "turno";
    private static final String DB_ACT_CALORIAS = "cant_calorias";
    private static final String DB_ACT_AFORO = "aforo";
    private static final String DB_ACT_DIFICULAD = "dificultad";




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
    public static ResultSet getActivities(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            return stmt.executeQuery(DB_ACTIVIDADES_SELECT);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<Activity> getActivitiesList(){
        List<Activity> activities = new ArrayList<>();
        try {
            ResultSet rs = getActivities(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int id = rs.getInt(DB_ACT_ID);
                String nombre = rs.getString(DB_ACT_NOMBRE);
                int duracion = rs.getInt(DB_ACT_DURACION);
                String horario = rs.getString(DB_ACT_HORARIO);
                int turno = rs.getInt(DB_ACT_TURNO);
                int calorias = rs.getInt(DB_ACT_CALORIAS);
                int aforo = rs.getInt(DB_ACT_AFORO);
                String dificultad = rs.getString(DB_ACT_DIFICULAD);
                activities.add(new Activity(id,nombre,duracion,horario,turno,calorias,aforo,dificultad));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    public static boolean addActivity(Activity activity) {
        try {
            // Obtenemos la tabla
            ResultSet rs = getActivities(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            // Insertamos el nuevo registro
            rs.moveToInsertRow();
            rs.updateString(DB_ACT_NOMBRE, activity.getName());
            rs.updateInt(DB_ACT_DURACION, activity.getDuration());
            rs.updateString(DB_ACT_HORARIO,activity.getSchedule());
            rs.updateInt(DB_ACT_TURNO, activity.getTurn());
            rs.updateInt(DB_ACT_CALORIAS, activity.getCalories());
            rs.updateInt(DB_ACT_AFORO,activity.getCapacity());
            rs.updateString(DB_ACT_DIFICULAD, activity.getDifficulty());
            rs.insertRow();

            // Todo bien, cerramos ResultSet y devolvemos true
            rs.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }


    }
}
