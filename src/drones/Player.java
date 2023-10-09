package drones;

import java.util.ArrayList;
import java.util.Scanner;

public interface Player {

	public boolean move(Cellule c);
	
	public void play(ArrayList<Cellule> cellules, Scanner sc);
	
}
