/**
 * JUnit test class for Dreamtile, StepBack
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
import model.IPillowToken;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.Player;
import model.ScoreBoard;
import model.dreamtiles.DreamTile;
import model.dreamtiles.StepBack;

public class StepBackTest{
    private IPlayer player;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    
    @BeforeEach
    public void setUp(){
        player = new Player("name");
        player.initializeTokens();
        tile = new StepBack();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
	    player.removeZzzToken();

        IGameBoard gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);

        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        pillow.setBoard(scoreBoard);

        sheep.changeInPosition(3);
    }

    @Test
    public void testActivateTile(){
		//Test Case 1: There is no ZzToken on the tile
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(), 3);


        //Test Case 2: There is ZzzToken on the tile, but the sheep is already brave
        tile.addToken(zzz);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getScares(), 0);
        assertEquals(sheep.getPositionOnBoard(),2);

        //Test Case 3: There is ZzzToken on the tile, and the sheep has been scared
        tile.addToken(zzz);
        sheep.incrementScare();
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getScares(), 0);
        assertEquals(sheep.getPositionOnBoard(),1);

        //Test Case 4: There is ZzzToken on the tile, and the sheep locates at 0

        tile.addToken(zzz);
        sheep.changeInPosition(-1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getScares(), 0);
        assertEquals(sheep.getPositionOnBoard(),0);
    }

}
