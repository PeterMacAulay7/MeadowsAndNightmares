/**
 * JUnit class for SheepToken Class
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Fence;
import model.GameBoard;
import model.IFence;
import model.IGameBoard;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.Player;
import model.ScoreBoard;

public class SheepTokenTest {
    private IPlayer player;
    private ISheepToken sheep;
    private IGameBoard gameBoard;
    private IScoreBoard scoreBoard;

    @BeforeEach
    public void setUp() {
        scoreBoard = new ScoreBoard();
        player = new Player("name");
        player.initializeTokens();
        sheep = player.getSheepToken();
        gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);
        sheep.setToDefaultPosition();
        player.getWinkToken().setBoard(scoreBoard);
        player.getPillowToken().setBoard(scoreBoard);
        
    }

    /**
     * Test method for changeInPosition() and getPositionOnBoard() methods
     */
    @Test
    public void testSetGetPosition() {
        // Test case 1: The initial position is 10
        assertEquals(sheep.getPositionOnBoard(), 10);

        // Test case 2: Normal Movement
        sheep.changeInPosition(9);
        assertEquals(sheep.getPositionOnBoard(), 9);

        // Test case 3: Moving from 10 to 1
        sheep.changeInPosition(1);
        assertEquals(sheep.getPositionOnBoard(), 0);
    }

    //Test method for getIsActive() and setIsActive() methods
    @Test
    public void testGetIsActive() {
        player.setIsOut(true);
        assertEquals(sheep.getIsActive(), true);
        player.setIsOut(false);
        assertEquals(sheep.getIsActive(), false);
    }

    //Test method for getIsStuck() and setIsStuck() methods
    @Test
    public void testGetIsStuck() {
        sheep.setIsStuck(true);
        assertEquals(sheep.getIsStuck(), true);
        sheep.setIsStuck(false);
        assertEquals(sheep.getIsStuck(), false);
    }

    /**
     * Test method for getPlayer()
     */
    @Test
    public void testGetPlayer() {
        try {
            assertEquals(sheep.getPlayer(), player);
        } catch (NullPointerException npe) {
            fail("Expected 'Player' object, get 'null'");
        }
    }

    /**
     * Test method for incrementScare(). This can also be considered as
     * test method for Scare class 
     */
    @Test
    public void testIncrementScare() {
        assertEquals(sheep.getScares(), 0);
        sheep.incrementScare();
        assertEquals(sheep.getScares(), 1);
        sheep.incrementScare();
        assertEquals(sheep.getScares(), 2);
        try {
            sheep.incrementScare();
        } catch (IllegalStateException ise) {
            return;
        }
    }

    @Test
    public void testdecrementScare() {
        sheep.incrementScare();
        sheep.incrementScare();

        sheep.decrementScare();
        assertEquals(sheep.getScares(), 1);
        sheep.decrementScare();
        assertEquals(sheep.getScares(), 0);
        try {
            sheep.decrementScare();
        } catch (IllegalStateException ise) {
            return;
        }
    }

    @Test
    public void testSetGetBoard() {
        IScoreBoard scoreboard = new ScoreBoard();
        try {

            // Test passing wrong board as argument: Expect IllegalArgumentException
            sheep.setBoard(scoreboard);

        } catch (IllegalArgumentException iae) {

            // Test: successfully setting scoreboard to winktoken

            IGameBoard gameBoard = new GameBoard();
            sheep.setBoard(gameBoard);
            assertEquals(sheep.getBoard(), gameBoard);
            return;
        } catch (ClassCastException cce) {
            fail("Code tries to cast ScoreBoard to GameBoard");
        }
        fail("Code should catch IllegalArgumentException");
    }
}
