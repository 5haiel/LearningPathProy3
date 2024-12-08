package menuSwingEstudiante;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.Inscriptor;
import menuSwing.JComponentCaminosDisponibles;
import traductores.TraductorCamino;

public class VentanaCaminosDispInscripcion extends JFrame implements ActionListener
{
	private JComponentCaminosDisponibles lCaminosDisp;
	
	private JPanel pInscripcion;
	private JLabel lNombreCamino;
	
	private JButton bRegresar;
	private static final String REGRESAR="regresar";
	
	private JButton bInscripcion;
	private static final String INSCRIBIR="inscribir";
	
	private String idEstudiante;
	
	public VentanaCaminosDispInscripcion(String idEstudianteP)
	{
		this.idEstudiante=idEstudianteP;
		
		this.setLayout(new BorderLayout());
		
		//Añado componente de los caminos
		lCaminosDisp= new JComponentCaminosDisponibles(this);
		this.add(lCaminosDisp, BorderLayout.CENTER);
		lCaminosDisp.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Añado botones y textField de inscripcion
		pInscripcion= new JPanel();
		pInscripcion.setLayout(new BoxLayout(pInscripcion, BoxLayout.Y_AXIS) );
		
	    //Nombre de camino 
		lNombreCamino = new JLabel("");
    	
    	JLabel tagLabel= new JLabel("Camino que te deseas inscribir: ");

    	JPanel panelNombreCamino= new JPanel();
    	panelNombreCamino.setLayout(new FlowLayout(FlowLayout.CENTER));
    	panelNombreCamino.add(tagLabel);
    	panelNombreCamino.add(lNombreCamino);
    	
    	pInscripcion.add(panelNombreCamino);
    	panelNombreCamino.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
        //Boton de inscripcion
        bInscripcion = new JButton( "Inscribirse" );
        bInscripcion.setActionCommand( INSCRIBIR );
        bInscripcion.addActionListener( this );
        pInscripcion.add( bInscripcion );
        bInscripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Boton de regresar
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pInscripcion.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
 
    	
    	//Añado a la ventana
		this.add(pInscripcion, BorderLayout.SOUTH);
		
		
        // Termina de configurar la ventana
        setTitle( "Learning Path System: Inscribirse a un camino" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 700, 600 );
        setLocationRelativeTo( null );
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
        else if(comando.equals( INSCRIBIR ))
        {
        	String idCamino = null;
        	
        	if (!(lNombreCamino.getText().equals("")))
        	{
    			try
    			{
    				idCamino = TraductorCamino.getIDfromNombre(lNombreCamino.getText());

    				Inscriptor.inscribirseCamino(idCamino, idEstudiante);

    				JOptionPane.showMessageDialog(null, "Usuario inscrito exitosamente");
    	
    			}
    			catch (Exception e1)
    			{
            		JOptionPane.showMessageDialog(null, e1.getMessage());
    			}
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(null, "No se ha escogido un camino");
        	}
        }
		
	}

	public void changeLblCaminoSeleccionado(String nombreCamino) 
	{
		lNombreCamino.setText(nombreCamino);
		
	}
}
