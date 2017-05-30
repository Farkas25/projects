/*
 * Nem l�phet�, nem l�het� mez�
 */

package szoflab4;
public class Wall extends Field {

	/*
	 * Megpr�b�lunk falra tenni egy dobozt ami nem lehets�ges.
	 * Ez�rt n�lunk marad a doboz refenci�ja
	 */
	public Box pickOrDrop(Box b) {
		System.out.println("Box Wall.pickOrDrop(Box)");
		return b;
	}

	/*
	 * Nem l�phet�nk fallra ez�rt ez mindig false-szal t�r vissza.
	 */
	public boolean canIStep() {
		System.out.println("Wall.canIStep()");
		return false;
	}

	/*
	 * Mivel nem lehet l�ni fallra, de �t l�ni se leehet rajta ez�rt itt elt�nik a l�v�s.
	 * A shootOver h�v�s sorozat itt befejez�dik
	 */
	public void shootOver(Bullet b, Direction dir) {
		System.out.println("Wall.shootOver(Bullet b, Direction dir)");
	}

	/*
	 * Egy �res Field-del t�r�nk vissza mert nem l�phet�nk falra.
	 */
	public Field step(MoveInter o) {
		System.out.println("Field Wall.step(Oneill o)");
		return o.getPoz();
	}
}

