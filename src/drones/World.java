package drones;

import java.util.ArrayList;

public class World {

	private int width;
	private int height;
	private ArrayList<Cellule> cellules;
	private Cell[][] map;
	
	public World() {
		height = 5;
		width = 7;
		cellules = new ArrayList<Cellule>(height*width);
		map = new Cell[5][7];
		
		for(int i=0; i<width; i++) {
			cellules.add(new Wall(0,i));
		}
		cellules.add(new Wall(1,0)); 
		cellules.add(new EmptyCell(1,1)); 
		cellules.add(new EmptyCell(1,2)); 
		cellules.add(new Intruder(1,3)); 
		cellules.add(new EmptyCell(1,4)); 
		cellules.add(new MoneyBag(1,5));
		cellules.add(new Wall(1,6));
		
		cellules.add(new Wall(2,0)); 
		cellules.add(new EmptyCell(2,1)); 
		cellules.add(new EmptyCell(2,2)); 
		cellules.add(new EmptyCell(2,3)); 
		cellules.add(new EmptyCell(2,4)); 
		cellules.add(new MoneyBag(2,5));
		cellules.add(new Wall(2,6));
		
		cellules.add(new Wall(3,0)); 
		cellules.add(new Drone(3,1)); 
		cellules.add(new EmptyCell(3,2)); 
		cellules.add(new EmptyCell(3,3)); 
		cellules.add(new EmptyCell(3,4)); 
		cellules.add(new EmptyCell(3,5));
		cellules.add(new EmptyCell(3,6));
		
		for(int i=0; i<width; i++) {
			cellules.add(new Wall(4,i));
		}
	}
	
	public void majMap() {
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			map[c.getPosX()][c.getPosY()] = c;
		}
	}
	
	public void printWorld() {
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				map[i][j].print();
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void movements() {
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c instanceof Drone) {
				
				((Drone)c).move(caseVoisine(c));
			}
			
		}
		/*
		Drone d = (Drone)cellules.get(22);		
		d.move(caseVoisineHaut(d));
		
		Intruder i = (Intruder)cellules.get(10);		
		i.move(caseVoisineBas(i));		
		*/
	}
	
	public Cellule caseVoisine(Cellule c) {
		int i = (int)Math.floor(Math.random()*4);
		switch(i) {
			case 0 : return caseVoisineGauche(c);
			case 1 : return caseVoisineDroite(c);
			case 2 : return caseVoisineHaut(c);
			default : return caseVoisineBas(c);
		}
	}
	
	public Cellule caseVoisineDroite(Cellule c) {
		return getCellule(c.getPosX(), c.getPosY()+1);
	}
	public Cellule caseVoisineGauche(Cellule c) {
		return getCellule(c.getPosX(), c.getPosY()-1);
	}
	public Cellule caseVoisineHaut(Cellule c) {
		return getCellule(c.getPosX()-1, c.getPosY());
	}
	public Cellule caseVoisineBas(Cellule c) {
		return getCellule(c.getPosX()+1, c.getPosY());
	}
	
	public Cellule getCellule(int x, int y) {
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c.getPosX() == x && c.getPosY() == y) {
				return c;
			}
		}
		return null;
	}
}
