/*
 * Mozgathat� s�ly
 */
package szoflab4;
public class Box extends Item {
	private int weight=1;		// Doboz s�lya
	
	public boolean isZpm() { //Mindig hamis ha dobozr�l van sz�
		return false;
	}
}
