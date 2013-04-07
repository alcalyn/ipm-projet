package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import views.PeriodeView;

public class MouseToolListener implements MouseListener, MouseMotionListener {
	
	
	private PeriodeView periode_view;

	public MouseToolListener(PeriodeView periode_view) {
		this.periode_view = periode_view;
	}
	

	public void mouseDragged(MouseEvent e) {
		periode_view.getTool().mouseDragged(e);
	}

	public void mouseMoved(MouseEvent e) {
		periode_view.getTool().mouseMoved(e);
	}

	public void mouseClicked(MouseEvent e) {
		periode_view.getTool().mouseClicked(e);
	}

	public void mousePressed(MouseEvent e) {
		periode_view.getTool().mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		periode_view.getTool().mouseReleased(e);
	}

	public void mouseEntered(MouseEvent e) {
		periode_view.getTool().mouseEntered(e);
	}

	public void mouseExited(MouseEvent e) {
		periode_view.getTool().mouseExited(e);
	}

}
