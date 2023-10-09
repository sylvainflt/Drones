package drones;

import java.util.ArrayList;
import java.util.Scanner;

public class World {

	private int width;
	private int height;
	private ArrayList<Cellule> cellules;
	private Cell[][] map;
	private Scanner scanner;
	/*
	public World(Scanner sc) {
		this.scanner = sc;
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
		cellules.add(new Intruder(1,3, "georges")); 
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
		cellules.add(new Exit(3,6));
		
		for(int i=0; i<width; i++) {
			cellules.add(new Wall(4,i));
		}
	}*/
	/*
	public World(Scanner sc) {
		this.scanner = sc;
		height = 7;
		width = 10;
		cellules = new ArrayList<Cellule>(height*width);
		map = new Cell[height][width];
		
		for(int i=0; i<width; i++) {
			cellules.add(new Wall(0,i));
		}
		cellules.add(new Wall(1,0)); 
		cellules.add(new EmptyCell(1,1)); 
		cellules.add(new EmptyCell(1,2)); 
		cellules.add(new EmptyCell(1,3)); 
		cellules.add(new MoneyBag(1,4)); 
		cellules.add(new EmptyCell(1,5));
		cellules.add(new EmptyCell(1,6));
		cellules.add(new EmptyCell(1,7)); 
		cellules.add(new Intruder(1,8, "x")); 
		cellules.add(new Wall(1,9)); 
		
		cellules.add(new Wall(2,0)); 
		cellules.add(new MoneyBag(2,1)); 
		cellules.add(new EmptyCell(2,2)); 
		cellules.add(new EmptyCell(2,3)); 
		cellules.add(new EmptyCell(2,4)); 
		cellules.add(new EmptyCell(2,5));
		cellules.add(new EmptyCell(2,6));
		cellules.add(new EmptyCell(2,7));
		cellules.add(new EmptyCell(2,8));
		cellules.add(new Wall(2,9));
		
		cellules.add(new Wall(3,0)); 
		cellules.add(new EmptyCell(3,1)); 
		cellules.add(new EmptyCell(3,2)); 
		cellules.add(new EmptyCell(3,3)); 
		cellules.add(new Drone(3,4)); 
		cellules.add(new EmptyCell(3,5));
		cellules.add(new EmptyCell(3,6));
		cellules.add(new EmptyCell(3,7));
		cellules.add(new EmptyCell(3,8));
		cellules.add(new Wall(3,9));
		
		cellules.add(new Exit(4,0)); 
		cellules.add(new EmptyCell(4,1)); 
		cellules.add(new EmptyCell(4,2)); 
		cellules.add(new EmptyCell(4,3)); 
		cellules.add(new EmptyCell(4,4)); 
		cellules.add(new EmptyCell(4,5));
		cellules.add(new EmptyCell(4,6));
		cellules.add(new EmptyCell(4,7));
		cellules.add(new Intruder(4,8, "xx"));
		cellules.add(new Wall(4,9));
		
		cellules.add(new Wall(5,0)); 
		cellules.add(new EmptyCell(5,1)); 
		cellules.add(new EmptyCell(5,2)); 
		cellules.add(new EmptyCell(5,3)); 
		cellules.add(new EmptyCell(5,4)); 
		cellules.add(new EmptyCell(5,5));
		cellules.add(new EmptyCell(5,6));
		cellules.add(new EmptyCell(5,7));
		cellules.add(new MoneyBag(5,8));
		cellules.add(new Wall(5,9));
		
		for(int i=0; i<width; i++) {
			cellules.add(new Wall(6,i));
		}
	}*/
	
	public World(Scanner sc) {
		this.scanner = sc;
		height = 5+ (int)Math.floor(Math.random()*5);
		width = 10+ (int)Math.floor(Math.random()*5);
		System.out.println("height "+height+" width "+width);
		cellules = new ArrayList<Cellule>(height*width);
		map = new Cell[height][width];
		
		for(int i=0; i<width; i++) {				
			cellules.add(new Wall(0,i)); 								
		}
		for(int j=1; j<height-1; j++) {			
			cellules.add(new Wall(j,0)); 
			for(int i=1; i<width-1; i++) {
				if((int)Math.floor(Math.random()*20) == 0) {
					cellules.add(new MoneyBag(j,i));
				}else {
					cellules.add(new EmptyCell(j,i));
				}				 							
			}
			cellules.add(new Wall(j,width-1));
		}
		for(int i=0; i<width; i++) {				
			cellules.add(new Wall(height-1,i)); 								
		}
		placeExit();
		placeIntruders();
		placeDrone();
	}
	
	private void placeDrone() {
		boolean setDrone = false;
		for(int i=1; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c instanceof EmptyCell && 
					(!setDrone && (int)Math.floor(Math.random()*20)==0)) {				
				cellules.set(i, new Drone(c.posY, c.posX));	
				setDrone = true;
			}			
		}
	}
	
	private void placeExit() {
		boolean setExit = false;
		for(int i=1; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c instanceof Wall && 
					(!setExit || (int)Math.floor(Math.random()*20)==0)) {				
				cellules.set(i, new Exit(c.posY, c.posX));	
				setExit = true;
			}			
		}
	}
	
	private void placeIntruders() {
		boolean setIntruder = false;
		for(int i=0; i<cellules.size(); i++) {
			Cellule c = (Cellule)cellules.get(i);			
			if(c instanceof EmptyCell && 
					(!setIntruder || (int)Math.floor(Math.random()*20)==0)) {				
				cellules.set(i, new Intruder(c.posY, c.posX, "xxx"));	
				setIntruder = true;
			}			
		}
	}
	
	public Scanner getScanner() {
		return scanner;
	}

	public void majMap() {
		for(Cellule c : cellules) {					
			map[c.getPosY()][c.getPosX()] = c;
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
		for(Cellule c : cellules) {				
			if(c instanceof Player) {				
				((Player)c).play(cellules, scanner);
				majMap();
				printWorld();
			}			
		}
		
	}
	
	
}
