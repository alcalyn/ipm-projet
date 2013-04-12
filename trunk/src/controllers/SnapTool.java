package controllers;

import java.awt.event.MouseEvent;

import views.CourbePanel;

public class SnapTool extends ToolAdapter {
	
	
	public SnapTool(CourbePanel courbe_panel) {
		super(courbe_panel);
		brush = new Brush("0.200", "1.0");
	}
	
	@Override
	public String[] getBrushParams() {
		return new String [] {
				"Taille",
				"Force",
		};
	}


	public void mouseDragged(MouseEvent e) {
		if(periode != null) {
			snap(e.getX(), e.getY(), brush, courbe_panel);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(periode != null) {
			snap(e.getX(), e.getY(), brush, courbe_panel);
		}
	}
	
	
	public static void snap(int x, int y, Brush brush, CourbePanel courbe_panel) {
		double force = brush.getDouble(0);
		
		int affect_size = (int) (periode.sampling() * force);
		int mouse_idx = (x * periode.sampling()) / courbe_panel.getWidth();
		double mouse_y = (double) y / (double) courbe_panel.getHeight() - 0.5;
		mouse_y *= -2.0;
		int from = mouse_idx - affect_size / 2;
		int to = mouse_idx + affect_size / 2;
		
		for(int i=from;i<to;i++) {
			double dist = (double) Math.abs(i - mouse_idx) / (double) Math.abs(to - mouse_idx);
			double local_force = force * Math.pow(1 - dist, 1 / brush.getDouble(1));
			
			double sample = (double) periode.get(mod(i)) / (double) Short.MAX_VALUE;
			double diff = mouse_y - sample;
			
			periode.set(mod(i), sample + diff * local_force);
		}
		
		periode.flushCourbe();
	}
	


	@Override
	public String getName() {
		return "Serpent";
	}
	
	
}
