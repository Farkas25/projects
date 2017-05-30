package szoflab4;
public class Scale extends Floor {
	private Door door = new Door();
	private int limit = 2;
	/*
	 * Megh�vjuk a hozz�nk tartoz� door set-j�t hogy �ll�tani tudjuk az ajt�t
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
	public Box pickOrDrop(Box b) {	//m�r a list�s anyj�nyitogat�ssal m�k�dik, ez k�sz.
		try { 						// van el�ttem valami(csak doboz lehet){
			this.getItem().isZpm();
			if (b == null) { 		// nincs dobozom,felveszem
				Box tmp = (Box) remItem();
				this.setDoor(); 
				return tmp; 		// n�lam van a doboz
			}
			if (b != null) { 		// van dobozom
				addItem(b); 		// r�teszek +1 dobozt
				this.setDoor(); 				
			}
		} catch (NullPointerException e) { // semmi sincs el�ttem
			if (b != null) { 		// van dobozom
				addItem(b);			 // leteszem
			}
			return null;
		}
		return null;
	}
	@Override
	/*
	 * Megn�zz�k hogy van-e m�r rajta doboz mert
	 * ha igen akkor nem tudunk r�l�pni.
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
	 * R�l�pt�nk egy scalere ez�rt a hozz�tartoz� ajt�t aktiv�ljuk.
	 */
	@Override
	public Field step(MoveInter o) {		//ez is megvan a s�llyal
		if (((Player) o).getWgh() >= limit)
			door.setDoor(false);
		return this;
	}
}