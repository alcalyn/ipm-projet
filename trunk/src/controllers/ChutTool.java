package controllers;

import java.awt.Component;
import java.awt.event.MouseEvent;

import views.CourbePanel;

public class ChutTool extends ToolAdapter {
	
	

	public ChutTool(CourbePanel courbe_panel) {
		super(courbe_panel);
		brush = new Brush("0.200", "1.0", "0.0");
	}
	
	@Override
	public String[] getBrushParams() {
		return new String [] {
				"Taille",
				"Force",
				"Origine",
		};
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(periode != null) {
			int origine = (int) ((- brush.getDouble(2) / 2.0 + 0.5) * ((Component) e.getSource()).getHeight());
			SnapTool.snap(e.getX(), origine, brush, courbe_panel);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mouseDragged(e);
	}


	@Override
	public String getName() {
		return "Attenuer";
	}

}
