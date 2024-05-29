/**
 * JUnit test class for Dreamtile, FindAFriend
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.dreamtiles.DreamTile;
import model.dreamtiles.FindAFriend;

import java.util.ArrayList;

public class FindAFriendTest {
    private IPlayer player;
    private IPlayer player2;
    private DreamTile tile;
    private ISheepToken sheep1;
    private ISheepToken sheep2;
    private ArrayList<String> names;
    private ArrayList<Integer> bedtimes;
    private IScoreBoard scoreBoard;

    @BeforeEach
    public void setUp(){
        names = new ArrayList<>();
        bedtimes = new ArrayList<>();
        names.add("name");
        names.add("name2");
        bedtimes.add(1);
        bedtimes.add(2);
        IFirstSheep firstSheep = new FirstSheep();
        firstSheep.receivePlayersInfo(bedtimes, names);
        scoreBoard = new ScoreBoard();
        player = firstSheep.getTurnSequence().get(0);
        player2 = firstSheep.getTurnSequence().get(1);
        player.initializeTokens();
        player2.initializeTokens();
        tile = new FindAFriend();
        sheep1 = player.getSheepToken();
        sheep2 = player2.getSheepToken();
        tile.setFirstSheep(firstSheep);
        IGameBoard gameBoard = new GameBoard();
        sheep1.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep1.setFence(fence);

        player.getWinkToken().setBoard(scoreBoard);
        sheep2.setBoard(gameBoard);
        sheep2.setFence(fence);

    
    }
    @Test
    public void testActivateTile(){
        sheep1.changeInPosition(1);
        sheep2.changeInPosition(3);

        // Test Case 1: Has no ZzzToken on the tile
        tile.activateTile(player);
        assertEquals(sheep1.getPositionOnBoard(), 1);
        assertEquals(sheep2.getPositionOnBoard(), 3);

        //Test Case 2: Has ZzzToken on the tile, another SheepToken locates later position in the GameBoard

        IZzzToken zzz = player.removeZzzToken();
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep1.getPositionOnBoard(), 3);
        assertEquals(sheep2.getPositionOnBoard(), 3);

        //Test Case 3: Has ZzzToken on the tile, another SheepToken locates later position in the GameBoard

        tile.addToken(zzz);
        sheep1.changeInPosition(1);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep1.getPositionOnBoard(), 3);
        assertEquals(sheep2.getPositionOnBoard(), 3);
    }
}
