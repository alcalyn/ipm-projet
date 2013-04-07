package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class FileManager {
	
	
	public static File current_file = null;
	
	
	public static void saveAs(Periode periode) throws IOException {
		JFileChooser chooser = new JFileChooser(".");
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			save(periode, file);
			current_file = file;
		}
	}
	
	public static void save(Periode periode) throws IOException {
		if(current_file == null) {
			saveAs(periode);
		} else {
			save(periode, current_file);
		}
	}
	
	private static void save(Periode periode, File file) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(periode);
		oos.flush();
		oos.close();
	}
	
	
	private static void load(Periode periode, File file) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		periode.load((Periode) ois.readObject());
		ois.close();
	}
	
	
}
