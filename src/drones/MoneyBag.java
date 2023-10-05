package drones;

public class MoneyBag extends Cellule implements Cell{

	public MoneyBag(int x, int y) {
		super(x,y);
	}
	
	public void print() {
		System.out.print("$");
	}
	public boolean isEmpty() {
		return false;
	}
	public boolean canMove() {
		return false;
	}
}
