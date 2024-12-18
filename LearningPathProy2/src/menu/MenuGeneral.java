package menu;

import java.util.Scanner;
import controllers.Autentificador;
import controllers.LearningPathSystem;
import persistencia.CentralPersistencia;
import usuarios.Estudiante;
import usuarios.Profesor;
import usuarios.Usuario;

public class MenuGeneral
{
	public static LearningPathSystem LPS = null;
	public static Autentificador autentificador = null;

	public static void main(String[] args)
	{	
		try
		{
			CentralPersistencia.cargarTodo(false);
		}
		catch (Exception e)
		{
			System.out.println("Error al cargar los datos: " + e.getMessage());
		}
		
		if (LPS == null)
			LPS = LearningPathSystem.getInstance();

		if (autentificador == null)
			autentificador = Autentificador.getInstance(LPS);

		while (true)
		{
			mostrarMenu();
		}
	}

	public static void mostrarMenu()
	{
		System.out.println("¡Bienvenido al sistema de gestión de caminos de aprendizaje!");
		System.out.println("Seleccione una opcion:");
		System.out.println("1. Iniciar sesion");
		System.out.println("2. Registrarse");
		System.out.println("3. Salir");

		Scanner scanner = new Scanner(System.in);
		
		int opcion=1;
		try
		{
			opcion = scanner.nextInt();
		}
		catch (Exception e)
		{
			System.out.println("Opcion no valida ingresada");
			mostrarMenu();
		}
		
		switch (opcion)
		{
		case 1:
			// Iniciar sesion
			iniciarSesion();
			break;
		case 2:
			// Registrarse
			registrarse();
			break;
		case 3:
			// Salir
			System.out.println("Desea cerrar la aplicacion?");
			System.out.println("1. Si");
			System.out.println("2. No");
			int cerrar = scanner.nextInt();
			if (cerrar == 1)
			{
				System.out.println("Gracias por usar el sistema. \n¡Hasta luego!");
				try
				{
					CentralPersistencia.guardarTodo(false);
				}
				catch (Exception e)
				{
					System.out.println("Error al guardar los datos: " + e.getMessage());
				}
				System.exit(0);
			}
			else if (cerrar == 2)
			{
				System.out.println("Volviendo al menu principal. \n");
			}
			else
			{
				System.out.println("Opcion no valida. Volviendo al menu principal. \n");
			}
			break;
		default:
			System.out.println("Opcion no valida. Por favor intente de nuevo. \n");
		}

	}

	public static void iniciarSesion()
	{
		System.out.println("Ingrese su login:");
		Scanner scanner = new Scanner(System.in);
		String login = scanner.nextLine();
		System.out.println("Ingrese su password:");
		String password = scanner.nextLine();
		// Validar login y password

		try
		{
			Usuario usuario = autentificador.autentificar(login, password);
			if (usuario != null)
			{
				if (usuario.getType().equals("Profesor"))
				{
					System.out.println("Bienvenido Profesor " + usuario.getNombre() + "\n");
					while (true)
					{
						MenuProfesor.mostrarMenuProfesor((Profesor) usuario);
					}
				}
				else if (usuario.getType().equals("Estudiante"))
				{
					System.out.println("Bienvenido Estudiante " + usuario.getNombre() + "\n");
					while (true)
					{
						MenuEstudiante.mostrarMenuEstudiante((Estudiante) usuario);
					}
				}
				else
				{
					System.out.println("Usuario no encontrado en la base de datos. \n");
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Ocurrió un error en el proceso de inicio de sesión: " + e.getMessage());
		}

	}

	public static void registrarse()
	{
		int typeNewUsuario = 0;

		System.out.println("Por favor complete los siguientes campos para registrarse:");
		System.out.println("Ingrese su nombre:");
		Scanner scanner = new Scanner(System.in);
		String nombre = scanner.nextLine();
		System.out.println("Ingrese su login:");
		String login = scanner.nextLine();
		System.out.println("Ingrese su password:");
		String password = scanner.nextLine();
		System.out.println("Seleccione el tipo de usuario:");
		System.out.println("1. Estudiante");
		System.out.println("2. Profesor");
		int type = scanner.nextInt();
		switch (type)
		{
		case 1:
			// estudiante
			typeNewUsuario = 1;
			break;
		case 2:
			// profesor
			typeNewUsuario = 2;
			break;
		default:
			System.out.println("Opcion no valida. \n");
		}

		if (typeNewUsuario != 0)
		{
			try
			{
				boolean registro = autentificador.registrarUsuario(nombre, password, login, typeNewUsuario); 
				if (registro == true)
				{
					System.out.println("Usuario registrado exitosamente. \n");
				}
				else
				{
					System.out.println("Error en el proceso de registro. \n");

				}
			}
			catch (Exception e)
			{
				System.out.println("Ocurrió un error en el proceso de registro: " + e.getMessage());
			}
		}

	}
}
