package reiterhof;

public class Pferd {
	private String pName;
	private int niveau;
	private boolean verfuegbar;
	
	public Pferd(String name, int niv, boolean verfuegbar) {
		this.pName = name;
		this.niveau = niv;
		this.verfuegbar = verfuegbar;
	}
	
	@Override
	public String toString() {
		return this.pName;
	}
	
	public int getNiveau() {
		return this.niveau;
	}
	
	//getta für verfuegbar
	public boolean verfuegbar() {
		return this.verfuegbar;
	}
	
	//setta für verfuegbar
	public boolean nehmen() {
		if(verfuegbar) {
			this.verfuegbar = false;
			return true;
		}
		return false;
	}
	
}
