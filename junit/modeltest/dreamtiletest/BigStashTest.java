/**
 * JUnit Test class for Dreamtile BigStash
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.IPlayer;
import model.IScoreBoard;
import model.IWinkToken;
import model.IZzzToken;
import model.Player;
import model.ScoreBoard;
import model.dreamtiles.BigStash;
import model.dreamtiles.DreamTile;

public class BigStashTest {
    @Test
    public void testActivateTile(){
        IPlayer player = new Player("name");
        player.initializeTokens();
        DreamTile tile = new BigStash();
        IWinkToken wink = player.getWinkToken();
        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);
        wink.changeInPosition(1);

        //Test Case 1: When there is no ZzzToken on the DreamTile
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(),1);

        //Test Case 2: When there is enough ZzzToken on the DreamTile
        IZzzToken zzz = player.removeZzzToken();

        tile.addToken(zzz);
        IZzzToken zzz2 = player.removeZzzToken();

        tile.addToken(zzz2);
        assertEquals(tile.getTokens().size(), 2);
        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(),5);
        assertEquals(tile.getTokens().size(), 0);
                
    }
}
