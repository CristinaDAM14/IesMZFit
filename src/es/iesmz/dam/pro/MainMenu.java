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

    private ActionListener listenerSignIn() {
        return l -> {
            String userName = textFieldUserLogin.getText();
            char[] passwordChars = passwordFieldLogin.getPassword();
            if (userName != null && userName.matches("^[0-9]*$") && passwordChars.length != 0){
                String userPassword = Arrays.toString(passwordChars);

            }
        };
    }

    private void setText() {
        buttonSignIn.setText("Sign in");
        buttonSignUp.setText("Sign up");
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
