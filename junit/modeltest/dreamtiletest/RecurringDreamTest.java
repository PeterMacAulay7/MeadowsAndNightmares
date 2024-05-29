/**
 * JUnit test class for Dreamtile, RecurringDream
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.dreamtiles.ActionHero;
import model.dreamtiles.BigStash;
import model.dreamtiles.DreamTile;
import model.dreamtiles.RecurringDream;

public class RecurringDreamTest {
    private IPlayer player;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    private IZzzToken zzz2;
    private IZzzToken zzz3;
    private IZzzToken zzz4;
    private IZzzToken zzz5;
    private IZzzToken zzz6;
    private IZzzToken zzz7;
    private IRestingPhase restingPhase;

    private IGameBoard gameBoard;
    private IScoreBoard scoreBoard;

    @BeforeEach
    public void setUp() {
        player = new Player("name");
        player.initializeTokens();
        tile = new RecurringDream();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();

        gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);

        scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        pillow.setBoard(scoreBoard);

        restingPhase = new RestingPhase();
        restingPhase.setBoard(gameBoard);
        restingPhase.placeTile(1, tile);

        zzz = player.removeZzzToken();
        zzz2 = player.removeZzzToken();
        zzz3 = player.removeZzzToken();
        zzz4 = player.removeZzzToken();
        zzz5 = player.removeZzzToken();
        zzz6 = player.removeZzzToken();
        zzz7 = player.removeZzzToken();
       
    }

    @Test
    public void testActivateTile() {
        DreamTile tile2 = new ActionHero();
        DreamTile tile3 = new BigStash();
        restingPhase.placeTile(3, tile2);
        restingPhase.placeTile(2,tile3);

        //Test Case 1: There is ZzzToken, but there is no other dreamtiles that has 3 or more ZzzTokens
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        try {
            tile.activateTile(player);
        } catch (NullPointerException e) {
            fail();
        }        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(), 0);
        //Test Case 2: There is ZzzToken, there are two other dreamtiles that has 3 or more ZzzTokens
        tile2.addToken(zzz2);
        tile2.addToken(zzz3);
        tile2.addToken(zzz4);
        tile3.addToken(zzz5);
        tile3.addToken(zzz6);
        tile3.addToken(zzz7);

        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(), 4);
        assertEquals(tile.getTokens().size(), 0);

        //Test Case 3: There is no ZzzToken on the tile
        assertEquals(tile.getTokens().size(), 0);
        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(), 4);
        
    
    }

}
