/**
 * JUnit class for GameBoard Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


import model.DreamTileDeck;
import model.GameBoard;
import model.IGameBoard;
import model.IToken;
import model.dreamtiles.DreamTile;

public class GameBoardTest {
    IGameBoard gameBoard;
    
    @BeforeEach
    public void setUp(){
        gameBoard = new GameBoard();
    }

    /**
     * Test method for getBoard() method
     */
    @Test
    public void testGetBoard() {
        assertNotNull(gameBoard.getBoard());
        assertTrue(gameBoard.getBoard() instanceof List<List<IToken>>);
        assertEquals(gameBoard.getBoard().size(), 11);
        
    }

    @Test
    public void testSetGetDreamTileDeck() {
        assertNull(gameBoard.getDreamTileDeck());

        DreamTileDeck deck = new DreamTileDeck();
        gameBoard.setDreamTileDeck(deck);
        assertEquals(gameBoard.getDreamTileDeck(), deck);
    }

    @Test
    public void testGetDreamTileArray(){
        assertTrue(gameBoard.getDreamTileArray() instanceof DreamTile[]);
    }
}
