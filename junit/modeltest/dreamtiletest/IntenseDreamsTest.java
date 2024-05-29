/**
 * JUnit test class for Dreamtile, IntenseDreams
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Fence;
import model.GameBoard;
import model.IFence;
import model.IGameBoard;
import model.INightmareToken;
import model.IPillowToken;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.Player;
import model.ScoreBoard;
import model.dreamtiles.DreamTile;
import model.dreamtiles.IntenseDreams;
import model.IScare;
import model.Scare;


public class IntenseDreamsTest {
    private IPlayer player;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    

    @BeforeEach
    public void setUp() {
        player = new Player("name");
        player.initializeTokens();
        tile = new IntenseDreams();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
        IGameBoard gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);
        IScare scare = new Scare();
        tile.setScare(scare);


        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        pillow.setBoard(scoreBoard);
    }

    @Test
    public void testActivateTile() {
        wink.changeInPosition(0);
        
        //Test Case 1: There is no ZzzToken
        try {
            tile.activateTile(player);
        } catch (NullPointerException e) {
            fail("getScare() is Null");
        }
        
        assertEquals(wink.getPositionOnBoard(), 0);
        assertEquals(sheep.getScares(), 0);

        //Test Case 2: There is ZzzToken, and sheepToken is currently brave
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(),4);
        assertEquals(sheep.getScares(),1);
        
        //Test Case 3: There is ZzzToken, and SheepToken is currently got scared
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        assertEquals(wink.getPositionOnBoard(), 4);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(),8);
        assertEquals(sheep.getScares(),2);
    }

}
