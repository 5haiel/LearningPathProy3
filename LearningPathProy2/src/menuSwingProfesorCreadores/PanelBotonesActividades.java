package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class PanelBotonesActividades extends JPanel
{
	private JButton bAR;
	public static final String ACTRECURSO="Actividad de recurso";

	private JButton bEncuesta;
	public static final String ENCUESTA="Encuesta";
	
	private JButton bQuiz;
	public static final String QUIZ="Quiz";
	
	private JButton bTarea;
	public static final String TAREA="Quiz";

	private JButton bExamen;
	public static final String EXAMEN="Quiz";
	
	private ActionListener listener;
	
	public PanelBotonesActividades(ActionListener actionListenerComponent)
	{
		this.listener=actionListenerComponent;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS ) );
		
		bAR= new JButton( "Crear actividad de recurso" );
		bAR.setActionCommand( ACTRECURSO );
		bAR.addActionListener( listener );
        this.add( bAR );
        bAR.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bEncuesta= new JButton( "Crear encuesta" );
        bEncuesta.setActionCommand( ENCUESTA );
        bEncuesta.addActionListener( listener );
        this.add( bEncuesta );
        bEncuesta.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bTarea= new JButton( "Crear tarea" );
        bTarea.setActionCommand( TAREA );
        bTarea.addActionListener( listener );
        this.add( bTarea );
        bTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bExamen= new JButton( "Crear examen" );
        bExamen.setActionCommand( EXAMEN );
        bExamen.addActionListener( listener );
        this.add( bExamen );
        bExamen.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bQuiz= new JButton( "Crear quiz" );
        bQuiz.setActionCommand( QUIZ );
        bQuiz.addActionListener( listener );
        this.add( bQuiz );
        bQuiz.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
}
