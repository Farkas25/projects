/*
 * J�rhat� mez�k megval�s�t�sa
 */
package szoflab4;

import java.util.ArrayList;

public class Floor extends Field {
	// A flooron lehet egyszerre t�bb fajta item ezeket t�roljuk ebben a list�ban.
	protected ArrayList<Item> item;
	protected Ghost repli=null;
	public Floor(){
		item= new ArrayList<Item>();
	}
	public void RepStep(Ghost r) {	// Replik�tor be�ll�t�sa
		r.setPoz(this);
		repli=r;
	}
	public void setRep(Ghost r){
		repli=r;
	}

	public String getItemType(int index) {
		if(item!=null) {
			if(item.get(index).isZpm() == true) {
				return "Z";
			}
			else{
				return "B";
			}
		}
		
		return "";
	}
	
	//bele�rtam csak seg�df�ggv�ny teszthez
	public int getItemsSize() {
		if(item!=null)
			return item.size();
		
		return 0;
	}
	public void setItem(int i) {	//R�tesz�nk egy Itemet ami elehet zpm vagy box
		switch (i) {
		case 0:			
			break;
		case 1:
			item.add(new Zpm());
			break;
		case 2:
			item.add(new Box());
			break;
		}
	}

	public Item getItem() {	// Visszaadjuk rajta l�v� Item list utols� elem�t.
		if (item != null && item.size() != 0)
			return item.get(item.size() - 1);
		return null;
	}

	public Item remItem() {	// T�r�lj�k a rajta l�v� Item lista utols� elem�t
		return item.remove(item.size() - 1);
	}

	public void addItem(Item i) {	// Hozz�adunk az Item list�hoz egy elemet.
		//if(item!=null)
			item.add(i);
	}

	/*
	 * Megn�zz�k hogy el�tt�nk van-e zpm vagy doboz.
	 * Ha zpm-van el�tt�nk �s van n�lunk doboz akkor nem tudjuk lerakni
	 * ha doboz van el�tt�nk vagy �res akkor letessz�k a dobozt persze
	 * ha van n�lunk
	 */
	public Box pickOrDrop(Box b) {
		try { // van ell�tte vmi(zpm vagy doboz)
			boolean tmp = this.getItem().isZpm();

			if (b == null && tmp == false) { // nincs n�lam doboz, �s egy doboz
												// van el�ttem
				return (Box) remItem();
			}
			if (b != null && tmp == false) { // van dobozom, �s doboz van
												// el�ttem
				addItem(b); // r�teszem
				return null;
			}
			if (b != null && tmp == true)
				return b;

		} catch (NullPointerException e) { // nincs semmi előttem
			if (b != null) // letesz
				addItem(b);
			return null;
		}
		return null;
	}

	/*
	 * Tudunk-e l�pni.
	 * Floor eset�ben csak akkor nem tudunk l�pni ha box van rajta,
	 * �gy itt csak egyszer�en megn�zz�k hogy van-e rajta box, ha nincs akkor true,
	 * tudunk l�pni ha van rajta box akkor false nem tudunk l�pni
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
	 * Megn�zz�k hogy �tl�het�-e a mez�,
	 * nincs rajta replik�tor,
	 * nem z�rt ajt�,
	 * nem fal
	 * ha �t tudnuk felette l�ni akkor meg kell h�vni a dir ir�ny�ba l�v� szomsz�d shootOver-j�t
	 */
	public void shootOver(Bullet b, Direction dir) {
		
		if (repli == null)
			this.getNeighbour(dir).shootOver(b, dir);
		else {
			repli.die(this);
		}
	}

	/*
	 * Visszadjuk moveInter ir�ny�ba l�v� mez�t,
	 * de el�tte megn�zz�k hogy van-e rajta zpm,
	 * ha van akkor az a zpm hozz�ad�dik a playerhez �s kit�rl�dik ennek a mez�nek a Item list�j�b�l
	 */
	public Field step(MoveInter o) {
		
		if (item.size() == 1 && item.get(0).isZpm() == true)
			((Player) o).addZPM((Zpm) this.item.remove(0));
		
		
		return this;
	}

	public int getItemListLenght() {
		return item.size();
	}
}
