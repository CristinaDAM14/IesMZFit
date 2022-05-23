package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {


    private JButton buttonSignIn;
    private JPanel loginPanel;
    private JButton buttonSingUp;
    private JTextField textFieldUserLogin;
    private JPasswordField passwordFieldLogin;
    private JLabel userLabel;
    private JLabel passwdLabel;

    public MainMenu(){
        super("Marcos Zaragoza Fit");
        setContentPane(loginPanel);
        setText();
    }

    private void setText() {
        buttonSignIn.setText("Sign in");
        buttonSingUp.setText("Sign up");
        userLabel.setText("User");
        passwdLabel.setText("Password");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame mainMenu = new MainMenu();
            mainMenu.setSize(300,400);
            mainMenu.setVisible(true);

            // Method yo close db connection when closing the window
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
