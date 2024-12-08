package menuSwingProfesorCreadores;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import creadores.CreadorCamino;

public abstract class VentanaCreacionActividad extends JFrame implements ActionListener
{
	protected PanelCreacionActividad pInfoActividad;
	
	protected JPanel pBotones;
	
	protected JButton bRegresar;
	public static final String REGRESAR= "regresar";

	protected JButton bCrearActividad;
	public static final String CREARACTIVIDAD= "crear actividad";

	public VentanaCreacionActividad(String idProfesor)
	{
		this.setLayout(new BorderLayout());
		
    	//Boton de regresar
		pBotones = new JPanel();
		pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS ) );
		
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        bCrearActividad= new JButton( "Crear camino" );
        bCrearActividad.setActionCommand( CREARACTIVIDAD );
        bCrearActividad.addActionListener( this );
        pBotones.add( bCrearActividad );
        bCrearActividad.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    	//Añado a la ventana
		this.add(pBotones, BorderLayout.SOUTH);
		
		addPInfoActividad();
	}
	
	protected abstract void crearActividad();
	
	protected abstract void addPInfoActividad();
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( REGRESAR ) )
        {
        	dispose();
        }
        else if (comando.equals(CREARACTIVIDAD))
        {
        	try 
        	{
        		crearActividad();
			} 
        	catch (Exception e1) 
        	{
        		JOptionPane.showMessageDialog(null, e1.getMessage());
			}
        }
	}

}
