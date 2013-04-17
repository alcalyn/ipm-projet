package model;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Note {
	
	public static final int
		DO = -9,
		DOd = -8,	REb = -8,
		RE = -7,
		REd = -6,	MIb = -6,
		MI = -5,
		FA = -4,
		FAd = -3,	SOLb = -3,
		SOL = -2,
		SOLd = -1,	LAb = -1,
		LA = 0,
		LAd = 1,	SIb = 1,
		SI = 2;
	
	
	
	private int note, octave;
	
	
	public Note() {
	}
	
	public Note(String note) throws NoteFormatException {
		this.note = -1;
		this.octave = 0;
		
		String n = unify(note);
		
		if(n.contains("do")) this.note = DO;
		if(n.contains("re")) this.note = RE;
		if(n.contains("mi")) this.note = MI;
		if(n.contains("fa")) this.note = FA;
		if(n.contains("sol")) this.note = SOL;
		if(n.contains("la")) this.note = LA;
		if(n.contains("si")) this.note = SI;
		
		if(this.note == -1) throw new NoteFormatException("Aucune note reconnue");
		
		if(n.contains("#")) this.note++;
		if(n.contains("b")) this.note--;
		if(this.note == -10) {
			this.note = 2;
			this.octave --;
		}
		if(this.note == 3) {
			this.note = -10;
			this.octave ++;
		}
		
		Pattern p = Pattern.compile("[0-9\\-]+");
		Matcher m = p.matcher(n);
		if(m.find()) {
			String o = m.group();
			octave += Integer.parseInt(o);
		} else {
			octave += 3;
		}
		
	}
	
	public Note(int note, int octave) {
		this.note = note;
		this.octave = octave;
	}
	
	
	private static String unify(String s) {
		return s
			.trim()
			.toLowerCase()
			.replace('é', 'e')
			.replace('è', 'e');
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}
	
	public double getFrequency() {
		return 440.0 * Math.pow(2, (octave - 3) + ((double) note / 12.0));
	}
	
	public String getFrequency(int decimales) {
		double hz = getFrequency();
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		return df.format(hz);
	}

	public String toString() {
		return Note.toString(this);
	}
	
	
	public static Note parseNote(String s) {
		try {
			return new Note(s);
		} catch (NoteFormatException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static double getFrequency(String note) throws NoteFormatException {
		return new Note(note).getFrequency();
	}
	
	public static String getFrequency(String note, int decimales) throws NoteFormatException {
		return new Note(note).getFrequency(decimales);
	}
	
	public static String toString(Note note) {
		String s = "";
		
		s += new String [] {
				"La", "La#", "Si", "Do", "Do#", "Ré",
				"Ré#", "Mi", "Fa", "Fa#", "Sol", "Sol#"
		} [note.note];
		
		s += Integer.toString(note.octave);
		
		return s;
	}
	
	
	
	
	
	public static class NoteFormatException extends Exception {
		
		private static final long serialVersionUID = -2159366439062248615L;
		
		public NoteFormatException() {
			super();
		}
		public NoteFormatException(String msg) {
			super(msg);
		}
		
	}
	
}
