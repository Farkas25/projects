package szoflab4;
public class Hole extends Floor {
	
	@Override
	public Box pickOrDrop(Box b) {
		
		return null;
	}
	
	/*
	 * A replik�tor bele esett a szak�dba
	 * �gy az a szakad�k darab Floor t�pus�v� kell hogy v�ltozzon.
	 */
	public void RepStep(Ghost r){
		r.setPoz(this);
		repli=r;
		r.die(this);
		//ezt �t kell �ll�tani egy sima floorra
	}

	/*
	 * Ha a player belel�p a holeba akkor meghall.
	 * Itt h�vjuk meg a player die met�dus�t
	 */
	public Field step(MoveInter o) {
		o.die();
		return null;
	}
}

