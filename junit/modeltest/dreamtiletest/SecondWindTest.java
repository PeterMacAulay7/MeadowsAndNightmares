/**
 * JUnit test class for Dreamtile, SecondWind
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest.dreamtiletest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.Card;
import model.CardBuilder;
import model.Fence;
import model.GameBoard;
import model.ICardBuilder;
import model.IFence;
import model.IGameBoard;
import model.INightmareToken;
import model.IPillowToken;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.NightmareToken;
import model.Player;
import model.ScoreBoard;
import model.SheepCard;
import model.ZzzToken;
import model.exceptions.ExceededAllowedAmountOfPlayers;
import model.dreamtiles.DreamTile;
import model.dreamtiles.SecondWind;

public class SecondWindTest {
    private IPlayer player;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    private SheepCard card;
    private SheepCard card2;
    private SheepCard card3;
    private ICardBuilder cardBuilder;

    @BeforeEach
    public void setUp() {
        player = new Player("name");
        player.initializeTokens();
        tile = new SecondWind();
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

        ICardBuilder cardBuilder = new CardBuilder();

        card = cardBuilder.buildSheepCard();
        card2 = cardBuilder.buildSheepCard();
        card3 = cardBuilder.buildSheepCard();
    }

    @Test
    public void testActivateTile1() {
        try {
            player.addCard(card);
            player.addCard(card2);
        } catch (ExceededAllowedAmountOfPlayers e) {
            fail();
        }
        try {
            player.addCard(card3);
        } catch (ExceededAllowedAmountOfPlayers e) {
            sheep.incrementScare();
            // Test Case 1: There is no ZzzToken on the tile
            tile.activateTile(player);
            assertEquals(sheep.getScares(), 1);
            assertEquals(player.getNumCards(), 2);
            return;
        }
        fail();

    }

    @Test
    public void testActivateTile2() {
        try {
            player.addCard(card);
            player.addCard(card2);
        } catch (ExceededAllowedAmountOfPlayers e) {
            fail();
        }
        try {
            player.addCard(card3);
        } catch (ExceededAllowedAmountOfPlayers e) {
            // Test Case 2: There is ZzzToken on the tile, and SheepToken is already got
            // scared
            sheep.incrementScare();
            tile.addToken(zzz);
            assertEquals(tile.getTokens().size(), 1);
            tile.activateTile(player);
            assertEquals(tile.getTokens().size(), 0);
            assertEquals(sheep.getScares(), 0);
            assertEquals(player.getNumCards(), 0);
            return;
        }
        fail();
    }
        

    @Test
    public void testActivateTile3() {
        try {
            player.addCard(card);
            player.addCard(card2);
        } catch (ExceededAllowedAmountOfPlayers e) {
            fail();
        }
        try {
            player.addCard(card3);
        } catch (ExceededAllowedAmountOfPlayers e) {
            // Test Case 3: There is ZzzToken on the tile, and Sheep is currently brave
            tile.addToken(zzz);
            assertEquals(tile.getTokens().size(), 1);
            tile.activateTile(player);
            assertEquals(tile.getTokens().size(), 0);
            assertEquals(sheep.getScares(), 0);
            assertEquals(player.getNumCards(), 0);
            return;
        }
        fail();
    }

}
