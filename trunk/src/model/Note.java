package model;

public class Note {
	
	public static final int
		LA = 0,		A = 0,
		LAd = 1,	As = 1,		SIb = 1,	Bf = 1,
		SI = 2,		B = 2,
		DO = 3,		C = 3,
		DOd = 4,	Cs = 4,		REb = 4,	Df = 4,
		RE = 5,		D = 5,
		REd = 6,	Ds = 6,		MIb = 6,	Ef = 6,
		MI = 7,		E = 7,
		FA = 8,		F = 8,
		FAd = 9,	Fs = 9,		SOLb = 9,	Gf = 9,
		SOL = 10,	G = 10,
		SOLd = 11,	Gs = 11,	LAb = 11,	Af = 11;
	
	
	
	
	private int note, octave;
	
	
	public Note() {
	}
	
	public Note(String note) {
		// TODO parsing
	}
	
	public Note(int note, int octave) {
		this.note = note;
		this.octave = octave;
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
		// TODO
		return 0.0;
	}

	public String toString() {
		return Note.toString(this);
	}
	
	
	public static Note parseNote(String s) {
		return new Note(s);
	}
	
	
	public static String toString(Note note) {
		// TODO render
		return null;
	}
	
}
