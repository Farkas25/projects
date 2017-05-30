package szoflab4;
public class Scale extends Floor {
	private Door door = new Door();
	private int limit = 2;
	/*
	 * Meghívjuk a hozzánk tartozó door set-jét hogy állítani tudjuk az ajtót
	 */
	public void setDoor() {
		if (item.size() >= limit)
			door.setDoor(false);
		else {
			door.setDoor(true);
		}
	}
	public void setDoor(Door d) {
		door = d;
	}
	@Override
	public Box pickOrDrop(Box b) {	//már a listás anyjónyitogatással mûködik, ez kész.
		try { 						// van elõttem valami(csak doboz lehet){
			this.getItem().isZpm();
			if (b == null) { 		// nincs dobozom,felveszem
				Box tmp = (Box) remItem();
				this.setDoor(); 
				return tmp; 		// nálam van a doboz
			}
			if (b != null) { 		// van dobozom
				addItem(b); 		// ráteszek +1 dobozt
				this.setDoor(); 				
			}
		} catch (NullPointerException e) { // semmi sincs elõttem
			if (b != null) { 		// van dobozom
				addItem(b);			 // leteszem
			}
			return null;
		}
		return null;
	}
	@Override
	/*
	 * Megnézzük hogy van-e már rajta doboz mert
	 * ha igen akkor nem tudunk rálépni.
	 */
	public boolean canIStep() {
		try {
			if (this.getItem().isZpm() == false) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return true;
		}
	}
	/*
	 * Ráléptünk egy scalere ezért a hozzátartozó ajtót aktiváljuk.
	 */
	@Override
	public Field step(MoveInter o) {		//ez is megvan a súllyal
		if (((Player) o).getWgh() >= limit)
			door.setDoor(false);
		return this;
	}
}