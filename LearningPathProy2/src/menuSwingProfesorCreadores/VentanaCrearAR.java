package menuSwingProfesorCreadores;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import creadores.CreadorAR;

public class VentanaCrearAR extends VentanaCreacionActividad
{

	public VentanaCrearAR(String idProfesor) 
	{
		super(idProfesor);
		this.setTitle("Learning Path System: Creacion de actividad de recurso");

	}

	@Override
	protected void crearActividad() 
	{
		try
		{
			List<String> objetivos = new LinkedList<String>();
			
			int[] fechaLim = new int[]{Integer.valueOf(pInfoActividad.getAnio()), Integer.valueOf(pInfoActividad.getMes()),
					Integer.valueOf(pInfoActividad.getDia())};
			
			String recurso = ((PanelCreacionAR) pInfoActividad).txtRecurso.getText();
			String instrucciones = ((PanelCreacionAR) pInfoActividad).txtInstrucciones.getText();

			CreadorAR.crearARCero(pInfoActividad.getIDCamino(), pInfoActividad.getNombre(), pInfoActividad.getDescricpion(), 
					pInfoActividad.pObjetivos.guardarObjetivos(objetivos), Double.valueOf(pInfoActividad.getDificultad()), 
					Integer.valueOf(pInfoActividad.getDuracion()), fechaLim, pInfoActividad.getObligatoria(), 
					recurso, instrucciones, this.idProfesor, Integer.valueOf(pInfoActividad.getPosCamino())-1);
			
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
		pInfoActividad= new PanelCreacionAR();
		this.add(pInfoActividad, BorderLayout.CENTER);
	}

}
