package es.iesmz.dam.pro;

import javax.swing.*;

public class Suscripciones extends JFrame{
    private JPanel panelGeneral;
    private JPanel panelBronze;
    private JPanel panelSilver;
    private JPanel panelGold;
    private JPanel panelPlatinum;
    private JLabel titleBronze;
    private JLabel titleSilver;
    private JLabel titleGold;
    private JLabel titlePlatinum;
    private JRadioButton bronzeButton;
    private JRadioButton silverButton;
    private JRadioButton goldButton;
    private JRadioButton platinumButton;

    public Suscripciones(){
        super("Suscripciones");
        setContentPane(panelGeneral);

    }
}
