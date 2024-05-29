/**
 * JUnit test class for Dreamtile, SweetDream
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.dreamtiles.DreamTile;
import model.dreamtiles.SweetDream;

public class SweetDreamTest{
    private IPlayer player;
    private IPlayer player2;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IWinkToken wink2;
    private IPillowToken pillow2;
    private IZzzToken zzz;
    private IBoard scoreboard;
    
    @BeforeEach
    public void setUp(){
        player = new Player("name");
        player2 = new Player("name2");
        player.initializeTokens();
        player2.initializeTokens();
        tile = new SweetDream();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        wink2 = player2.getWinkToken();
        pillow2 = player2.getPillowToken();
        zzz = player.removeZzzToken();
	    player.removeZzzToken();
        scoreboard = new ScoreBoard();
        pillow.setBoard(scoreboard);
        pillow2.setBoard(scoreboard);
        wink.setBoard(scoreboard);
        wink2.setBoard(scoreboard);
        wink.setToDefaultPosition();
        wink2.setToDefaultPosition();
        pillow.setToDefaultPosition();
        pillow2.setToDefaultPosition();


    }

    @Test
    public void testActivateTile(){
		//Test Case 1: There is no ZzzToken
        tile.activateTile(player);
        assertEquals(pillow.getPositionOnBoard(), 39);
        assertEquals(pillow2.getPositionOnBoard(),39);

        //Test Case 2: there is ZzzToken, player's pillowToken is not at the highest position
        pillow.changeInPosition(-10);
        tile.addToken(zzz);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(), 2);
        assertEquals(pillow.getPositionOnBoard(), 29);
        assertEquals(pillow2.getPositionOnBoard(),39);

        //Test Case 3: there is ZzzToken, pillowTokens of player and player2 are at the same position
        pillow.changeInPosition(10);
        tile.addToken(zzz);
        wink.changeInPosition(0);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(), 6);
        assertEquals(pillow.getPositionOnBoard(), 39);
        assertEquals(pillow2.getPositionOnBoard(),39);
        
        //Test Case 4: there is ZzzToken, player's pillowToken is at the highest position
        tile.addToken(zzz);
        pillow2.changeInPosition(-10);
        wink.changeInPosition(0);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(), 10);
        assertEquals(pillow.getPositionOnBoard(), 39);
        assertEquals(pillow2.getPositionOnBoard(),29);

    }

}
