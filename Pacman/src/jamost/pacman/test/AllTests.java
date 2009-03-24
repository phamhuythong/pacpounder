package jamost.pacman.test;

import jamost.pacman.test.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * 
 * @author Mohamed Elzayat
 * @version 1.0
 * 
 * a Test suite to test all the test classes which were put in a different package
 * to separate from the other classes
 *
 */
public class AllTests{
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for jamost.pacman");
		//$JUnit-BEGIN$
		suite.addTestSuite(BlinkyTest.class);
		suite.addTestSuite(ClydeTest.class);
		suite.addTestSuite(InkyTest.class);
		suite.addTestSuite(PinkyTest.class);
		suite.addTestSuite(MazeTest.class);
		suite.addTestSuite(PacmanModelTest.class);
		suite.addTestSuite(ScoreTest.class);
		//$JUnit-END$
		return suite;
	}

}
