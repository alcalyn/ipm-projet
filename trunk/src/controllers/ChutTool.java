package controllers;

import java.awt.Component;
import java.awt.event.MouseEvent;

import views.CourbePanel;

public class ChutTool extends ToolAdapter {
	
	
	private SnapTool snap_tool;

	public ChutTool(CourbePanel courbe_panel) {
		super(courbe_panel);
		snap_tool = (SnapTool) Tool.getTool(Tool.SNAP, courbe_panel);
	}
	
	
	public void mouseDragged(MouseEvent e) {
		if(periode != null) {
			snap_tool.snap(e.getX(), ((Component) e.getSource()).getHeight()/2);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(periode != null) {
			snap_tool.snap(e.getX(), ((Component) e.getSource()).getHeight()/2);
		}
	}

}
