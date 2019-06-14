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
	
	public Pferd getWunschpferd(int i) {
		return this.wunschPferde[i];
	}
	
	//
	public boolean hasNextWunschpferd(int i) {
		if(i < this.getWunschpferde().length - 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.rName;
	}
}
