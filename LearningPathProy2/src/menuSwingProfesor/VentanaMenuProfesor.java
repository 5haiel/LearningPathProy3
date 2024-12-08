package menuSwingProfesor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import menuSwingProfesorCreadores.VentanaCreacionCamino;
import menuSwingProfesorCreadores.VentanaMenuCrearActividad;


public class VentanaMenuProfesor extends JFrame
{
	private String idProfesor;
		
	private PanelBotonesMenuProfesor pBotones;
	
	private VentanaCaminosDisp ventCaminosDisp;
	private VentanaCreacionCamino ventCrearCamino;
	private VentanaCaminosCreados ventCaminosCreados;
	private VentanaMenuCrearActividad ventCrearActividad;
	
	public VentanaMenuProfesor( String idProfesorP)
	{
		this.idProfesor=idProfesorP;
				
        setLayout( new BorderLayout( ) );
        
        pBotones= new PanelBotonesMenuProfesor(this);
        
        add( pBotones, BorderLayout.CENTER );
        
        setTitle( "Learning Path System: Menu profesor" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );
        setVisible( true );

	}

	public void mostrarVentanaCaminosDisponibles() 
	{
		if( ventCaminosDisp == null || !ventCaminosDisp.isVisible( ) )
        {
        	ventCaminosDisp = new VentanaCaminosDisp();
        	ventCaminosDisp.setVisible( true );
        }
		
	}
	
	public void mostrarVentanaCaminosCreados() 
	{
		if( ventCaminosCreados == null || !ventCaminosCreados.isVisible( ) )
        {
			ventCaminosCreados = new VentanaCaminosCreados(idProfesor);
			ventCaminosCreados.setVisible( true );
        }
		
	}
	
	public void mostrarVentanaCreacionCamino() 
	{
		if( ventCrearCamino == null || !ventCrearCamino.isVisible( ) )
        {
			ventCrearCamino = new VentanaCreacionCamino(idProfesor);
			ventCrearCamino.setVisible( true );
        }
		
	}
	
	public void mostrarVentanaCrearActividad() 
	{
		if( ventCrearActividad == null || !ventCrearActividad.isVisible( ) )
        {
			ventCrearActividad = new VentanaMenuCrearActividad(idProfesor);
			ventCrearActividad.setVisible( true );
        }
		
	}
}
