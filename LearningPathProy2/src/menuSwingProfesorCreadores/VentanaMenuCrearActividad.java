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
	
	private VentanaCrearEncuesta ventCrearEncuesta;
	private VentanaCrearAR ventCrearAR;
	private VentanaCrearExamen ventCrearExamen;
	private VentanaCrearTarea ventCrearTarea;
	private VentanaCrearQuiz ventCrearQuiz;
	
	public VentanaMenuCrearActividad(String idProfesor)
	{
		this.setLayout(new BorderLayout() );
	   
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
	   this.add(bRegresar, BorderLayout.SOUTH);
	   
	   this.pActividadesMenu=new PanelBotonesActividades(this);
	   this.add(pActividadesMenu, BorderLayout.CENTER);

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
		// TODO Auto-generated method stub
		
	}

	private void mostrarVentanaTarea() {
		// TODO Auto-generated method stub
		
	}

	private void mostrarVentanaQuiz() 
	{
		// TODO Auto-generated method stub
		
	}

	private void mostrarVentanaExamen() {
		// TODO Auto-generated method stub
		
	}

	private void mostrarVentanaEncuesta() {
		// TODO Auto-generated method stub
		
	}
}
