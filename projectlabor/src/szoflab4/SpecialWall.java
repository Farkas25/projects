/*
 * Lõhetõ falak
 */

package szoflab4;
public class SpecialWall extends Wall {

	private boolean active = false;
	private Portal portal;
	private boolean port = false;
	
	

	public boolean getport() {
		return port;
	}

	public Portal getPortal() {
		return portal;
	}

	public void setActive(boolean b){
		active=b;
	}
	public void setPortal(Portal p){
		portal=p;
	}
	public void setPort(boolean b){
		port=b;
	}
	public boolean isActive() {
		return this.active;
	}
	
	/*
	 * Megpróbálunk felvenni vagy letenni egy dobozt a portálon keresztül.
	 * Ehhez persze aktív portállal kell  rendelkezünk
	 */
	public Box pickOrDrop(Box b) {
		if(isActive() == true) {
			return portal.whereToStep(this).pickOrDrop(b);
		}
		return b;
	}

	/*
	 * Megnézzük hogy van-e aktív portálunk ezen a speciálisfalon,
	 * majd megnézzük hogy a másik portál melyik fieldre vezet 
	 * és ezek után már csak attól a field-tõl meg kell kérdeznünk hogy ráléphetünk-e.
	 */
	public boolean canIStep() {
		if(isActive() == true){
			if(portal.whereToStep(this).canIStep()) {
			return true;
			}
		}
		return false;
	}

	/*
	 * A shootOver itt válik igazán izgalmassá, hiszen eddig csak az volt a kérdés hogy át tudunk -e lõni a dolgokon vagy megsemmisült a lövés.
	 * De most végre találtunk egy speciális falat.
	 * Elsõnek meg kell néznünk hogy van-e ezen a speciális falon portál
	 * ha nincs akkor be kell állítanunk a portálba ezt a speciálisfalat a megfelelõ színhez és a hozzákapcsolódó field-et.
	 */
	public void shootOver(Bullet b, Direction dir) {

		if(port==false){
			port=true;
			if(dir==Direction.left)
				portal.setPortal(b,this,this.getNeighbour(Direction.right));
			if(dir==Direction.right)
				portal.setPortal(b,this,this.getNeighbour(Direction.left));
			if(dir==Direction.up)
				portal.setPortal(b,this,this.getNeighbour(Direction.down));
			if(dir==Direction.down)
				portal.setPortal(b,this,this.getNeighbour(Direction.up));
		}
	}

	/*
	 * A portál whereToStepje segítségével megnézzük hogy hova kell lépnünk.
	 */
	public Field step(MoveInter o) {
		if(o.getPoz().equals(portal.getBF()) || o.getPoz().equals(portal.getYF()) || o.getPoz().equals(portal.getGF()) || o.getPoz().equals(portal.getRF()))
			return portal.whereToStep(this);
		return o.getPoz();
	}
}
