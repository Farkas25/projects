package szoflab4;

public class Bullet {

	private Color color;		// A t�lt�ny aktu�lis sz�ne

	public Bullet(Color c){
		color=c;
	};
	public void changeColor() {		//L�v�s el�tt megv�ltoztatjuk a t�lt�ny sz�n�t
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
