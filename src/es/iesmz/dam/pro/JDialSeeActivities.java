package es.iesmz.dam.pro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class JDialSeeActivities extends JDialog {
    private JPanel seeActivitiesPanel;
    private JScrollPane activitiesScrollPanel;
    private JTable tableActivities;
    private JButton buttonExit;

    public JDialSeeActivities() {
        setContentPane(seeActivitiesPanel);
        setModal(true);
        setTitle("See Activities");
        buttonExit.setText("Exit");
        buttonExit.addActionListener(l -> dispose());
        createTable();
    }
    public void createTable(){
        String id = "ID";
        String name = "NAME";
        String duration = "DURATION";
        String schedule = "SCHEDULE";
        String turn = "TURN";
        String calories = "CALORIES";
        String capacity = "CAPACITY";
        String difficulty = "DIFFICULTY";
        Object[] tableNames = {id,name,duration,schedule,turn,calories,capacity,difficulty};
        List<Activity> activities = DBManager.getActivitiesList();
        Object[][] datosTabla = new Object[activities.size()][tableNames.length];

        for (int i = 0; i < activities.size(); i++) {
            datosTabla[i][0] = activities.get(i).getId();
            datosTabla[i][1] = activities.get(i).getName();
            datosTabla[i][2] = activities.get(i).getDuration();
            datosTabla[i][3] = activities.get(i).getSchedule();
            datosTabla[i][4] = activities.get(i).getTurn();
            datosTabla[i][5] = activities.get(i).getCalories();
            datosTabla[i][6] = activities.get(i).getCapacity();
            datosTabla[i][7] = activities.get(i).getDifficulty();
        }
        tableActivities.setModel(new DefaultTableModel(datosTabla,tableNames));
        tableActivities.setVisible(true);
    }

}
