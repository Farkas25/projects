package szoflab4;

public class Bullet {

	private Color color;		// A töltény aktuális színe

	public Bullet(Color c){
		color=c;
	};
	public void changeColor() {		//Lövés elõtt megváltoztatjuk a töltény színét
		switch(color){
		case blue:
			color=Color.yellow;
			break;
		case yellow:
			color=Color.blue;
			break;
		case red:
			color=Color.green;
			break;
		case green:
			color=Color.red;
			break;
		}
	}

	public Color getColor() {
		return color;
	}

}
