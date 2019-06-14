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
	private int[] zugeordnetePferde = new int[9];
	
	//private int currentWPferd = 1;
	private int currReiter;
	private int currPferd;
	private Pferd[] wpferde;
	private int i = 0;
	private int wCount = 0;
	
	//dies ist die HashMap, in der im Laufe des Programms die Reiter-Pferd-Paare eingetragen werden.
	Map<Reiter, Pferd> out = new HashMap<>();
	Map<Reiter, Pferd> out2 = new HashMap<>();
	
	//um die Reiter Pferde wählen zu lassen, werden zu Beginn die Reiter und alle verfügbaren Pferde eingetragen.
	//Reiter mit Namen (""), Koennen (1, 2, 3) und Wunschpferden [""]
	public Zuordnung() {
		pferde[0] = new Pferd("Alex", 1, true);
		pferde[1] = new Pferd("Nicki", 2, true);
		pferde[2] = new Pferd("Pucki", 1, true);
		pferde[3] = new Pferd("Wittchen", 1, true);
		pferde[4] = new Pferd("Hurrikan", 2, true);
		pferde[5] = new Pferd("Tornado", 3, true);
		pferde[6] = new Pferd("Sturmwind", 2, true);
		pferde[7] = new Pferd("Zausel", 1, true);
		pferde[8] = new Pferd("Zickzack", 3, true);
		
		reiter[0] = new Reiter("Anja", 1, wpferde = Stream.of(pferde[2], pferde[1], pferde[0], pferde[3]).toArray(Pferd[]::new));
		reiter[1] = new Reiter("Bertram", 2, wpferde = Stream.of(pferde[6], pferde[4], pferde[2], pferde[3], pferde[1], pferde[5], pferde[0], pferde[7], pferde[8]).toArray(Pferd[]::new));
		reiter[2] = new Reiter("Christa", 3, wpferde = Stream.of(pferde[4]).toArray(Pferd[]::new));
		reiter[3] = new Reiter("Doris", 1, wpferde = Stream.of(pferde[4]).toArray(Pferd[]::new));//pferde[2], pferde[0], pferde[3]
		reiter[4] = new Reiter("Emil", 1, wpferde = Stream.of(pferde[0], pferde[1], pferde[2], pferde[3], pferde[4], pferde[5], pferde[6], pferde[7], pferde[8]).toArray(Pferd[]::new));
		reiter[5] = new Reiter("Fritz", 3, wpferde = Stream.of(pferde[4]).toArray(Pferd[]::new));//, pferde[6]
		reiter[6] = new Reiter("Gabi", 2, wpferde = Stream.of(pferde[0], pferde[6], pferde[5], pferde[4]).toArray(Pferd[]::new));
	}
	
	public Reiter getReiter(int i) {
		return reiter[i];
	}
	
	public Reiter nextReiter(int i) {
		return reiter[i + 1];
	}
	
	public boolean hasNextReiter(int i) {
		if(i == reiter.length - 1) {
			return true;
		}
		return false;
	}
	
	public Pferd[] getPferdeArray() {
		return this.pferde;
	}
	
	public Reiter[] getReiterArray() {
		return this.reiter;
	}
	
	public boolean generiereZuordnung(int reit, int wPferd) {
		
		
		//Endbedingung; es soll bei Aufruf von Reiter 7 true zurückgegeben werden
		if(reit == reiter.length) {
			return true;
		}
		int currentWPferd = wPferd;
		System.out.println("\nGerade ist: " + reiter[reit] + " an der Reihe");
		boolean erfolgreich = true;
		do {
			boolean check1 = false;
			//boolean containsNot = false;
//			if(!erfolgreich) {
//				out.remove(reiter[reit]);
//				System.out.println("removed 0");
//				//erfolgreich = true; // !? 20190613
//			}
//			if(!reiter[reit].hasNextWunschpferd(currentWPferd)) {
//			System.out.println("Reiter nach falscheer Zuordnung: " + reiter[reit].toString());
//				return false;
//			}
			do {
				System.out.println("VOR Check");
				System.out.println("reiter[reit] - " + reiter[reit]);
				System.out.println("wpferd - " + currentWPferd);
				System.out.println("reiter[reit].getWunschpferd(currentWPferd) - " + reiter[reit].getWunschpferd(currentWPferd));
				System.out.println("out contains - " + out.containsValue(reiter[reit].getWunschpferd(currentWPferd)));
		//prueft, ob das aktuelle Pferd auch dem koennen entspricht, wenn ja, geht's weiter
				if(checkWPferd(reit, currentWPferd)) {
					System.out.println("NACH Check");
					System.out.println("reiter[reit] - " + reiter[reit]);
					System.out.println("reiter[reit].getWunschpferd(currentWPferd) - " + reiter[reit].getWunschpferd(currentWPferd));
					System.out.println("out contains - " + out.containsValue(reiter[reit].getWunschpferd(currentWPferd)));
					
					
		//Hier wird überprüft, ob das Pferd bereits genommen wurde
					if(!out.containsValue(reiter[reit].getWunschpferd(currentWPferd))) {
						//containsNot = true;
						
						System.out.println("currentWpferd: " + currentWPferd + " namens: " + reiter[reit].getWunschpferd(currentWPferd));
						out.put(reiter[reit], reiter[reit].getWunschpferd(currentWPferd));
							
						check1 = true;
						System.out.println("nach put");
					} else {
						check1 = false;
						System.out.println("in Out");
		//Hier wird überprüft, ob der reiter x überhaupt noch weitere Wunschpferde hat.
						if (reiter[reit].hasNextWunschpferd(currentWPferd)) {
							System.out.println("hat ein weiteres Wunschpferd, das current war schon in out");
							//check1 = false; //dazu gemacht 20190613
							currentWPferd ++;
							//out.put(reiter[reit], reiter[reit].getWunschpferd(currentWPferd)); //dann wird ja immer sofort das neue WPferd in der Map gespeichert
							//generiereZuordnung(reit, currentWPferd);
						} else {
							if (out.size() > out2.size()) {
								out2.clear();
								for(Reiter key : out.keySet()) {
									Reiter k = key;
									Pferd v = out.get(key);
									out2.put(k, v);
								}
								
							}
							System.out.println("out "+out);
							System.out.println("out2"+ out2);

							out.remove(reiter[reit]);
							System.out.println("removed 1"+reiter[reit]);
							System.out.println("out"+out);
							System.out.println("out2"+out2);
//							check1 = true;
							return false;
						}
					}
				} else {
					
					if (reiter[reit].hasNextWunschpferd(currentWPferd)) {
						System.out.println("hat ein weiteres Wunschpferd, das current war nicht geeignet.");
						System.out.println(reiter[reit].toString());
						currentWPferd ++;
						//out.put(reiter[reit], wpferde[currentWPferd]); //kann man weglassen!? 20190613
						//generiereZuordnung(reit, currentWPferd);	
						check1 = false;

					} else {
						out.remove(reiter[reit]); //kann man weglassen!? 20190613
						System.out.println("removed 2");
						//check1 = true;
						return false;
					}
				}
				
			} while (!check1);
				erfolgreich = generiereZuordnung(reit+1, 0);
				System.out.println(currentWPferd);
				System.out.println("Es gab einen FEHLER bei der Zuordnung");
				
			} while (!erfolgreich);
			System.out.println(reiter[reit] + " ist am Ende.");
			return true;
		}
	
	/*
	public int genZuordnung(Reiter[] reiterA, int reit, Pferd[] pferdeA, int pfe) {
		Reiterhof reiterHof = new Reiterhof(reiter, pferde);
		if (pferd == 0 && reit == 0) {
			wCount = 0;
		}
		if (reiterHof.hasNextReiter(reiter[reit])) {
			return 0;
		}
		if (wunschPferd(pferde[pferd], reiter[reit])) {
			wCount ++;
			return 1;
		}
		
		if(nimmPferd()) {
			
		}
		
	
		
		
		genZuordnung(reit + 1, pferd);
	}
	*/
			
	public boolean nimmPferd(int reit, int pferd) {
		if(out.containsValue(pferde[pferd])) {
			return false;
		}
		out.put(reiter[reit], pferde[pferd]);
		return true;
	}
		
	
	public boolean checkWPferd(int reit, int wpferd) {
		System.out.println(" - " + reiter[reit].toString() + " und " + reiter[reit].getWunschpferd(wpferd).toString() + "; Niveau: " + reiter[reit].getWunschpferd(wpferd).getNiveau() + "; Koennen: " + reiter[reit].getKoennen());
		if(reiter[reit].getWunschpferd(wpferd).getNiveau() <= reiter[reit].getKoennen()) {
			System.out.println("check is true");
			return true;
		} else {
			System.out.println("check is false");
			return false;
		}
	}
	
	
	//@SuppressWarnings("unlikely-arg-type")
	public boolean wunschPferd(Pferd pferd, Reiter reiter) {
		if (Arrays.asList(reiter.getWunschpferde()).contains(pferd)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		String pairs = "";
		if (out.size()>out2.size()) {
			for(Reiter key : out.keySet())
			{
			      pairs = pairs + ("Key: " + key + " - Value: " + out.get(key) + "\n");
			}
		}
		else {
			for(Reiter key : out2.keySet())
		    {
		      pairs = pairs + ("Key: " + key + " - Value: " + out2.get(key) + "\n");
		    }

		}
				return pairs;
	}
	
	
}
 