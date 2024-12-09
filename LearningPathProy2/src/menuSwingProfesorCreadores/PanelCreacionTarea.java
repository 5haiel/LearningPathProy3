package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCreacionTarea extends PanelCreacionActividad
{
	protected JTextField txtInstrucciones;
	
	public PanelCreacionTarea()
	{
		super();
	}

	@Override
	public void addInfoEspecifica() 
	{	
		//Instrucciones
    	txtInstrucciones = new JTextField( 30 );
    	txtInstrucciones.setEditable( true );
    	
    	JLabel lblInstrucciones= new JLabel("Instrucciones: ");

    	JPanel pInstrucciones= new JPanel();
    	pInstrucciones.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pInstrucciones.add(lblInstrucciones);
    	pInstrucciones.add(txtInstrucciones);
    	
    	this.add(pInstrucciones);
    	pInstrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	
}
