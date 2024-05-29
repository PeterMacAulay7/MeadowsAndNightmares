/**
 * JUnit test class for Dreamtile, SnoozeMoves
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;


import model.Fence;
import model.GameBoard;
import model.IFence;
import model.IGameBoard;
import model.IPillowToken;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.Player;
import model.ScoreBoard;
import model.dreamtiles.DreamTile;
import model.dreamtiles.SnoozeMoves;

public class SnoozeMovesTest{
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
        tile = new SnoozeMoves();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();

        IGameBoard gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);

        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        pillow.setBoard(scoreBoard);

        sheep.changeInPosition(1);
    }

    @Test
    public void testActivateTile(){
        try {
            //Test Case 1: There is no ZzzToken on the Tile
            tile.activateTile(player);
            assertEquals(sheep.getPositionOnBoard(), 1);
            assertEquals(tile.getTokens().size(), 0);

            //Test Case 2: There is ZzzToken on the tile, and there are enough ZzzToken in supply
            tile.addToken(zzz);
            assertEquals(tile.getTokens().size(), 1);
            tile.activateTile(player);
            assertEquals(sheep.getPositionOnBoard(), 5);
            assertEquals(tile.getTokens().size(), 0);

            //Test Case 3: There is ZzZToken on the tile, but there is no ZzzToken in supply
            for(int i=0; i<8;i++){
                player.removeZzzToken();
            }

            tile.addToken(player.removeZzzToken());
            player.removeZzzToken();
            assertEquals(tile.getTokens().size(), 1);
            tile.activateTile(player);
            assertEquals(sheep.getPositionOnBoard(), 5);
            assertEquals(tile.getTokens().size(), 0);
        } catch (NoSuchElementException nsee) {
            fail();
        }
    }

}
