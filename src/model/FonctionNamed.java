package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.Icon;

public class FonctionNamed {
	
	protected Fonction f;
	protected String name;
	
	public FonctionNamed(Fonction f, String name) {
		this.f = f;
		this.name = name;
	}
	
	public Fonction fonction() {
		return f;
	}
	
	public double f(double x) {
		return f.f(x);
	}
	
	public String name() {
		return name;
	}
	
	public Icon createIcon(final int width, final int height) {
		return new Icon() {
			
			public void paintIcon(Component c, Graphics g, int x, int y) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x + 2, y + 2, width - 4, height - 4);
				
				g.setColor(Color.DARK_GRAY);
				g.drawLine(x, y + height / 2, x + width - 1, y + height / 2);
				
				Polygon polygon = new Polygon();
				for(int i=0;i<width;i++) {
					polygon.addPoint(
							x + i,
							(int) (f((double) i / (double) width) * ((double) height / -2.0)) + height / 2 + 4
					);
				}
				
				g.setColor(new Color(0x008800));
				for(int i=1;i<polygon.npoints;i++) {
					g.drawLine(
							polygon.xpoints[i-1],
							polygon.ypoints[i-1],
							polygon.xpoints[i],
							polygon.ypoints[i]
					);
				}
			}
			
			public int getIconWidth() {
				return width;
			}
			
			public int getIconHeight() {
				return height;
			}
		};
	}

}
