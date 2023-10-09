package drones;

import java.util.Scanner;

public class TestWorld {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		World w = new World(sc);
		w.majMap();
		w.printWorld();
		
		System.out.println("appuyer sur 1 pour la prochaine étape");
		while(sc.nextInt() == 1) {
			w.movements();
			System.out.println("appuyer sur 1 pour la prochaine étape");
		}
		
		
		
		
	}

}
