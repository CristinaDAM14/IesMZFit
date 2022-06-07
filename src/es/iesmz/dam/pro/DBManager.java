package es.iesmz.dam.pro;

import java.sql.*;
import java.time.LocalDate;
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
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static boolean insertMonitor(Monitor monitor){

        try {
            // Obtenemos la tabla monitores
            ResultSet rs = getTablaMonitores(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            Statement smtm = conn.createStatement();
            ResultSet rst = smtm.executeQuery("SELECT MAX(codigo_monitores) FROM monitores");


            rs.moveToInsertRow();
            if (rst.next()){
                int codigo = (rst.getInt(1)+1);
                rs.updateInt(DB_MONITOR_CODIGO, codigo);
            }
            rs.updateString(DB_MONITOR_NOMBRE, monitor.getName());
            rs.updateString(DB_MONITOR_APELLIDOS, monitor.getLastName());
            rs.updateDate(DB_MONITOR_NACIMIENTO, monitor.getBirthDate());
            rs.updateString(DB_MONITOR_GENERO, monitor.getGender());
            rs.insertRow();

            // Todo bien, cerramos ResultSet y devolvemos true
            rs.close();
            rst.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }
    public static ResultSet getMonitorST(int id) {
        try {
            // Realizamos la consulta SQL
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_MONITOR_SELECT + " WHERE " + DB_MONITOR_CODIGO + "='" + id + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // Si no hay primer registro entonces no existe el monitor
            if (!rs.first()) {
                return null;
            }

            // si existe devolvemos el resulset con la actividad.
            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // method that gets and ID and returns the Activity matching that ID
    public static Monitor getMonitor(int id) {
        Monitor monitor = null;
        try {
            ResultSet rs = getMonitorST(id);
            if (rs == null){
                return monitor;
            }
            String name = rs.getString(DB_MONITOR_NOMBRE);
            String lastName = rs.getString(DB_MONITOR_NOMBRE);
            LocalDate birthDate =rs.getDate(DB_MONITOR_NACIMIENTO).toLocalDate();
            String gender = rs.getString(DB_MONITOR_GENERO);
            monitor = new Monitor(id,name,lastName,birthDate,gender);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monitor;
    }

    public static List<Monitor> getMonitorsList(){
        List<Monitor> monitors = new ArrayList<>();
        try {
            ResultSet rs = getTablaMonitores(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int id = rs.getInt(DB_MONITOR_CODIGO);
                String name = rs.getString(DB_MONITOR_NOMBRE);
                String lastName = rs.getString(DB_MONITOR_NOMBRE);
                LocalDate birthDate =rs.getDate(DB_MONITOR_NACIMIENTO).toLocalDate();
                String gender = rs.getString(DB_MONITOR_GENERO);
                monitors.add( new Monitor(id,name,lastName,birthDate,gender));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monitors;
    }

    public static ResultSet getTablaUsuarios(int resultSetType, int resultSetConcurrency) {
        try {
            Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
            ResultSet rs = stmt.executeQuery(DB_USUARIOS_SELECT);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static List<User> getUsersList(){
        List<User> users = new ArrayList<>();
        try {
            ResultSet rs = getTablaMonitores(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            while (rs.next()) {
                int id = rs.getInt(DB_USUARIOS_CODIGO);
                String dni = rs.getString(DB_USUARIOS_DNI);
                String name = rs.getString(DB_USUARIOS_NOMBRE);
                String lastName = rs.getString(DB_USUARIOS_APELLIDOS);
                String phone =rs.getString(DB_USUARIOS_TELEFONO);
                String email = rs.getString(DB_USUARIOS_CORREO);
                String paymentMethod = rs.getString(DB_USUARIOS_METODOPAGO);
                int subID = rs.getInt(DB_USUARIOS_CODIGOSUSCRIPCION);
                users.add(new User(id,dni,name,lastName,phone,email,paymentMethod,subID));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static boolean insertUsuario(User user){

        try {
            // Obtenemos la tabla usuarios
            ResultSet rs = getTablaUsuarios(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            Statement smtm = conn.createStatement();
            ResultSet rst = smtm.executeQuery("SELECT MAX(codigo_usuarios) FROM usuarios");

            rs.moveToInsertRow();
            if (rst.next()){
                int codigo = (rst.getInt(1)+1);
                rs.updateInt(DB_USUARIOS_CODIGO, codigo);
            }
            rs.updateString(DB_USUARIOS_DNI, user.getDni());
            rs.updateString(DB_USUARIOS_NOMBRE, user.getName());
            rs.updateString(DB_USUARIOS_APELLIDOS, user.getLastName());
            rs.updateString(DB_USUARIOS_TELEFONO, user.getPhone());
            rs.updateString(DB_USUARIOS_CORREO, user.getEmail());
            rs.updateString(DB_USUARIOS_METODOPAGO, user.getPaymentMethod());
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
    // Methods for Activities

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

            // bien, cerramos ResultSet y devolvemos true
            rs.close();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }


    }

    // method that gets and ID and returns a resultset with the query results of that id
    public static ResultSet getActivityST(int id) {
        try {
            // Realizamos la consulta SQL
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = DB_ACTIVIDADES_SELECT + " WHERE " + DB_ACT_ID + "='" + id + "';";
            ResultSet rs = stmt.executeQuery(sql);

            // Si no hay primer registro entonces no existe la actividad
            if (!rs.first()) {
                return null;
            }

            // si existe devolvemos el resulset con la actividad.
            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // method that gets and ID and returns the Activity matching that ID
    public static Activity getActivity(int id) {
        Activity activity = null;
        try {
            ResultSet rs = getActivityST(id);
            if (rs == null){
                return activity;
            }
            String nombre = rs.getString(DB_ACT_NOMBRE);
            int duracion = rs.getInt(DB_ACT_DURACION);
            String horario = rs.getString(DB_ACT_HORARIO);
            int turno = rs.getInt(DB_ACT_TURNO);
            int calorias = rs.getInt(DB_ACT_CALORIAS);
            int aforo = rs.getInt(DB_ACT_AFORO);
            String dificultad = rs.getString(DB_ACT_DIFICULAD);
            activity = new Activity(id,nombre,duracion,horario,turno,calorias,aforo,dificultad);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }

    // Method that gets and ID and deletes the Activity matching that id
    public static boolean deleteActivity(int id) {
        try {

            // Obtenemos la actividad
            ResultSet rs = getActivityST(id);

            // Si existe y tiene primer registro, lo eliminamos
            if (rs.first()) {
                rs.deleteRow();
                rs.close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean updateActivity(Activity activity) {
        try {
            // Obtenemos la actividad
            ResultSet rs = getActivityST(activity.getId());

            // Si no existe el Resultset
            if (rs == null) {
                return false;
            }
            // Si tiene un primer registro, lo actualizamos.
            if (rs.first()) {
                rs.updateString(DB_ACT_NOMBRE, activity.getName());
                rs.updateInt(DB_ACT_DURACION, activity.getDuration());
                rs.updateString(DB_ACT_HORARIO,activity.getSchedule());
                rs.updateInt(DB_ACT_TURNO, activity.getTurn());
                rs.updateInt(DB_ACT_CALORIAS, activity.getCalories());
                rs.updateInt(DB_ACT_AFORO,activity.getCapacity());
                rs.updateString(DB_ACT_DIFICULAD, activity.getDifficulty());
                rs.updateRow();
                rs.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    // Methods for Monitors
    public static boolean updateMonitor(Monitor monitor) {
        try {
            // Obtenemos el monitor
            ResultSet rs = getMonitorST(monitor.getId());

            // Si no existe el Resultset
            if (rs == null) {
                return false;
            }
            // Si tiene un primer registro, lo actualizamos.
            if (rs.first()) {
                rs.updateString(DB_MONITOR_NOMBRE, monitor.getName());
                rs.updateString(DB_MONITOR_APELLIDOS,monitor.getLastName());
                rs.updateDate(DB_MONITOR_NACIMIENTO, monitor.getBirthDate());
                rs.updateString(DB_MONITOR_GENERO, monitor.getGender());
                rs.updateRow();
                rs.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    // Method that gets and ID and deletes the Monitor matching that id
    public static boolean deleteMonitor(int id) {
        try {

            // Obtenemos el Monitor
            ResultSet rs = getMonitorST(id);

            // Si existe y tiene primer registro, lo eliminamos
            if (rs.first()) {
                rs.deleteRow();
                rs.close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
