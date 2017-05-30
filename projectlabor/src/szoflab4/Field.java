/*
 * Absztrakt oszt�ly, k�z�s f�ggv�nyek defini�l�sa.
 * �sszefoglalja a k�l�nb�z� mez�k k�z�s tulajdons�gait.
 */

package szoflab4;

import java.util.*;

public abstract class Field {
	// mez� szomsz�dos mez�inek list�ja. 2,3,4 szomsz�dunk van
	public ArrayList<Field> neighbours=new ArrayList<Field>();
	
	public void addAllNeighbours(){
		for(int i=0;i<4;i++)
			add(new Floor());
	}
	
	public void add(Field f){	//�j elem hozz�ad�sa a Field list�hoz
		this.neighbours.add(f);
	}
	
	public void RepStep(Ghost r){
	}
	public void setRep(Ghost r){
	}
	
	public Field getNeighbour(Direction dir) { //Dir ir�ny� Szomsz�d lek�rdez�se
		switch(dir){
			case left: return neighbours.get(0);
			case up: return neighbours.get(1);
			case right: return neighbours.get(2);
			case down: return neighbours.get(3);
		}
		return null;
	}
	
	public void setNeighbour(Direction dir,Field f) {		
		//Szomsz�dok be�ll�t�sa
		switch(dir){
			case left:  this.neighbours.set(0, f);
			break;
			case up:  	this.neighbours.set(1, f);
			break;
			case right: this.neighbours.set(2, f);
			break;
			case down:  this.neighbours.set(3, f);
			break;
		}
		
	}
	/**
	 * 
	 * @param b
	 * @param d
	 */
	public abstract Box pickOrDrop(Box b);

	/**
	 * 
	 * @param dir
	 */
	public abstract boolean canIStep();

	/**
	 * 
	 * @param b
	 * @param dir
	 */
	public abstract void shootOver(Bullet b, Direction dir);

	/**
	 * 
	 * @param dir
	 */
	public abstract Field step(MoveInter o);

	/**
	 * 
	 * @param box
	 */

}

