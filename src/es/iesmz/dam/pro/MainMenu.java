package es.iesmz.dam.pro;

import javax.swing.*;

public class MainMenu extends JFrame {


    private JButton button1;
    private JPanel panel1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame mainMenu = new MainMenu();
            mainMenu.setSize(300,400);
            mainMenu.setVisible(true);
        });
    }
}
