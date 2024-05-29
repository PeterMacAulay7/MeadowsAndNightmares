/**
 * JUnit test class for Dreamtile, ActionHero
 * @author Dylan Kim
 * @version 0.5
 */


package modeltest.dreamtiletest;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.dreamtiles.ActionHero;
import model.dreamtiles.DreamTile;

/**
 * Test Case1: When SheepToken is not scared
 * Test Case2: When SheepToken is scared and there is ZzzToken on the tile
 * Test Case3: When SheepToken is scared, but there is no ZzzToken on the tile
 */
public class ActionHeroTest {

    
    
    @Test
    public void testActivateTile() {

        IPlayer player = new Player("name");
        player.initializeTokens();
        DreamTile tile = new ActionHero();

        IWinkToken wink = player.getWinkToken();
        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        IScare scare = new Scare();
        tile.setScare(scare);
        ISheepToken sheep = player.getSheepToken();
        IFence fence = new Fence();
        sheep.setFence(fence);
        IGameBoard gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);

        IRestingPhase restingPhase = new RestingPhase();
        restingPhase.setBoard(gameBoard);
        restingPhase.placeTile(1, tile);
        
        IZzzToken zzz = player.removeZzzToken();
        try { 
            tile.addToken(zzz);
            wink.changeInPosition(3);
        } catch (NullPointerException npe) {
            fail();
        }

        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(),3);
        assertEquals(tile.getTokens().size(), 0);


        sheep.incrementScare();
        tile.addToken(zzz);
        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(),6);
        assertEquals(tile.getTokens().size(), 0);

        tile.addToken(zzz);
        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(),9);
        assertEquals(tile.getTokens().size(), 0);
    }
}
