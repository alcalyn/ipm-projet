package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.PeriodeView;

import model.Periode;

public class Control implements ActionListener {
	
	
	public static final int
		SELECT_TOOL_SNAP = 5,
		SELECT_TOOL_WRITE = 6;
	
	
	private static Periode periode;
	private static PeriodeView periode_view;


	private int action;
	

	public Control(int action) {
		this.action = action;
	}


	public void actionPerformed(ActionEvent e) {
		System.out.println(action);
		switch (action) {
			case SELECT_TOOL_SNAP:
				periode_view.selectTool(Tool.SNAP);
				break;
				
			case SELECT_TOOL_WRITE:
				periode_view.selectTool(Tool.WRITE);
				break;
				
			default:
				break;
		}
	}
	
	
	public static Periode getPeriode() {
		return periode;
	}


	public static void setPeriode(Periode periode) {
		Control.periode = periode;
	}
	
	public static PeriodeView getPeriodeView() {
		return periode_view;
	}


	public static void setPeriodeView(PeriodeView periode_view) {
		Control.periode_view = periode_view;
	}

}
