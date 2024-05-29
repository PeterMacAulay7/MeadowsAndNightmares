/**
 * JUnit test class for Dreamtile, RacingRival
 *
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;

import java.util.ArrayList;

import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import model.dreamtiles.DreamTile;
import model.dreamtiles.RacingRival;

public class RacingRivalTest {
    private IPlayer player;
    private IPlayer player2;
    private DreamTile tile;
    private ISheepToken sheep;
    private ISheepToken sheep2;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    private IZzzToken zzz2;
    private ArrayList<Integer> bedtimes;
    private ArrayList<String> names;

    @BeforeEach
    public void setUp(){
        bedtimes = new ArrayList<>();
        names = new ArrayList<>();
        bedtimes.add(1);
        bedtimes.add(2);
        names.add("Jeff");
        names.add("Name's Jeff");
        IFirstSheep fs = new FirstSheep();
        fs.receivePlayersInfo(bedtimes, names);
        player = fs.getTurnSequence().get(0);
        player2 = fs.getTurnSequence().get(1);
        player.initializeTokens();
        player2.initializeTokens();
        tile = new RacingRival();
        tile.setFirstSheep(fs);
        sheep = player.getSheepToken();
        sheep2 = player2.getSheepToken();
        zzz = player.removeZzzToken();
        IGameBoard gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);

        sheep2.setBoard(gameBoard);
        sheep2.setFence(fence);

        zzz.setPlayer(player);
        zzz2 = player2.removeZzzToken();
        zzz2.setPlayer(player2);

        IRestingPhase restingPhase = new RestingPhase();
        restingPhase.setBoard(gameBoard);
        restingPhase.placeTile(1, tile);
    }

    @Test
    public void testActivateTile() {
        sheep.changeInPosition(1);
        sheep2.changeInPosition(1);
        //Test case 1: there is no ZzzToken on tile
        try {
            tile.activateTile(player);
        } catch (NullPointerException e) {
            fail();
        }

        assertEquals(sheep.getPositionOnBoard(), 1);
        assertEquals(sheep2.getPositionOnBoard(), 1);

        //Test case 2: there is ZzzToken, player 1 has ealier turn than player2

        IZzzToken expendablezzz1 = new ZzzToken(player);
        IZzzToken expendablezzz2 = new ZzzToken(player);
        IZzzToken expendablezzz3 = new ZzzToken(player);

        tile.addToken(expendablezzz1);
        tile.addToken(expendablezzz2);
        tile.addToken(expendablezzz3);

        //System.out.println(tile.getTokens().size());
        //sheep2.changeInPosition(3);
        assertEquals(tile.getTokens().size(), 3);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getPositionOnBoard(), 2);
        assertEquals(sheep2.getPositionOnBoard(), 1);

        //Test case 3: there is ZzzToken, player 2 has later turn than player 1
        tile.addToken(expendablezzz1);
        tile.addToken(expendablezzz2);
        tile.addToken(expendablezzz3);
        assertEquals(tile.getTokens().size(), 3);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(sheep.getPositionOnBoard(), 3);
        assertEquals(sheep2.getPositionOnBoard(), 1);

        String description = "RacingRival: Move X Spaces. X is Equal To The Number of The Space The Previous Player Is On";

        assertEquals(description, tile.getDescription());
    }

}

