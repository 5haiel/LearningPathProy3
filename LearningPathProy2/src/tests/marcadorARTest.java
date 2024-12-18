package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import datosEstudiantes.DatosEstudianteAR;
import datosEstudiantes.DatosEstudianteActividad;
import datosEstudiantes.DatosEstudianteExamen;
import marcadoresActividades.calificadorExamen;
import marcadoresActividades.marcadorAR;
import senders.ExamenSender;
import traductores.TraductorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;

public class marcadorARTest 
{
	private static String idEstudiante;
	private static String idActividad;
	private static String idCamino;
	private static CaminoAprendizaje camino;
	private static ActividadRecurso AR;
	private static LearningPathSystem LPS;
	private static Profesor profesor;
	private static String idActividadSecundaria;
	private static Estudiante estudiante;
	

	@BeforeEach
 	void init( ) 
    {
		try
		{
			LearningPathSystem.resetLPS(); 
			LPS=LearningPathSystem.getInstance();
			
			profesor = new Profesor("Aizawa999", "Aizawa 123", "Aizawa Shouta");
			LPS.addProfesor(profesor);
						
			List<String> objetivos = new LinkedList<String>();
			objetivos.add("Saber la diferencia entre distintos tipos de cuervos.");
			objetivos.add("Poder sustentar porque los cuervos son tan increibles.");
			objetivos.add("Volverse fan de los cuervos.");
			
			camino = new CaminoAprendizaje("El maravilloso mundo de los cuervos", "Esto es un curso que te enseña lo increible que son los cuervos",
					objetivos, 1.5, profesor.getID());
	
			LPS.addCamino(camino);
			idCamino=camino.getID();

	
			int[] fechaLim= new int[]{0,1,0};
			
			List<String> preguntasString= new LinkedList<String>();
			preguntasString.add("¿Cuales son las caracteristicas princiaples de los cuervos?");
			preguntasString.add("¿Que otro animal es parecido e igual de increible que los cuervos?");
			preguntasString.add("¿Por que son mejores los cuervos que otros pajaros?");
			
			Examen examen = new Examen("Tarea Test", "Esto es una tarea sobre cuervos", objetivos, 1.5, 20, 
					fechaLim, true, 3, preguntasString, profesor.getID(), camino, 0);
			idActividadSecundaria=examen.getId();

			estudiante= new Estudiante("Trey999", "Trey123", "Trey Clover");
			LPS.addEstudiante(estudiante);
			
			idEstudiante= TraductorEstudiante.getIDfromLogin("Trey999");
			
			AR= new ActividadRecurso("Lectura Test", "Esto es una lectura de tipos de variables", objetivos, 1.5, 20, fechaLim, 
					false, "https://www.w3schools.com/python/python_variables.asp", "Leer el articulo.", profesor.getID(), camino, 0);
			idActividad=AR.getID();
			
    		Inscriptor.inscribirseCamino(idCamino, idEstudiante);

    	}
		catch (Exception e) 
		{
			fail("Error en el setup: "+e.getMessage());
		}
    }

	@Test
	public void marcarARTerminadoTest()
	{
		
		try 
		{
			marcadorAR.marcarARTerminado(idCamino, idActividadSecundaria, idEstudiante);
			fail("Deberia sacar error por ser actividad de un tipo distinto");
		} 
		catch (Exception e) 
		{
	    	assertEquals("La actividad pasada no fue una actividad de recurso.", e.getMessage(), "No saco la exception correcta");
		}
		
		try 
		{
			marcadorAR.marcarARTerminado(idCamino, idActividad, idEstudiante);
			fail("Deberia sacar error por no haber iniciado la actividad el estudiante ");
		} 
		catch (Exception e) 
		{
	    	assertEquals("No se ha iniciado esta actividad", e.getMessage(), "No saco la exception correcta");
		}
		
		try 
		{
    		Inscriptor.iniciarActivad(idCamino, idActividad, idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
		}
    	
		
		try 
		{
			marcadorAR.marcarARTerminado(idCamino, idActividadSecundaria, idEstudiante);
			fail("Deberia sacar error por no ser actividad de recurso ");
		} 
		catch (Exception e) 
		{
	    	assertEquals("La actividad pasada no fue una actividad de recurso.", e.getMessage(), "No saco la exception correcta");
		}
		
		try 
		{
			marcadorAR.marcarARTerminado(idCamino, idActividad, idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No deberia sacar error: "+e.getMessage());
		}
		
	 	DatosEstudianteAR datoEst=null;
		try 
		{
			datoEst = (DatosEstudianteAR) AR.getDatoEstudianteIndFromIDEstudiante(idEstudiante);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
    		fail("No deberia salir error: "+e.getMessage());
		}
    	
    	assertEquals(DatosEstudianteActividad.EXITOSO, datoEst.getEstado(), "No se actualizo el estado del dato del estudiante");
    	assertEquals(false, estudiante.isActividadActiva(), "No se puso false actividad activa" );
	    	
	}
	

}
