package controllers;

import creadores.CreadorProfesor;
import creadores.CreadorEstudiante;
import usuarios.Estudiante;
import usuarios.Profesor;
import usuarios.Usuario;

public class Autentificador {
	private static Autentificador autentificador = null;
	private LearningPathSystem LPS;

	private Autentificador(LearningPathSystem LPS) 
	{
		this.LPS = LPS; 
	}

	public static Autentificador getInstance(LearningPathSystem LPS) 
	{
		if (autentificador == null)
			autentificador = new Autentificador(LPS);
		return autentificador;
	}

	public Usuario autentificar(String ID, String contrasena) throws Exception 
	{
		Usuario usuario = null;
		usuario = LPS.getEstudianteIndividual("Estudiante-"+ID);
		if (usuario == null) 
		{
			usuario = LPS.getProfesorIndividual("Profesor-"+ID);
		}
		
		if (usuario != null) 
		{
			String psswrd = usuario.getPassword();
			if (psswrd.equals(contrasena)) 
			{
				return usuario;
			} 
			else 
			{
				throw new Exception("Usuario o contraseña incorrecta. \n");
			}
		} 
		else 
		{
			throw new Exception("Usuario no encontrado. \n");
		}

	}

	public boolean registrarUsuario(String nombreUsuario, String constrasenaUsuario, String loginUsuario, int tipoUsuario) throws Exception
	{
		if (tipoUsuario == 1) //Estudiante 
		{
			String ID = "Estudiante-"+loginUsuario;
			if (LPS.getEstudianteIndividual(ID) == null) 
			{
				CreadorEstudiante.crearEstudiante(loginUsuario, constrasenaUsuario, nombreUsuario);
				if (LPS.getEstudianteIndividual(ID) != null) 
				{
					return true;
				} 
				else
					return false;

			}
		} 
		else if (tipoUsuario == 2) // Profesor
		{
			String ID = "Profesor-"+loginUsuario;
			if (LPS.getProfesorIndividual(ID) == null) 
			{
				try 
				{
					CreadorProfesor.crearProfesor(loginUsuario, constrasenaUsuario,nombreUsuario);
					if (LPS.getProfesorIndividual(ID) != null)
					{
						return true;
					} else
						return false;
				} 
				catch (Exception e) 
				{
					e.getMessage();
				}

			}
		}
		return false;
	}

	public void eliminarUsuario(String ID) 
	{
		if (LPS.getEstudianteIndividual(ID) != null) {
			try 
			{
				CreadorEstudiante.eliminarEstudiante(ID);
			} 
			catch (Exception e) 
			{
				e.getMessage();
			}
		} 
		else if (LPS.getProfesorIndividual(ID) != null) 
		{
			try 
			{
				CreadorProfesor.eliminarProfesor(ID);
			} 
			catch (Exception e) 
			{
				e.getMessage();
			}
		}
	}

	public void modificarContrasena(String ID, String contrasena) 
	{
		if (LPS.getEstudianteIndividual(ID) != null) 
		{
			LPS.getEstudianteIndividual(ID).setPassword(contrasena);
		} 
		else if (LPS.getProfesorIndividual(ID) != null) 
		{
			LPS.getProfesorIndividual(ID).setPassword(contrasena);
		}
	}

	public void modificarNombre(String ID, String nombre) 
	{
		if (LPS.getEstudianteIndividual(ID) != null) 
		{
			LPS.getEstudianteIndividual(ID).setNombre(nombre);
		} 
		else if (LPS.getProfesorIndividual(ID) != null) 
		{
			LPS.getProfesorIndividual(ID).setNombre(nombre);
		}
	}
}
