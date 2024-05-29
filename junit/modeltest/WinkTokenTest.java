/**
 * JUnit class for WinkToken Class
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
import model.IWinkToken;
import model.Player;
import model.ScoreBoard;

public class WinkTokenTest {
    private IPlayer player;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IScoreBoard scoreBoard;

    @BeforeEach
    public void setUp(){
        player = new Player("name");
        player.initializeTokens();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        pillow.setBoard(scoreBoard);
        
    }
    /**
     * Tests method for changeinPosition() and getPositionOnBoard() methods
     */
    @Test
    public void testSetAndGetPosition() {
        //Test Initial Position of WinkToken: Expected 0
        assertEquals(wink.getPositionOnBoard(), 0);
        wink.changeInPosition(1);
        assertEquals(wink.getPositionOnBoard(), 1);

        //Test position WinkToken goes beyond the limit of scoreboard, which 39
        wink.changeInPosition(41);
        assertEquals(wink.getPositionOnBoard(), 39);

    }
    /**
     * Tests method for setBoard() and getBoard()
     */
    @Test
    public void testSetAndGetBoard() {
        
        IGameBoard gameBoard = new GameBoard();

        //Testing before settting the board, expect 'null'
        assertEquals(wink.getBoard(), scoreBoard);

        try {

            //Test passing wrong board as argument: Expect IllegalArgumentException
            wink.setBoard(gameBoard);

        } catch (IllegalArgumentException iae) {

            //Test: successfully setting scoreboard to winktoken
            IScoreBoard scoreboard = new ScoreBoard();
            wink.setBoard(scoreboard);
            assertEquals(wink.getBoard(), scoreboard);
            return;
        } catch(ClassCastException cce){
            fail("Code tries to cast GameBoard to ScoreBoard");
        }
        fail("Code should catch IllegalArgumentException");
    }

    /**
     * Test method for setPlayer() and getPlayer()
     */
    @Test
    public void testSetAndGetPlayer() {
        //Test after setting the player, expect 'player'
        assertEquals(wink.getPlayer(), player);
    }

    /**
     * Test method for reachedorPassedPillowToken()
     */
    @Test
    public void testReachedOrPassedPillowToken() {
        wink.changeInPosition(37);
        pillow.changeInPosition(38);
        
        //Test case: When winkToken did not reach or pass pillow, expect 'false'
        assertEquals(wink.reachedOrPassedPillowToken(), false);

        //Test case: When winkToken just reaches pillowToken, expceted 'true'
        wink.changeInPosition(38);
        assertEquals(wink.reachedOrPassedPillowToken(), true);

        //Test case: When winkToken passes pillowToken, expceted 'true'
        wink.changeInPosition(39);
        assertEquals(wink.reachedOrPassedPillowToken(), true);
    }
}
