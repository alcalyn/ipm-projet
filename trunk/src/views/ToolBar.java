package views;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Res;
import controllers.Control;

public class ToolBar extends JPanel {
	
	private static final long serialVersionUID = 8774931849219584680L;
	
	
	private static final Dimension
			dim_icon = new Dimension(20, 20),
			dim_button = new Dimension(24, 24);
	
	
	public ToolBar() {
		add(createButton("Nouveau", Control.NOUVEAU, "newFile.png"));
		add(createButton("Enregistrer", Control.SAVE, "save.png"));
		add(createButton("Snap", Control.SELECT_TOOL_SNAP, "periodSnakeIcon.png"));
		add(createButton("Plume", Control.SELECT_TOOL_WRITE, "plume.png"));
	}
	
	
	private static JButton createButton(String text, int control, String res) {
		JButton b = null;
		
		if(res == null) {
			b = new JButton(text);
		} else {
			b = new JButton(Res.getIcon(res, dim_icon));
			b.setToolTipText(text);
		}
		
		b.setPreferredSize(dim_button);
		b.addActionListener(new Control(control));
		return b;
	}
	

}
