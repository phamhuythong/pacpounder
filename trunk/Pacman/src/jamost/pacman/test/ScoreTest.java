package jamost.pacman.test;

/**
 * @author Steve Legere
 * @version 02.03.09
 * 
 * A test class which tests the methods of the Score class.
 */

import jamost.pacman.Score;
import junit.framework.TestCase;

public class ScoreTest extends TestCase {

	private static final int DEFAULT_SCORE = 0;
	private static final int DEFAULT_INCREMENT = 10;
	
	private Score score;
	
	protected void setUp() {
		score = new Score();
	}

	public void testScore() {
		assertTrue(score.getScore() == DEFAULT_SCORE);
	}

	public void testGetScore() {
		score.setScore(45);
		assertFalse(score.getScore() == DEFAULT_SCORE);
		assertTrue(score.getScore() == 45);
		
		score.setScore(DEFAULT_SCORE);
		assertTrue(score.getScore() == DEFAULT_SCORE);
	}

	public void testIncrementScore() {
		score.incrementScore();
		assertTrue(score.getScore() == DEFAULT_SCORE + DEFAULT_INCREMENT);
	}
}
