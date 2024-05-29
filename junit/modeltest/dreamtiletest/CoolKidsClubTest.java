/**
 * JUnit test class for Dreamtile, CoolKidsClub
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.IPillowToken;
import model.IPlayer;
import model.IScoreBoard;
import model.IZzzToken;
import model.Player;
import model.ScoreBoard;
import model.dreamtiles.CoolKidsClub;
import model.dreamtiles.DreamTile;



public class CoolKidsClubTest {

    @Test
    public void testActivateTile(){
        DreamTile tile = new CoolKidsClub();
        IPlayer player = new Player("name");
        player.initializeTokens();
        IPillowToken pillow = player.getPillowToken();
        IScoreBoard scoreBoard = new ScoreBoard();
        pillow.setBoard(scoreBoard);
        IZzzToken zzz = player.removeZzzToken();
        pillow.changeInPosition(39);

        //Test case 1: there is no ZzzToken on the tile
        tile.activateTile(player);
        assertEquals(pillow.getPositionOnBoard(), 39);
        
        //Test case 2: there is ZzzToken on the tile(Normal Case)
        tile.addToken(zzz);
        tile.activateTile(player);
        assertEquals(pillow.getPositionOnBoard(), 38);
        assertEquals(tile.getTokens().size(), 0);
        
        //Test case 3: there is ZzzToken on the tile and when pillowtoken on the location 0.
        pillow.changeInPosition(-38);
        tile.addToken(zzz);
        tile.activateTile(player);
        assertEquals(pillow.getPositionOnBoard(), 0);
        assertEquals(tile.getTokens().size(), 0);
    }
}
