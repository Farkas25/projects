package szoflab4;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import javax.swing.JFrame;

public class Menu extends JFrame {
	
	public Drawing drawing;
	private Game game;
	
	private Font font;
	
	public Menu(Game g, Drawing d) {
	
		game = g;
		drawing = d;
		font = new Font("Lofasz", Font.BOLD, 16);
		
		MenuEvents me = new MenuEvents();
		this.addMouseListener(me);
		
		setTitle("Menü");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void paint(Graphics g) {
		g.setColor(java.awt.Color.blue);
		g.fillRect(150, 170, 100, 40);
		g.fillRect(150, 230, 100, 40);
		g.fillRect(150, 290, 100, 40);
		g.setColor(java.awt.Color.white);
		g.setFont(font);
		g.drawString("Új játék", 172, 195);
		g.drawString("Folytatás", 166, 255);
		g.drawString("Kilépés", 172, 315);
	}
	
	//Menü gombjainak eseményei
	public class MenuEvents implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getX() >= 150 && e.getX() <= 250 && e.getY() >= 170 && e.getY() <= 210) {
				drawing.setVisible(true);
				setVisible(false);
			}
			
			if(e.getX() >= 150 && e.getX() <= 250 && e.getY() >= 230 && e.getY() <= 270) {
				try{
					FileInputStream f = new FileInputStream("mentes.txt");
					ObjectInputStream in = new ObjectInputStream(f);
					game.Load(in.readInt());
					in.close();
				}
				catch (IOException ex) { } 

				game.setLevel();
				drawing.setVisible(true);
				setVisible(false);
			}
			
			if(e.getX() >= 150 && e.getX() <= 250 && e.getY() >= 290 && e.getY() <= 330) {
				dispose();
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
