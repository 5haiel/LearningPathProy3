package tests;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import caminosActividades.Actividad;
import caminosActividades.CaminoAprendizaje;
import datosEstudiantes.DatosEstudianteExamen;
import controllers.Inscriptor;
import controllers.LearningPathSystem;
import creadores.CreadorAR;
import creadores.CreadorCamino;
import creadores.CreadorEstudiante;
import creadores.CreadorExamen;
import creadores.CreadorProfesor;
import persistencia.ActividadesPersistencia;
import persistencia.CentralPersistencia;
import persistencia.DatosEstudiantesPersistencia;
import traductores.TraductorCamino;
import traductores.TraductorEstudiante;
import traductores.TraductorProfesor;

public class DatosEstudiantesTest 
{
	/*
	private LearningPathSystem LPS =LearningPathSystem.getInstance();
	static boolean deleteDirectory(File directoryToBeDeleted) 
	{
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) 
	    {
	        for (File file : allContents) 
	        {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	
	@BeforeAll
	static void init() throws Exception 
	{
		File fileCaminosDirectorio = new File("LearningPathProy2/datosTests/Caminos/CaminosDirectorio.txt");
	
		//Leo el archivo
		try (BufferedReader br = new BufferedReader(new FileReader(fileCaminosDirectorio))) 
		{		        
			
		    String line;
		    //Recorro el directorio para borrar las carpetas
		    while ((line = br.readLine()) != null) 
		    {
		    	File carpetaCamino = new File("LearningPathProy2/datosTests/Caminos/"+line);
		       
		        deleteDirectory(carpetaCamino);
		    	
		    }
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			fail("Error en el set up, file not found");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();			
			fail("Error en el set up, IOException");

		}

		CreadorProfesor.crearProfesor("KakashiDatosEstudiante", "Kakashi123", "Kakashi Hatake");
		String IDprof = TraductorProfesor.getIDfromLogin("KakashiDatosEstudiante");
		
		List<String> objetivos = new LinkedList<String>();
		objetivos.add("Saber diferentes tipos de datos");
		objetivos.add("Aprender loops");
		objetivos.add("Aprender estructuras");
		
		CreadorCamino.crearCaminoCero("Python123", "Un curso para saber los basicos de python", objetivos, 2, IDprof);
		CreadorCamino.crearCaminoCero("Jaca123", "Un curso para saber los basicos de java", objetivos, 3, IDprof);
		
		String idCamino = TraductorCamino.getIDfromNombre("Python123");
		
		List<String> objetivosActividad = new LinkedList<String>();
		objetivosActividad.add("Saber que es un int");
		objetivosActividad.add("Saber que es un double");
		objetivosActividad.add("Saber que es un float");
		
		List<String> preguntasExamen = new LinkedList<String>();
		preguntasExamen.add("Cual es la diferencia entre double y float?");
		preguntasExamen.add("Se puede pasar de un double a un int o de un int a un double? Por que o por que no?");
		preguntasExamen.add("Diga cual es su tipo de variable favorita y justifique.");
		
		int [] fechaLim2= new int[] {0,0,0};
		CreadorAR.crearARCero(idCamino, "Lectura de variables", "Una lectura para saber los tipos de variable", objetivosActividad, 
				2, 20, fechaLim2, true, "https://www.w3schools.com/python/python_datatypes.asp", "Lea el articulo", IDprof,0);
		
		int[] fechaLim= new int[]{0,1,0};
		CreadorExamen.crearExamenCero(idCamino, "Tipos de variables examen", "Comprobar que el estudiante sabe diferenciar variables numericos", 
				objetivosActividad, 2.5, 30, fechaLim, true, 3, preguntasExamen, IDprof,1);
		
		
		CreadorEstudiante.crearEstudiante("TreyCloverDatosEstudiante", "Trey123", "Trey Clover");
		String IDEstudiante= TraductorEstudiante.getIDfromLogin("TreyCloverDatosEstudiante");

		Inscriptor.inscribirseCamino(idCamino, IDEstudiante);

	}

	@Test
	public void testGuardarPersitencia()
	{
		String idCamino=null;
		String IDEstudiante=null;
		

		try 
		{
			IDEstudiante= TraductorEstudiante.getIDfromLogin("TreyCloverDatosEstudiante");
			idCamino = TraductorCamino.getIDfromNombre("Python123");

		} 
		catch (Exception e) {
    		fail("No se encontro el ID del estudiante con el login"+e.getMessage()); 
    		e.printStackTrace();
		}
	
    	try
    	{
    		CaminoAprendizaje camino = LPS.getCaminoIndividual(idCamino);
    		Actividad actividad;
    		
    		List<Actividad> actividades = camino.getActividades();
    		DatosEstudianteExamen datosEst = new DatosEstudianteExamen(IDEstudiante);
    		
    		DatosEstudiantesPersistencia.guardarDatosEstudiante(datosEst, idCamino, actividades.getFirst().getId(), "/LearningPathProy2/datosTests/Caminos/");
  
    	}
    	catch (Exception e)
    	{
    		fail("No se creo el objeto del archivo"+e.getMessage()); 
    		e.printStackTrace();
    	}
    	

	}
	*/

}
