package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelPreguntasAbiertas extends JPanel
{
	private JComboBox<Integer> ccbNumPreguntas;
	private ActionListener listener;
	
	private JPanel pPreguntas;
	public static final String NUMPREGUNTAS= "numero preguntas" ;

	public PanelPreguntasAbiertas(ActionListener listener)
	{
    	this.listener=listener;

    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS ) );

    	
    	JPanel pNumPreguntas= new JPanel();

    	JLabel lblNumPreguntas= new JLabel("Número de preguntas: ");

    	Integer[] numeroPreguntas = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    	ccbNumPreguntas=new JComboBox<Integer>(numeroPreguntas);
    	ccbNumPreguntas.setEnabled(true);
    	ccbNumPreguntas.addActionListener(this.listener);
    	ccbNumPreguntas.setActionCommand( NUMPREGUNTAS );
    	
    	pNumPreguntas.setLayout(new FlowLayout(FlowLayout.CENTER));
    	pNumPreguntas.add(lblNumPreguntas);
    	pNumPreguntas.add(ccbNumPreguntas);
       	
    	this.add(pNumPreguntas);
    	pNumPreguntas.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	//Panel de preguntas
    	pPreguntas= new JPanel();
    	pPreguntas.setLayout(new BoxLayout(pPreguntas, BoxLayout.Y_AXIS ) );
    	
        JScrollPane scroll = new JScrollPane( pPreguntas );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

    	this.add(scroll);

	}
		
	public void mostrarPanelPreguntas() 
	{
		pPreguntas.setVisible(false);

		pPreguntas.removeAll();

		Integer numPreguntas=(Integer) ccbNumPreguntas.getSelectedItem();
		
		for (int i = 1; i < numPreguntas+1; i++) 
		{
			JTextField txtPregunta = new JTextField( 25 );
			txtPregunta.setEditable( true );
	    	
	    	JLabel lblPregunta= new JLabel("Pregunta "+String.valueOf(i)+": ");

	    	JPanel pPreguntaInd= new JPanel();
	    	pPreguntaInd.setLayout(new FlowLayout(FlowLayout.CENTER));
	    	pPreguntaInd.add(lblPregunta);
	    	pPreguntaInd.add(txtPregunta);
	    	
	    	pPreguntas.add(pPreguntaInd);
	    	pPreguntaInd.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
		pPreguntas.setVisible(true);
	}

	public List<String> guardarPreguntas(List<String> lstPreguntas) 
	{
		for (Component pObjetivoInd : pPreguntas.getComponents())
		{
			Component[] lblObjTxtObjetivo = ((JPanel) pObjetivoInd).getComponents();
			String strObjetivo = ((JTextField) lblObjTxtObjetivo[1]).getText();
			
			lstPreguntas.add(strObjetivo);
		}
		
		return lstPreguntas;
	}
}
