package menuSwingProfesorCreadores;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import creadores.CreadorEncuesta;
import creadores.CreadorExamen;

public class VentanaCrearEncuesta extends VentanaCreacionActividad
{

	public VentanaCrearEncuesta(String idProfesor)
	{
		super(idProfesor);
		this.setTitle("Learning Path System: Creacion de encuestta");
	}

	@Override
	protected void crearActividad() 
	{
		try
		{
			List<String> objetivos = new LinkedList<String>();
			
			List<String> preguntas = new LinkedList<String>();
			preguntas=((PanelCreacionEncuesta) pInfoActividad).pPreguntas.guardarPreguntas(preguntas);

			int[] fechaLim = new int[]{Integer.valueOf(pInfoActividad.getAnio()), Integer.valueOf(pInfoActividad.getMes()),
					Integer.valueOf(pInfoActividad.getDia())};
			
			CreadorEncuesta.crearEncuestaCero(pInfoActividad.getIDCamino(), pInfoActividad.getNombre(), pInfoActividad.getDescricpion(), 
					pInfoActividad.pObjetivos.guardarObjetivos(objetivos), Double.valueOf(pInfoActividad.getDificultad()), 
					Integer.valueOf(pInfoActividad.getDuracion()), fechaLim, pInfoActividad.getObligatoria(), 
					preguntas, this.idProfesor, Integer.valueOf(pInfoActividad.getPosCamino())-1);
			
			JOptionPane.showMessageDialog(null, "Actividad creada exitosamente");
		} 
    	catch (Exception e1) 
    	{
    		JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	}

	@Override
	protected void addPInfoActividad() 
	{
		pInfoActividad= new PanelCreacionEncuesta();
		this.add(pInfoActividad, BorderLayout.CENTER);
	}

}
