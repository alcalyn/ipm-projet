package views;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.Control;

public class ToolBar extends JPanel {
	
	private static final long serialVersionUID = 8774931849219584680L;
	
	
	
	public ToolBar() {
		JButton snap = new JButton("Snap");
		snap.addActionListener(new Control(Control.SELECT_TOOL_SNAP));
		add(snap);
		
		JButton write = new JButton("Write");
		write.addActionListener(new Control(Control.SELECT_TOOL_WRITE));
		add(write);
	}

}
