package menuSwingProfesorCreadores;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import creadores.CreadorCamino;

public class VentanaCreacionCamino extends JFrame implements ActionListener
{
	private JPanel pInfoCamino;
	
	private JButton bRegresar;
	public static final String REGRESAR="regresar";
	
	private JButton bCrearCamino;
	public static final String CREARCAMINO="crear camino";
		
	private String idProfesor;
	
	private JTextField txtTitulo;
	private JTextField txtDescripcion;
	private JTextField txtDificultad;
		
	public static final String NUMOBJETIVOS="numero objetivos";
	
	private PanelObjetivos pObjetivos; 
	
	private List<String> lstObjetivos=new LinkedList<String>();
	
	public VentanaCreacionCamino(String idProfesor)
	{
		this.setLayout(new BorderLayout());

		this.idProfesor=idProfesor;
	
		addPBotones();
		addPInfoCamino();
		
        // Termina de configurar la ventana
        setTitle( "Learning Path System: Ver caminos disponibles" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 700, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
	}

	private void addPBotones()
	{
    	//Boton de regresar
		JPanel pBotones = new JPanel();
		pBotones.setLayout(new FlowLayout( ));
		
        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        bCrearCamino= new JButton( "Crear camino" );
        bCrearCamino.setActionCommand( CREARCAMINO );
        bCrearCamino.addActionListener( this );
        pBotones.add( bCrearCamino );
        bCrearCamino.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    	//Añado a la ventana
		this.add(pBotones, BorderLayout.SOUTH);
	}
	
	private void addPInfoCamino()
	{
		pInfoCamino = new JPanel();
		pInfoCamino.setLayout(new BoxLayout(pInfoCamino, BoxLayout.Y_AXIS ) );
		pInfoCamino.setBorder( new TitledBorder( "Informacion del camino" ) );
        
        //Titulo 
        txtTitulo = new JTextField( 30 );
        txtTitulo.setEditable( true );
    	
    	JLabel lblTitulo= new JLabel("Titulo: ");

    	JPanel pTitulo= new JPanel();
    	pTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pTitulo.add(lblTitulo);
    	pTitulo.add(txtTitulo);
    	
    	pInfoCamino.add(pTitulo);
    	pTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Descripcion
        txtDescripcion = new JTextField( 30 );
        txtDescripcion.setEditable( true );
    	
    	JLabel lblDescripcion= new JLabel("Descripcion: ");

    	JPanel pDescripcion= new JPanel();
    	pDescripcion.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pDescripcion.add(lblDescripcion);
    	pDescripcion.add(txtDescripcion);
    	
    	pInfoCamino.add(pDescripcion);
    	pDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Dificultad
        txtDificultad = new JTextField( 15 );
        txtDificultad.setEditable( true );
    	
    	JLabel lblDificultad= new JLabel("Dificultad (escribe el numero en formato 1.0): ");

    	JPanel pDificultad= new JPanel();
    	pDificultad.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pDificultad.add(lblDificultad);
    	pDificultad.add(txtDificultad);
    	
    	pInfoCamino.add(pDificultad);
    	pDificultad.setAlignmentX(Component.CENTER_ALIGNMENT);
       	
    	//Panel objetivos
    	pObjetivos= new PanelObjetivos(this);
    	pInfoCamino.add(pObjetivos);
    	
        //Add el panel
		this.add(pInfoCamino, BorderLayout.CENTER);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( REGRESAR ) )
        {
        	dispose();
        }
        else if (comando.equals(CREARCAMINO))
        {
        	try 
        	{
        		lstObjetivos=pObjetivos.guardarObjetivos(lstObjetivos);
				CreadorCamino.crearCaminoCero(txtTitulo.getText(), txtDescripcion.getText(), lstObjetivos, 
						Double.valueOf(txtDificultad.getText()), idProfesor);
				
        		JOptionPane.showMessageDialog(null, "Camino creado exitosamente");

			} 
        	catch (Exception e1) 
        	{
        		JOptionPane.showMessageDialog(null, e1.getMessage());
			}
        }
        else if (comando.equals(NUMOBJETIVOS))
        {
        	pObjetivos.mostrarPanelObjetivos();
        }
	}



}
