/**
 * JUnit test class for Dreamtile, Shortcut
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.GameBoard;
import model.IBoard;
import model.IPillowToken;
import model.IPlayer;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.Player;
import model.dreamtiles.DreamTile;
import model.dreamtiles.Shortcut;

public class ShortcutTest{
    private IPlayer player;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    private IBoard gameboard;
    private IPlayer player2;
    
    @BeforeEach
    public void setUp(){
        player = new Player("name");
        player.initializeTokens();
        tile = new Shortcut();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
        gameboard = new GameBoard();
        player2 = new Player("name2");
        player2.initializeTokens();

    }

    @Test
    public void testActivateTile(){
        //TODO: comeback after method putting DreamTile in GameBoard is implemented
		//Test case 1: There is no ZzzToken on the tile

        
        //Test case 2: There is ZzzToken on the tile, but there was no other dreamtile that has 3 or more ZzzToken owned by single player
        
        //Test case 3: There is ZzzToken on the tile, there is one dreamtile that has 3 or more ZzzToken owned by single player, locates ealier than the sheepToken
        
        //Test case 4: There is ZzzToken on the tile, there are  two dreamtiles that have 3 or more ZzzToken owned by single player(Expected to move at the first one)

        //Test case 5: There is ZzzToken on the tile, there are two dreamtiles, first one doesn't have 3 or more ZzzToken owned by single player, second one does.(Expected to move at the second one)

    }

}
