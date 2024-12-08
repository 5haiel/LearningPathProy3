package menuSwingEstudianteEnvios;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import traductores.TraductorActividad;
import usuarios.Profesor;

public class PanelActividadInfo extends JPanel
{
	private String idCamino;
	private String idActividad;
	
	private HashMap<String, String> infoActividad=null;
	private JTextArea txtArInfoActividad;
	
	public PanelActividadInfo (String idActividadp, String idCaminop)
	{
		this.idActividad=idActividadp;
		this.idCamino=idCaminop;
		
        setBorder( new TitledBorder( "Información de la actividad" ) );
        setLayout( new BorderLayout( ) );
        

		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
		
		
		this.setInfoActividad();
		
		if (infoActividad!=null)
		{
			this.fillTxtArInfoActividad();
		}

	    // Crear un panel con barras de desplazamiento para la lista
        JScrollPane scroll = new JScrollPane( txtArInfoActividad );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        this.add( scroll );
	}
	

	private void fillTxtArInfoActividad() 
	{
		txtArInfoActividad= new JTextArea();
		String stringInfoActividad="";
		
		stringInfoActividad+="Nombre: "+infoActividad.get("Tipo de actividad: ")+ "\n";
		stringInfoActividad+="Tipo de actividad: "+ infoActividad.get("NomTipo de actividad: ")+ "\n";
		stringInfoActividad+="Descripcion: "+ infoActividad.get("Descripcion: ")+ "\n";
		stringInfoActividad+="Dificultad: "+ infoActividad.get("Dificultad: ")+ "\n";
		stringInfoActividad+="Duracion: "+ infoActividad.get("Duracion: ")+ "\n";
		stringInfoActividad+="Fecha límite: "+ infoActividad.get("Fecha límite: ")+ "\n";
		stringInfoActividad+="Nombre Profesor creador: "+ infoActividad.get("Nombre Profesor creador: ")+ "\n";
		stringInfoActividad+="Objetivos: "+ infoActividad.get("Objetivos")+ "\n";
		stringInfoActividad+="Rating: "+ infoActividad.get("Rating: ")+ "\n";
		stringInfoActividad+="Actividades Prerequisitos: "+ infoActividad.get("Actividades Prerequisitos")+ "\n";
		stringInfoActividad+="Actividades Siguientes Exitosas: "+ infoActividad.get("Actividades Siguientes Exitosas")+ "\n";
		stringInfoActividad+="Resenias: "+ infoActividad.get("Resenias")+ "\n";
		
		txtArInfoActividad.setText(stringInfoActividad);
		txtArInfoActividad.setEditable(false);
	}


	private void setInfoActividad()
	{
		try
		{
			infoActividad=TraductorActividad.verInfoGeneralActividad(idCamino, idActividad);
		}
		catch (Exception e)
		{
    		JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public String getTipoActividad()
	{
		return infoActividad.get("Tipo de actividad: ");
	}
	

}
