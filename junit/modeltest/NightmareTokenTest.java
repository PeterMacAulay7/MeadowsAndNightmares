/**
 * JUnit class for NightmareTokenTest Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.GameBoard;
import model.IGameBoard;
import model.INightmareToken;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.NightmareReferenceCard;
import model.Player;
import model.ScoreBoard;

public class NightmareTokenTest {
    NightmareReferenceCard nightmare;
    INightmareToken nToken;
    IGameBoard gameBoard;

    @BeforeEach
    public void setUp(){
        nightmare = new NightmareReferenceCard(1);
        gameBoard = new GameBoard();
        nightmare.initializeToken(gameBoard);
        nToken = nightmare.getNightmareToken();
    }

    /**
     * Test method for ChangeInPosition() and getPosition() methods
     */
    @Test
    public void testSetGetPosition() {

        /**
         * Test case 1: Check the default position 10
         * Expect: 10;
         */
        assertEquals(nToken.getPositionOnBoard(), 0);

        /**
         * Test case 2: Move to position 9
         * Expect: 9;
         */
        nToken.changeInPosition(9);
        assertEquals(nToken.getPositionOnBoard(), 9);

        /**
         * Test case 3: Pass the fence and move to position 1. Additionally, it should call fence.passedThrough(),
         * since fence object is not initialized, this will throw by NullPointerException. Thus, if it throws NullPointerException
         * then, it is valid. The actual result of fence.passedThrough() will be tested in FenceTest class
         * Expect: position 1 and NullPointerException
         */
        try {
            nToken.changeInPosition(2);
        } catch (NullPointerException e) {
            assertEquals(nToken.getPositionOnBoard(), 11);
        }
    }

    /**
     * Test method for setBoard() and getBoard() methods
     */
    @Test
    public void testSetGetBoard() {
        IScoreBoard scoreboard = new ScoreBoard();

        // Testing before setting the board, expect 'null'
        assertEquals(nToken.getBoard(), gameBoard);

        try {

            // Test passing wrong board as argument: Expect IllegalArgumentException
            nToken.setBoard(scoreboard);

        } catch (IllegalArgumentException iae) {

            // Test: successfully setting scoreboard to winktoken

            IGameBoard gameBoard = new GameBoard();
            nToken.setBoard(gameBoard);
            assertEquals(nToken.getBoard(), gameBoard);
            return;
        } catch (ClassCastException cce) {
            fail("Code tries to cast ScoreBoard to GameBoard");
        }
        fail("Code should catch IllegalArgumentException");
    }

    /**
     * Test method for scare()
     */
    @Test
    public void testScare(){
        IPlayer player = new Player("name");
        player.initializeTokens();
        ISheepToken sheep = player.getSheepToken();
        assertEquals(sheep.getScares(), 0);
        nToken.scare(sheep);
        assertEquals(sheep.getScares(), 1);
    }

    /**
     * Test method for scareAdjacent method.
     * 
     * @Note: The proper result of this method is already tested at testTakeActionSpecialAction() in NightmareWolfTest. Thus, in this method, this method is testing whether this method is called only if the nightmare is NightmareWolf
     * 
     */
    @Test
    public void testeScareAdjacent(){
        //Test Case 1: Valid call. Expect nothing is happening
        nToken.changeInPosition(1);
        try {
            nToken.scareAdjacent();
        } catch (IllegalStateException ise) {
            fail("This is valid method call. Should not have thrown IllegalStateException");
        }
    }
}
