import java.util.LinkedList;

public class background implements settable{

	final int lowBound = 143; //lower lower bounds are more intense- min/max 0/255
	final int highBound = 232; //lower higher bounds are darker- min/max 0/255
	final int difficulty = 30; //(higher easier)
	
	private int r,g,b;

	public background() {
		set();
	}

	public void showBackground(player p) {
		StdDraw.setPenColor(r, g, b);
		StdDraw.filledSquare(0.5, 0.5, 0.5);
		StdDraw.setPenColor();
		StdDraw.text(0.8, 0.9, "Score: "+  p.getWins());
	}
	
	/**
	 * assigns the background random RGB values based on RBG bounds for intensity and darkness
	 * to achieve a smooth color gradient, two values most hold at the upper and lower bounds
	 * while the third varies between them. Hence the three 'variance' cases for the RGB values, each
	 * case being chosen randomly to assure equal likelihood of any starting color within the bounds
	 */
	@Override
	public void set() {		
		
		int variance = (int) (3 * Math.random());
		
		if (variance == 0) { //case 1
			this.r = lowBound + (highBound - lowBound) * (int) (2 *  Math.random());
			if (this.r == lowBound) {
				this.g = highBound;
			}
			else {
				this.g = lowBound;
			}
			this.b = lowBound + (highBound - lowBound) * (int) (2 *  Math.random());
		}
		if (variance == 1) { //case 1
			this.r = lowBound + (highBound - lowBound) * (int) (2 *  Math.random());
			if (this.r == lowBound) {
				this.b = highBound;
			}
			else {
				this.b = lowBound;
			}
			this.g = lowBound + (highBound - lowBound) * (int) (2 *  Math.random());
		}
		if (variance == 2) { //case 1
			this.b = lowBound + (highBound - lowBound) * (int) (2 *  Math.random());
			if (this.b == lowBound) {
				this.g = highBound;
			}
			else { //case 1
				this.g = lowBound;
			}
			this.r = lowBound + (int) ((highBound - lowBound) * Math.random());
		}
		
	}

	/**
	 * determines whether the player has matched the background colors based on the difficulty
	 * this indicates a winning position
	 * @param player p whose RBG values are compared with the background
	 * @return a boolean for whether that players color is within a certain numerical alue of the background
	 */
	public boolean isSame(player p) {
		if ((Math.abs(this.r - p.getR()) <= difficulty) &&  (Math.abs(this.g - p.getG()) <= difficulty)  
				&& (Math.abs(this.b - p.getB()) <= difficulty)) return true;
		else return false;
	}


}
