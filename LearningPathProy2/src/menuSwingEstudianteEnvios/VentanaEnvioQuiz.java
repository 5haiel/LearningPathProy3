package menuSwingEstudianteEnvios;

import java.awt.event.ActionEvent;

public class VentanaEnvioQuiz extends VentanaRealizarEnvio
{

	public VentanaEnvioQuiz(String idEstudiante, String idActividad, String idCamino)
	{
		super(idEstudiante, idActividad, idCamino);
		
	    setTitle( "Learning Path System: Completar quiz" );
        setVisible( true );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActividadPanelInfo() {
		// TODO Auto-generated method stub
		
	}

}
