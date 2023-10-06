package drones;

import java.util.ArrayList;

public class Drone extends Cellule implements Cell, Player{

	public Drone(int x, int y) {
		super(x,y);
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
	
	public void play(ArrayList<Cellule> cellules) {
		if(nextToIntruder(cellules, this)) {
			System.out.println("Intruder!");
			killIntruder(cellules, this);
		}else {
			move(caseVoisine(cellules, this));
		}		
	}
	
	public void killIntruder(ArrayList<Cellule> cellules, Cellule c) {
		String name;
		int money;
		if(caseVoisineGauche(cellules, c) instanceof Intruder) {	
			name = ((Intruder)caseVoisineGauche(cellules, c)).getName();
			money = ((Intruder)caseVoisineGauche(cellules, c)).getMoney();
			replaceCell(cellules, caseVoisineGauche(cellules, c).getPosX(), 
					caseVoisineGauche(cellules, c).getPosY());
		}else if(caseVoisineDroite(cellules, c) instanceof Intruder) {	
			name = ((Intruder)caseVoisineDroite(cellules, c)).getName();
			money = ((Intruder)caseVoisineDroite(cellules, c)).getMoney();
			replaceCell(cellules, caseVoisineDroite(cellules, c).getPosX(), 
					caseVoisineDroite(cellules, c).getPosY());
		}else if(caseVoisineHaut(cellules, c) instanceof Intruder) {
			name = ((Intruder)caseVoisineHaut(cellules, c)).getName();
			money = ((Intruder)caseVoisineHaut(cellules, c)).getMoney();
			replaceCell(cellules, caseVoisineHaut(cellules, c).getPosX(), 
					caseVoisineHaut(cellules, c).getPosY());
		}else {			
			name = ((Intruder)caseVoisineBas(cellules, c)).getName();
			money = ((Intruder)caseVoisineBas(cellules, c)).getMoney();
			replaceCell(cellules, caseVoisineBas(cellules, c).getPosX(), 
					caseVoisineBas(cellules, c).getPosY());
		}
		System.out.println("you killed an intruder, their name was "+name
				+", they had "+money+" money");
	}
	
	public void replaceCell(ArrayList<Cellule> cellules, int posX, int posY) {
		cellules.set(cellIndex(cellules, posX, posY), new EmptyCell(posX, posY));
	}
	
	public int cellIndex(ArrayList<Cellule> cellules, int posX, int posY) {
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c.getPosX() == posX && c.getPosY() == posY) {
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
		return getCellule(cellules, c.getPosX(), c.getPosY()+1);
	}
	public Cellule caseVoisineGauche(ArrayList<Cellule> cellules, Cellule c) {
		return getCellule(cellules, c.getPosX(), c.getPosY()-1);
	}
	public Cellule caseVoisineHaut(ArrayList<Cellule> cellules, Cellule c) {
		return getCellule(cellules, c.getPosX()-1, c.getPosY());
	}
	public Cellule caseVoisineBas(ArrayList<Cellule> cellules, Cellule c) {
		return getCellule(cellules, c.getPosX()+1, c.getPosY());
	}
	
	public Cellule getCellule(ArrayList<Cellule> cellules, int x, int y) {
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c.getPosX() == x && c.getPosY() == y) {
				return c;
			}
		}
		return null;
	}
}
