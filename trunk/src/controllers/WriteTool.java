package controllers;

import java.awt.event.MouseEvent;

import views.CourbePanel;

public class WriteTool extends ToolAdapter {
	
	
	public WriteTool(CourbePanel courbe_panel) {
		super(courbe_panel);
		this.brush = new Brush(Double.toString(0.006));
	}
	
	public double getSize() {
		return brush.getDouble(0);
	}
	
	public void setSize(double size) {
		brush.set(0, Double.toString(size));
	}
	
	@Override
	public String [] getBrushParams() {
		return new String [] {
				"Taille",
		};
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
		final double brush_size = getSize();
		
		double mouse_x = (double) x / (double) courbe_panel.getWidth();
		double at0 = mouse_x - brush_size / 2;
		double at1 = mouse_x + brush_size / 2;
		double value = (double) y / (double) courbe_panel.getHeight();
		
		int s0 = (int) (at0 * periode.sampling());
		int s1 = (int) (at1 * periode.sampling());
		
		value = -value * 2 + 1;
		
		for(int i=s0;i<s1;i++)
			periode.set(mod(i), value);
		
		
		periode.flushCourbe();
	}

	@Override
	public String getName() {
		return "Plume";
	}

}
