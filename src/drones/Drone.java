package drones;

import java.util.ArrayList;
import java.util.Scanner;

public class Drone extends Cellule implements Cell, Player{

	public Drone(int y, int x) {
		super(y,x);
	}
	
	public void print() {
		System.out.print("D");
	}
	public boolean isEmpty() {
		return false;
	}
	public boolean canMove() {
		return true;
	}
	
	public void play(ArrayList<Cellule> cellules, Scanner sc) {
		if(nextToIntruder(cellules, this)) {
			System.out.println("Intruder!");
			killIntruder(cellules, this);
		}else {
			move(cellules, this, sc);
		}		
	}
	
	public void move(ArrayList<Cellule> cellules, Cellule c, Scanner sc) {
		System.out.println("choisir une direction : (4 8 6 2)");
		int direction = sc.nextInt();
		
		if(direction == 4) {
			super.move(caseVoisineGauche(cellules, c));
		}else if(direction == 6) {
			super.move(caseVoisineDroite(cellules, c));
		}else if(direction == 8) {
			super.move(caseVoisineHaut(cellules, c));
		}else if(direction == 2) {
			super.move(caseVoisineBas(cellules, c));
		}
		
	}
	
	public void killIntruder(ArrayList<Cellule> cellules, Cellule c) {
		String name;
		int money;
		if(caseVoisineGauche(cellules, c) instanceof Intruder) {	
			name = ((Intruder)caseVoisineGauche(cellules, c)).getName();
			money = ((Intruder)caseVoisineGauche(cellules, c)).getMoney();
			replaceCell(cellules, caseVoisineGauche(cellules, c).getPosY(), 
					caseVoisineGauche(cellules, c).getPosX());
		}else if(caseVoisineDroite(cellules, c) instanceof Intruder) {	
			name = ((Intruder)caseVoisineDroite(cellules, c)).getName();
			money = ((Intruder)caseVoisineDroite(cellules, c)).getMoney();
			replaceCell(cellules, caseVoisineDroite(cellules, c).getPosY(), 
					caseVoisineDroite(cellules, c).getPosX());
		}else if(caseVoisineHaut(cellules, c) instanceof Intruder) {
			name = ((Intruder)caseVoisineHaut(cellules, c)).getName();
			money = ((Intruder)caseVoisineHaut(cellules, c)).getMoney();
			replaceCell(cellules, caseVoisineHaut(cellules, c).getPosY(), 
					caseVoisineHaut(cellules, c).getPosX());
		}else {			
			name = ((Intruder)caseVoisineBas(cellules, c)).getName();
			money = ((Intruder)caseVoisineBas(cellules, c)).getMoney();
			replaceCell(cellules, caseVoisineBas(cellules, c).getPosY(), 
					caseVoisineBas(cellules, c).getPosX());
		}
		System.out.println("you killed an intruder, their name was "+name
				+", they had "+money+" money");
	}
	
	public void replaceCell(ArrayList<Cellule> cellules, int posY, int posX) {
		cellules.set(cellIndex(cellules, posY, posX), new EmptyCell(posY, posX));
	}
	
	public int cellIndex(ArrayList<Cellule> cellules, int posY, int posX) {
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c.getPosY() == posY && c.getPosX() == posX) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean nextToIntruder(ArrayList<Cellule> cellules, Cellule c) {
		if(caseVoisineGauche(cellules, c) instanceof Intruder
			|| caseVoisineDroite(cellules, c) instanceof Intruder
			|| caseVoisineHaut(cellules, c) instanceof Intruder
			|| caseVoisineBas(cellules, c) instanceof Intruder) {
			return true;
		}else {
			return false;
		}
	}
	
	public Cellule caseVoisine(ArrayList<Cellule> cellules, Cellule c) {
		int i = (int)Math.floor(Math.random()*4);
		switch(i) {
			case 0 : return caseVoisineGauche(cellules, c);
			case 1 : return caseVoisineDroite(cellules, c);
			case 2 : return caseVoisineHaut(cellules, c);
			default : return caseVoisineBas(cellules, c);
		}
	}
	
	public Cellule caseVoisineDroite(ArrayList<Cellule> cellules, Cellule c) {
		return getCellule(cellules, c.getPosY(), c.getPosX()+1);
	}
	public Cellule caseVoisineGauche(ArrayList<Cellule> cellules, Cellule c) {
		return getCellule(cellules, c.getPosY(), c.getPosX()-1);
	}
	public Cellule caseVoisineHaut(ArrayList<Cellule> cellules, Cellule c) {
		return getCellule(cellules, c.getPosY()-1, c.getPosX());
	}
	public Cellule caseVoisineBas(ArrayList<Cellule> cellules, Cellule c) {
		return getCellule(cellules, c.getPosY()+1, c.getPosX());
	}
	
	public Cellule getCellule(ArrayList<Cellule> cellules, int y, int x) {
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c.getPosY() == y && c.getPosX() == x) {
				return c;
			}
		}
		return null;
	}
}
