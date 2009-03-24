package jamost.pacman;

/*
 * @author: Steve Legere
 * @version: 02.03.09
 */
public class Score {
	
	private static final int DEFAULT_SCORE = 0;
	private static final int DEFAULT_INCREMENT = 10;
	
	private int score;
	
	public Score() {
		score = DEFAULT_SCORE;
	}
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void incrementScore() {
		score += DEFAULT_INCREMENT;
	}
}

