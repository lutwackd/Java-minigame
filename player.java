import java.awt.Color;

public class player implements settable {

	final int lowerBound = 143; //lower lower bounds are more intense- min/max 0/255
	final int upperBound = 232;//lower higher bounds are darker- min/max 0/255
	final int winScore = 15; 
	final int loseScore = -10;
	
	private int r,g,b, score;
	private boolean isMoving, isSafe;
	
	public player() {
		this.isMoving = false;
		this.score = 0;
		this.isSafe = true;
		set();
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public boolean getMoving() {
		return this.isMoving;
	}
	
	public int getWins() {
		return score;
	}

	/**
	 * Displays the player as a circle based on RGB values
	 */
	public void showPlayer() {
		StdDraw.setPenColor(r, g, b );
		StdDraw.filledCircle(0.5, 0.5, 0.2);
		StdDraw.setPenColor();
	}
	
	/**
	 * moves the colors in a along a smooth rainbow gradient
	 * upper and lower bounds dictate intensity and darkness as indicated
	 * at variable declarations
	 */
	public void moveColors() {
		
		if (r == upperBound && b == lowerBound && g < upperBound) {
			g++;
			return;
		}
		if (b == lowerBound && g == upperBound && r > lowerBound) {
			r--;
			return;
		}
		if (r == lowerBound && g == upperBound & b < upperBound) {
			b++;
			return;
		}
		if (r == lowerBound && b == upperBound & g > lowerBound) {
			g--;
			return;
		}
		if (g == lowerBound && b == upperBound & r < upperBound) {
			r++;
			return;
		}
		if (r == upperBound && g == lowerBound & b > lowerBound) {
			b--;
			return;	
		}
		
	}
	
	/**
	 * sets the value of moving boolean if spacebar is pressed
	 * set the safety boolean appropriately - if user was moving
	 * and is now not, they are unsafe and at risk for deduction
	 * 
	 */
	public void isMoving() {
		if (StdDraw.isKeyPressed(32)) {
			isMoving = true;
		}
		else {
			if (isMoving){
				isMoving = false;
				isSafe = false;
			}
		}
	}
	
	/**
	 * increments score by 5 on win and
	 * displays a message about the +5 points
	 * @param background b which will have its color's reset
	 */
	public void winner(background b) {
		this.score += 5;
		StdDraw.pause(150);
		StdDraw.disableDoubleBuffering();
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.setPenRadius(0.012);
		StdDraw.circle(0.5, 0.5, 0.2);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.5, 0.5, "+5");
		StdDraw.pause(2000);
		b.set();
		this.set();
		isSafe = true;

	}

	
	/**
	 * decrements score on loss and 
	 * displays message
	 */
	public void loser() {
		if (!isSafe) {
			this.score--;
			StdDraw.pause(150);
			StdDraw.disableDoubleBuffering();
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(0.012);
			StdDraw.circle(0.5, 0.5, 0.2);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.5, 0.5, "-1");
			StdDraw.pause(2000);
			isSafe = true;
		}
	}
	
	/**
	 * assigns the player random RGB values based on RBG bounds for intensity and darkness
	 * to achieve a smooth color gradient, two values most hold at the upper and lower bounds
	 * while the third varies between them. Hence the three 'variance' cases for the RGB values, each
	 * case being chosen randomly to assure equal likelihood of any starting color within the bounds
	 */
	@Override
	public void set() {
		
		int variance = (int) (3 * Math.random());
		
		if (variance == 0) { //case 1
			this.r = lowerBound + (upperBound - lowerBound) * (int) (2 *  Math.random());
			if (this.r == lowerBound) {
				this.g = upperBound;
			}
			else {
				this.g = lowerBound;
			}
			this.b = lowerBound + (upperBound - lowerBound) * (int) (2 *  Math.random()); //varying
		}
		if (variance == 1) { //case 2
			this.r = lowerBound + (upperBound - lowerBound) * (int) (2 *  Math.random());
			if (this.r == lowerBound) {
				this.b = upperBound;
			}
			else {
				this.b = lowerBound;
			}
			this.g = lowerBound + (upperBound - lowerBound) * (int) (2 *  Math.random()); //varying
		}
		if (variance == 2) { // case 3
			this.b = lowerBound + (upperBound - lowerBound) * (int) (2 *  Math.random());
			if (this.b == lowerBound) {
				this.g = upperBound;
			}
			else {
				this.g = lowerBound;
			}
			this.r = lowerBound + (int) ((upperBound - lowerBound) * Math.random()); //varying
		}
		
	}
	
	/**
	 * checks to see if the game has ended by exceeding the winning or being lower than the losing score
	 * @return boolean for whether the game is over
	 */
	boolean gameOver(){
		if (this.score >= winScore) {
			StdDraw.clear();
			StdDraw.text(0.5, 0.6, "YOU WIN");
			StdDraw.text(0.5, 0.5, "Final Score: " + score);

			return true;
		}
		if (this.score <= loseScore) {
			StdDraw.clear();
			StdDraw.setPenColor();
			StdDraw.text(0.5, 0.6, "YOU LOSE");
			StdDraw.text(0.5, 0.5, "Final Score: " + score);
			return true;
		}
		return false;
	}
		
}
