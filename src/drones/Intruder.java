package drones;

import java.util.ArrayList;
import java.util.Scanner;

public class Intruder extends Cellule implements Cell, Player{

	private String name;
	
	private int money = 0;
	
	public Intruder(int y, int x, String name) {
		super(y,x);
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
	
	public void play(ArrayList<Cellule> cellules, Scanner sc) {
		if(thereIsMoney(cellules)) {
			findMoney(cellules, this);
		}else {
			findExit(cellules, this);
		}
		/*if(nextToMoney(cellules, this)) {
			System.out.println("money!");
			collectMoney(cellules, this);
		}else {
			move(caseVoisine(cellules, this));
		}*/
	}
	
	public void findExit(ArrayList<Cellule> cellules, Cellule c) {
		int exitX = -1, exitY = -1;
		for(int i=0; i<cellules.size(); i++) {
			Cellule cellule = (Cellule)cellules.get(i);			
			if(cellule instanceof Exit) {
				exitX = cellule.posX;
				exitY = cellule.posY;
				break;
			}			
		}
		if(nextToExit(cellules, this)) {
			System.out.println("exit!");
			takeExit(cellules, this);
		}else {
			moveTowards(cellules, c, exitY, exitX);
		}
	}
	
	public void takeExit(ArrayList<Cellule> cellules, Cellule c) {
				
		replaceCell(cellules, this.getPosY(), this.getPosX());
		
		System.out.println(name+" is out with "+money+" money");
	}
	
	public void findMoney(ArrayList<Cellule> cellules, Cellule c) {
		int moneyX = -1, moneyY = -1;
		for(int i=0; i<cellules.size(); i++) {
			Cellule cellule = (Cellule)cellules.get(i);			
			if(cellule instanceof MoneyBag) {
				moneyX = cellule.posX;
				moneyY = cellule.posY;
				break;
			}			
		}
		if(nextToMoney(cellules, this)) {
			System.out.println("money!");
			collectMoney(cellules, this);
		}else {
			moveTowards(cellules, c, moneyY, moneyX);
		}
		
	}
	
	public void moveTowards(ArrayList<Cellule> cellules, Cellule c, int y, int x) {
		boolean moved = false;
		if(x > this.posX) {
			moved = move(caseVoisineDroite(cellules, c));
		}else if(x < this.posX) {
			moved = move(caseVoisineGauche(cellules, c));
		}
		if(!moved) {
			if(y > this.posY) {
				moved = move(caseVoisineBas(cellules, c));
			}else if(y < this.posY) {
				moved = move(caseVoisineHaut(cellules, c));
			}
		}
	}
	
	public boolean thereIsMoney(ArrayList<Cellule> cellules) {
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c instanceof MoneyBag) {
				return true;
			}			
		}
		return false;
	}
	
	public void collectMoney(ArrayList<Cellule> cellules, Cellule c) {
		money++;
		if(caseVoisineGauche(cellules, c) instanceof MoneyBag) {			
			replaceCell(cellules, caseVoisineGauche(cellules, c).getPosY(), 
					caseVoisineGauche(cellules, c).getPosX());
		}else if(caseVoisineDroite(cellules, c) instanceof MoneyBag) {			
			replaceCell(cellules, caseVoisineDroite(cellules, c).getPosY(), 
					caseVoisineDroite(cellules, c).getPosX());
		}else if(caseVoisineHaut(cellules, c) instanceof MoneyBag) {			
			replaceCell(cellules, caseVoisineHaut(cellules, c).getPosY(), 
					caseVoisineHaut(cellules, c).getPosX());
		}else {			
			replaceCell(cellules, caseVoisineBas(cellules, c).getPosY(), 
					caseVoisineBas(cellules, c).getPosX());
		}
		System.out.println("money of "+name+" is : "+money);
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
	public boolean nextToExit(ArrayList<Cellule> cellules, Cellule c) {
		if(caseVoisineGauche(cellules, c) instanceof Exit
			|| caseVoisineDroite(cellules, c) instanceof Exit
			|| caseVoisineHaut(cellules, c) instanceof Exit
			|| caseVoisineBas(cellules, c) instanceof Exit) {
			return true;
		}else {
			return false;
		}
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
