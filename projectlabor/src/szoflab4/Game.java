package szoflab4;
import java.io.IOException;
import java.io.Serializable;

public class Game implements Serializable {
	
public Map map;
public Drawing drawing;
public Control control;
public Menu menu;

private int lvl = 0;

	public Game() throws IOException {
		map = new Map(this);	
		control=new Control(this);
		drawing = new Drawing(map, control);
		
		menu = new Menu(this, drawing);
		menu.repaint();
		
		
	}
	
	public int getLvl() {
		return lvl;
	}
	
	public void Load(int i) {
		if(i >= 0) {
			lvl = i;
		}
	}
	
	public Map getMap(){
		return map;
	}

	public void lup(){
		
			lvl++;
			setLevel();
		
	}
	
	//Be�ll�tja a szintnek megfelel� p�ly�t
	public void setLevel() {
		String level[]={"1.txt","2.txt","3.txt"};
		try {
			map = new Map(this);
			map.initMap("src/Mapok/"+level[lvl]);
			drawing.changeMap(map);
			drawing.repaint();
			System.out.println("Map bet�lt�s");
		} catch (IOException e) {
			System.out.println("Nem tal�lom a mapot!");
		}
		
	}
	
	//A p�lya �jrakezd�se
	public void restart(){
		setLevel();
		drawing.changeMap(map);
		drawing.win = false;
		drawing.repaint();
	}
	
	//J�t�k v�ge
	public void endGame(Player o) {
		if(map.getOneill()!=null && map.getOneill().equals(o)){
			map.setOneill(null);
		}
		if(map.getJaffa()!=null && map.getJaffa().equals(o)){
			map.setJaffa(null);
		}
		if(map.getOneill()==null && map.getJaffa()==null){
			lvl=0;
			setLevel();
		}
		
	}
}