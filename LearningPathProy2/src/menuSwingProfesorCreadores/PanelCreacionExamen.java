package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCreacionExamen extends PanelCreacionActividad
{
	protected PanelPreguntasAbiertas pPreguntas;
	protected JTextField txtCalificacionMin; 
	
	public PanelCreacionExamen() 
	{
		super();
	}

	@Override
	public void addInfoEspecifica() 
	{
		pPreguntas = new PanelPreguntasAbiertas(this);
		this.add(pPreguntas);
		pPreguntas.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//calificacion minima
		txtCalificacionMin = new JTextField( 30 );
		txtCalificacionMin.setEditable( true );
    	
    	JLabel lblCal= new JLabel("Calificación mínima: ");

    	JPanel pCalMin= new JPanel();
    	pCalMin.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pCalMin.add(lblCal);
    	pCalMin.add(txtCalificacionMin);
    	
    	this.add(pCalMin);
    	pCalMin.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
                
        if (comando.equals(PanelObjetivos.NUMOBJETIVOS))
        {
        	pObjetivos.mostrarPanelObjetivos();
        }
        else if (comando.equals(PanelPreguntasAbiertas.NUMPREGUNTAS))
        {
        	pPreguntas.mostrarPanelPreguntas();
        }
	}

}
