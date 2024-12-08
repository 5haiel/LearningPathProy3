package menuSwingEstudianteEnvios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class VentanaRealizarEnvio extends JFrame implements ActionListener
{
	protected String idEstudiante;
	protected String idActividad;
	protected String idCamino;
	
	protected JButton bRegresar;
	public static final String REGRESAR="Regresar";

	protected JButton bEnviarEnvio;
	public static final String ENVIARENVIO="Enviar envio";
	
	protected JPanel pActividad;
	
	public VentanaRealizarEnvio(String idEstudiante, String idActividad, String idCamino)
	{
		this.idEstudiante=idEstudiante;
		this.idActividad=idActividad;
		this.idCamino=idCamino;
		
		this.setLayout(new BorderLayout());
		
		this.addPBotones();
		
		pActividad = new JPanel();
		pActividad.setLayout(new BoxLayout(pActividad, BoxLayout.Y_AXIS ) );
		
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );		
	}
	
	private void addPBotones()
	{
		JPanel pBotones = new JPanel();
		pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS ) );
        
        bEnviarEnvio = new JButton( "Enviar envio" );
        bEnviarEnvio.setActionCommand( ENVIARENVIO );
        bEnviarEnvio.addActionListener( this );
        pBotones.add( bEnviarEnvio );
        bEnviarEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        
	    bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.add(pBotones, BorderLayout.SOUTH);
	}
	
	public abstract void addActividadPanelInfo();

}
