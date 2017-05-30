package szoflab4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Drawing extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image dbImage;
	private Graphics dbg;
	
	private Image img_floor;
	private Image img_wall;
	private Image img_sw;
	private Image img_hole;
	private Image img_odoor;
	private Image img_cdoor;
	private Image img_scale;
	private Image img_box;
	private Image img_zpm;
	private Image img_rport;
	private Image img_gport;
	private Image img_yport;
	private Image img_bport;
	private Image img_oneill0;
	private Image img_oneill1;
	private Image img_oneill2;
	private Image img_oneill3;
	private Image img_jaffa0;
	private Image img_jaffa1;
	private Image img_jaffa2;
	private Image img_jaffa3;
	private Image img_repli0;
	private Image img_repli1;
	private Image img_repli2;
	private Image img_repli3;

	
	
	private Player oneill;
	private Player jaffa;
	private Ghost repli;
	Field[][] table=new Field[10][15];
	
	public Map map;
	
	public boolean win = false;
	private Font font;
	
	public Drawing(Map m,Control c){
		addKeyListener(c);
		addMouseListener(c);
		map=m;
		table=m.table;
		oneill=m.getOneill();
		jaffa=m.getJaffa();
		repli=m.getRepli();
		font = new Font("Lofasz", Font.BOLD, 32);
		setTitle("Labirintus Játék");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300,780);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		
		
		img_floor=new ImageIcon("src/pictures/floor.png").getImage();
		img_wall=new ImageIcon("src/pictures/wall.png").getImage();
		img_sw=new ImageIcon("src/pictures/sw.png").getImage();
		img_hole=new ImageIcon("src/pictures/hole.png").getImage();
		img_cdoor=new ImageIcon("src/pictures/closed_door.png").getImage();
		img_odoor=new ImageIcon("src/pictures/open_door.png").getImage();
		img_scale=new ImageIcon("src/pictures/scale.png").getImage();
		img_box=new ImageIcon("src/pictures/box.png").getImage();
		img_zpm=new ImageIcon("src/pictures/zpm.png").getImage();
		img_rport=new ImageIcon("src/pictures/redportal.png").getImage();
		img_gport=new ImageIcon("src/pictures/greenportal.png").getImage();
		img_yport=new ImageIcon("src/pictures/yellowportal.png").getImage();
		img_bport=new ImageIcon("src/pictures/blueportal.png").getImage();
		img_oneill0=new ImageIcon("src/pictures/oneill0.png").getImage();
		img_oneill1=new ImageIcon("src/pictures/oneill1.png").getImage();
		img_oneill2=new ImageIcon("src/pictures/oneill2.png").getImage();
		img_oneill3=new ImageIcon("src/pictures/oneill3.png").getImage();
		img_jaffa0=new ImageIcon("src/pictures/jaffa0.png").getImage();
		img_jaffa1=new ImageIcon("src/pictures/jaffa1.png").getImage();
		img_jaffa2=new ImageIcon("src/pictures/jaffa2.png").getImage();
		img_jaffa3=new ImageIcon("src/pictures/jaffa3.png").getImage();
		img_repli0=new ImageIcon("src/pictures/repli0.png").getImage();
		img_repli1=new ImageIcon("src/pictures/repli1.png").getImage();
		img_repli2=new ImageIcon("src/pictures/repli2.png").getImage();
		img_repli3=new ImageIcon("src/pictures/repli3.png").getImage();
		
		
	}
	
	public void changeMap(Map m) {
		map=m;
		table=m.table;
		oneill=m.getOneill();
		jaffa=m.getJaffa();
		repli=m.getRepli();
	}
	
	public void paint(Graphics g){
		dbImage = createImage(getWidth(), getHeight());
		dbg=dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
		
		if(win) {
			g.setColor(Color.BLUE);
			g.fillRect(500, 340, 300, 100);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("Nyertetek!!!", 560, 400);
		}
		
	}
	
	public void paintComponent(Graphics g){
	int fieldsize=75;
		
		g.setColor(java.awt.Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.YELLOW);
		g.fillRect(1170, 100, 100, 80);
		g.setColor(Color.GREEN);
		g.fillRect(1170, 220, 100, 80);
		g.setColor(java.awt.Color.black);
		g.drawString("O'Neill", 1203, 120);
		g.drawString("ZPM: " + oneill.getListLength(), 1190, 145);
		if(oneill.getBox() != null) {
			g.drawString("doboz", 1190, 165);
		}
		g.drawString("Jaffa", 1207, 240);
		g.drawString("ZPM: " + jaffa.getListLength(), 1190, 265);
		if(jaffa.getBox() != null) {
			g.drawString("doboz", 1190, 285);
		}
		g.setColor(Color.GRAY);
		g.fillRect(1170, 340, 100, 30);
		g.fillRect(1170, 390, 100, 30);
		g.setColor(Color.WHITE);
		g.drawString("Játék mentése", 1180, 360);
		g.drawString("Újrakezdés", 1190, 410);
		
		
		for(int j=0;j<table[0].length;j++){
			for(int i=0;i<table.length;i++){	
				
				if(table[i][j].getClass().getSimpleName().equals("Floor")){
					g.drawImage(img_floor, j*fieldsize+20, i*fieldsize+20,null);
					for(int k = 0; k < ((Floor) table[i][j]).getItemsSize(); k++){
						if(((Floor) table[i][j]).getItemType(k).equals("Z")){
							g.drawImage(img_zpm, j*fieldsize+20, i*fieldsize+20,null);
						}
						if(((Floor) table[i][j]).getItemType(k).equals("B")){
							g.drawImage(img_box, j*fieldsize+20, i*fieldsize+20,null);
						}
					}
					
				}
				if(table[i][j].getClass().getSimpleName().equals("Wall")){
					g.drawImage(img_wall, j*fieldsize+20, i*fieldsize+20,null);
				}
				if(table[i][j].getClass().getSimpleName().equals("Hole")){
					g.drawImage(img_hole, j*fieldsize+20, i*fieldsize+20,null);
				}
				if(table[i][j].getClass().getSimpleName().equals("Scale")){
					g.drawImage(img_scale, j*fieldsize+20, i*fieldsize+20,null);
					for(int k = 0; k < ((Scale) table[i][j]).getItemsSize(); k++){
						if(((Scale) table[i][j]).getItemType(k).equals("B")){
							g.drawImage(img_box, j*fieldsize+20, i*fieldsize+20,null);
						}
					}
				}
				if(table[i][j].getClass().getSimpleName().equals("SpecialWall")){
					g.drawImage(img_sw, j*fieldsize+20, i*fieldsize+20,null);
					if(((SpecialWall) table[i][j]).getport() == true){
						if(table[i][j].equals(((SpecialWall) table[i][j]).getPortal().getYW())) {
							//System.out.print("y");
							g.drawImage(img_yport, j*fieldsize+20, i*fieldsize+20,null);
						}
						if(table[i][j].equals(((SpecialWall) table[i][j]).getPortal().getBW())) {
							//System.out.print("b");
							g.drawImage(img_bport, j*fieldsize+20, i*fieldsize+20,null);
						}
						if(table[i][j].equals(((SpecialWall) table[i][j]).getPortal().getRW())) {
							//System.out.print("r");
							g.drawImage(img_rport, j*fieldsize+20, i*fieldsize+20,null);
						}
						if(table[i][j].equals(((SpecialWall) table[i][j]).getPortal().getGW())) {
							//System.out.print("g");
							g.drawImage(img_gport, j*fieldsize+20, i*fieldsize+20,null);
						}
						//System.out.print("P");
					}
					else{
						//System.out.print("SW");
					}
				}
				if(table[i][j].getClass().getSimpleName().equals("Door")){
					if(((Door) table[i][j]).getClosed() == true){
						g.drawImage(img_cdoor, j*fieldsize+20, i*fieldsize+20,null);
					}
					else{
						g.drawImage(img_odoor, j*fieldsize+20, i*fieldsize+20,null);
					}
					//System.out.print("D");
				}
				
				
				if(oneill!=null && table[i][j].equals(oneill.getPoz())) {
					//System.out.print("O");
					
					if(oneill.getDir()==Direction.left)
						g.drawImage(img_oneill0, j*fieldsize+20, i*fieldsize+20,null);
					if(oneill.getDir()==Direction.up)
						g.drawImage(img_oneill1, j*fieldsize+20, i*fieldsize+20,null);
					if(oneill.getDir()==Direction.right)
						g.drawImage(img_oneill2, j*fieldsize+20, i*fieldsize+20,null);
					if(oneill.getDir()==Direction.down)
						g.drawImage(img_oneill3, j*fieldsize+20, i*fieldsize+20,null);
					
					if(oneill.getBox() != null){
						//System.out.print("b");
					}
						
				}
				if(jaffa!=null && table[i][j].equals(jaffa.getPoz())) {
					//System.out.print("J");
					if(jaffa.getDir()==Direction.left)
						g.drawImage(img_jaffa0, j*fieldsize+20, i*fieldsize+20,null);
					if(jaffa.getDir()==Direction.up)
						g.drawImage(img_jaffa1, j*fieldsize+20, i*fieldsize+20,null);
					if(jaffa.getDir()==Direction.right)
						g.drawImage(img_jaffa2, j*fieldsize+20, i*fieldsize+20,null);
					if(jaffa.getDir()==Direction.down)
						g.drawImage(img_jaffa3, j*fieldsize+20, i*fieldsize+20,null);
					
					if(jaffa.getBox() != null){
						//System.out.print("b");
					}
				}
				if(repli!=null && table[i][j].equals(repli.getPoz())) {
					//System.out.print("R");
					if(repli.getDir()==Direction.left)
						g.drawImage(img_repli0, j*fieldsize+20, i*fieldsize+20,null);
					if(repli.getDir()==Direction.up)
						g.drawImage(img_repli1, j*fieldsize+20, i*fieldsize+20,null);
					if(repli.getDir()==Direction.right)
						g.drawImage(img_repli2, j*fieldsize+20, i*fieldsize+20,null);
					if(repli.getDir()==Direction.down)
						g.drawImage(img_repli3, j*fieldsize+20, i*fieldsize+20,null);
					
				}

			}
		}
	}
}
