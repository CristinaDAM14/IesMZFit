package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tarjetas extends JDialog {
    private JPanel PanelGeneral;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel datosPanel;
    private JTextField NomText;
    private JLabel NomLabel;
    private JPanel PanelPago;
    private JTextField TitularText;
    private JTextField TarjetaText;
    private JTextField CVVText;
    private JSpinner DataText;
    private JButton anyadirB;
    private JLabel ImagenLabel;
    private JLabel TarjetaLabel;
    private JLabel TitularLabel;
    private JLabel CVVLabel;
    private JLabel DataLabel;
    private JPanel PanelBotton;
    private JPanel PanelDatos;
    private JButton buscarButton;
    private JTextField TipoText;
    private JLabel TipoLabel;
    private JTextField caducidadText;


    public Tarjetas() {
        setContentPane(PanelGeneral);
        setModal(true);
        setTitle("Cards");
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
        PanelGeneral.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ImagenLabel.setIcon(new ImageIcon("src\\img\\PagosTarjeta.PNG"));

        DBManager.loadDriver();
        DBManager.connect();


        anyadirB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.insertTarjeta(TarjetaText.getText(),CVVText.getText(),TitularText.getText(),caducidadText.getText(),TipoText.getText());
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTarjetas(NomText.getText());
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

    public static ResultSet getInformacion(String titular) {
        try {
            // Realizamos la consulta SQL
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "Select * from tarjetas WHERE nombre_titular='" + titular + "';";
            //System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            //stmt.close();

            // Si no hay primer registro entonces no existe el alumno
            if (!rs.first()) {
                return null;
            }

            // Todo bien, devolvemos el cliente
            return rs;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }



    public void getTarjetas(String titular) {
        try {
            // Obtenemos la tarjeta

            ResultSet rs = getInformacion(titular);
            if (rs == null || !rs.first()) {
                System.out.println("La tarjeta de " + titular + " NO EXISTE");
                JOptionPane.showMessageDialog(null,"La tarjeta no existe",
                        "Tarjetas", JOptionPane.ERROR_MESSAGE);
                TarjetaText.setText("");
                CVVText.setText("");
                TitularText.setText("");
                TipoText.setText("");
                caducidadText.setText("");
                return;
            }

            // Imprimimos su información por pantalla
            String nombre = rs.getString("nombre_titular");
            String CVV = rs.getString("CVV");
            String numero = rs.getString("numero");
            String tipo = rs.getString("tipo");
            String caducidad = rs.getString("caducidad");

            TarjetaText.setText(numero);
            CVVText.setText(CVV);
            TitularText.setText(nombre);
            TipoText.setText(tipo);
            caducidadText.setText(caducidad);


        } catch (SQLException ex) {
            System.out.println("Error al solicitar la tarjeta de " + titular);
            ex.printStackTrace();
        }
    }

}
