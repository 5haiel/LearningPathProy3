package menuSwingEstudiante;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import caminosActividades.CaminoAprendizaje;
import controllers.Inscriptor;
import traductores.*;

import javax.swing.JComboBox;

public class VentanaActividadesDisponibles extends JFrame implements ActionListener
{
    private JComboBox<String> cbCaminos;
    private JComponentActividadesCaminos lActividadesDelCamino;

    private JPanel pSeleccionActividad;
    private JLabel lNombreActividad;

    private JButton bRegresar;
    private static final String REGRESAR = "regresar";

    private JButton bIniciarActividad;
    private static final String INICIAR = "iniciar";

    private String idEstudiante;
    private String idCaminoSeleccionado;

    public VentanaActividadesDisponibles(String idEstudianteP)
    {
        this.idEstudiante = idEstudianteP;

        this.setLayout(new BorderLayout());

        cbCaminos = new JComboBox<>();
        cargarCaminosDisponibles();
        cbCaminos.addActionListener(e -> actualizarActividadesDelCamino());
        this.add(cbCaminos, BorderLayout.NORTH);

        lActividadesDelCamino = new JComponentActividadesCaminos(this);
        this.add(lActividadesDelCamino, BorderLayout.CENTER);
        lActividadesDelCamino.setAlignmentX(Component.CENTER_ALIGNMENT);

        pSeleccionActividad = new JPanel();
        pSeleccionActividad.setLayout(new BoxLayout(pSeleccionActividad, BoxLayout.Y_AXIS));

        lNombreActividad = new JLabel("");

        JLabel tagLabel = new JLabel("Actividad que deseas iniciar: ");

        JPanel panelNombreActividad = new JPanel();
        panelNombreActividad.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelNombreActividad.add(tagLabel);
        panelNombreActividad.add(lNombreActividad);

        pSeleccionActividad.add(panelNombreActividad);
        panelNombreActividad.setAlignmentX(Component.CENTER_ALIGNMENT);

        bIniciarActividad = new JButton("Iniciar Actividad");
        bIniciarActividad.setActionCommand(INICIAR);
        bIniciarActividad.addActionListener(this);
        pSeleccionActividad.add(bIniciarActividad);
        bIniciarActividad.setAlignmentX(Component.CENTER_ALIGNMENT);

        bRegresar = new JButton("Regresar");
        bRegresar.setActionCommand(REGRESAR);
        bRegresar.addActionListener(this);
        pSeleccionActividad.add(bRegresar);
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(pSeleccionActividad, BorderLayout.SOUTH);

        setTitle("Learning Path System: Seleccionar Actividad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cargarCaminosDisponibles()
    {
        try
        {
        	List<CaminoAprendizaje> caminosEstudiante = TraductorEstudiante.getCaminosEstudiante(idEstudiante);
        	for (CaminoAprendizaje camino : caminosEstudiante)
        	{
        		String IDCamino = camino.getID();
        		HashMap<String, String> caminos = TraductorCamino.verInfoGeneralCamino(IDCamino);
        		cbCaminos.addItem(caminos.get("Titulo"));
        	}
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error al cargar caminos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarActividadesDelCamino()
    {
        try
        {
            String seleccion = (String) cbCaminos.getSelectedItem();
            if (seleccion != null)
            {
            	String idCamino = TraductorCamino.getIDfromNombre(seleccion);
                lActividadesDelCamino.actualizarActividades(idCamino);
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error al cargar actividades: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String comando = e.getActionCommand();

        if (comando.equals(REGRESAR))
        {
            dispose();
        }
        else if (comando.equals(INICIAR))
        {
            String nombreActividad = lNombreActividad.getText();

            if (!nombreActividad.equals(""))
            {
                try
                {
                    Inscriptor.iniciarActivad(nombreActividad, idEstudiante, nombreActividad);
                    JOptionPane.showMessageDialog(this, "Actividad iniciada exitosamente");
                }
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna actividad.");
            }
        }
    }

    public void changeLblActividadSeleccionada(String nombreActividad)
    {
        lNombreActividad.setText(nombreActividad);
    }
}
