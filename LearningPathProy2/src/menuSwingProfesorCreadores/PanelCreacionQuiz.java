package menuSwingProfesorCreadores;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import caminosActividades.OpcionQuiz;
import caminosActividades.PreguntaQuiz;

public class PanelCreacionQuiz extends PanelCreacionActividad
{
	protected JRadioButton rbMultipleChoice;
    protected JRadioButton rbTrueFalse;
    protected JTextField txtNumPreguntas;
    protected JButton btnGenerarPreguntas;
    protected JPanel pPreguntasDinamico; // Panel para preguntas dinámicas
    protected JTextField txtCalificacionMin;
    protected JScrollPane scrollPreguntas;
	    
    private boolean tipoQuizVerdaderoFalso;
    private int numPreguntas;
    
	public PanelCreacionQuiz() 
	{
		super();
	}

	@Override
    public void addInfoEspecifica() {
        // Tipo de quiz
        rbMultipleChoice = new JRadioButton("Opción múltiple (4 opciones)");
        rbTrueFalse = new JRadioButton("Verdadero/Falso (2 opciones)");
        ButtonGroup grupoTipoQuiz = new ButtonGroup();
        grupoTipoQuiz.add(rbMultipleChoice);
        grupoTipoQuiz.add(rbTrueFalse);

        JPanel pTipoQuiz = new JPanel();
        pTipoQuiz.setLayout(new FlowLayout(FlowLayout.CENTER));
        pTipoQuiz.add(new JLabel("Tipo de Quiz:"));
        pTipoQuiz.add(rbMultipleChoice);
        pTipoQuiz.add(rbTrueFalse);

        rbMultipleChoice.addActionListener(this);
        rbTrueFalse.addActionListener(this);

        this.add(pTipoQuiz);
        pTipoQuiz.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Número de preguntas
        txtNumPreguntas = new JTextField(5);
        btnGenerarPreguntas = new JButton("Generar Preguntas");
        btnGenerarPreguntas.addActionListener(this);

        JPanel pNumPreguntas = new JPanel();
        pNumPreguntas.setLayout(new FlowLayout(FlowLayout.CENTER));
        pNumPreguntas.add(new JLabel("Número de preguntas:"));
        pNumPreguntas.add(txtNumPreguntas);
        pNumPreguntas.add(btnGenerarPreguntas);

        this.add(pNumPreguntas);
        pNumPreguntas.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para preguntas dinámicas
        pPreguntasDinamico = new JPanel();
        pPreguntasDinamico.setLayout(new GridLayout(0, 1)); // Una fila por pregunta
        scrollPreguntas = new JScrollPane(pPreguntasDinamico);
        scrollPreguntas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPreguntas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPreguntas.setPreferredSize(new Dimension(600, 300));
        this.add(scrollPreguntas);

        // Calificación mínima
        txtCalificacionMin = new JTextField(30);
        txtCalificacionMin.setEditable(true);

        JLabel lblCalificacionMin = new JLabel("Calificación mínima: ");
        JPanel pCalificacionMin = new JPanel();
        pCalificacionMin.setLayout(new FlowLayout(FlowLayout.CENTER));
        pCalificacionMin.add(lblCalificacionMin);
        pCalificacionMin.add(txtCalificacionMin);

        this.add(pCalificacionMin);
        pCalificacionMin.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rbMultipleChoice || e.getSource() == rbTrueFalse) {
            tipoQuizVerdaderoFalso = rbTrueFalse.isSelected();
        } else if (e.getSource() == btnGenerarPreguntas) {
            try {
                numPreguntas = Integer.parseInt(txtNumPreguntas.getText());
                generarCamposPreguntas();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido de preguntas.");
            }
        }
    }

    private void generarCamposPreguntas() {
        pPreguntasDinamico.removeAll(); // Limpiar preguntas previas

        for (int i = 1; i <= numPreguntas; i++) {
            JPanel pPregunta = new JPanel();
            pPregunta.setLayout(new GridLayout(tipoQuizVerdaderoFalso ? 3 : 6, 1)); // Verdadero/Falso: 3 filas, Opción múltiple: 6

            JLabel lblPregunta = new JLabel("Pregunta " + i + ":");
            JTextField txtPregunta = new JTextField(30);

            pPregunta.add(lblPregunta);
            pPregunta.add(txtPregunta);

            int opciones = tipoQuizVerdaderoFalso ? 2 : 4;
            for (int j = 1; j <= opciones; j++) {
                JPanel pOpcion = new JPanel();
                pOpcion.setLayout(new FlowLayout(FlowLayout.LEFT));

                JLabel lblOpcion = new JLabel("Opción " + j + ":");
                JTextField txtOpcion = new JTextField(20);
                JCheckBox chkCorrecta = new JCheckBox("Correcta");

                pOpcion.add(lblOpcion);
                pOpcion.add(txtOpcion);
                pOpcion.add(chkCorrecta);

                pPregunta.add(pOpcion);
            }

            pPreguntasDinamico.add(pPregunta);
        }

        revalidate();
        repaint();
    }
    
    public List<PreguntaQuiz> guardarPreguntas() throws Exception {
        List<PreguntaQuiz> preguntas = new LinkedList<>();
        Component[] componentes = pPreguntasDinamico.getComponents();

        for (int i = 0; i < componentes.length; i++) {
            if (componentes[i] instanceof JPanel) {
                JPanel pPregunta = (JPanel) componentes[i];
                Component[] camposPregunta = pPregunta.getComponents();

                String textoPregunta = ((JTextField) camposPregunta[1]).getText();
                if (textoPregunta.isEmpty()) {
                    throw new Exception("El texto de la pregunta " + (i + 1) + " está vacío.");
                }

                int opciones = tipoQuizVerdaderoFalso ? 2 : 4;
                List<OpcionQuiz> opcionesQuiz = new LinkedList<>();
                int correcta = -1;

                for (int j = 0; j < opciones; j++) {
                    JPanel pOpcion = (JPanel) camposPregunta[2 + j];
                    Component[] camposOpcion = pOpcion.getComponents();

                    String textoOpcion = ((JTextField) camposOpcion[1]).getText();
                    boolean esCorrecta = ((JCheckBox) camposOpcion[2]).isSelected();

                    if (textoOpcion.isEmpty()) {
                        throw new Exception("La opción " + (j + 1) + " de la pregunta " + (i + 1) + " está vacía.");
                    }

                    opcionesQuiz.add(new OpcionQuiz(textoOpcion, "", esCorrecta));

                    if (esCorrecta) {
                        if (correcta != -1) {
                            throw new Exception("Solo una opción puede ser correcta en la pregunta " + (i + 1) + ".");
                        }
                        correcta = j + 1;
                    }
                }

                if (correcta == -1) {
                    throw new Exception("Debe haber al menos una opción correcta en la pregunta " + (i + 1) + ".");
                }

                PreguntaQuiz pregunta = new PreguntaQuiz(textoPregunta, correcta, opciones);
                for (int j = 0; j < opcionesQuiz.size(); j++) {
                    pregunta.setOpcion(j + 1, opcionesQuiz.get(j));
                }

                preguntas.add(pregunta);
            }
        }

        return preguntas;
    }

}
