package es.iesmz.dam.pro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MostrarMonitor extends JDialog {
    private JPanel seeMonitorsPanel;
    private JScrollPane monitorsScrollPanel;
    private JTable tableMonitors;
    private JButton buttonExit;

    public MostrarMonitor() {
        setContentPane(seeMonitorsPanel);
        setModal(true);
        buttonExit.setText("Exit");
        buttonExit.addActionListener(l -> dispose());
        createTable();
    }
    public void createTable(){
        String id = "ID";
        String name = "NAME";
        String lastName = "LAST NAME";
        String birthDate = "BIRTH DATE";
        String gender = "GENDER";
        Object[] tableNames = {id,name,lastName,birthDate,gender};
        List<Monitor> monitors = DBManager.getMonitorsList();
        Object[][] datosTabla = new Object[monitors.size()][tableNames.length];

        for (int i = 0; i < monitors.size(); i++) {
            datosTabla[i][0] = monitors.get(i).getId();
            datosTabla[i][1] = monitors.get(i).getName();
            datosTabla[i][2] = monitors.get(i).getLastName();
            datosTabla[i][3] = monitors.get(i).getBirthDate();
            datosTabla[i][4] = monitors.get(i).getGender();
        }
        tableMonitors.setModel(new DefaultTableModel(datosTabla,tableNames));
        tableMonitors.setVisible(true);
    }

}
