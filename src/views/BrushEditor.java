package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		
		tool_panel = createPanel(this.periode_view.getTool());
		add(tool_panel);
		
		setVisible(true);
	}
	
	
	private JPanel createPanel(Tool tool) {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(new JLabel(tool.getName()), BorderLayout.NORTH);
		
		Brush brush = tool.getBrush();
		
		// TODO
		panel.add(new JLabel("args : "+brush.getParametersCount()), BorderLayout.CENTER);
		
		return panel;
	}
	
	public void updateTool(Tool tool) {
		getContentPane().remove(tool_panel);
		tool_panel = createPanel(tool);
		add(tool_panel);
		validate();
	}
	
}
