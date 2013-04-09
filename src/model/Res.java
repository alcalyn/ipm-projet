package model;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Res {
	
	
	
	private static URL getRes(String res) {
		return new Res().getClass().getResource("/"+res);
	}
	
	public static File getFile(String r) {
		return new File(r);
	}
	
	public static String getFileContent(String r) {
		return readFile(Res.getFile(r).getAbsolutePath());
	}
	
	private static String readFile(String filename) {
	   String content = null;
	   File file = getFile(filename);
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
		ImageIcon img = new ImageIcon(getRes(res));
		return img.getImage();
	}
	
	public static Icon getIcon(String res) {
		return new ImageIcon(getRes(res));
	}
	
	public static Icon getIcon(String res, Dimension dimension) {
		return new ImageIcon(getImage(res).getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH));
	}
	
	
}
