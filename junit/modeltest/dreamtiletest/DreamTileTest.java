/**
 * JUnit test class for Dreamtile
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.Fence;
import model.GameBoard;
import model.IFence;
import model.IGameBoard;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.Player;
import model.ScoreBoard;
import model.dreamtiles.ActionHero;
import model.dreamtiles.DreamTile;

/**
     * Test the basic method of DreamTile Classes. Since Original DreamTile class is
     * abstract, I instantiate the specific subclass of the DreamTile, but in this test class, 
     * We are going to test all methods except activateTile method.
     */
public class DreamTileTest {
    
    private IPlayer player;
    private DreamTile tile;
    private IWinkToken wink;
    private ISheepToken sheep;

    @BeforeEach
    public void setUp(){
        player = new Player("name");
        player.initializeTokens();
        tile = new ActionHero();
        wink = player.getWinkToken();
        sheep = player.getSheepToken();

        IGameBoard gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);

        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);
        
    }
    
    @Test
    public void testAddToken(){
        IZzzToken zzz = player.removeZzzToken();
        IZzzToken zzz2 = player.removeZzzToken();
        IZzzToken zzz3 = player.removeZzzToken();
        assertEquals(tile.getTokens().size(), 0);
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.addToken(zzz2);
        assertEquals(tile.getTokens().size(), 2);
        tile.addToken(zzz3);
        assertEquals(tile.getTokens().size(), 3);
    }

    @Test
    public void testRemoveTokens(){
        
    }

}
