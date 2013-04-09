package model;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Res {
	
	
	private static final String res_base = "res"+File.separator;
	
	private static String path(String res) {
		return res_base+res;
	}
	
	
	public static File getFile(String ressource_name) {
		return new File(path(ressource_name));
	}
	
	public static String getFileContent(String r) {
		return readFile(Res.getFile(r).getAbsolutePath());
	}
	
	private static String readFile(String filename) {
	   String content = null;
	   File file = new File(filename); //for ex foo.txt
	   try {
	       FileReader reader = new FileReader(file);
	       char[] chars = new char[(int) file.length()];
	       reader.read(chars);
	       content = new String(chars);
	       reader.close();
	   } catch (IOException e) {
	       e.printStackTrace();
	   }
	   return content;
	}
	
	public static Image getImage(String res) {
		ImageIcon img = new ImageIcon(path(res));
		return img.getImage();
	}
	
	public static Icon getIcon(String res) {
		return new ImageIcon(path(res));
	}
	
	public static Icon getIcon(String res, Dimension dimension) {
		return new ImageIcon(new ImageIcon(path(res)).getImage().getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH));
	}
	
	
}
