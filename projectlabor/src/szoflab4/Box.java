/*
 * Mozgatható súly
 */
package szoflab4;
public class Box extends Item {
	private int weight=1;		// Doboz súlya
	
	public boolean isZpm() { //Mindig hamis ha dobozról van szó
		return false;
	}
}
