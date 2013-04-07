package model;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Res {
	
	
	private static final String res_base = "res"+File.separator;
	
	private static String path(String res) {
		return res_base+res;
	}
	
	
	public static File getFile(String ressource_name) {
		return new File(path(ressource_name));
	}
	
	public static Image getImage(String res) {
		ImageIcon img = new ImageIcon(path(res));
		return img.getImage();
	}
	
	
}
