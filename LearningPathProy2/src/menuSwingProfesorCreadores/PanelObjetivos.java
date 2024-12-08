package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelObjetivos extends JPanel
{
	private JComboBox<Integer> ccbNumObjetivos;
	private ActionListener listener;
	
	private JPanel pObjetivos;
	public static final String NUMOBJETIVOS= VentanaCreacionCamino.NUMOBJETIVOS ;

	public PanelObjetivos(ActionListener listener)
	{
    	this.listener=listener;

    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS ) );

    	
    	JPanel pNumObjetivos= new JPanel();

    	JLabel lblObjetivos= new JLabel("Número de objetivos: ");

    	Integer[] numeroObjetivos = new Integer[]{1,2,3,4,5,6,7,8,9,10};
    	ccbNumObjetivos=new JComboBox<Integer>(numeroObjetivos);
    	ccbNumObjetivos.setEnabled(true);
    	ccbNumObjetivos.addActionListener(this.listener);
    	ccbNumObjetivos.setActionCommand( NUMOBJETIVOS );
    	
    	pNumObjetivos.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pNumObjetivos.add(lblObjetivos);
    	pNumObjetivos.add(ccbNumObjetivos);
       	
    	this.add(pNumObjetivos);
    	pNumObjetivos.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Panel de objetivos
    	pObjetivos= new JPanel();
    	pObjetivos.setLayout(new BoxLayout(pObjetivos, BoxLayout.Y_AXIS ) );
    	
        JScrollPane scroll = new JScrollPane( pObjetivos );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

    	this.add(scroll);

	}
		
	public void mostrarPanelObjetivos() 
	{
		pObjetivos.setVisible(false);

		pObjetivos.removeAll();

		Integer numObjetivos=(Integer) ccbNumObjetivos.getSelectedItem();
		
		for (int i = 1; i < numObjetivos+1; i++) 
		{
			JTextField txtObjetivo = new JTextField( 25 );
			txtObjetivo.setEditable( true );
	    	
	    	JLabel lblObjetivo= new JLabel("Objetivo "+String.valueOf(i)+": ");

	    	JPanel pObjetivoInd= new JPanel();
	    	pObjetivoInd.setLayout(new FlowLayout(FlowLayout.CENTER));
	    	pObjetivoInd.add(lblObjetivo);
	    	pObjetivoInd.add(txtObjetivo);
	    	
	    	pObjetivos.add(pObjetivoInd);
	    	pObjetivoInd.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		pObjetivos.setVisible(true);
	}

	public void guardarObjetivos(List<String> lstObjetivos) 
	{
		for (Component pObjetivoInd : pObjetivos.getComponents())
		{
			Component[] lblObjTxtObjetivo = ((JPanel) pObjetivoInd).getComponents();
			String strObjetivo = ((JTextField) lblObjTxtObjetivo[1]).getText();
			
			lstObjetivos.add(strObjetivo);
		}
	}
}
