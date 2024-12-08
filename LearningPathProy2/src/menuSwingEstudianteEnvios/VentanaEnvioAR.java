package menuSwingEstudianteEnvios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import marcadoresActividades.marcadorAR;
import traductores.TraductorAR;

public class VentanaEnvioAR extends VentanaRealizarEnvio
{
	private JLabel lblRecurso;
	private JLabel lblInstrucciones;
	
	public VentanaEnvioAR(String idEstudiante, String idActividad, String idCamino) 
	{
		super(idEstudiante, idActividad, idCamino);
		addActividadPanelInfo();
		
	    setTitle( "Learning Path System: Completar actividad de recurso" );
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
        else if (comando.equals(VentanaRealizarEnvio.ENVIARENVIO))
        {
        	try 
        	{
				marcadorAR.marcarARTerminado(this.idCamino, this.idActividad, this.idEstudiante);
			} 
        	catch (Exception e1)
        	{
        		JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage());
			}
        }
		
	}

	@Override
	public void addActividadPanelInfo() 
	{
		try 
		{
			String[] instruccionesRecurso = TraductorAR.retornarInstruccionesRecurso(idCamino, idActividad);

			lblInstrucciones.setText(instruccionesRecurso[0]);
			pActividad.add(lblInstrucciones);	
			
			lblRecurso.setText(instruccionesRecurso[1]);
			pActividad.add(lblRecurso);	
		} 
		catch (Exception e) 
		{
    		JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
		

	}

}
