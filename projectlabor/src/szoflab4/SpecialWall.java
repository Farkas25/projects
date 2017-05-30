/*
 * L�het� falak
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
	 * Megpr�b�lunk felvenni vagy letenni egy dobozt a port�lon kereszt�l.
	 * Ehhez persze akt�v port�llal kell  rendelkez�nk
	 */
	public Box pickOrDrop(Box b) {
		if(isActive() == true) {
			return portal.whereToStep(this).pickOrDrop(b);
		}
		return b;
	}

	/*
	 * Megn�zz�k hogy van-e akt�v port�lunk ezen a speci�lisfalon,
	 * majd megn�zz�k hogy a m�sik port�l melyik fieldre vezet 
	 * �s ezek ut�n m�r csak att�l a field-t�l meg kell k�rdezn�nk hogy r�l�phet�nk-e.
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
	 * A shootOver itt v�lik igaz�n izgalmass�, hiszen eddig csak az volt a k�rd�s hogy �t tudunk -e l�ni a dolgokon vagy megsemmis�lt a l�v�s.
	 * De most v�gre tal�ltunk egy speci�lis falat.
	 * Els�nek meg kell n�zn�nk hogy van-e ezen a speci�lis falon port�l
	 * ha nincs akkor be kell �ll�tanunk a port�lba ezt a speci�lisfalat a megfelel� sz�nhez �s a hozz�kapcsol�d� field-et.
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
	 * A port�l whereToStepje seg�ts�g�vel megn�zz�k hogy hova kell l�pn�nk.
	 */
	public Field step(MoveInter o) {
		if(o.getPoz().equals(portal.getBF()) || o.getPoz().equals(portal.getYF()) || o.getPoz().equals(portal.getGF()) || o.getPoz().equals(portal.getRF()))
			return portal.whereToStep(this);
		return o.getPoz();
	}
}
