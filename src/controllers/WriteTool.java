package controllers;

import java.awt.event.MouseEvent;

import model.ModelUpdate;
import views.CourbePanel;

public class WriteTool extends ToolAdapter {

	public WriteTool(CourbePanel courbe_panel) {
		super(courbe_panel);
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		write(e.getX(), e.getY());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		write(e.getX(), e.getY());
	}
	
	
	private void write(int x, int y) {
		double at = (double) x / (double) courbe_panel.getWidth();
		double value = (double) y / (double) courbe_panel.getHeight();
		
		value = -value * 2 + 1;
		
		periode.set(at, value);
		
		periode.flushCourbe();
	}

}
