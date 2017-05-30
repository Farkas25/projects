package szoflab4;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;


public class Control extends KeyAdapter implements MouseListener{
	
public Game game;

	public Control(Game g){
		game=g;
	}

	
		public void keyPressed(KeyEvent e){
			int keyCode=e.getKeyCode();
			
			//oneill 
			if (keyCode ==e.VK_LEFT){
				if(game.map.getOneill().getDir()==Direction.left)
					game.map.getOneill().move();
				else
					game.map.getOneill().setDir(Direction.left);
				
			}
			if (keyCode ==e.VK_RIGHT){
				if(game.map.getOneill().getDir()==Direction.right)
					game.map.getOneill().move();
				else
					game.map.getOneill().setDir(Direction.right);
			}
			if (keyCode ==e.VK_UP){
				if(game.map.getOneill().getDir()==Direction.up)
					game.map.getOneill().move();
				else
					game.map.getOneill().setDir(Direction.up);
			}
			if (keyCode ==e.VK_DOWN){
				if(game.map.getOneill().getDir()==Direction.down)
					game.map.getOneill().move();
				else
					game.map.getOneill().setDir(Direction.down);		
			}
			
			if (keyCode ==e.VK_N){
					game.map.getOneill().shoot();	
			}
			if (keyCode ==e.VK_M){
					game.map.getOneill().pickOrDrop();		
			}
			
			//jaffa
			if (keyCode ==e.VK_A){
				if(game.map.getJaffa().getDir()==Direction.left)
					game.map.getJaffa().move();
				else
					game.map.getJaffa().setDir(Direction.left);
				
			}
			if (keyCode ==e.VK_D){
				if(game.map.getJaffa().getDir()==Direction.right)
					game.map.getJaffa().move();
				else
					game.map.getJaffa().setDir(Direction.right);
			}
			if (keyCode ==e.VK_W){
				if(game.map.getJaffa().getDir()==Direction.up)
					game.map.getJaffa().move();
				else
					game.map.getJaffa().setDir(Direction.up);
			}
			if (keyCode ==e.VK_S){
				if(game.map.getJaffa().getDir()==Direction.down)
					game.map.getJaffa().move();
				else
					game.map.getJaffa().setDir(Direction.down);		
			}
			
			if (keyCode ==e.VK_Q){
					game.map.getJaffa().shoot();	
			}
			if (keyCode ==e.VK_E){
					game.map.getJaffa().pickOrDrop();		
			}
			
			game.map.showMap();
			game.menu.drawing.repaint();
		}
		
		//replikátor
		public void keyReleased(KeyEvent e){
			if(game.map.getRepli() != null) {
				Direction tempdir;
				tempdir=game.map.getRepli().getDir();
			
				game.map.getRepli().generateDir();
			
				if(game.map.getRepli().getDir()==tempdir)
					game.map.getRepli().move();
			}
		}

		//A játékba található gombok eseményei
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getX() >= 1170 && e.getX() <= 1270 && e.getY() >= 340 && e.getY() <= 370) {
				try{
					FileOutputStream f = new FileOutputStream("mentes.txt");
					ObjectOutputStream out = new ObjectOutputStream(f);
					out.writeInt(game.getLvl());
					out.close();
				}
				catch (IOException ex){	}
			}
			
			if(e.getX() >= 1170 && e.getX() <= 1270 && e.getY() >= 390 && e.getY() <= 420) {
				game.restart();
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
