package menuSwing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controllers.Autentificador;
import controllers.LearningPathSystem;
import menuSwingEstudiante.VentanaMenuEstudiante;
import menuSwingProfesor.VentanaMenuProfesor;
import persistencia.CentralPersistencia;
import traductores.TraductorEstudiante;
import traductores.TraductorProfesor;
import usuarios.Usuario;

public class VentanaLogIn extends JFrame implements ActionListener
{
	public static LearningPathSystem LPS = null;
	public static Autentificador autentificador = null;
	private static Usuario usuario;
	
	private JPanel pBotones;
	
	private JButton bIngresar;
	private JButton bNuevoUsuario;
	private JButton bSalir;
	
	private JPanel pTxtFields;
	private JTextField txtLogin;
	private JTextField txtPassword;
		
	private VentanaRegistrarUsuario ventRegistrar=null;
	
	public static final String INGRESAR="Ingresar";
	public static final String NUEVOUSUARIO="Nuevo usuario";
	public static final String SALIR="Salir";
	
    public VentanaLogIn( )
    {
        setLayout( new BorderLayout( ) );

        pBotones = new JPanel();
        pBotones.setLayout(new BoxLayout(pBotones, BoxLayout.Y_AXIS) );
        
        pTxtFields = new JPanel();
        pTxtFields.setLayout(new BoxLayout(pTxtFields, BoxLayout.Y_AXIS) );
        
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
       
        pTxtFields.setBackground(Color.WHITE);
        add( pTxtFields, BorderLayout.CENTER );
        
        //Botones
        bIngresar = new JButton( "Ingresar" );
        bIngresar.setActionCommand( INGRESAR );
        bIngresar.addActionListener( this );
        pBotones.add( bIngresar );
        bIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bNuevoUsuario = new JButton( "Crear nuevo usuario" );
        bNuevoUsuario.setActionCommand( NUEVOUSUARIO );
        bNuevoUsuario.addActionListener( this );
        pBotones.add( bNuevoUsuario );
        bNuevoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        bSalir = new JButton( "Salir" );
        bSalir.setActionCommand( SALIR );
        bSalir.addActionListener( this );
        pBotones.add( bSalir );
        bSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add( pBotones, BorderLayout.SOUTH );


        // Termina de configurar la ventana
        setTitle( "Learning Path System" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setLocationRelativeTo( null );
        setVisible( true );
    }

    
    /**
     * Inicia la aplicación creando la interfaz de la aplicación
     * @param args
     */
    public static void main( String[] args )
    {
    	try 
    	{
			CentralPersistencia.cargarTodo(false);
		} 
    	catch (Exception e) 
    	{
			System.out.println(e.getMessage());
		}
    	
		if (LPS == null)
			LPS = LearningPathSystem.getInstance();

		if (autentificador == null)
			autentificador = Autentificador.getInstance(LPS);
        
    	new VentanaLogIn( );
    }

    public void mostrarVentanaRegistrarUsuario( )
    {
        if( ventRegistrar == null || !ventRegistrar.isVisible( ) )
        {
        	ventRegistrar = new VentanaRegistrarUsuario();
        	ventRegistrar.setVisible( true );
        }
    }
    
    public void abrirVentanaMenuProfesor(String idProfesor)
    {
    	VentanaMenuProfesor ventProfesor = new VentanaMenuProfesor(idProfesor);
    	ventProfesor.setVisible( true );
    }
    
    public void abrirVentanaMenuEstudiante(String idEstudiante)
    {
    	VentanaMenuEstudiante ventEstudiante = new VentanaMenuEstudiante(idEstudiante);
    	ventEstudiante.setVisible( true );
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
        String comando = e.getActionCommand( );
        
        if( comando.equals( SALIR ) )
        {
			try
			{
				CentralPersistencia.guardarTodo(false);
			}
			catch (Exception e1)
			{
        		JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
            System.exit(0);
        }
        
        else if( comando.equals( INGRESAR ) )
        {
        	try 
        	{
				usuario=autentificador.autentificar(txtLogin.getText(), txtPassword.getText());
				
	        	if (usuario.getType().equals(Usuario.ESTUDIANTE))
	        	{
	        		String idEstudiante=TraductorEstudiante.getIDfromLogin(txtLogin.getText());
	        		
	        		this.abrirVentanaMenuEstudiante(idEstudiante);
	        		dispose();
	        	}
	        	else
	        	{
	        		String idProfesor=TraductorProfesor.getIDfromLogin(txtLogin.getText());

	        		this.abrirVentanaMenuProfesor(idProfesor);
	        		dispose();
	        	}
			} 
        	catch (Exception e2) 
        	{
        		JOptionPane.showMessageDialog(null, e2.getMessage());
			}
        	

        }
		
        else if (comando.equals(NUEVOUSUARIO))
        {
        	mostrarVentanaRegistrarUsuario();
        }
	}


}
