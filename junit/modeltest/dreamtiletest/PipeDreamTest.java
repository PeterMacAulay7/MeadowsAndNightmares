/**
 * JUnit test class for Dreamtile, PipeDream
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.INightmareToken;
import model.IPillowToken;
import model.IPlayer;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.Player;
import model.dreamtiles.DreamTile;
import model.dreamtiles.PipeDream;

public class PipeDreamTest{
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
        tile = new PipeDream();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
        
    }

    @Test
    public void testActivateTile(){
         //TODO: Creates after impelmentation

    }

}
