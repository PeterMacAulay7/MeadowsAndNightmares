/**
 * JUnit test class for Dreamtile, RestingSpot
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.IPillowToken;
import model.IPlayer;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.Player;
import model.dreamtiles.DreamTile;
import model.dreamtiles.RestingSpot;

public class RestingSpotTest{
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
        tile = new RestingSpot();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
    }

    @Test
    public void testActivateTile(){
        //TODO: Comeback after catching zzz is implemented
        //Test Case 1: There is no ZzzToken on the tile
        sheep.incrementScare();
        tile.activateTile(player);
        assertEquals(sheep.getScares(), 1);

        //Test Case 2: There is ZzzToken on the tile, and the SheepToken has been scared
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getScares(), 0);

        //Test Case 3: There is ZzzToken on the tile, and the SheepToken is already brave
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getScares(), 0);
        
    }

}
