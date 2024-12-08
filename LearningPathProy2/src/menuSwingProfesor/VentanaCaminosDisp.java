package menuSwingProfesor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.Inscriptor;
import menuSwing.JComponentCaminosDisponibles;
import traductores.TraductorCamino;

public class VentanaCaminosDisp extends JFrame implements ActionListener
{
	private JComponentCaminosDisponibles lCaminosDisp;
	
	private JButton bRegresar;
	private static final String REGRESAR="regresar";
	
	
	public VentanaCaminosDisp()
	{		
		this.setLayout(new BorderLayout());
		
		//Añado componente de los caminos
		lCaminosDisp= new JComponentCaminosDisponibles(this);
		this.add(lCaminosDisp, BorderLayout.CENTER);
		lCaminosDisp.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Boton de regresar
		JPanel pBotones = new JPanel();
		pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS ) );
		
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
 
    	
    	//Añado a la ventana
		this.add(pBotones, BorderLayout.SOUTH);
		
		
        // Termina de configurar la ventana
        setTitle( "Learning Path System: Ver caminos disponibles" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 700, 600 );
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
	}
	
}
