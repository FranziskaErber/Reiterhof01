package main;

import reiterhof.Zuordnung;

public class Main {

	private static int pferdl;
	private static int reiterl;
	
	public static void main(String[] args) {
		Zuordnung optPairs = new Zuordnung();
		//optPairs.pferdFinden(0);
		System.out.println(optPairs.getReiter(0));
		//int p = 0;
		for (int p = 0; p < optPairs.getPferdeArray().length; p++) {
			optPairs.nimmPferd(p);
		}
		
		optPairs.pferdFinden(reiterl, pferdl);
		
	}

}
