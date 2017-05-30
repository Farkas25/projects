package szoflab4;
public class Door extends Floor {
	private boolean Closed = true;
	
	public void setDoor(boolean b) {
		Closed = b;
	}
	
	@Override
	public Box pickOrDrop(Box b) {
		return b;
	}
	public boolean getClosed() {
		return Closed;
	}

	/*
	 * Ha az isClosed false szóval nyitva van az ajtó akkor true-val térvissza,
	 * ha az isClosed true akkor a canIStep false értéket add vissza.
	 * Mivel ajtóra nem tehetünk dobozt, nem lehet rajta portál,
	 * így csak azt kell megnézzni hogy zárt vagy nyitott állapotban van-e.
	 */
	public boolean canIStep() {

		if(this.Closed){
			return false;
		}else{
			return true;
		}
	}

	/*
	 * Nyitott ajtón át tudunk lõni.
	 * Ezért itt megnézzük hogy a Door isClosed-je milyen állapotban van,
	 * ha false - nyitva van az ajtó - akkor át tudunk lõni rajta,
	 * így meghívjuk a szomszéd mezõ shootOverjét,
	 * ha isClosed true akkor megakad a golyó az ajtóban, mondhatni eltûnik
	 */
	public void shootOver(Bullet b, Direction dir) {

		if(!this.Closed)
			this.getNeighbour(dir).shootOver(b, dir);
	}

	/*
	 * Visszatérünk a Door helyzetével.
	 * Mivel a canIStep után hívódik meg a step itt nem kell már megvizsgálni hogy léphetünk-e
	 * Csak átadni az új Field-et
	 */
	public Field step(MoveInter o) {
		return this;
	}
}