package menuSwing;

import java.awt.BorderLayout;
import java.util.Collection;
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
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import menuSwingEstudiante.VentanaCaminosDispInscripcion;
import traductores.TraductorCamino;

public class JComponentCaminosDisponibles extends JPanel implements ListSelectionListener
{

    private JList<String> listaDeCaminos;
    
    private DefaultListModel<String> dataModel;

    private JFrame ventana;
    
    private List<String> listStringCaminos;

    public JComponentCaminosDisponibles( JFrame ventanaP)
    {
    	this.ventana=ventanaP;
    	
        setBorder( new TitledBorder( "Caminos Disponibles" ) );
        setLayout( new BorderLayout( ) );

        // Crea la lista con un modelo de datos 
        dataModel = new DefaultListModel<>( );
        dataModel.addAll( crearListStringCaminos() );
        
        listaDeCaminos = new JList<>( dataModel );
        listaDeCaminos.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaDeCaminos.addListSelectionListener( this );

        // Crear un panel con barras de desplazamiento para la lista
        JScrollPane scroll = new JScrollPane( listaDeCaminos );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        add( scroll );
    }
    
	private List<String> crearListStringCaminos() 
	{
		List<String> listStringCaminos= new LinkedList<String>();
		
		try
		{
			HashMap<String, String> caminos = TraductorCamino.verTodosCaminos();
			for (HashMap.Entry<String, String> camino : caminos.entrySet())
			{
				listStringCaminos.add("Titulo: " + camino.getKey() + " ; Creador: " + camino.getValue()+"\n");
			}
		}
		
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return listStringCaminos;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		if (ventana instanceof VentanaCaminosDispInscripcion)
		{
			String[] divLinea=listaDeCaminos.getSelectedValue().split(" ;");
			String[] divPuntos=divLinea[0].split(": ");

			String titulo=divPuntos[1];
			((VentanaCaminosDispInscripcion) ventana).changeLblCaminoSeleccionado(titulo);
		}
		
	}


}
