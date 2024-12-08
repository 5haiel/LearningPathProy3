package menuSwingEstudiante;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import controllers.Inscriptor;
import menuSwing.JComponentCaminosDisponibles;
import traductores.TraductorCamino;
import traductores.TraductorEstudiante;

public class VentanaAvancesEstudiante extends JFrame implements ActionListener
{

	private JButton bRegresar;
	private static final String REGRESAR="regresar";
	
	private JTextArea textAreaAvances;
	
	private String idEstudiante;
	
	public VentanaAvancesEstudiante(String idEstudianteP)
	{
		this.idEstudiante=idEstudianteP;
		
		this.setLayout(new BorderLayout());
		
		//Añado text area de los avances
		textAreaAvances= createTextAreaAvances();
		this.add(textAreaAvances, BorderLayout.CENTER);
		textAreaAvances.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
	   	//Boton de regresar
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
 
    	this.add(bRegresar, BorderLayout.SOUTH);
		
		
        // Termina de configurar la ventana
        setTitle( "Learning Path System: Avances de un estudiante" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 700, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
	}
	
	
	private JTextArea createTextAreaAvances()
	{
		JTextArea txtArAvances= new JTextArea();
		String avancesString="";
		
		try
		{
			HashMap<String, String> avances = TraductorEstudiante.getAvancesCaminos(idEstudiante);
			for (HashMap.Entry<String, String> camino : avances.entrySet())
			{
				avancesString+="Camino: " + camino.getKey() + " | Avance: " + camino.getValue()+"\n";
			}
			
		}
		catch (Exception e)
		{
    		JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		txtArAvances.setText(avancesString);
		txtArAvances.setEditable(false);
		
		return txtArAvances;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( REGRESAR ) )
        {
        	dispose();
        }
		
	}

}
