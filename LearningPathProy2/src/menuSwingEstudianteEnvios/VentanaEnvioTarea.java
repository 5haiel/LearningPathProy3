package menuSwingEstudianteEnvios;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import marcadoresActividades.marcadorAR;
import senders.TareaSender;
import traductores.TraductorAR;
import traductores.TraductorTarea;

public class VentanaEnvioTarea extends VentanaRealizarEnvio
{
	private JTextField txtMetodoEntrega;
	private JLabel lblInstrucciones;
	
	public VentanaEnvioTarea(String idEstudiante, String idActividad, String idCamino) 
	{
		super(idEstudiante, idActividad, idCamino);
		addActividadPanelInfo();
		
	    setTitle( "Learning Path System: Completar tarea" );
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
				TareaSender.addMetodoEntregaTarea(this.idCamino, this.idActividad, this.idEstudiante,
						this.txtMetodoEntrega.getText());
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
			String instrucciones = TraductorTarea.retornarInstrucciones(idCamino, idActividad);

			lblInstrucciones.setText(instrucciones);
			pActividad.add(lblInstrucciones);	
						
			//Metodo de entrega
			txtMetodoEntrega = new JTextField( 15 );
			txtMetodoEntrega.setEditable( true );
	    	
	    	JLabel lblEntrega= new JLabel("Metodo de entrega: ");

	    	JPanel pEntrega= new JPanel();
	    	pEntrega.setLayout(new FlowLayout(FlowLayout.CENTER));
	    	pEntrega.add(lblEntrega);
	    	pEntrega.add(txtMetodoEntrega);
	    	
	    	pActividad.add(pEntrega);
	    	pEntrega.setAlignmentX(Component.CENTER_ALIGNMENT);
			

		} 
		catch (Exception e) 
		{
    		JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());
		}
		
	}

}
