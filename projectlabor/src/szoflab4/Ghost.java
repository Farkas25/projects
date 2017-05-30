package szoflab4;

import java.util.Random;

public class Ghost implements MoveInter {
	private Field poz;
	private Direction dir;
	private Map map;
	public boolean randomMove=true;
	Ghost(Field p, Map m){
		poz=p;
		map=m;
		dir=Direction.left;
	}
	public Direction getDir(){
		return dir;
	}
	public void setDir(Direction d){
		dir=d;
	}


	public void die(Field f) {	
		if(f.getClass().getSimpleName().equals("Hole")){
			map.HoleToFloor(poz);
		}
		else{
			map.setRepli(null);
		}
	}
	
	//Random irányt generál
	public void generateDir(){
		if(randomMove){
			Random r= new Random();
			int i= r.nextInt(4);
			switch (i){
			case 0:
				dir=Direction.left;
				break;
			case 1:
				dir=Direction.up;
				break;
			case 2:
				dir=Direction.right;
				break;
			case 3:
				dir=Direction.down;
				break;
			}
		}
	}
	
	//Random irányba elmozdul ha tud.
	public void move() {
	
		
		Field tmp=poz.getNeighbour(dir);		
			if(!((tmp.getClass().getSimpleName()).equals("Wall")||(tmp.getClass().getSimpleName()).equals("SpecialWall"))){
				poz.setRep(null);
				tmp.RepStep(this);		
			}
						
	}

	public void setPoz(Field f){
		this.poz=f;
	}
	public Field getPoz() {
		// TODO Auto-generated method stub
		return poz;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}


}
