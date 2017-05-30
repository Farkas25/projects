/*
 * Nem léphetõ, nem lõhetõ mezõ
 */

package szoflab4;
public class Wall extends Field {

	/*
	 * Megpróbálunk falra tenni egy dobozt ami nem lehetséges.
	 * Ezért nálunk marad a doboz refenciája
	 */
	public Box pickOrDrop(Box b) {
		System.out.println("Box Wall.pickOrDrop(Box)");
		return b;
	}

	/*
	 * Nem léphetünk fallra ezért ez mindig false-szal tér vissza.
	 */
	public boolean canIStep() {
		System.out.println("Wall.canIStep()");
		return false;
	}

	/*
	 * Mivel nem lehet lõni fallra, de át lõni se leehet rajta ezért itt eltûnik a lövés.
	 * A shootOver hívás sorozat itt befejezõdik
	 */
	public void shootOver(Bullet b, Direction dir) {
		System.out.println("Wall.shootOver(Bullet b, Direction dir)");
	}

	/*
	 * Egy üres Field-del térünk vissza mert nem léphetünk falra.
	 */
	public Field step(MoveInter o) {
		System.out.println("Field Wall.step(Oneill o)");
		return o.getPoz();
	}
}

