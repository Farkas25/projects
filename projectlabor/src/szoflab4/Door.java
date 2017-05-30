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
	 * Ha az isClosed false sz�val nyitva van az ajt� akkor true-val t�rvissza,
	 * ha az isClosed true akkor a canIStep false �rt�ket add vissza.
	 * Mivel ajt�ra nem tehet�nk dobozt, nem lehet rajta port�l,
	 * �gy csak azt kell megn�zzni hogy z�rt vagy nyitott �llapotban van-e.
	 */
	public boolean canIStep() {

		if(this.Closed){
			return false;
		}else{
			return true;
		}
	}

	/*
	 * Nyitott ajt�n �t tudunk l�ni.
	 * Ez�rt itt megn�zz�k hogy a Door isClosed-je milyen �llapotban van,
	 * ha false - nyitva van az ajt� - akkor �t tudunk l�ni rajta,
	 * �gy megh�vjuk a szomsz�d mez� shootOverj�t,
	 * ha isClosed true akkor megakad a goly� az ajt�ban, mondhatni elt�nik
	 */
	public void shootOver(Bullet b, Direction dir) {

		if(!this.Closed)
			this.getNeighbour(dir).shootOver(b, dir);
	}

	/*
	 * Visszat�r�nk a Door helyzet�vel.
	 * Mivel a canIStep ut�n h�v�dik meg a step itt nem kell m�r megvizsg�lni hogy l�phet�nk-e
	 * Csak �tadni az �j Field-et
	 */
	public Field step(MoveInter o) {
		return this;
	}
}