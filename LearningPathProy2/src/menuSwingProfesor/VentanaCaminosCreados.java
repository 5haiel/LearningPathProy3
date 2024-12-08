package menuSwingProfesor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import menuSwing.JComponentCaminosDisponibles;
import traductores.TraductorCamino;
import traductores.TraductorProfesor;

public class VentanaCaminosCreados extends JFrame implements ActionListener 
{
	private JScrollPane pCaminosCreados;
	
    private DefaultListModel<String> dataModel;
    
    private JList<String> jLstCaminos;
	
	private JButton bRegresar;
	private static final String REGRESAR="regresar";
	
	private String idProfesor;
	
	
	public VentanaCaminosCreados(String idProfesor)
	{		
		this.setLayout(new BorderLayout());
		this.idProfesor=idProfesor;
		
		//Añado componente de los caminos
		addPCaminosCreados();
    	
    	//Boton de regresar
		JPanel pBotones = new JPanel();
		pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS ) );
		
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
 
    	
    	//Añado a la ventana
		this.add(pBotones, BorderLayout.SOUTH);
		
		
        // Termina de configurar la ventana
        setTitle( "Learning Path System: Ver caminos disponibles" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 700, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
        
	}


	private void addPCaminosCreados()
	{		

        // Crea la lista con un modelo de datos 
        dataModel = new DefaultListModel<>( );
        dataModel.addAll( crearListStringCaminos() );
        
        jLstCaminos = new JList<>( dataModel );
        jLstCaminos.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        // Crear un panel con barras de desplazamiento para la lista
        pCaminosCreados = new JScrollPane( jLstCaminos );
        
		pCaminosCreados.setBorder( new TitledBorder( "Caminos creados" ) );
		
        pCaminosCreados.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        pCaminosCreados.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        this.add( pCaminosCreados);
	    
		//Add
		this.add(pCaminosCreados, BorderLayout.CENTER);
		pCaminosCreados.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	}

    
	private List<String> crearListStringCaminos() 
	{
		List<String> listStringCaminos= new LinkedList<String>();
		
		try
		{
			HashMap<String, String> caminos = TraductorProfesor.verCaminosCreados(idProfesor);
			for (HashMap.Entry<String, String> camino : caminos.entrySet())
			{
				listStringCaminos.add("Titulo: " + camino.getValue() + " ; ID: " + camino.getKey()+"\n");
			}
		}
		
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return listStringCaminos;
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
