package menuSwingEstudianteEnvios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import caminosActividades.Actividad;
import menuSwingEstudiante.VentanaAvancesEstudiante;

public class VentanaActividadIniciada extends JFrame implements ActionListener
{
	private PanelActividadInfo pActividad;
	
	private JPanel pBotones;
	
	private JButton bRegresar;
	public static final String REGRESAR="Regresar";

	private JButton bRealizarEnvio;
	public static final String REALIZARENVIO="Realizar envio";

	private String idCamino;
	private String idActividad;
	private String idEstudiante;
	
	private VentanaRealizarEnvio ventRealizarEnvio=null;

	public VentanaActividadIniciada(String idCamino, String idActividad, String idEstudiante)
	{
		this.idCamino=idCamino;
		this.idActividad=idActividad;
		this.idEstudiante=idEstudiante;
		
		this.setLayout(new BorderLayout());
		
		pActividad=new PanelActividadInfo(idActividad, idCamino);
		
		this.add(pActividad, BorderLayout.CENTER);
		
		addPBotones();
		
        // Termina de configurar la ventana
        setTitle( "Learning Path System: Actividad iniciada" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );
        setVisible( true );
	}
	
	private void addPBotones()
	{
		pBotones= new JPanel();
		pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS ) );

        bRealizarEnvio = new JButton( "Realizar envio" );
        bRealizarEnvio.setActionCommand( REALIZARENVIO );
        bRealizarEnvio.addActionListener( this );
        pBotones.add( bRealizarEnvio );
        bRealizarEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.add(pBotones, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( REGRESAR ) )
        {
        	dispose();
        }
        else if( comando.equals(REALIZARENVIO))
        {
        	abrirVentanaEnvio();
        }
		
	}

	private void abrirVentanaEnvio() 
	{
		String typeActividad = pActividad.getTipoActividad();
		
		if (typeActividad.equals(Actividad.ACTIVIDADRECURSO))
		{
	        if( ventRealizarEnvio == null || !ventRealizarEnvio.isVisible( ) )
	        {
	        	ventRealizarEnvio = new VentanaEnvioAR(idEstudiante, idActividad, idCamino);
	        	ventRealizarEnvio.setVisible( true );
	        }
		}
		else if (typeActividad.equals(Actividad.EXAMEN))
		{
	        if( ventRealizarEnvio == null || !ventRealizarEnvio.isVisible( ) )
	        {
	        	ventRealizarEnvio = new VentanaEnvioExamen(idEstudiante, idActividad, idCamino);
	        	ventRealizarEnvio.setVisible( true );
	        }
		}
		else if (typeActividad.equals(Actividad.ENCUESTA))
		{
	        if( ventRealizarEnvio == null || !ventRealizarEnvio.isVisible( ) )
	        {
	        	ventRealizarEnvio = new VentanaEnvioExamen(idEstudiante, idActividad, idCamino);
	        	ventRealizarEnvio.setVisible( true );
	        }
		}
		else if (typeActividad.equals(Actividad.QUIZ))
		{
	        if( ventRealizarEnvio == null || !ventRealizarEnvio.isVisible( ) )
	        {
	        	ventRealizarEnvio = new VentanaEnvioQuiz(idEstudiante, idActividad, idCamino);
	        	ventRealizarEnvio.setVisible( true );
	        }
		}
		else
		{
	        if( ventRealizarEnvio == null || !ventRealizarEnvio.isVisible( ) )
	        {
	        	ventRealizarEnvio = new VentanaEnvioTarea(idEstudiante, idActividad, idCamino);
	        	ventRealizarEnvio.setVisible( true );
	        }
		}
		
	}
	
	
}
