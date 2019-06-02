package reiterhof;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
/**
 * In dieser Klasse stehen die Methoden, über welche die Pferde den Reitern zugeteilt werden.
 * @author chef
 *
 */
public class Zuordnung {
	
	private Reiter[] reiter = new Reiter[7];
	private Pferd[] pferde = new Pferd[9];
	//Pferdenummer entspricht der Stelle im array von 0-8; 0 = nicht verfügbar; 1 = verfügbar; ändert sich in  jeder Rekursion, wird jedes Mal überschrieben,
	private int[] pferdVerfuegbar = new int[9];
	
	private int currentWPferd = 1;
	private int currReiter;
	private int currPferd;
	private Pferd[] wpferde;
	private int i = 0;
	
	//dies ist die HashMap, in der im Laufe des Programms die Reiter-Pferd-Paare eingetragen werden.
	Map<Reiter, Pferd> out = new HashMap<>();
	
	//um die Reiter Pferde wählen zu lassen, werden zu Beginn die Reiter und alle verfügbaren Pferde eingetragen.
	//Reiter mit Namen (""), Koennen (1, 2, 3) und Wunschpferden [""]
	public Zuordnung() {
		pferde[0] = new Pferd("Alex", 1, true);
		pferde[1] = new Pferd("Nicki", 2, true);
		pferde[2] = new Pferd("Pucki", 2, true);
		pferde[3] = new Pferd("Wittchen", 1, true);
		pferde[4] = new Pferd("Hurrikan", 2, true);
		pferde[5] = new Pferd("Tornado", 3, true);
		pferde[6] = new Pferd("Sturmwind", 2, true);
		pferde[7] = new Pferd("Zausel", 1, true);
		pferde[8] = new Pferd("Zickzack", 3, true);
		
		reiter[0] = new Reiter("Anja", 1, wpferde = Stream.of(pferde[0], pferde[1], pferde[2], pferde[3]).toArray(Pferd[]::new));
		reiter[1] = new Reiter("Bertram", 2, wpferde = Stream.of(pferde[0], pferde[1], pferde[2], pferde[3], pferde[4], pferde[5], pferde[6], pferde[7], pferde[8]).toArray(Pferd[]::new));
		reiter[2] = new Reiter("Christa", 3, wpferde = Stream.of(pferde[4]).toArray(Pferd[]::new));
		reiter[3] = new Reiter("Doris", 1, wpferde = Stream.of(pferde[0], pferde[2], pferde[3]).toArray(Pferd[]::new));
		reiter[4] = new Reiter("Emil", 1, wpferde = Stream.of(pferde[0], pferde[1], pferde[2], pferde[3], pferde[4], pferde[5], pferde[6], pferde[7], pferde[8]).toArray(Pferd[]::new));
		reiter[5] = new Reiter("Fritz", 3, wpferde = Stream.of(pferde[4], pferde[6]).toArray(Pferd[]::new));
		reiter[6] = new Reiter("Gabi", 2, wpferde = Stream.of(pferde[0], pferde[4], pferde[5], pferde[6]).toArray(Pferd[]::new));
	}
	
	public Reiter getReiter(int i) {
		return reiter[i];
	}
	
	public Pferd[] getPferdeArray() {
		return this.pferde;
	}
	
	public Reiter[] getReiterArray() {
		return this.reiter;
	}
	
	public boolean generiereZuordnung(int reit, int wPferd) {
		
		currentWPferd = wPferd;
		//Endbedingung; es soll bei Aufruf von Reiter 7 true zurückgegeben werden
		if(reit == reiter.length) {
			return true;
		}
		
		System.out.println(reiter[reit]);
		out.put(reiter[reit], wpferde[currentWPferd]);
		//prueft, ob das aktuelle Pferd auch dem koennen entspricht, wenn ja, geht's weiter
		if(checkWPferd(reit, currentWPferd)) {
			if(!out.containsValue(wpferde[currentWPferd])) {
				//generiereZuordnung(reit, currentWPferd);
				if(generiereZuordnung(reit+1, 0)) {
					return true;
				}else {
					return false;
				}
			}
			return false;
			
		} else {
			out.put(reiter[reit], wpferde[currentWPferd]);
			currentWPferd ++;
			
			//return false;
			generiereZuordnung(reit, currentWPferd);
		}
		
		return false;
	}


	
	
/*
 * der aktuelle Reiter wählt ein Pferd und es wird überprüft, ob es ein erlaubtes oder sogar ein Wunschpferd ist.
 * @param i
 * @return
 */
	public int nimmPferd(int j, int i){
		//System.out.println("Pferd Niveau: " + pferde[i].getNiveau());
		//System.out.println("Reiter Koennen: " + reiter[j].getKoennen());
		if(pferde[i].getNiveau() <= reiter[j].getKoennen()) {
			if(wunschPferd(pferde[i], reiter[j])) {
				//System.out.println("Juhuuuu " + pferde[i].toString() + ", das ist genau mein (" + reiter[j].toString() + ") Wunschpferd!" + "\n");
				return 1;
			}
			else {
				//System.out.println("OH NO, wie schade, das ist leider nicht mein (" + reiter[j].toString() + ") Wunschpferd! Aber ich versuch es trotzdem mit " + pferde[i].toString() + " ;)" + "\n");
				return 0;
			}
		}
		else {
			//System.out.println("Was ist denn hier passiert? Dieses Pferd (" + pferde[i].toString() +") kann ich (" + reiter[j].toString() + ") noch nicht reiten!" + "\n");
			return -1;
		}
	}
	
	public boolean checkWPferd(int reit, int wpferd) {
		System.out.println(reiter[reit].toString() + " und " + reiter[reit].getWunschpferd(wpferd).toString() + "; Niveau: " + reiter[reit].getWunschpferd(wpferd).getNiveau() + "; Koennen: " + reiter[reit].getKoennen());
		if(wpferde[wpferd].getNiveau() <= reiter[reit].getKoennen()) {
			return true;
		}
		return false;
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean wunschPferd(Pferd pferd, Reiter reiter) {
		if (Arrays.asList(reiter.getWunschpferde()).contains(pferd)) {
			return true;
		}
		return false;
	}
	
	/*@Override
	public String toString() {
		String paare = "--------------------\n";
		for (int r = 0; r < 7; r++) {
			 paare = paare + "Reiter: " + this.reiter[r] + " & Pferd: " + pferde[pferdPfadinRec[r]] + "\n";
		}
		paare = paare + "dabei wurden " + pferdPfadinRec[7] + " Wünsche erfüllt!";
		return paare;
	}
	*/
	
	@Override
	public String toString() {
		String pairs = "";
		for(Reiter key : out.keySet())
	    {
	      pairs = pairs + ("Key: " + key + " - Value: " + out.get(key) + "\n");
	    }
		return pairs;
	}
	
	
}
 