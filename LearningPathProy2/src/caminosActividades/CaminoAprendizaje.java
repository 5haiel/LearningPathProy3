package caminosActividades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CaminoAprendizaje {
	
	private String titulo;
	private String descripcion;
	private List<String> objetivos;
	private double dificultad;
	private int duracion;
	private Date fechaCreacion;
	private double rating;
	private int ratingsTotales;
	private int version;
	private Date fechaModificacion;
	private int numActividadesObligatorias;
	private List<Actividad> actividades; 
	private String creadorLogin;
	private final String ID;

//Constructor normal
	public CaminoAprendizaje(String titulo, String descripcion, List<String> objetivos, double dificultad, String creadorLogin) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.fechaCreacion= new Date();
		this.creadorLogin=creadorLogin;
		this.actividades=new ArrayList<Actividad>();
		
		this.ratingsTotales=0;
		this.version=1;
		this.numActividadesObligatorias=0;
		this.ID="Actividad"+UUID.randomUUID().toString();

	}
	
//Constructor clonador
	public CaminoAprendizaje(CaminoAprendizaje caminoOG, String creadorLogin, String titulo)
	{
		this.titulo= titulo;
		this.descripcion= caminoOG.getDescripcion();
		this.dificultad= caminoOG.getDificultad();
		this.duracion=caminoOG.getDuracion();
		this.numActividadesObligatorias=caminoOG.getNumActividadesObligatorias();

		this.actividades=new ArrayList<Actividad>();

		this.ratingsTotales=0;
		this.version=1;
		
		this.fechaCreacion= new Date();

    	Iterator<String> it1 = caminoOG.getObjetivos().iterator(); 
    	
    	while (it1.hasNext())
    	{
    		this.objetivos.add(it1.next());
    	}
    	
    	
    	//Copia de actividades
    	Iterator<Actividad> it2 = caminoOG.getActividades().iterator(); 
    	Actividad actividad;
    	
    	while (it2.hasNext())
    	{
    		Actividad act2 = it2.next();
    		if (act2 .getType().equals(Actividad.ENCUESTA))
    		{
    			actividad=new Encuesta (creadorLogin, (Encuesta) act2 , this);
    		}
    		
    		else if (act2 .getType().equals(Actividad.ACTIVIDADRECURSO))
    		{
    			actividad=new ActividadRecurso (creadorLogin, (ActividadRecurso) act2, this );
    		}
    		
    		else if (act2 .getType().equals(Actividad.EXAMEN))
    		{
    			actividad=new Examen (creadorLogin, (Examen) act2, this );
    		}
    		
    		else if (act2 .getType().equals(Actividad.QUIZ))
    		{
    			actividad=new Quiz (creadorLogin, (Quiz) act2, this );
    		}
    		
    		else
    		{
    			actividad= new Tarea (creadorLogin, (Tarea) act2, this );
    		}
    		
    		this.actividades.add(actividad);
    	}
		
		this.creadorLogin=creadorLogin;
		this.ID="Actividad"+UUID.randomUUID().toString();
	}

//Constructor cargar
	public CaminoAprendizaje(String titulo, String descripcion, List<String> objetivos, double dificultad, int duracion,
			Date fechaCreacion, double rating, int ratingsTotales, int version, Date fechaModificacion,
			int numActividadesObligatorias, List<Actividad> actividades, String creadorLogin, String id) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.dificultad = dificultad;
		this.duracion = duracion;
		this.fechaCreacion = fechaCreacion;
		this.rating = rating;
		this.ratingsTotales = ratingsTotales;
		this.version = version;
		this.fechaModificacion = fechaModificacion;
		this.numActividadesObligatorias = numActividadesObligatorias;
		this.actividades = actividades;
		this.creadorLogin = creadorLogin;
		this.ID=id;
	}

	
	public String getID() {
		return ID;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getCreadorLogin()
	{
		return this.creadorLogin;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<String> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<String> objetivos) {
		this.objetivos = objetivos;
	}

	public double getDificultad() {
		return dificultad;
	}

	public void setDificultad(double dificultad) {
		this.dificultad = dificultad;
	}

	public int getDuracion() {
		return duracion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public double getRating() {
		return rating;
	}

	public int getRatingsTotales() {
		return ratingsTotales;
	}

	public int getVersion() {
		return version;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getNumActividadesObligatorias() {
		return numActividadesObligatorias;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}
	
	public void addRating(double ratingNuevo)
	{
		double sumatoriaPrev=this.rating*this.ratingsTotales;
		this.ratingsTotales+=1;
		this.rating=(sumatoriaPrev+ratingNuevo)/this.ratingsTotales;
	}
	
	public void addObjetivo(String objetivo)
	{
		this.objetivos.add(objetivo);
	}
	
	/**
	 * @param Actividad actividad, int posicion
	 * Añade una actividad en el camino en la posicion indicada
	 * Actualiza la duracion del camino en total
	 * añade al contador de obligatorias si es obligatoria
	 */
	public void addActividad(Actividad actividad, int pos)
	{
		this.actividades.add(pos, actividad);
		this.duracion+=actividad.getDuracion();
		
		if (actividad.isObligatoria())
		{
			this.numActividadesObligatorias+=1;
		}
	}
	
	/**
	 * @param Actividad actividad
	 * Añade una actividad al final del camino
	 * Actualiza la duracion del camino en total
	 * 	 * añade al contador de obligatorias si es obligatoria
	 */
	public void addActividad(Actividad actividad)
	{
		this.actividades.add(actividad);
		this.duracion+=actividad.getDuracion();
		
		if (actividad.isObligatoria())
		{
			this.numActividadesObligatorias+=1;
		}
	}
	
	public void delActividad(int pos)
	{
		this.actividades.remove(pos);
	}
	
	public void delObjetivo(int pos)
	{
		this.objetivos.remove(pos);
	}
}
