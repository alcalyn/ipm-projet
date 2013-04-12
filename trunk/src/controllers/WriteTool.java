package controllers;

import java.awt.event.MouseEvent;

import views.CourbePanel;

public class WriteTool extends ToolAdapter {
	
	
	public WriteTool(CourbePanel courbe_panel, int size) {
		super(courbe_panel);
		this.brush = new Brush(size);
	}
	
	public int getSize() {
		return brush.getInt(0);
	}
	
	public void setSize(int size) {
		brush.set(0, size);
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
		final int brush_size = getSize();
		
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

	@Override
	public String getName() {
		return "Plume";
	}

}
