package szoflab4;

public class Portal {

	//oneill
	private Field yellowField;
	private Field blueField;
	private SpecialWall yellowWall;
	private SpecialWall blueWall;
	//jaffa
	private Field redField;
	private Field greenField;
	private SpecialWall redWall;
	private SpecialWall greenWall;

	public SpecialWall getYW() {
		return this.yellowWall;
	}

	public SpecialWall getBW() {
		return this.blueWall;
	}
	public SpecialWall getRW() {
		return this.redWall;
	}

	public SpecialWall getGW() {
		return this.greenWall;
	}
	
	public Field getYF() {
		return this.yellowField;
	}

	public Field getBF() {
		return this.blueField;
	}
	public Field getRF() {
		return this.redField;
	}

	public Field getGF() {
		return this.greenField;
	}

	/*
	 * Itt �ll�tjuk be a port�lt.
	 * Megn�zz�k hogy milyen sz�n� Bullet �rkezet 
	 * �s hogy van-e m�r k�t fajta sz�n� port�l a falakon ha igen act�vra �ll�tjuk a port�lt.
	 */
	public void setPortal(Bullet b, SpecialWall sw, Field f) {
		
		if (b.getColor() == Color.yellow) {
			if (yellowWall != null) {
				this.yellowWall.setActive(false);
				this.yellowWall.setPort(false);
			}
			this.yellowField = f;
			this.yellowWall = sw;
		} else if (b.getColor() == Color.blue) {
			if (blueWall != null) {
				this.blueWall.setActive(false);
				this.blueWall.setPort(false);
			}
			this.blueField = f;
			this.blueWall = sw;

		} else if (b.getColor() == Color.green) {
			if (greenWall != null) {
				this.greenWall.setActive(false);
				this.greenWall.setPort(false);
			}
			this.greenField = f;
			this.greenWall = sw;
		} else if (b.getColor() == Color.red) {
			if (redWall != null) {
				this.redWall.setActive(false);
				this.redWall.setPort(false);
			}
			this.redField = f;
			this.redWall = sw;
		}
		if (yellowField != null && blueField != null) {
			yellowWall.setActive(true);
			blueWall.setActive(true);
		}
		if (redField != null && greenField != null) {
			redWall.setActive(true);
			greenWall.setActive(true);
		}

	}

	public void setAllOneill(SpecialWall yw, SpecialWall bw, Field yf, Field bf) { 	
		this.yellowField = yf;
		this.blueField = bf;
		this.yellowWall = yw;
		this.blueWall = bw;
	}
	public void setAllJaffa(SpecialWall rw, SpecialWall gw, Field rf, Field gf) {
		this.redField = rf;
		this.greenField = gf;
		this.redWall = rw;
		this.greenWall = gw;
	}

	/*
	 * Megn�zz�k hogy melyik port�lon akarunk bel�pni �s
	 * visszadjuk hogy a m�sik port�ln�l hova fogunk kil�pni
	 */
	public Field whereToStep(SpecialWall sw) {
		
		if (sw.equals(yellowWall)) {
			return blueField;
		}

		if (sw.equals(blueWall) ) {
			return yellowField;
		}
		if (sw.equals(redWall)) {
			return greenField;
		}

		if (sw.equals(greenWall) ) {
			return redField;
		}

		return null;

	}

}
