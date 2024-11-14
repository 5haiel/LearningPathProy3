package creadores;

import java.util.List;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import caminosActividades.Tarea;
import controllers.LearningPathSystem;
import usuarios.Profesor;

public class CreadorExamen
{

	public static void crearExamenCero(String IDcamino, String nombre, String descripcion,
			List<String> objetivos, double dificultad, int duracion, int[] fechaLim, boolean obligatoria, 
			double calificacionMin, List<String> preguntas, String IDprofesor, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor=LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		Examen examen= new Examen(nombre, descripcion, objetivos, dificultad, duracion, fechaLim, obligatoria,
				calificacionMin, preguntas, profesor.getID(), camino, pos);
		
	}
	
	
	public static void clonarExamen(Actividad examenOG, String IDprofesor, String IDcaminoNuevo, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Profesor profesor= LPS.getProfesorIndividual(IDprofesor);
		CaminoAprendizaje caminoNuevo= LPS.getCaminoIndividual(IDcaminoNuevo);
		
		Examen examen = new Examen(profesor.getID(), (Examen) examenOG, caminoNuevo, pos);

	}
	

}
