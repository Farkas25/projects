/*
 * A karakter�rt felel�s oszt�ly.
 * Mindent megval�s�t, lekezel amit a j�t�kos tud csin�lni a p�ly�n
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
	 * Lek�rj�k a j�t�kos s�ly�t.
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
	 * Lek�rj�k a getNeighbour-ral azt a szomsz�dot amerre l�pni akarunk, majd megk�rj�k t�le 
	 * hogy r� tudunk- e l�pni ha igen akkor �t�ll�tjuk a poz�tci�nkat az �jra,
	 * ha nem akkor a poz�tci� marad. A pozitci� �ll�t�sa el�tt m�g megn�zz�k hogy scaler�l akarunk-e lel�pni
	 * mert ha igen akkor az deaktiv�l�dik.
	 */
	public void move() {	//nem kell szarozni a s�llyal, csak az a baj ha j�n a jaffa...

		Field tmp=poz.getNeighbour(dir);
		if(tmp.canIStep()){
			if((poz.getClass().getSimpleName()).equals("Scale")){				
				((Scale)poz).setDoor();
			}
			setPoz(tmp.step(this));
		}		
	}
	/*
	 * Dobozt szeretn�nk felvenni vagy lerakni
	 * �gy az el�tt�nk l�v� field pickOrDropj�t megh�vjuk.
	 */
	public void pickOrDrop() {
		if(map.getJaffa()==null || map.getOneill()==null){
			setBox(poz.getNeighbour(dir).pickOrDrop(box));
		}
		else if(!((map.getJaffa().getPoz().equals(poz.getNeighbour(dir))) || (map.getOneill().getPoz().equals(poz.getNeighbour(dir))))){
					setBox(poz.getNeighbour(dir).pickOrDrop(box));
			}
		
	}

	public void die() {			//Meghalt a j�t�kos, jelezz�k a Gamek hogy halottak vagyunk
		game.endGame(this);
	}

	/**
	 * A felvett zpm hozz�adjuk a list�hoz
	 * @param zpm
	 */
	public void addZPM(Zpm zpm) {
		
		zpmlist.add(zpm);
		this.map.zpmPicked(this);
	}

}

