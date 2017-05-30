package szoflab4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class Map {

	
	private int neededZPM =4;

	public Field[][] table = new Field[10][15]; //ez egy teszt table
	private Player oneill;
	private Player jaffa;
	private Ghost repli;
	private Game game;
	Portal p = new Portal();
	

	
	public Map(Game game) throws IOException {
		oneill = new Player(new Floor(),this,game, Color.blue);
		jaffa = new Player(new Floor(),this,game, Color.green);
		repli = new Ghost(new Floor(),this);
		
		this.game = game;
		initMap("src/Mapok/1.txt");
		
		
	}

	//Vizsgálja a pályán és a játékosoknál lévõ ZPM-ek számát, akkor hívódik ha felvesznek egy ZPM-et
	public void zpmPicked(Player p) {
		if (p.equals(oneill)) {
			if (oneill.getListLength() % 2 == 0){
				System.out.println(oneill.getListLength());
				putZpm();			
			}
		}
		if (jaffa.getListLength() == neededZPM||oneill.getListLength()==neededZPM) {
			if(game.getLvl() == 2) {
				game.drawing.win = true;
				game.drawing.repaint();
			}
			game.lup();
		}
	}
	public Player getOneill(){
		return oneill;
	}
	public Player getJaffa(){
		return jaffa;
	}
	public void setOneill(Player o){
		oneill=o;
	}
	public void setJaffa(Player o){
		jaffa=o;
	}
	public Ghost getRepli(){
		return repli;
	}
	public void setRepli(Ghost o){
		repli=o;
	}

	//A map fájlból történõ beolvasása
	public void initMap(String mapname) throws IOException {
		

		BufferedReader br = new BufferedReader(new FileReader(mapname));
		String line = br.readLine();
		Field tmp = null;
		
		
		while (line != null) {
			
			String[] bemenet = line.split("\t");
			if (bemenet[2].equals("F"))
				tmp = new Floor();
			else if (bemenet[2].equals("D"))
				tmp = new Door();
			else if (bemenet[2].equals("H"))
				tmp = new Hole();
			else if (bemenet[2] .equals( "W"))
				tmp = new Wall();
			else if (bemenet[2].equals("S")){
				tmp = new Scale();
				((Scale)tmp).setDoor((Door) table[Integer.parseInt(bemenet[5])][Integer.parseInt(bemenet[6])]);
			}	
			else if (bemenet[2].equals("SW")){
				tmp = new SpecialWall();
				((SpecialWall) tmp).setPortal(p);
			}

			else if (bemenet[2].length() != 1 && (bemenet[2].charAt(1))=='P') {
				tmp = new SpecialWall();
				((SpecialWall) tmp).setPortal(p);
				
			}		
			
			if (bemenet[3] .equals("O"))
				oneill.setPoz(tmp);
			else if (bemenet[3] .equals("bO")) {
				oneill.setPoz(tmp);
				oneill.setBox(new Box());
			} else if (bemenet[3].equals("J"))
				jaffa.setPoz(tmp);
			else if (bemenet[3] .equals("bJ")) {
				jaffa.setPoz(tmp);
				jaffa.setBox(new Box());
			} else if (bemenet[3] .equals("R"))
				repli.setPoz(tmp);
			if(!(tmp.getClass().getSimpleName().equals("Wall")||tmp.getClass().getSimpleName().equals("SpecialWall"))){
				if (bemenet[4].equals("B"))
					((Floor) tmp).setItem(2);
				if (bemenet[4].equals( "Z"))
					((Floor) tmp).setItem(1);
				if (bemenet[4].equals( "X"))
					((Floor) tmp).setItem(0);
			}
			tmp.addAllNeighbours();
			
			table[Integer.parseInt(bemenet[0])][Integer.parseInt(bemenet[1])] = tmp;	
			line = br.readLine();
		}
		br.close();

		initNeightbours();
	}

	// A pálya konzolon történõ megjelenítése
	public void showMap(){
		
		for(int i=0;i<table.length;i++){
			for(int j=0;j<table.length;j++){
				System.out.print("\t|   ");
				
				if(table[i][j].getClass().getSimpleName().equals("Floor")){
					System.out.print("F");
					for(int k = 0; k < ((Floor) table[i][j]).getItemsSize(); k++){
						System.out.print(((Floor) table[i][j]).getItemType(k));
					}
				}
				if(table[i][j].getClass().getSimpleName().equals("Wall")){
					System.out.print("W");
				}
				if(table[i][j].getClass().getSimpleName().equals("Hole")){
					System.out.print("H");
				}
				if(table[i][j].getClass().getSimpleName().equals("Scale")){
					System.out.print("S");
					for(int k = 0; k < ((Scale) table[i][j]).getItemsSize(); k++){
						System.out.print(((Scale) table[i][j]).getItemType(k));
					}
				}
				if(table[i][j].getClass().getSimpleName().equals("SpecialWall")){
					if(((SpecialWall) table[i][j]).getport() == true){
						if(table[i][j].equals(((SpecialWall) table[i][j]).getPortal().getYW())) {
							System.out.print("y");
						}
						if(table[i][j].equals(((SpecialWall) table[i][j]).getPortal().getBW())) {
							System.out.print("b");
						}
						if(table[i][j].equals(((SpecialWall) table[i][j]).getPortal().getRW())) {
							System.out.print("r");
						}
						if(table[i][j].equals(((SpecialWall) table[i][j]).getPortal().getGW())) {
							System.out.print("g");
						}
						System.out.print("P");
					}
					else{
						System.out.print("SW");
					}
				}
				if(table[i][j].getClass().getSimpleName().equals("Door")){
					if(((Door) table[i][j]).getClosed() == true){
						System.out.print("c");
					}
					else{
						System.out.print("o");
					}
					System.out.print("D");
				}
				
				if(oneill!=null && table[i][j].equals(oneill.getPoz())) {
					System.out.print("O");
					if(oneill.getBox() != null){
						System.out.print("b");
					}
						
				}
				if(jaffa!=null && table[i][j].equals(jaffa.getPoz())) {
					System.out.print("J");
					if(jaffa.getBox() != null){
						System.out.print("b");
					}
				}
				if(repli!=null && table[i][j].equals(repli.getPoz())) {
					System.out.print("R");
				}
				
	
				
				if(j==table.length-1){
					System.out.print("\t|");
				}
			}
			System.out.println();
			System.out.print("\t");
			for(int h=0;h<table.length;h++)
				System.out.print("--------");
			System.out.println();
		}
		
	}
	
	//A mezõk szomszédjainak beállítására szolgáló függvény
	public void initNeightbours(){
		Field[][] temptable=new Field[table.length+2][table[0].length+2];
		
		for(int i=0;i<temptable.length;i++){
			for(int j=0;j<temptable[0].length;j++){
				if(i==0 || i==temptable.length-1 || j==0 || j==temptable.length-1){
					temptable[i][j]=new Wall();
				}
			}
		}
		for(int i=0;i<table.length;i++){
			for(int j=0;j<table[0].length;j++){
				temptable[i+1][j+1]=table[i][j];
			}
		}
		
		for(int i=0;i<table.length;i++){
			for(int j=0;j<table[0].length;j++){
				table[i][j].setNeighbour(Direction.left, temptable[i+1][j+1-1]);
				table[i][j].setNeighbour(Direction.right, temptable[i+1][j+1+1]);
				table[i][j].setNeighbour(Direction.up, temptable[i+1-1][j+1]);
				table[i][j].setNeighbour(Direction.down, temptable[i+1+1][j+1]);
			}
		}
		
		
	}
	
	//Ha O'Neill-nál két ZPM van ez a függvény létrehoz a pályán egy újat random helyen
	public void putZpm() {
		boolean lerakta = false;
		Random r = new Random();
		while (!lerakta) {
			int x = r.nextInt(table.length);
			int y = r.nextInt(table[0].length);
			if (table[x][y].getClass().getSimpleName().equals( "Floor")
					&& ((Floor) table[x][y]).getItemListLenght() == 0) {
				lerakta = true;
				((Floor) table[x][y]).setItem(1);
			}
		}
	}
	
	//Ha a replikátor lyukba lép akkor ez a függvény átalakítja a lyukat
	public void HoleToFloor(Field f) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				if (table[i][j].equals(f)) {
					table[i][j] = new Floor();
					table[i][j].addAllNeighbours();
					repli=null;
				}
			}
		}
		
		this.initNeightbours();
	}
}
