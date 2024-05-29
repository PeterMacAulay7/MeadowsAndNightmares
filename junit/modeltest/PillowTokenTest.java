/**
 * JUnit class for PillowToken Class
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.GameBoard;
import model.IGameBoard;
import model.IPillowToken;
import model.IPlayer;
import model.IScoreBoard;
import model.Player;
import model.ScoreBoard;

public class PillowTokenTest {
    private IPlayer player;
    private IPillowToken pillow;
    private IScoreBoard scoreBoard;

    @BeforeEach
    public void setUp() {
        player = new Player("name");
        player.initializeTokens();
        pillow = player.getPillowToken();
        scoreBoard = new ScoreBoard();
        pillow.setBoard(scoreBoard);

    }

    /**
     * Tests method for changeinPosition() and getPositionOnBoard() methods
     */
    @Test
    public void testSetGetPosition() {
        
        // Test Initial Position of PillowToken: Expected 0
        assertEquals(pillow.getPositionOnBoard(), 0);
        pillow.changeInPosition(1);
        assertEquals(pillow.getPositionOnBoard(), 1);

        // Test position PillowToken goes beyond the limit of scoreboard, which 40
        pillow.changeInPosition(41);
        assertEquals(pillow.getPositionOnBoard(), 2);

    }

    /**
     * Tests method for setBoard() and getBoard()
     */
    @Test
    public void testSetGetBoard() {

        IGameBoard gameBoard = new GameBoard();

        try {

            // Test passing wrong board as argument: Expect IllegalArgumentException
            pillow.setBoard(gameBoard);

        } catch (IllegalArgumentException iae) {

            // Test: successfully setting scoreboard to pillowtoken
            IScoreBoard scoreboard = new ScoreBoard();
            pillow.setBoard(scoreboard);
            assertEquals(pillow.getBoard(), scoreboard);
            return;
        } catch (ClassCastException cce) {
            fail("Code tries to cast GameBoard to ScoreBoard");
        }
        fail("Code should catch IllegalArgumentException");
    }

    /**
     * Test method for setPlayer() and getPlayer()
     */
    @Test
    public void testSetGetPlayer() {
        assertEquals(pillow.getPlayer(), player);
        IPlayer player2 = new Player("name2");
        pillow.setPlayer(player2);
        assertEquals(pillow.getPlayer(), player2);

    }
}
