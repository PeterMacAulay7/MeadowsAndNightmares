/**
 * JUnit test class for Dreamtile, RushAhead
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.dreamtiles.DreamTile;
import model.dreamtiles.RushAhead;

public class RushAheadTest{
    private IPlayer player;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    private IGameBoard gameBoard;
    private IScoreBoard scoreBoard;
    
    @BeforeEach
    public void setUp(){
        player = new Player("name");
        player.initializeTokens();
        tile = new RushAhead();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
        gameBoard = new GameBoard();
        scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);
        sheep.setBoard(gameBoard);
        pillow.setBoard(scoreBoard);
        sheep.setToDefaultPosition();
        wink.setToDefaultPosition();
        pillow.setToDefaultPosition();
    }

    @Test
    public void testActivateTile(){
		//TODO: Comeback after implementing how to resolve the top card

        IRestingPhase restingPhase = new RestingPhase();
        restingPhase.setBoard(gameBoard);
        restingPhase.placeTile(1, tile);

        //Test Case 1: there is no ZzzToken on the tile
        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(), 0);

        //Test Case 2: There is ZzzToken on the tile
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);

        assertEquals(tile.getPlayerZzzs(player).size(), 0);
        assertEquals(wink.getPositionOnBoard(), 1);
    
    }

}
