/**
 * JUnit test class for Dreamtile, MoonWalk
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
import model.dreamtiles.MoonWalk;

public class MoonWalkTest{
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
        tile = new MoonWalk();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();

        IGameBoard gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);

        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        pillow.setBoard(scoreBoard);
    }

    @Test
    public void testActivateTile(){
        //Test Case 1: There is no ZzzToken on tile
        sheep.changeInPosition(2);
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(), 2);
        assertEquals(wink.getPositionOnBoard(), 0);

        //Test Case 2: There is ZzzToken on tile and sheepToken locates before space 3(Should stop at 1)
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getPositionOnBoard(), 1);
        assertEquals(wink.getPositionOnBoard(), 2);

        //Test Case 3: There is ZzzToken on tile and sheepToken locates after space 3
        tile.addToken(zzz);
        sheep.changeInPosition(3);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(), 2);
        assertEquals(tile.getTokens().size(), 0);


    }

}
