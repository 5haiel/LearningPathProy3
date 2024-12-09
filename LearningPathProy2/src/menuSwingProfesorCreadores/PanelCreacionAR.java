package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCreacionAR extends PanelCreacionActividad
{
	protected JTextField txtRecurso;
	protected JTextField txtInstrucciones;
	
	public PanelCreacionAR()
	{
		super();
	}

	@Override
	public void addInfoEspecifica() 
	{
		//Recurso
		txtRecurso = new JTextField( 30 );
		txtRecurso.setEditable( true );
    	
    	JLabel lblRecurso= new JLabel("Recurso: ");

    	JPanel pRecurso= new JPanel();
    	pRecurso.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pRecurso.add(lblRecurso);
    	pRecurso.add(txtRecurso);
    	
    	this.add(pRecurso);
    	pRecurso.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
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
