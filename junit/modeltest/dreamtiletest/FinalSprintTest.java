/**
 * JUnit test class for Dreamtile, FinalSprint
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;

import model.*;
import model.dreamtiles.FinalSprint;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.dreamtiles.DreamTile;


public class FinalSprintTest {
    @Test
    public void testActivateTile(){
        IPlayer player = new Player("name");
        player.initializeTokens();
        DreamTile tile = new FinalSprint();
        ISheepToken sheep = player.getSheepToken();
        IGameBoard gameBoard = new GameBoard();
        IScoreBoard scoreBoard = new ScoreBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        player.getWinkToken().setBoard(scoreBoard);
        sheep.setFence(fence);

        IZzzToken zzz = player.removeZzzToken();
        tile.addToken(zzz);
        sheep.changeInPosition(4);

        //Test Case1: When SheepToken is not scared
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(),4);
        assertEquals(tile.getTokens().size(), 0);


        //Test Case2: When SheepToken is scared and there is ZzzToken on the tile
        sheep.incrementScare();
        tile.addToken(zzz);
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(),1);
        assertEquals(tile.getTokens().size(), 0);
        
        //Test Case3: When SheepToken is scared, but there is no ZzzToken on the tile
        tile.activateTile(player);
        assertEquals(sheep.getPositionOnBoard(),1);
        assertEquals(tile.getTokens().size(), 0);
    }
}
