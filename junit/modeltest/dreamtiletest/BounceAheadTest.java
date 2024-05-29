/**
 * JUnit test class for Dreamtile, BounceAhead
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.dreamtiles.BounceAhead;
import model.dreamtiles.DreamTile;

public class BounceAheadTest {
    private IGameBoard gameBoard;
    private IRestingPhase restingPhase;
    private IScoreBoard scoreBoard;

    @Test
    public void testActivateTile(){
        IPlayer player = new Player("name");
        player.initializeTokens();
        DreamTile tile = new BounceAhead();
        ISheepToken sheep = player.getSheepToken();
        gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);
        IZzzToken zzz = player.removeZzzToken();
        sheep.changeInPosition(1);
        restingPhase = new RestingPhase();
        restingPhase.setBoard(gameBoard);
        restingPhase.placeTile(1, tile);
        sheep.setBoard(gameBoard);
        IWinkToken wink = player.getWinkToken();
        scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        //Test Case 1: When there is no ZzzToken on the tile
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(), 1);
        assertEquals(tile.getTokens().size(), 0);

        //Test Case 2: There is ZzzToken, and the sheepToken is not on 10
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(),2);
        assertEquals(tile.getTokens().size(), 0);
        
        //Test Case 3: When there is ZzzToken and the sheepToken is on 10(Checking position update to 1)
        sheep.changeInPosition(9);        
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(),2);
        assertEquals(tile.getTokens().size(), 0);
    }    
}
