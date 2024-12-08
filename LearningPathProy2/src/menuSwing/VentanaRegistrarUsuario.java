package menuSwing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.Autentificador;
import controllers.LearningPathSystem;
import persistencia.CentralPersistencia;
import usuarios.Usuario;

public class VentanaRegistrarUsuario extends JFrame implements ActionListener
{
	public static Autentificador autentificador = null;
	public static LearningPathSystem LPS = null;
	
	private JPanel pBotones;
	
	private JButton bRegistrar;
	private JButton bRegresar;
	
	private JPanel pTxtFields;
	private JTextField txtNombre;
	private JTextField txtLogin;
	private JTextField txtPassword;
	
    private JComboBox<String> ccbTipoUsuario;

	public static final String REGISTRAR="Registrar";
	public static final String REGRESAR="Salir";
	
    public VentanaRegistrarUsuario( )
    {
        setLayout( new BorderLayout( ) );

        pBotones = new JPanel();
        pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS) );

        pTxtFields = new JPanel();
        pTxtFields.setLayout(new BoxLayout(pTxtFields, BoxLayout.Y_AXIS) );
        
        //Nombre 
        txtNombre = new JTextField( 15 );
        txtNombre.setEditable( true );
    	
    	JLabel nombreLabel= new JLabel("Nombre: ");

    	JPanel panelNombre= new JPanel();
    	panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER));
    	panelNombre.add(nombreLabel);
    	panelNombre.add(txtNombre);
    	
    	pTxtFields.add(panelNombre);
        panelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        ///Login
        txtLogin = new JTextField( 15 );
        txtLogin.setEditable( true );
    	
    	JLabel loginLabel= new JLabel("Login: ");

    	JPanel panelLogin= new JPanel();
    	panelLogin.setLayout(new FlowLayout(FlowLayout.CENTER));
    	panelLogin.add(loginLabel);
    	panelLogin.add(txtLogin);
    	
    	pTxtFields.add(panelLogin);
        panelLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Password 
        txtPassword = new JTextField( 15 );
        txtPassword.setEditable( true );
    	
    	JLabel passwrodLabel= new JLabel("Contraseña: ");

    	JPanel panelPassword= new JPanel();
    	panelPassword.setLayout(new FlowLayout(FlowLayout.CENTER));
    	panelPassword.add(passwrodLabel);
    	panelPassword.add(txtPassword);
    	
    	pTxtFields.add(panelPassword);
        panelPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Tipo de usuario
    	String[] tipoUsuario = new String[]{"Estudiante", "Profesor"};
    	ccbTipoUsuario=new JComboBox<String>(tipoUsuario);
    	ccbTipoUsuario.setEnabled(true);
    	
    	JLabel usuarioLabel= new JLabel("Tipo de usuario: ");

       	JPanel panelTipoUsuario= new JPanel();
       	panelTipoUsuario.setLayout(new FlowLayout(FlowLayout.CENTER));
       	panelTipoUsuario.add(usuarioLabel);
       	panelTipoUsuario.add(ccbTipoUsuario);
       	
       	pTxtFields.add(panelTipoUsuario);
        panelTipoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
       	
        pTxtFields.setBackground(Color.WHITE);
        add( pTxtFields, BorderLayout.CENTER );
        
        //Botones
        bRegistrar = new JButton( "Registrar nuevo usuario" );
        bRegistrar.setActionCommand( REGISTRAR );
        bRegistrar.addActionListener( this );
        pBotones.add( bRegistrar );
        bRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        bRegresar = new JButton( "Regresar" );
        bRegresar.setActionCommand( REGRESAR );
        bRegresar.addActionListener( this );
        pBotones.add( bRegresar );
        bRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add( pBotones, BorderLayout.SOUTH );


        // Termina de configurar la ventana
        setTitle( "Learning Path System: Registrar usuario" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );
        setVisible( true );
        

		if (LPS == null)
			LPS = LearningPathSystem.getInstance();

		if (autentificador == null)
			autentificador = Autentificador.getInstance(LPS);
    }

    /**
     * Indica si en el selector se seleccionó la opción que dice que el restaurante fue visitado
     * @return
     */
    public int getTipoUsuario( )
    {
    	if (ccbTipoUsuario.getSelectedItem( ).equals("Estudiante"))
    	{
    		return 1;
    	}
    	else
    	{
    		return 2;
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
        
        else if( comando.equals( REGISTRAR ) )
        {
			try
			{
				boolean registro = autentificador.registrarUsuario(txtNombre.getText(), txtPassword.getText(), 
						txtLogin.getText(), getTipoUsuario()); 
				if (registro == true)
				{
	        		JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
				}
				else
				{
	        		JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario");

				}
			}
			catch (Exception e1)
			{
        		JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
        
		
	}

}
