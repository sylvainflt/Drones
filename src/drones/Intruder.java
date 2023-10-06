package drones;

import java.util.ArrayList;

public class Intruder extends Cellule implements Cell, Player{

	private String name;
	
	private int money = 0;
	
	public Intruder(int x, int y, String name) {
		super(x,y);
		this.name = name;
	}
		
	public String getName() {
		return name;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void print() {
		System.out.print("I");
	}
	public boolean isEmpty() {
		return false;
	}
	public boolean canMove() {
		return true;
	}
	
	public void play(ArrayList<Cellule> cellules) {
		if(nextToMoney(cellules, this)) {
			System.out.println("money!");
			collectMoney(cellules, this);
		}else {
			move(caseVoisine(cellules, this));
		}
	}
	
	public void collectMoney(ArrayList<Cellule> cellules, Cellule c) {
		money++;
		if(caseVoisineGauche(cellules, c) instanceof MoneyBag) {			
			replaceCell(cellules, caseVoisineGauche(cellules, c).getPosX(), 
					caseVoisineGauche(cellules, c).getPosY());
		}else if(caseVoisineDroite(cellules, c) instanceof MoneyBag) {			
			replaceCell(cellules, caseVoisineDroite(cellules, c).getPosX(), 
					caseVoisineDroite(cellules, c).getPosY());
		}else if(caseVoisineHaut(cellules, c) instanceof MoneyBag) {			
			replaceCell(cellules, caseVoisineHaut(cellules, c).getPosX(), 
					caseVoisineHaut(cellules, c).getPosY());
		}else {			
			replaceCell(cellules, caseVoisineBas(cellules, c).getPosX(), 
					caseVoisineBas(cellules, c).getPosY());
		}
		System.out.println("money of "+name+" is : "+money);
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
	
	public boolean nextToMoney(ArrayList<Cellule> cellules, Cellule c) {
		if(caseVoisineGauche(cellules, c) instanceof MoneyBag
			|| caseVoisineDroite(cellules, c) instanceof MoneyBag
			|| caseVoisineHaut(cellules, c) instanceof MoneyBag
			|| caseVoisineBas(cellules, c) instanceof MoneyBag) {
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
