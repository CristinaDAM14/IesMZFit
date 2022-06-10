package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;

public class UsuarioActividades extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox Actividades;
    private JTextField CaloriasText;
    private JTextArea DescripcionText;
    private JLabel ImagenHorario;
    private JButton ActuBotton;

    public UsuarioActividades() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("User Activities");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ImagenHorario.setIcon(new ImageIcon("src\\img\\MarcosFit.PNG"));

        ActuBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Actividades.getSelectedItem().toString().equals("Aerobic")){
                    DescripcionText.setText("El aeróbic es un tipo de deporte realizado al son de la música. \n" +
                            "Esta actividad dirigida trabaja la fuerza, flexibilidad,\n" +
                            "resistencia y coordinación a través de coreografías de \n" +
                            "diversos tipos y niveles.");
                    CaloriasText.setText("116 calorías");
                    ImagenHorario.setIcon(new ImageIcon("src\\img\\Aerobic.PNG"));
                }
                else if(Actividades.getSelectedItem().toString().equals("Body balance")){
                    DescripcionText.setText("El body balance es un programa de ejercicio que combina yoga,\n" +
                            "estiramientos, pilates y taichi para entrenar de forma suave \n" +
                            "pero efectiva, relajarse y equilibrar cuerpo y mente.");
                    CaloriasText.setText("400 calorías");
                    ImagenHorario.setIcon(new ImageIcon("src\\img\\balance.PNG"));
                }
                else if(Actividades.getSelectedItem().toString().equals("Body combat")){
                    DescripcionText.setText("El body combat es un programa de ejercicios cardiovasculares \n" +
                            "que se realiza en grupo y consiste en realizar movimientos de \n" +
                            "diferentes artes marciales como el taekwondo, karate, capoeira, \n" +
                            "muay thay (boxeo tailandés), tai chi o boxeo, entre otros.");
                    CaloriasText.setText("514 calorías");
                    ImagenHorario.setIcon(new ImageIcon("src\\img\\combat.PNG"));
                }
                else if(Actividades.getSelectedItem().toString().equals("Body pump")){
                    DescripcionText.setText("El Body Pump es un programa de entrenamiento físico\n" +
                            "intenso que combina actividad aeróbica y trabajo muscular\n" +
                            "mediante el levantamiento de pesas al ritmo de la música.\n" +
                            "Son sesiones dirigidas, divertidas y motivadoras, en las que\n" +
                            "se fortalece el sistema cardiovascular y la gran mayoría de\n" +
                            "los músculos del cuerpo.");
                    CaloriasText.setText("411 calorías");
                    ImagenHorario.setIcon(new ImageIcon("src\\img\\pump.PNG"));
                }
                else if(Actividades.getSelectedItem().toString().equals("Crossfit")){
                    DescripcionText.setText("CrossFit se define como un sistema de\n" +
                            "entrenamiento de fuerza y acondicionamiento basado en\n" +
                            "ejercicios funcionales constantemente variados\n" +
                            "realizados a una alta intensidad.");
                    CaloriasText.setText("800 calorías");
                    ImagenHorario.setIcon(new ImageIcon("src\\img\\crossfit.PNG"));
                }
                else if(Actividades.getSelectedItem().toString().equals("Pilates")){
                    DescripcionText.setText("Pilates es un método de entrenamiento mental\n" +
                            "y físico. Siendo una mezcla de gimnasia y yoga, se consigue\n" +
                            "tonificar los músculos, además de ganar equilibrio, elasticidad y fuerza.");
                    CaloriasText.setText("230 calorías");
                    ImagenHorario.setIcon(new ImageIcon("src\\img\\Pilates.PNG"));
                }
                else if(Actividades.getSelectedItem().toString().equals("Yoga")){
                    DescripcionText.setText("El yoga es una práctica que conecta el cuerpo,\n" +
                            "la respiración y la mente. Esta práctica utiliza posturas físicas,\n" +
                            "ejercicios de respiración y meditación para mejorar la salud general.");
                    CaloriasText.setText("180 calorías");
                    ImagenHorario.setIcon(new ImageIcon("src\\img\\yoga.PNG"));
                }
                else if(Actividades.getSelectedItem().toString().equals("Zumba")){
                    DescripcionText.setText("Zumba es una disciplina deportiva que se\n" +
                            "imparte en clases dirigidas en la que se realizan ejercicios\n" +
                            "aeróbicos al ritmo de música latina (merengue, samba, reggaeton,\n" +
                            "cumbia y salsa) con la finalidad de perder peso de forma divertida y\n" +
                            "mejorar el estado de ánimo de los deportistas.");
                    CaloriasText.setText("450 calorías");
                    ImagenHorario.setIcon(new ImageIcon("src\\img\\zumba.PNG"));
                }
                else{
                    DescripcionText.setText("");
                    CaloriasText.setText("");
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
