package menuSwingEstudiante;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import traductores.TraductorCamino;

public class JComponentActividadesCaminos extends JPanel implements ListSelectionListener
{
    private JList<String> listaDeCaminosActividades;
    private DefaultListModel<String> dataModel;
    private JFrame ventana;

    public JComponentActividadesCaminos(JFrame ventanaP)
    {
        this.ventana = ventanaP;

        setBorder(new TitledBorder("Actividades del camino"));
        setLayout(new BorderLayout());

        dataModel = new DefaultListModel<>();
        listaDeCaminosActividades = new JList<>(dataModel);
        listaDeCaminosActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaDeCaminosActividades.addListSelectionListener(this);

        JScrollPane scroll = new JScrollPane(listaDeCaminosActividades);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll, BorderLayout.CENTER);
    }

    public void actualizarActividades(String idCamino) {
        try {
            dataModel.clear();

            List<String[]> actividades = TraductorCamino.verActividadesCamino(idCamino);

            for (String[] actividad : actividades) {
                String nombreActividad = actividad[1];
                dataModel.addElement("Nombre de la actividad: " + nombreActividad);
            }

            if (actividades.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay actividades disponibles para este camino.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar actividades: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedValue = listaDeCaminosActividades.getSelectedValue();
            if (selectedValue != null && ventana instanceof VentanaActividadesDisponibles) {
                ((VentanaActividadesDisponibles) ventana).changeLblActividadSeleccionada(selectedValue);
            }
        }
    }
}
