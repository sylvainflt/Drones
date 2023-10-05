package drones;

public abstract class Cellule implements Cell{

	protected int posX;
	protected int posY;
	
	public Cellule(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void move(Cellule c) {
		if(this.canMove() && c.canMove()) {
			int prevPosX = getPosX();
			int prevPosY = getPosY();
			
			this.setPosX(c.getPosX());
			this.setPosY(c.getPosY());
			
			c.setPosX(prevPosX);
			c.setPosY(prevPosY);
		}
		
	}
	
	
}
