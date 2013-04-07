package views;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ModelUpdate;
import model.Periode;
import model.Res;
import controllers.MouseToolListener;
import controllers.Tool;

public class PeriodeView extends JFrame implements Observer {
	
	private static final long serialVersionUID = -1146139996955796557L;
	
	
	private Menu menu;
	private JPanel center;
	private ToolBar toolbar;
	private CourbePanel panel_courbe;
	
	
	private Tool tool = null;
	private MouseToolListener mouse_tool;
	
	
	public PeriodeView() {
		super("PeriodSnake");
		
		setIconImage(Res.getImage("periodSnakeIcon.png"));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		
		menu = new Menu();	
		center = new JPanel(new BorderLayout());
		toolbar = new ToolBar();
		panel_courbe = new CourbePanel();
		
		selectTool(Tool.SNAP);
		mouse_tool = new MouseToolListener(this);
		
		panel_courbe.addMouseListener(mouse_tool);
		panel_courbe.addMouseMotionListener(mouse_tool);
		
		add(menu, BorderLayout.NORTH);
		center.add(panel_courbe, BorderLayout.CENTER);
		center.add(toolbar, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		
		
		setVisible(true);
	}
	
	public void observe(Periode periode) {
		periode.addObserver(this);
		periode.addObserver(panel_courbe);
	}



	public void update(Observable o, Object arg) {
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
