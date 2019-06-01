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
	private Pferd[] wpferde;
	private Pferd currPony;
	private int fulfilled;
	private int currentWunschScore = 0;
	private int i = 0;
	
	//dies ist die HashMap, in der im Laufe des Programms die Reiter-Pferd-Paare eingetragen werden.
	Map<Reiter, Pferd> out = new HashMap<>();
	//dies ist die HashMap, in der die Reiter-Wunschpferde-Paare angegeben sind.
	Map<Reiter, Pferd> wanted = new HashMap<>();
	
	//um die Reiter Pferde wählen zu lassen, werden zu Beginn die Reiter und alle verfügbaren Pferde eingetragen.
	//Reiter mit Namen (""), Koennen (1, 2, 3) und Wunschpferden [""]
	public Zuordnung() {
		pferde[0] = new Pferd("Alex", 1, true);
		pferde[1] = new Pferd("Nicki", 2, true);
		pferde[2] = new Pferd("Pucki", 2, true);
		/*pferde[3] = new Pferd("Wittchen", 1, true);
		pferde[4] = new Pferd("Hurrikan", 2, true);
		pferde[5] = new Pferd("Tornado", 3, true);
		pferde[6] = new Pferd("Sturmwind", 2, true);
		pferde[7] = new Pferd("Zausel", 1, true);
		pferde[8] = new Pferd("Zickzack", 3, true);*/
		
		reiter[0] = new Reiter("Anja", 1, wpferde = Stream.of(pferde[0], pferde[1], pferde[2], pferde[3]).toArray(Pferd[]::new));
		/*reiter[1] = new Reiter("Bertram", 2, wpferde = Stream.of(pferde[0], pferde[1], pferde[2], pferde[3], pferde[4], pferde[5], pferde[6], pferde[7], pferde[8]).toArray(Pferd[]::new));
		reiter[2] = new Reiter("Christa", 3, wpferde = Stream.of(pferde[4]).toArray(Pferd[]::new));
		reiter[3] = new Reiter("Doris", 1, wpferde = Stream.of(pferde[0], pferde[2], pferde[3]).toArray(Pferd[]::new));
		reiter[4] = new Reiter("Emil", 1, wpferde = Stream.of(pferde[0], pferde[1], pferde[2], pferde[3], pferde[4], pferde[5], pferde[6], pferde[7], pferde[8]).toArray(Pferd[]::new));
		reiter[5] = new Reiter("Fritz", 3, wpferde = Stream.of(pferde[4], pferde[6]).toArray(Pferd[]::new));
		reiter[6] = new Reiter("Gabi", 2, wpferde = Stream.of(pferde[0], pferde[4], pferde[5], pferde[6]).toArray(Pferd[]::new));*/
		
		fulfilled = 0;
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
	
	public Map<Reiter, Pferd> paare(int reit, int pfer) {
		if ((pferde.length - reiter.length) == 3) {
			MaximizeAction nimmPferd(pfer);
			nimmPferd(pfer + 1);
			nimmPferd(pfer + 2);
			fulfilled = fulfilled + nimmPferd(pfer);
			
		}
		else {
			nimmPferd(i);
		}
		
		paare(i+1);
		return out;
	}
	
	
	//a entspricht Reiter im Reiter[]
	public Pferd pferdFinden(int r, int p){
		//für Reiter a neues Pferd finden
		int currReiter = r;//Reiter-Pferd-Paar in Map out speichern
		int pony = p;
		fulfilled = fulfilled + nimmPferd(pony/*, currReiter*/);
		//reiter[a];
		a++;
		pferdFinden(a);
		//return 
		
		out.put(reiter[a], pferde[0]);
		
		//erneuter Aufruf der Methode
		//a++;
		value = value + nimmPferd(0);
				
		System.out.println("aktueller value: " + value);
		
		a++;
		/*if (a < reiter.length) {
			currPony = pferdFinden(a, b);
		}*/
		
		System.out.println(out.toString());
		return pferde[a];
		
	}
	
/**
 * der aktuelle Reiter wählt ein Pferd und es wird überprüft, ob es ein erlaubtes oder sogar ein Wunschpferd ist.
 * @param i
 * @return
 */
	public int nimmPferd(int i/*, int j*/){
		/*i++;
		System.out.println("----- Anfang von rekursiv. ------" + i);
		nimmPferd(i);
		System.out.println("----- Ende von rekursiv. ------" + i);
		*/
		
		//if()
		
		System.out.println("Pferd Niveau: " + pferde[i].getNiveau());
		System.out.println("Reiter Koennen: " + reiter[0].getKoennen());
		if(pferde[i].getNiveau() <= reiter[0].getKoennen()) {
			if(wunschPferd(pferde[i], reiter[0])) {
				System.out.println("Juhuuuu " + pferde[i].toString() + ", das ist genau mein (" + reiter[0].toString() + ") Wunschpferd!" + "\n");
				//i++;
				currPony = pferde[i];
				out.put(reiter[0], pferde[i]);
				return 1;
			}
			else {
				System.out.println("OH NO, wie schade, das ist leider nicht mein (" + reiter[0].toString() + ") Wunschpferd! Aber ich versuch es trotzdem mit " + pferde[i].toString() + " ;)" + "\n");
				//i++;
				currPony = pferde[i];
				out.put(reiter[0], pferde[i]);
				return 0;
			}
		}
		else {
			System.out.println("Was ist denn hier passiert? Dieses Pferd (" + pferde[i].toString() +") kann ich (" + reiter[0].toString() + ") noch nicht reiten!" + "\n");
			//i++;
			currPony = pferde[i];
			return -1;
		}
	}
	
/**
 * wie ist es, wenn ich ein Pferd am Endes des pferde[] betrachte?
 * @param currP
 * @return
 */
	public Pferd nextPferd(int currP) {
		if(currP < pferde.length - 1) {
			while (!pferde[currP + 1].verfuegbar())
			{
				currP ++;
			}
			Pferd naechstes = pferde[currP];
			return naechstes;
		}
		return null;
	}
	
	public boolean hasNextPferd(int i) {
		if(i < pferde.length - 1) {
			return true;
		}
		return false;
			
		
		return null;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean wunschPferd(Pferd pferd, Reiter reiter) {
		/*List<Pferd> x = );
		System.out.println(x);
		for (Pferd e : x) {
			System.out.println(e.toString());
		}*/
		if (Arrays.asList(reiter.getWunschpferde()).contains(pferd)) {
			return true;
		}
		return false;
	}
	
}
 