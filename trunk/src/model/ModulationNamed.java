package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.Icon;

import appli.Modulation;

public class ModulationNamed extends FonctionNamed {
	
	
	private static Fonction icon_base_function = null;
	
	
	private Modulation modulation;
	
	
	public ModulationNamed(final Modulation m, String name) {
		super(new Fonction() {
			
			@Override
			public double f(double x) {
				return m.f(x, getMiSinusoide());
			}
		}, name);
		
		this.modulation = m;
	}
	
	
	@Override
	public Fonction fonction() {
		return null;
	}
	
	public Modulation modulation() {
		return modulation;
	}
	
	
	
	private static final Fonction getMiSinusoide() {
		if(icon_base_function == null) {
			icon_base_function = new Fonction() {
				
				@Override
				public double f(double x) {
					return Math.sin(x * Math.PI * 2) * 0.5;
				}
			};
		}
		
		return icon_base_function;
	}
	

}
