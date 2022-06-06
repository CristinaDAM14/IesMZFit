package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDialDelActivities extends JDialog {
    private JPanel mainPanelDel;
    private JPanel searchPanel;
    private JPanel dataPanel;
    private JPanel buttonsPanel;
    private JButton buttonDelete;
    private JButton buttonExit;
    private JTextField textFieldID;
    private JButton buttonSearch;
    private JTextField textFieldName;
    private JTextField textFieldDuration;
    private JTextField textFieldSchedule;
    private JTextField textFieldTurn;
    private JTextField textFieldCalories;
    private JTextField textFieldCapacity;
    private JTextField textFieldDifficulty;

    public JDialDelActivities(){
        setContentPane(mainPanelDel);
        setModal(true);
        setTitle("Delete Activities");
        buttonExit.addActionListener(l ->dispose());
        buttonSearch.addActionListener(listenerSearch());
        buttonDelete.addActionListener(listenerDelete());
    }

    private ActionListener listenerDelete() {
        return l -> {
            Object[] options = new Object[2];
            options[0] = "YES";
            options[1] = "NO";
            // Dialogo de confirmacion para que el usuario decida si quiere borrarlo o no.
            int resp = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the Activity?", "Eliminar."
                    , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null
                    , options, null);
            // Si pulsa si, intentamos eliminarlo, en caso de que no exista saldra un mensaje diciendo que no existe.
            if (resp == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(textFieldID.getText());
                if (DBManager.deleteActivity(id)) {
                    JOptionPane.showMessageDialog(null, "Activity successfully deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong, impossible to delete the activity"
                            , "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private ActionListener listenerSearch() {
        return l->{
            try {
                int id = Integer.parseInt(textFieldID.getText());
                Activity activity = DBManager.getActivity(id);
                if (activity != null) {
                    poblateTextFields(activity);
                }else {
                    JOptionPane.showMessageDialog(null,"There is not an activity matching that id"
                            ,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Introduce a numeric ID"
                        ,"Error",JOptionPane.ERROR_MESSAGE);
            }
        };
    }
    private void poblateTextFields(Activity activity) {
        textFieldName.setText(activity.getName());
        textFieldDuration.setText(String.valueOf(activity.getDuration()));
        textFieldSchedule.setText(activity.getSchedule());
        textFieldDifficulty.setText(activity.getDifficulty());
        textFieldTurn.setText(String.valueOf(activity.getTurn()));
        textFieldCalories.setText(String.valueOf(activity.getCalories()));
        textFieldCapacity.setText(String.valueOf(activity.getCapacity()));
        dataPanel.setVisible(true);
        textFieldID.setEditable(false);
        this.setSize(400,600);
        buttonDelete.setEnabled(true);
    }
}
