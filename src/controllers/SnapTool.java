package controllers;

import java.awt.event.MouseEvent;

import views.CourbePanel;

public class SnapTool extends ToolAdapter {
	
	public SnapTool(CourbePanel courbe_panel) {
		super(courbe_panel);
	}


	public void mouseDragged(MouseEvent e) {
		if(periode != null) {
			snap(e.getX(), e.getY());
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(periode != null) {
			snap(e.getX(), e.getY());
		}
	}
	
	
	public void snap(int x, int y) {
		double force = 0.200;
		
		int affect_size = (int) (periode.sampling() * force);
		int mouse_idx = (x * periode.sampling()) / courbe_panel.getWidth();
		double mouse_y = (double) y / (double) courbe_panel.getHeight() - 0.5;
		mouse_y *= -2.0;
		int from = mouse_idx - affect_size / 2;
		int to = mouse_idx + affect_size / 2;
		
		for(int i=from;i<to;i++) {
			double dist = (double) Math.abs(i - mouse_idx) / (double) Math.abs(to - mouse_idx);
			double local_force = force * (1 - dist);
			
			double sample = (double) periode.get(mod(i, periode.sampling())) / (double) Short.MAX_VALUE;
			double diff = mouse_y - sample;
			
			periode.set(mod(i, periode.sampling()), sample + diff * local_force);
		}
		
		periode.flushCourbe();
	}
	
	private static int mod(int n, int mod) {
		int r = n % mod;
		if(r < 0) r += mod;
		return r;
	}


	@Override
	public String getName() {
		return "Serpent";
	}
	
	
}
