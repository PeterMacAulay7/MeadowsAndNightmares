/**
 * JUnit test class for Dreamtile, RUUUUUUN
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.dreamtiles.DreamTile;
import model.dreamtiles.RUUUUUUN;
public class RUUUUUUNTest{
    private IPlayer player;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    private INightmareToken nightmare;
    private IGameBoard gameboard;
    private IRestingPhase restingPhase;
    
    @BeforeEach
    public void setUp(){
        player = new Player("name");
        player.initializeTokens();
        tile = new RUUUUUUN();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
        gameboard = new GameBoard();
        NightmareReferenceCard referenceCard = new NightmareReferenceCard(1);
        referenceCard.initializeToken(gameboard);
        nightmare = referenceCard.getNightmareToken();

        
        sheep.setBoard(gameboard);
        IFence fence = new Fence();
        sheep.setFence(fence);

        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        pillow.setBoard(scoreBoard);

        sheep.changeInPosition(1);
        restingPhase = new RestingPhase();
        restingPhase.setBoard(gameboard);
        restingPhase.placeTile(1,tile);
        nightmare.setToDefaultPosition();
        player.setNightmareReferenceCard(referenceCard);
    }

    @Test
    public void testActivateTile(){
		//Test Case 1: There is no ZzzToken on the tile
        try {
            tile.activateTile(player);
        } catch (NullPointerException e) {
            fail();
        }
        assertEquals(sheep.getPositionOnBoard(), 1);
        assertEquals(nightmare.getPositionOnBoard(), 10);

        //Test Case 2: There is ZzzToken on the tile, but there is no NightmareToken on the board
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getPositionOnBoard(), 1);
        assertEquals(nightmare.getPositionOnBoard(), 10);

        //Test Case 3: There is ZzzToken on the tile, but NightmareToken locates same as SheepToken
        nightmare.changeInPosition(1);
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getPositionOnBoard(), 1);
        assertEquals(nightmare.getPositionOnBoard(), 0);

        //Test Case 4: There is ZzzToken on the tile, and NightmareToken locates later than SheepToken
        nightmare.changeInPosition(2);
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getPositionOnBoard(), 3);
        assertEquals(nightmare.getPositionOnBoard(), 2);

        //Test Case 5: There is ZzzToken on the tile, and NightmareToken locates earlier than SheepToken
        sheep.changeInPosition(2);
        assertEquals(sheep.getPositionOnBoard(), 5);
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getPositionOnBoard(), 3);
        assertEquals(nightmare.getPositionOnBoard(), 2);
    }

}
