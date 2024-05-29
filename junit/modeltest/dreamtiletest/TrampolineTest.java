/**
 * JUnit test class for Dreamtile, Tampoline
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
import model.dreamtiles.Trampoline;

public class TrampolineTest{
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
        tile = new Trampoline();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
	    player.removeZzzToken();
    }

    @Test
    public void testActivateTile(){
		//TODO: Comeback after implementation

    }

}
