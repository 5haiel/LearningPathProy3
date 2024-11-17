package editores;

import java.util.Date;

import caminosActividades.Actividad;
import caminosActividades.ActividadRecurso;
import caminosActividades.CaminoAprendizaje;
import caminosActividades.Examen;
import caminosActividades.Tarea;
import controllers.LearningPathSystem;

public class EditorTarea extends EditorActividadGeneral
{ 
	public static void editInstrucciones(String instrucciones, String IDcamino, String IDactividad) throws Exception 
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Tarea actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.TAREA))
				{
					throw new Exception ("La actividad pasada no fue una tarea.");
				}
				
				actividad= (Tarea) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.setInstrucciones(instrucciones);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	public static void editAddActividadSigFracaso(String IDcamino, String IDactividad, String nombreActividadSigFracaso) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Tarea actividad=null;
		Actividad actividadSigFracaso=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.TAREA))
				{
					throw new Exception ("La actividad pasada no fue una tarea.");
				}
				
				actividad= (Tarea) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		actividad.addActividadSigFracaso(nombreActividadSigFracaso);
		
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}
	
	
	public static void editDelActividadSigFracaso(String IDcamino, String IDactividad, int pos) throws Exception
	{
		LearningPathSystem LPS= LearningPathSystem.getInstance();
		CaminoAprendizaje camino= LPS.getCaminoIndividual(IDcamino);
		
		if (camino==null)
		{
			throw new Exception ("No se encontro el camino");
		}
		
		Tarea actividad=null;
		
		//Consigo la actividad del id
		for (Actividad actividadIterator: camino.getActividades())
		{
			if (actividadIterator.getId().equals(IDactividad))
			{
				if (!actividadIterator.getType().equals(Actividad.TAREA))
				{
					throw new Exception ("La actividad pasada no fue una tarea.");
				}
				
				actividad= (Tarea) actividadIterator;
			}
		}
		
		if (actividad==null)
		{
			throw new Exception ("No se encontro la actividad");
		}
		
		if (pos>actividad.getActividadesSigFracaso().size() || pos<=0)
		{
			throw new Exception ("El número de la actividad no existe");
		}
		else
		{
			actividad.delActividadSigFracaso(pos-1);
		}
				
		int version=camino.getVersion();
		camino.setVersion(version+=1);
		Date fecha = new Date();
		camino.setFechaModificacion(fecha.toString());
	}


}
