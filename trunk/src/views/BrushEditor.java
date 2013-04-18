package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controllers.Brush;
import controllers.Tool;

public class BrushEditor extends JFrame {
	
	private static final long serialVersionUID = -548754579280961477L;
	
	
	private PeriodeView periode_view;
	private JPanel tool_panel;
	
	
	public BrushEditor(PeriodeView view) {
		super("Editeur de pinceau");
		periode_view = view;
		
		setSize(200, 200);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		
		tool_panel = createPanel(this.periode_view.getTool());
		add(tool_panel);
		
		setVisible(true);
	}
	
	
	private JPanel createPanel(Tool tool) {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(new JLabel(tool.getName()), BorderLayout.NORTH);
		
		final Brush brush = tool.getBrush();
		
		String [] params = tool.getBrushParams();
		JPanel inputs = new JPanel(new GridLayout(params.length, 2));
		for(int i=0;i<params.length;i++) {
			final int index = i;
			String name = params[i];
			Object value = brush.get(i);
			
			JLabel label_name = new JLabel(name);
			final JTextField tf_value = new JTextField(value.toString());
			tf_value.getDocument().addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					changedUpdate(e);
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					changedUpdate(e);
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					brush.set(index, tf_value.getText());
				}
			});
			
			inputs.add(label_name);
			inputs.add(tf_value);
		}
		
		panel.add(inputs, BorderLayout.CENTER);
		
		return panel;
	}
	
	public void updateTool(Tool tool) {
		getContentPane().remove(tool_panel);
		tool_panel = createPanel(tool);
		add(tool_panel);
		validate();
	}
	
}
