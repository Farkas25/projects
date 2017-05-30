package szoflab4;
public class Hole extends Floor {
	
	@Override
	public Box pickOrDrop(Box b) {
		
		return null;
	}
	
	/*
	 * A replikátor bele esett a szakédba
	 * így az a szakadék darab Floor típusúvá kell hogy változzon.
	 */
	public void RepStep(Ghost r){
		r.setPoz(this);
		repli=r;
		r.die(this);
		//ezt át kell állítani egy sima floorra
	}

	/*
	 * Ha a player belelép a holeba akkor meghall.
	 * Itt hívjuk meg a player die metódusát
	 */
	public Field step(MoveInter o) {
		o.die();
		return null;
	}
}

