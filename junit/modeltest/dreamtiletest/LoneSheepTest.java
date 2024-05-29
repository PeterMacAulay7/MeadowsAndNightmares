/**
 * JUnit test class for Dreamtile, LoneSheep
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import model.*;
import model.dreamtiles.FindAFriend;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import model.dreamtiles.DreamTile;
import model.dreamtiles.LoneSheep;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoneSheepTest{
    private IPlayer player;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    private IZzzToken zzz2;
    private IGameBoard gameBoard;
    private IScoreBoard scoreBoard;
    private IRestingPhase restingPhase;

    
    @BeforeEach
    public void setUp(){

        player = new Player("name");
        player.initializeTokens();
        tile = new LoneSheep();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
        zzz2 = player.removeZzzToken();
        gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);

        scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);

        pillow.setBoard(scoreBoard);
        restingPhase = new RestingPhase();
        restingPhase.setBoard(gameBoard);
        restingPhase.placeTile(1, tile);
    }

    @Test
    public void testActivateTile(){
        //Test 1: there is no ZzzToken on the dreamtile
        tile.activateTile(player);

        //Test 2: There is ZzzToken on the dreamtile, there is no other Dreamtile

        tile.addToken(zzz);
        //checks if tiles are in correct places
        assertEquals(gameBoard.getDreamTileArray()[1], tile);
        assertEquals(gameBoard.getDreamTileArray()[2], null);
        assertEquals(gameBoard.getDreamTileArray()[3], null);

        assertEquals(wink.getPositionOnBoard(), 0);
        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(), 3);


        //Test 3: There is ZzzToken on the dreamtile, there is another Dreamtile
        DreamTile findAFriend = new FindAFriend();
        restingPhase.placeTile(2,findAFriend);
        findAFriend.addToken(zzz2);
        tile.addToken(zzz);
        //checks if tiles are in correct places
        assertEquals(gameBoard.getDreamTileArray()[1], tile);
        assertEquals(gameBoard.getDreamTileArray()[2], findAFriend);
        assertEquals(gameBoard.getDreamTileArray()[3], null);

        tile.activateTile(player);

        assertEquals(wink.getPositionOnBoard(), 3);

    }

}
