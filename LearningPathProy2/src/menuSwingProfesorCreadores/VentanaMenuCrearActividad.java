package menuSwingProfesorCreadores;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import menuSwingProfesor.VentanaMenuProfesor;
import persistencia.CentralPersistencia;

public class VentanaMenuCrearActividad extends JFrame implements ActionListener
{	
	private PanelBotonesActividades pActividadesMenu;
	
	private JButton bRegresar;
	public static final String REGRESAR="regresar";
	
	private VentanaCrearEncuesta ventCrearEncuesta=null;
	private VentanaCrearAR ventCrearAR=null;
	private VentanaCrearExamen ventCrearExamen=null;
	private VentanaCrearTarea ventCrearTarea=null;
	private VentanaCrearQuiz ventCrearQuiz=null;
	
	private String idProfesor;
	
	public VentanaMenuCrearActividad(String idProfesor)
	{
		this.idProfesor=idProfesor;
		this.setLayout(new BorderLayout() );
	   
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
	   this.add(bRegresar, BorderLayout.SOUTH);
	   
	   this.pActividadesMenu=new PanelBotonesActividades(this);
	   this.add(pActividadesMenu, BorderLayout.CENTER);
	   
       // Termina de configurar la ventana
       setTitle( "Learning Path System: Crear actividad menu" );
       setDefaultCloseOperation( EXIT_ON_CLOSE );
       setSize( 400, 400 );
       setLocationRelativeTo( null );
       setVisible( true );

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{       
		String comando = e.getActionCommand( );
    
	    if( comando.equals( REGRESAR ) )
	    {
			dispose();
	    }
        else if (comando.equals(PanelBotonesActividades.ENCUESTA))
        {
        	mostrarVentanaEncuesta();
        }
        else if (comando.equals(PanelBotonesActividades.EXAMEN))
        {
        	mostrarVentanaExamen();
        }
        else if (comando.equals(PanelBotonesActividades.QUIZ))
        {
        	mostrarVentanaQuiz();;
        }
        else if (comando.equals(PanelBotonesActividades.TAREA))
        {
        	mostrarVentanaTarea();;
        }
        else if (comando.equals(PanelBotonesActividades.ACTRECURSO))
        {
        	mostrarVentanaAR();;
        }

	}

	private void mostrarVentanaAR() 
	{
		if( ventCrearAR == null || !ventCrearAR.isVisible( ) )
        {
			ventCrearAR = new VentanaCrearAR(idProfesor);
			ventCrearAR.setVisible( true );
        }
	}

	private void mostrarVentanaTarea()
	{
		if( ventCrearTarea == null || !ventCrearTarea.isVisible( ) )
        {
			ventCrearTarea = new VentanaCrearTarea(idProfesor);
			ventCrearTarea.setVisible( true );
        }
	}

	private void mostrarVentanaQuiz() 
	{
		if( ventCrearQuiz == null || !ventCrearQuiz.isVisible( ) )
        {
			ventCrearQuiz = new VentanaCrearQuiz(idProfesor);
			ventCrearQuiz.setVisible( true );
        }
		
	}

	private void mostrarVentanaExamen() 
	{
		if( ventCrearExamen == null || !ventCrearExamen.isVisible( ) )
        {
			ventCrearExamen = new VentanaCrearExamen(idProfesor);
			ventCrearExamen.setVisible( true );
        }
		
	}

	private void mostrarVentanaEncuesta() 
	{
		if( ventCrearEncuesta == null || !ventCrearEncuesta.isVisible( ) )
        {
			ventCrearEncuesta = new VentanaCrearEncuesta(idProfesor);
			ventCrearEncuesta.setVisible( true );
        }
		
	}
}
