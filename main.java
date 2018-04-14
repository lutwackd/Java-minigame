  

public class main {

	public static void main(String[] args) {

		long currentTime = System.currentTimeMillis();
				
		player p = new player();
		background b = new background();
		
		while(!p.gameOver()) {
			
			StdDraw.enableDoubleBuffering();
			
			p.isMoving(); 
			b.showBackground(p); 
			
			if (System.currentTimeMillis() - currentTime >= 3 && p.getMoving()) {
				p.moveColors();
				currentTime = System.currentTimeMillis();
			}
			
			p.showPlayer();
			
			if (b.isSame(p) == true && !p.getMoving()) {
				p.winner(b);
			}

			if (b.isSame(p) == false && !p.getMoving()) {
				p.loser();
			}
			
			StdDraw.show();

		}
		StdDraw.disableDoubleBuffering();
		p.gameOver();
	}
}

