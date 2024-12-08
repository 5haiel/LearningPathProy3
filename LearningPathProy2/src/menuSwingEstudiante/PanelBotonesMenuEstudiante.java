package menuSwingEstudiante;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import persistencia.CentralPersistencia;

public class PanelBotonesMenuEstudiante extends JPanel implements ActionListener
{
	private JButton bAvances;
	public static final String AVANCES="avances";
	
	private JButton bCaminosDisponibles;
	public static final String CAMINOSDISPONIBLES="caminos disp";

	private JButton bActividadIniciadaEnvio;
	public static final String ACT_INC_ENV="actividad iniciada envio";
	
	private JButton bSalir;
	public static final String SALIR="salir";
	
	private VentanaMenuEstudiante ventMenuEst;

	public PanelBotonesMenuEstudiante(VentanaMenuEstudiante ventana)
	{
		this.ventMenuEst=ventana;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
	    
	    //Botones
	    bAvances = new JButton( "Ver avances en todos los caminos" );
	    bAvances.setActionCommand( AVANCES );
	    bAvances.addActionListener( this );
	    this.add( bAvances );
	    bAvances.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    bCaminosDisponibles = new JButton( "Ver caminos disponibles e inscribirse" );
	    bCaminosDisponibles.setActionCommand( CAMINOSDISPONIBLES );
	    bCaminosDisponibles.addActionListener( this );
	    this.add( bCaminosDisponibles );
	    bCaminosDisponibles.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    bActividadIniciadaEnvio = new JButton( "Ver actividad iniciada y realizar un envio" );
	    bActividadIniciadaEnvio.setActionCommand( ACT_INC_ENV );
	    bActividadIniciadaEnvio.addActionListener( this );
	    this.add( bActividadIniciadaEnvio );
	    bActividadIniciadaEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    bSalir = new JButton( "Salir" );
	    bSalir.setActionCommand( SALIR );
	    bSalir.addActionListener( this );
	    this.add( bSalir );
	    bSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{       
		String comando = e.getActionCommand( );
    
	    if( comando.equals( SALIR ) )
	    {
			try
			{
				CentralPersistencia.guardarTodo(false);
			}
			catch (Exception e1)
			{
	    		JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
	        System.exit(0);
	    }
        else if (comando.equals(CAMINOSDISPONIBLES))
        {
        	ventMenuEst.mostrarVentanaCaminosDisponibles();
        }
        else if ( comando.equals( AVANCES ) )
        {
        	ventMenuEst.mostrarVentanaAvances();
        }
        else if ( comando.equals( AVANCES ) )
        {
        	ventMenuEst.mostrarVentanaActividadEnvio();
        }
	}

}
