package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public abstract class PanelCreacionActividad extends JPanel implements ActionListener
{
	protected JTextField txtIDCamino;
	protected JTextField txtPosCamino;

	protected JTextField txtNombre;
	protected JTextField txtDescripcion;
	protected JTextField txtDificultad;
	protected JTextField txtDuracion;
	
	protected JTextField txtAnio;
	protected JTextField txtMes;
	protected JTextField txtDia;
	
    private JComboBox<String> ccbObligatoria;

	protected PanelObjetivos pObjetivos;

	public PanelCreacionActividad()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS ) );
		this.setBorder( new TitledBorder( "Informacion de la actividad" ) );
       	
		addTextFields();
		
    	//Panel objetivos
    	pObjetivos= new PanelObjetivos(this);
    	this.add(pObjetivos);
    	
    	//Obligatoria
      	String[] siNo = new String[]{"Si", "No"};
      	ccbObligatoria=new JComboBox<String>(siNo);
      	ccbObligatoria.setEnabled(true);
    	
    	JLabel lblObligatoria= new JLabel("Obligatoria: ");

       	JPanel pObligatoria= new JPanel();
       	pObligatoria.setLayout(new FlowLayout(FlowLayout.CENTER));
       	pObligatoria.add(lblObligatoria);
       	pObligatoria.add(ccbObligatoria);
       	
       	this.add(pObligatoria);
       	pObligatoria.setAlignmentX(Component.CENTER_ALIGNMENT);
       	
       	//Info especial de la actividad
       	this.addInfoEspecifica();
	}
	
	public void addTextFields()
	{
        //ID camino 
		txtIDCamino = new JTextField( 30 );
		txtIDCamino.setEditable( true );
    	
    	JLabel lblIDCamino= new JLabel("ID del camino: ");

    	JPanel pIDCamino= new JPanel();
    	pIDCamino.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pIDCamino.add(lblIDCamino);
    	pIDCamino.add(txtIDCamino);
    	
    	this.add(pIDCamino);
    	pIDCamino.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
        //Titulo 
		txtNombre = new JTextField( 30 );
		txtNombre.setEditable( true );
    	
    	JLabel lblNombre= new JLabel("Nombre: ");

    	JPanel pNombre= new JPanel();
    	pNombre.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pNombre.add(lblNombre);
    	pNombre.add(txtNombre);
    	
    	this.add(pNombre);
    	pNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Descripcion
        txtDescripcion = new JTextField( 30 );
        txtDescripcion.setEditable( true );
    	
    	JLabel lblDescripcion= new JLabel("Descripcion: ");

    	JPanel pDescripcion= new JPanel();
    	pDescripcion.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pDescripcion.add(lblDescripcion);
    	pDescripcion.add(txtDescripcion);
    	
    	this.add(pDescripcion);
    	pDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Dificultad
        txtDificultad = new JTextField( 15 );
        txtDificultad.setEditable( true );
    	
    	JLabel lblDificultad= new JLabel("Dificultad (escribe el numero en formato 1.0): ");

    	JPanel pDificultad= new JPanel();
    	pDificultad.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pDificultad.add(lblDificultad);
    	pDificultad.add(txtDificultad);
    	
    	this.add(pDificultad);
    	pDificultad.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Duracion
        txtDuracion = new JTextField( 15 );
        txtDuracion.setEditable( true );
    	
    	JLabel lblDuracion= new JLabel("Duracion: ");

    	JPanel pDuracion= new JPanel();
    	pDuracion.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pDuracion.add(lblDuracion);
    	pDuracion.add(txtDuracion);
    	
    	this.add(pDuracion);

    	//Posicion
        txtPosCamino = new JTextField( 15 );
        txtPosCamino.setEditable( true );
    	
    	JLabel lblPosCamino= new JLabel("Posicion en el camino: ");

    	JPanel pPosCamino= new JPanel();
    	pPosCamino.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pPosCamino.add(lblDuracion);
    	pPosCamino.add(txtDuracion);
    	
    	this.add(pPosCamino);

    	//Fecha
    	JPanel pFecha= new JPanel();
    	pFecha.setLayout(new FlowLayout(FlowLayout.CENTER));
    	
        txtAnio = new JTextField( 15 );
        txtAnio.setEditable( true );
    	JLabel lblAnio= new JLabel("Año límite: ");
    	pFecha.add(lblAnio);
    	pFecha.add(txtAnio);
    	
        txtMes = new JTextField( 15 );
        txtMes.setEditable( true );
    	JLabel lblMes= new JLabel("Mes límite: ");
    	pFecha.add(lblMes);
    	pFecha.add(txtMes);
    	
        txtDia = new JTextField( 15 );
        txtDia.setEditable( true );
    	JLabel lblDia= new JLabel("Dia límite: ");
    	pFecha.add(lblDia);
    	pFecha.add(txtDia);
    	
    	this.add(pFecha);

    	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
        
        if (comando.equals(PanelObjetivos.NUMOBJETIVOS))
        {
        	pObjetivos.mostrarPanelObjetivos();
        }
	}
	
	public abstract void addInfoEspecifica();
	
}
