package views;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controllers.MouseToolListener;
import controllers.Tool;

import model.ModelUpdate;
import model.Periode;

public class PeriodeView extends JFrame implements Observer {
	
	private static final long serialVersionUID = -1146139996955796557L;
	
	
	private Menu menu;
	private CourbePanel panel_courbe;
	
	
	private Tool tool = null;
	private MouseToolListener mouse_tool;
	
	
	public PeriodeView() {
		super("PeriodSnake");
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		
		menu = new Menu();	
		panel_courbe = new CourbePanel();
		
		selectTool(Tool.SNAP);
		mouse_tool = new MouseToolListener(this);
		
		panel_courbe.addMouseListener(mouse_tool);
		panel_courbe.addMouseMotionListener(mouse_tool);
		
		add(menu, BorderLayout.NORTH);
		add(panel_courbe, BorderLayout.CENTER);
		
		
		setVisible(true);
	}
	
	public void observe(Periode periode) {
		periode.addObserver(this);
		periode.addObserver(panel_courbe);
	}



	public void update(Observable o, Object arg) {
		Periode periode = (Periode) o;
		ModelUpdate up = (ModelUpdate) arg;
		
		switch(up.type) {
			case ModelUpdate.COURBE:
		}
	}
	
	
	public void selectTool(int tool) {
		this.tool = Tool.getTool(tool, panel_courbe);
	}
	
	public Tool getTool() {
		return tool;
	}

}
