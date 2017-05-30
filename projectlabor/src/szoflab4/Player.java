/*
 * A karakterért felelõs osztály.
 * Mindent megvalósít, lekezel amit a játékos tud csinálni a pályán
 */
package szoflab4;

import java.util.ArrayList;

public class Player implements MoveInter{
	
	private Game game;
	private Map map;
	private Direction dir = Direction.right;
	private ArrayList<Zpm> zpmlist = new ArrayList<Zpm>();
	private Field poz;
	private Box box = null;
	private Color color;	
	private Bullet bullet;
	private int weight = 2;
	
	Player(Field poz, Map m, Game g, Color c){
		bullet=new Bullet(c);
		setPoz(poz);
		map = m;
		game=g;
		poz.addAllNeighbours();
	}
		
	public Field getPoz() {
		return poz;
	}
	/*
	 * Lekérjük a játékos súlyát.
	 */
	public int getWgh() {
		return weight;
	}
	public void setPoz(Field f){
		this.poz=f;
	}
	public void setDir(Direction dir){
		this.dir=dir;
	}

	public void setBox(Box b){
		box=b;
	}
	public Direction getDir(){
		return this.dir;
	}
	public int getListLength(){
		return zpmlist.size();
	}
	public Box getBox(){
		return box;
	}
	public void shoot() {

		poz.shootOver(bullet, dir);
		bullet.changeColor();
	}
	/*
	 * Lekérjük a getNeighbour-ral azt a szomszédot amerre lépni akarunk, majd megkérjük tõle 
	 * hogy rá tudunk- e lépni ha igen akkor átállítjuk a pozítciónkat az újra,
	 * ha nem akkor a pozítció marad. A pozitció állítása elõtt még megnézzük hogy scalerõl akarunk-e lelépni
	 * mert ha igen akkor az deaktiválódik.
	 */
	public void move() {	//nem kell szarozni a súllyal, csak az a baj ha jön a jaffa...

		Field tmp=poz.getNeighbour(dir);
		if(tmp.canIStep()){
			if((poz.getClass().getSimpleName()).equals("Scale")){				
				((Scale)poz).setDoor();
			}
			setPoz(tmp.step(this));
		}		
	}
	/*
	 * Dobozt szeretnénk felvenni vagy lerakni
	 * így az elõttünk lévõ field pickOrDropját meghívjuk.
	 */
	public void pickOrDrop() {
		if(map.getJaffa()==null || map.getOneill()==null){
			setBox(poz.getNeighbour(dir).pickOrDrop(box));
		}
		else if(!((map.getJaffa().getPoz().equals(poz.getNeighbour(dir))) || (map.getOneill().getPoz().equals(poz.getNeighbour(dir))))){
					setBox(poz.getNeighbour(dir).pickOrDrop(box));
			}
		
	}

	public void die() {			//Meghalt a játékos, jelezzük a Gamek hogy halottak vagyunk
		game.endGame(this);
	}

	/**
	 * A felvett zpm hozzáadjuk a listához
	 * @param zpm
	 */
	public void addZPM(Zpm zpm) {
		
		zpmlist.add(zpm);
		this.map.zpmPicked(this);
	}

}

