package controllers;

import java.awt.event.MouseEvent;

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
		final int brush_size = 4;
		double at0 = (double) (x - brush_size / 2) / (double) courbe_panel.getWidth();
		double at1 = (double) (x + brush_size / 2) / (double) courbe_panel.getWidth();
		double value = (double) y / (double) courbe_panel.getHeight();
		
		int s0 = (int) (at0 * periode.sampling());
		int s1 = (int) (at1 * periode.sampling());
		
		value = -value * 2 + 1;
		
		for(int i=Math.max(s0, 0);i<Math.min(s1, periode.sampling());i++)
			periode.set(i, value);
		
		
		periode.flushCourbe();
	}

}
