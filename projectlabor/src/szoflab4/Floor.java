/*
 * Járható mezõk megvalósítása
 */
package szoflab4;

import java.util.ArrayList;

public class Floor extends Field {
	// A flooron lehet egyszerre több fajta item ezeket tároljuk ebben a listában.
	protected ArrayList<Item> item;
	protected Ghost repli=null;
	public Floor(){
		item= new ArrayList<Item>();
	}
	public void RepStep(Ghost r) {	// Replikátor beállítása
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
	
	//beleírtam csak segédfüggvény teszthez
	public int getItemsSize() {
		if(item!=null)
			return item.size();
		
		return 0;
	}
	public void setItem(int i) {	//Ráteszünk egy Itemet ami elehet zpm vagy box
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

	public Item getItem() {	// Visszaadjuk rajta lévõ Item list utolsó elemét.
		if (item != null && item.size() != 0)
			return item.get(item.size() - 1);
		return null;
	}

	public Item remItem() {	// Töröljük a rajta lévõ Item lista utolsó elemét
		return item.remove(item.size() - 1);
	}

	public void addItem(Item i) {	// Hozzáadunk az Item listához egy elemet.
		//if(item!=null)
			item.add(i);
	}

	/*
	 * Megnézzük hogy elõttünk van-e zpm vagy doboz.
	 * Ha zpm-van elõttünk és van nálunk doboz akkor nem tudjuk lerakni
	 * ha doboz van elõttünk vagy üres akkor letesszük a dobozt persze
	 * ha van nálunk
	 */
	public Box pickOrDrop(Box b) {
		try { // van ellõtte vmi(zpm vagy doboz)
			boolean tmp = this.getItem().isZpm();

			if (b == null && tmp == false) { // nincs nálam doboz, és egy doboz
												// van elõttem
				return (Box) remItem();
			}
			if (b != null && tmp == false) { // van dobozom, és doboz van
												// elõttem
				addItem(b); // ráteszem
				return null;
			}
			if (b != null && tmp == true)
				return b;

		} catch (NullPointerException e) { // nincs semmi elÅ‘ttem
			if (b != null) // letesz
				addItem(b);
			return null;
		}
		return null;
	}

	/*
	 * Tudunk-e lépni.
	 * Floor esetében csak akkor nem tudunk lépni ha box van rajta,
	 * így itt csak egyszerûen megnézzük hogy van-e rajta box, ha nincs akkor true,
	 * tudunk lépni ha van rajta box akkor false nem tudunk lépni
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
	 * Megnézzük hogy átlõhetõ-e a mezõ,
	 * nincs rajta replikátor,
	 * nem zárt ajtó,
	 * nem fal
	 * ha át tudnuk felette lõni akkor meg kell hívni a dir irányába lévõ szomszéd shootOver-jét
	 */
	public void shootOver(Bullet b, Direction dir) {
		
		if (repli == null)
			this.getNeighbour(dir).shootOver(b, dir);
		else {
			repli.die(this);
		}
	}

	/*
	 * Visszadjuk moveInter irányába lévõ mezõt,
	 * de elõtte megnézzük hogy van-e rajta zpm,
	 * ha van akkor az a zpm hozzáadódik a playerhez és kitörlõdik ennek a mezõnek a Item listájából
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
