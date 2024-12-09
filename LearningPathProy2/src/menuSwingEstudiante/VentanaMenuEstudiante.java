package menuSwingEstudiante;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import menuSwingEstudianteEnvios.VentanaActividadIniciada;
import persistencia.CentralPersistencia;
import traductores.TraductorActividad;
import traductores.TraductorCamino;
import traductores.TraductorEstudiante;

public class VentanaMenuEstudiante extends JFrame
{
	private PanelBotonesMenuEstudiante pBotones;

	private VentanaCaminosDispInscripcion ventCaminosDisp=null;
	private VentanaAvancesEstudiante ventAvances=null;
	private VentanaActividadIniciada ventActividadIniciada=null;
	private VentanaActividadesDisponibles ventActividadesDisp=null;
	
	private String idEstudiante;
	
	public VentanaMenuEstudiante(String idEstudianteP )
	{
		this.idEstudiante=idEstudianteP;
		
        setLayout( new BorderLayout( ) );
        
        pBotones= new PanelBotonesMenuEstudiante(this);
        
        add( pBotones, BorderLayout.CENTER );
        
        setTitle( "Learning Path System: Menu estudiante" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );
        setVisible( true );
        
	}


    public void mostrarVentanaCaminosDisponibles( )
    {
        if( ventCaminosDisp == null || !ventCaminosDisp.isVisible( ) )
        {
        	ventCaminosDisp = new VentanaCaminosDispInscripcion(idEstudiante);
        	ventCaminosDisp.setVisible( true );
        }
    }


	public void mostrarVentanaAvances()
	{
        if( ventAvances == null || !ventAvances.isVisible( ) )
        {
        	ventAvances = new VentanaAvancesEstudiante(idEstudiante);
        	ventAvances.setVisible( true );
        }
		
	}
	
	public void mostrarVentanaActividadesDisponibles()
	{
		try
		{
			if (TraductorEstudiante.isActividadActiva(idEstudiante))
			{
				JOptionPane.showMessageDialog(null, "Ya hay una actividad iniciada");
			}
			else
			{
				ventActividadesDisp = new VentanaActividadesDisponibles(idEstudiante);
				ventActividadesDisp.setVisible(true);
			}
		}
		catch (HeadlessException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error al verificar actividades disponibles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}
	}


	public void mostrarVentanaActividadEnvio() 
	{
		try 
		{
			boolean isActActiva=TraductorEstudiante.isActividadActiva(idEstudiante);
			if (isActActiva)
			{
		        if( ventActividadIniciada == null || !ventActividadIniciada.isVisible( ) )
		        {
					String actividad = TraductorEstudiante.verActividadActiva(idEstudiante);

		        	String[] actividadAll = actividad.split(";");
					String nombreActividad = actividadAll[0];
					String nombreCamino = actividadAll[2];
					String idCamino = TraductorCamino.getIDfromNombre(nombreCamino);
					String idActividad = TraductorActividad.getIDfromNombre(idCamino, nombreActividad);
					
		        	ventActividadIniciada = new VentanaActividadIniciada(idCamino, idActividad, idEstudiante);
		        	ventActividadIniciada.setVisible( true );
		        }
			}
			else 
			{
	    		JOptionPane.showMessageDialog(null, "No hay una actividad iniciada");
			}
		}
		catch (Exception e)
		{
    		JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
		
	}
    
	
		

}
