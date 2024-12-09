package menuSwingProfesorCreadores;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import creadores.CreadorQuiz;
import caminosActividades.PreguntaQuiz;

public class VentanaCrearQuiz extends VentanaCreacionActividad
{

	public VentanaCrearQuiz(String idProfesor) 
	{
		super(idProfesor);
		this.setTitle("Learning Path System: Creacion de quiz");
	}

	@Override
	protected void crearActividad() {
	    try {
	        List<String> objetivos = new LinkedList<>();

	        int[] fechaLim = new int[]{Integer.valueOf(pInfoActividad.getAnio()), Integer.valueOf(pInfoActividad.getMes()),
	                Integer.valueOf(pInfoActividad.getDia())};
	        double calMin = Double.valueOf(((PanelCreacionQuiz) pInfoActividad).txtCalificacionMin.getText());
	        boolean verdaderoFalso = ((PanelCreacionQuiz) pInfoActividad).rbTrueFalse.isSelected();
	        List<PreguntaQuiz> preguntas = ((PanelCreacionQuiz) pInfoActividad).guardarPreguntas();

	        CreadorQuiz.crearQuizCero(pInfoActividad.getIDCamino(), pInfoActividad.getNombre(), pInfoActividad.getDescricpion(),
	                pInfoActividad.pObjetivos.guardarObjetivos(objetivos), (double) Double.valueOf(pInfoActividad.getDificultad()),
	                (int) Integer.valueOf(pInfoActividad.getDuracion()), fechaLim,  pInfoActividad.getObligatoria(), calMin, preguntas,
	                this.idProfesor, verdaderoFalso, Integer.valueOf(pInfoActividad.getPosCamino()) - 1);

	        JOptionPane.showMessageDialog(null, "Quiz creado exitosamente");
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e.getMessage());
	    }
	}


	@Override
	protected void addPInfoActividad() 
	{
		pInfoActividad= new PanelCreacionQuiz();
		this.add(pInfoActividad, BorderLayout.CENTER);
	}

}
