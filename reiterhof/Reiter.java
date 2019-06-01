package reiterhof;

public class Reiter {
	private String rName;
	private int koennen;
	private Pferd[] wunschPferde;
	
	public Reiter(String name, int koennen, Pferd[] wPferde) {
		this.rName = name;
		this.koennen = koennen;
		this.wunschPferde = wPferde;
	}
	
	public int getKoennen() {
		return this.koennen;
	}
	
	public Pferd[] getWunschpferde() {
		return this.wunschPferde;
	}
	
	@Override
	public String toString() {
		return this.rName;
	}
}
