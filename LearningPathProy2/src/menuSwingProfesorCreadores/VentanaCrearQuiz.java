package menuSwingProfesorCreadores;

import java.awt.BorderLayout;

public class VentanaCrearQuiz extends VentanaCreacionActividad
{

	public VentanaCrearQuiz(String idProfesor) 
	{
		super(idProfesor);
		this.setTitle("Learning Path System: Creacion de quiz");
	}

	@Override
	protected void crearActividad()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addPInfoActividad() 
	{
		pInfoActividad= new PanelCreacionQuiz();
		this.add(pInfoActividad, BorderLayout.CENTER);
	}

}
