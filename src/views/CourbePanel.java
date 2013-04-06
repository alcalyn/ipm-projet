package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.ModelUpdate;
import model.Periode;

public class CourbePanel extends JPanel implements Observer {
	
	private static final long serialVersionUID = -4081500199182406051L;
	
	
	private Periode periode;
	private int last_edit = -1;
	
	
	public CourbePanel() {
		super();
		
		addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseMoved(MouseEvent e) {
				
			}
			
			public void mouseDragged(MouseEvent e) {
				if(periode != null) {
					snap(e.getX(), e.getY());
				}
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(periode == null) {
			drawBg(g);
		} else {
			drawBg(g);
			drawCourbe(g);
		}
	}

	
	private void drawBg(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		
		for(int i=1;i<8;i++) {
			for(int j=1;j<8;j++) {
				g.drawRect((getWidth() * i) / 8, (getHeight() * j) / 8, 1, 1);
			}
		}
	}
	
	private void drawCourbe(Graphics g) {
		g.setColor(Color.BLACK);
		for(int i=1;i<periode.sampling();i++) {
			Point a = new Point(
					((i - 1) * getWidth()) / periode.sampling(),
					- (periode.get(i - 1) * getHeight()) / (Short.MAX_VALUE - Short.MIN_VALUE) + getHeight() / 2
			);
			
			Point b = new Point(
					(i * getWidth()) / periode.sampling(),
					- (periode.get(i) * getHeight()) / (Short.MAX_VALUE - Short.MIN_VALUE) + getHeight() / 2
			);
			
			g.drawLine(a.x, a.y, b.x, b.y);
			
			//fillDisc(g, a.x, a.y, 4);
		}
	}
	
	private void fillDisc(Graphics g, int x, int y, int size) {
		g.fillOval(x - size / 2, y - size / 2, size, size);
	}
	
	
	private void snap(int x, int y) {
		System.out.println("snap");
		double force = 0.200;
		
		int affect_size = (int) (periode.sampling() * force);
		int mouse_idx = (x * periode.sampling()) / getWidth();
		double mouse_y = (double) y / (double) getHeight() - 0.5;
		mouse_y *= -2.0;
		int from = mouse_idx - affect_size / 2;
		int to = mouse_idx + affect_size / 2;
		
		System.out.println(mouse_idx+" : "+from+" > "+to);
		for(int i=from;i<to;i++) {
			double dist = (double) Math.abs(i - mouse_idx) / (double) Math.abs(to - mouse_idx);
			double local_force = force * (1 - dist);
			
			double sample = (double) periode.get(i) / (double) Short.MAX_VALUE;
			double diff = mouse_y - sample;
			
			System.out.println(diff * local_force);
			
			periode.set(i, sample + diff * local_force);
		}
	}
	
	
	
	public void update(Observable o, Object arg) {
		Periode periode = (Periode) o;
		ModelUpdate up = (ModelUpdate) arg;
		
		switch(up.type) {
			case ModelUpdate.COURBE:
				if(this.periode == null) this.periode = periode;
				repaint();
				break;
		}
	}
	
	

}
