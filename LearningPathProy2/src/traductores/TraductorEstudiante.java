package traductores;

import java.util.HashMap;
import java.util.Iterator;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteExamen;
import usuarios.Estudiante;
import usuarios.Profesor;

public class TraductorEstudiante 
{
	/**
	 * retorna un HashMap<String, String> que contiene como llave el nombre del camino y como valor el porcentaje
	 * logrado en ese camino
	 */
	public static HashMap<String, String> getAvancesCaminos(String IDestudiante) throws Exception
	{

		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Estudiante estudiante=LPS.getEstudianteIndividual(IDestudiante);
		
		HashMap<String, String> avances = new HashMap<String, String>();
		
		
		for (CaminoAprendizaje camino: estudiante.getHistorialCaminos())
		{
			int actvCompletadas =0;
			
			for (Actividad actividad: camino.getActividades())
			{
				DatosEstudianteActividad datoEst = actividad.getDatoEstudianteIndFromIDEstudiante(estudiante.getID());
				
				if (actividad.isObligatoria()  && (datoEst.getEstado().equals(DatosEstudianteActividad.EXITOSO)))
				{
					actvCompletadas+=1;
				}
				
			}
			
			double porcentaje;
			
			if (camino.getNumActividadesObligatorias()==0)
			{
				porcentaje=0;
			}
			else
			{
				porcentaje = ((double) actvCompletadas/ (double)camino.getNumActividadesObligatorias())*100.0;
			}
			
			avances.put(camino.getTitulo(), String.valueOf(porcentaje)+"%");

		}
		
		return avances;
	}

	
	/**
	 * retorna un HashMap<String, String> que contiene como llave el nombre de la actividad y como valor el estado de la actividad.
	 * Si está calificada, muestra la calificacion
	 */
	public static HashMap<String, String> getAvancesCaminoIndividual(String IDestudiante, String idCamino) throws Exception
	{

		LearningPathSystem LPS= LearningPathSystem.getInstance();
		Estudiante estudiante=LPS.getEstudianteIndividual(IDestudiante);
		
		CaminoAprendizaje camino=LPS.getCaminoIndividual(idCamino);
		
		HashMap<String, String> avances = new HashMap<String, String>();
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		for (Actividad actividadIterator: camino.getActividades())
		{
			DatosEstudianteActividad datoEst = actividadIterator.getDatoEstudianteIndFromIDEstudiante(estudiante.getID());
			
			String estado;
			if (actividadIterator.getType()==Actividad.EXAMEN && !datoEst.getEstado().equals(DatosEstudianteActividad.PENDIENTE) && !datoEst.getEstado().equals(DatosEstudianteActividad.ENVIADO))
			{
				estado=datoEst.getEstado()+" con calificacion de: "+ ((DatosEstudianteExamen) datoEst).getCalificacion();;

			}
			else if (actividadIterator.getType()==Actividad.QUIZ && !datoEst.getEstado().equals(DatosEstudianteActividad.PENDIENTE))
			{
				estado=datoEst.getEstado()+" con calificacion de: "+ ((DatosEstudianteExamen) datoEst).getCalificacion();;
			}
			else
			{
				estado=datoEst.getEstado();
			}
				
			avances.put(actividadIterator.getNombre(), estado);

		}
		
		return avances;
	}
	
	public static String getIDfromLogin(String login) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Estudiante> estudiantes = LPS.getEstudiantes();
		String IDtoReturn=null;
		
		for (String IDestudiante: estudiantes.keySet())
		{
			Estudiante estudianteIterator=estudiantes.get(IDestudiante);
			
			if (estudianteIterator.getLogin().equals(login))
			{
				IDtoReturn= IDestudiante;
			}
		}
		
		if (IDtoReturn==null)
		{
			throw new Exception("No se encontro el login");
		}
		else
		{
			return IDtoReturn;
		}
	}

	public static String getNombrefromID(String idEstudiante) throws Exception 
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Estudiante> estudiantes = LPS.getEstudiantes();
		
		Estudiante estudiante=estudiantes.get(idEstudiante);
		
		if (estudiante==null)
		{
			throw new Exception ("No se encontro el estudiante");
		}
		
		return estudiante.getNombre();
		
	}
	
	/*
	 * Retoran un string que dice el nombre del camino y actividad si el estudiante ha iniciado una actividad.
	 * Si no ha iniciado una, el string dice "Ninguna actividad activa"
	 */
	public static String verActividadActiva(String idEstudiante) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Estudiante> estudiantes = LPS.getEstudiantes();
		
		Estudiante estudiante=estudiantes.get(idEstudiante);
		
		if (estudiante==null)
		{
			throw new Exception ("No se encontro el estudiante");
		}
		
		return estudiante.getNombreCaminoActividadActiva();
	}
	
	public static boolean isActividadActiva(String idEstudiante) throws Exception
	{
		LearningPathSystem LPS = LearningPathSystem.getInstance();
		HashMap<String, Estudiante> estudiantes = LPS.getEstudiantes();
		
		Estudiante estudiante=estudiantes.get(idEstudiante);
		
		if (estudiante==null)
		{
			throw new Exception ("No se encontro el estudiante");
		}
		
		return estudiante.isActividadActiva();
	}
}
