/**
 * JUnit class for WebToken Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WebTokenTest {
    private IWebToken web;
    @BeforeEach
    public void setUp(){
        NightmareReferenceCard spider = new NightmareReferenceCard(3);
        spider.createWebToken();
        web = spider.getWebToken();
        //stuckable = new SheepToken();
    }

    @Test
    public void testCapture() {

    }

    /**
     * Test method for changeInPosition() and getPositionOnBoard() methods
     */
    @Test
    public void testSetGetPosition() {
        IGameBoard gameBoard = new GameBoard();
        web.setBoard(gameBoard);
        //Test Initial Position of WebToken: Expected 0
        assertEquals(web.getPositionOnBoard(), 0);

        //Test: move to position 1 on board: Expected 1;
        web.changeInPosition(1);
        assertEquals(web.getPositionOnBoard(), 1);
    }

    /**
     * Test Method for getBoard(), setBoard()
     */
    @Test
    public void testSetGetBoard() {
        IScoreBoard scoreboard = new ScoreBoard();

        //Testing before settting the board, expect 'null'
        assertEquals(web.getBoard(), null);

        try {

            //Test passing wrong board as argument: Expect IllegalArgumentException
            web.setBoard(scoreboard);


        } catch (IllegalArgumentException iae) {

            //Test: successfully setting scoreboard to winktoken
            
            IGameBoard gameBoard = new GameBoard();
            web.setBoard(gameBoard);
            assertEquals(web.getBoard(), gameBoard);
            return;
        } catch (ClassCastException cce) {
            fail("Code tries to cast ScoreBoard to GameBoard");
        }
    }
}
