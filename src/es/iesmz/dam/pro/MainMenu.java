package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class MainMenu extends JFrame {


    private JButton buttonSignIn;
    private JPanel loginPanel;
    private JButton buttonSignUp;
    private JTextField textFieldUserLogin;
    private JPasswordField passwordFieldLogin;
    private JLabel userLabel;
    private JLabel passwdLabel;

    public MainMenu(){
        super("Marcos Zaragoza Fit");
        setContentPane(loginPanel);
        setText();
        buttonSignUp.addActionListener(listenerSignUp());
        buttonSignIn.addActionListener(listenerSignIn());
    }

    private ActionListener listenerSignUp() {
        return l ->{

        };
    }
    /* Listener for login, checks if textfields username and password are not empty/null
       then call the DBManager to check if there's a username matching that password in the database
       if there's a user matching that username and password then we call a menu depending of his userlevel
       1 -> Administrator
       2 -> Monitor
       3 -> User
     */
    private ActionListener listenerSignIn() {
        return l -> {
            String userName = textFieldUserLogin.getText();
            char[] passwordChars = passwordFieldLogin.getPassword();
            if (userName != null && passwordChars.length != 0){
                String userPassword = String.valueOf(passwordChars);
                int userLevel = DBManager.getLoginLvl(userName,userPassword);
                switch (userLevel){
                    case 1 -> menuAdmin();
                    //case 2 -> menuMonitor();
                    case 3 -> menuUser(userName,userPassword);
                    default -> JOptionPane.showMessageDialog(null,"Username or Password not matching"
                            ,"Error: Login failed",JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }


    private void menuUser(String user, String password) {
        JDialog userMenu = new JDialUserMenu(user, password);
        userMenu.setSize(300,300);
        userMenu.setVisible(true);
    }
    /*
    private void menuMonitor() {
        JDialog monitorMenu = new JDialMonitorMenu();
        monitorMenu.setSize(300,300);
        monitorMenu.setVisible(true);
    }
     */

    private void menuAdmin() {
        JDialog adminMenu = new JDialAdminMenu();
        adminMenu.setSize(300,300);
        adminMenu.setVisible(true);
    }

    private void setText() {
        buttonSignIn.setText("Sign in");
        buttonSignUp.setText("Sign up");
        userLabel.setText("User");
        passwdLabel.setText("Password");
    }



    public static void main(String[] args) {
        if (DBManager.loadDriver() && DBManager.connect()) {
            SwingUtilities.invokeLater(() -> {
                JFrame mainMenu = new MainMenu();
                mainMenu.setSize(300, 400);
                mainMenu.setVisible(true);


                // Method to close db connection when closing the window
                mainMenu.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                mainMenu.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        DBManager.close();
                        mainMenu.dispose();
                        System.exit(0);
                    }
                });
            });
        }
    }
}
