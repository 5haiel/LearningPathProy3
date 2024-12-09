package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import creadores.CreadorCamino;

public class PanelCreacionEncuesta extends PanelCreacionActividad implements ActionListener
{
	protected PanelPreguntasAbiertas pPreguntas;
		
	public PanelCreacionEncuesta() 
	{
		super();
	}

	@Override
	public void addInfoEspecifica() 
	{
		pPreguntas = new PanelPreguntasAbiertas(this);
		this.add(pPreguntas);
		pPreguntas.setAlignmentX(Component.CENTER_ALIGNMENT);
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
