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
			if(c instanceof Player) {
				
				((Player)c).play(cellules);
			}
			
		}
		
	}
	
	
}
