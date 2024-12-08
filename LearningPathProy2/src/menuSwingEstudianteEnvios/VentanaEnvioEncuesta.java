package menuSwingEstudianteEnvios;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import senders.ExamenSender;
import traductores.TraductorExamen;

public class VentanaEnvioEncuesta extends VentanaRealizarEnvio
{
	private HashMap<String, String > hashRespuestas = new HashMap<String, String>();

	public VentanaEnvioEncuesta(String idEstudiante, String idActividad, String idCamino) 
	{
		super(idEstudiante, idActividad, idCamino);
		addActividadPanelInfo();
		
	    setTitle( "Learning Path System: Completar encuesta" );
        setVisible( true );
	}

	public void guardarRespuestas()
	{
		for (Component pPregunta : pActividad.getComponents())
		{
			Component[] lblPreguntaTxtRespuesta = ((JPanel) pPregunta).getComponents();
			String respuesta = ((JTextArea) lblPreguntaTxtRespuesta[1]).getText();
			String pregunta = ((JLabel) lblPreguntaTxtRespuesta[0]).getText();
			
			hashRespuestas.put(pregunta, respuesta);
		}
	}
	
	@Override
	public void addActividadPanelInfo() 
	{
		try
		{
			List<String> listPreguntas = TraductorExamen.retornarPreguntas(idCamino, idActividad);
			
			for (String preguntaInd: listPreguntas)
			{
				JTextArea txtRespuesta = new JTextArea( );
				txtRespuesta.setEditable( true );
		    	
		    	JLabel lblPregunta= new JLabel(preguntaInd);

		    	JPanel pPregunta= new JPanel();
		    	pPregunta.setLayout(new FlowLayout(FlowLayout.CENTER));
		    	pPregunta.add(lblPregunta);
		    	pPregunta.add(txtRespuesta);
		    	
		    	pActividad.add(pPregunta);
		    	pPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
			}
			
		}
      	catch (Exception e1)
    	{
    		JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( REGRESAR ) )
        {
        	dispose();
        }
        else if (comando.equals(VentanaRealizarEnvio.ENVIARENVIO))
        {
        	guardarRespuestas();
        	
        	try 
        	{
				ExamenSender.sendEnvioExamen(idCamino, idActividad, idEstudiante, hashRespuestas);
			} 
        	catch (Exception e1)
        	{
        		JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
			}
        }
		
	}

}
