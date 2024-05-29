/**
 * JUnit class for Player Class
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;



import model.CardBuilder;
import model.GameBoard;
import model.IGameBoard;
import model.INightmareCardBuilder;
import model.IPillowToken;
import model.IPlayer;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.NightmareCard;
import model.NightmareCardBuilder;
import model.NightmareReferenceCard;
import model.PillowToken;
import model.Player;
import model.SheepCard;
import model.SheepToken;
import model.WinkToken;
import model.ZzzToken;
import model.exceptions.ExceededAllowedAmountOfPlayers;
import model.dreamtiles.ActionHero;
import model.dreamtiles.DreamTile;

public class PlayerTest {
    private IPlayer player;
    private INightmareCardBuilder nBuilder;
    private CardBuilder cardBuilder;
    private SheepCard card1;
    private SheepCard card2;
    private NightmareCard wolfCard;
    private NightmareCard spiderCard;

    @BeforeEach
    public void setUp() {
        player = new Player("name");
        player.initializeTokens();
        nBuilder = new NightmareCardBuilder();
        cardBuilder = new CardBuilder();

        card1 = cardBuilder.withMovableDistance(3)
        .withCatchableZzz(1)
        .withHasAndStatement(false)
        .withMinimumPlayersRequired(2)
        .withDescription("Move 3 Spacves or Catch 1 Zzz").buildSheepCard();

        card2 = cardBuilder
        .withMovableDistance(5)
        .withWinks(2)
        .withHasAndStatement(false)
        .withMinimumPlayersRequired(2)
        .withDescription("Move 5 Spaces or Gain 2 Winks").buildSheepCard();
    }

    /**
     * Test method for getHand() method
     * 
     * @LRicker: ExceededAllowedAmountOfPlayers: Inappropriate naming(isn't end with
     *                                      "Exception") & Inappropriate
     *                                      Hierarchy(Should have extended
     *                                      RunTimeException)
     */
    @Test
    public void testGetHand() {
        /**
         * Test Case 1: Check whether Card[][] hand is initialized properly
         */
        assertNotNull(player.getHand());

        try {
            /**
             * Test case 2: Add one card in the hand
             * Expected Result
             * - cards[0][0] should be equivalent to card1
             * - cards[1][0] shoudld be null
             */
            player.addCard(card1);
            assertEquals(player.getHand()[0][0], card1);
            assertNull(player.getHand()[1][0]);

            /**
             * Test case 3: Add anoer card in the hand and now hand is full
             * Expected Result
             * - cards[0][0] should be equivalent to card1
             * - cards[1][0] shoudld be equivalent to card2
             */
            player.addCard(card2);
            assertEquals(player.getHand()[0][0], card1);
            assertEquals(player.getHand()[1][0], card2);
        } catch (ExceededAllowedAmountOfPlayers e) {
            fail("Shouldn't have thrown the ExceededAllowedAmountOfPlayers");
        }

    }

    /**
     * Test method for setIsOut() and getIsOut() methods
     */
    @Test
    public void testSetGetIsOut() {
        assertEquals(player.getIsOut(), false);
        player.setIsOut(true);
        assertEquals(player.getIsOut(), true);
    }

    /**
     * Test method for getName()
     */
    @Test
    public void testGetName() {
        assertEquals(player.getName(), "name");
    }

    @Test
    public void testSetGetNightmareReferenceCard() {
        //Test Case 1: Checking default value
        assertNull(player.getNightmareReferenceCard());

        //Test Case 2: Checking all type of nightmares
        NightmareReferenceCard nightmare = new NightmareReferenceCard(2);
        player.setNightmareReferenceCard(nightmare);
        assertEquals(player.getNightmareReferenceCard(), nightmare);
        
        nightmare = new NightmareReferenceCard(1);
        player.setNightmareReferenceCard(nightmare);
        assertEquals(player.getNightmareReferenceCard(), nightmare);
        
        nightmare = new NightmareReferenceCard(3);
        player.setNightmareReferenceCard(nightmare);
        assertEquals(player.getNightmareReferenceCard(), nightmare);
    }


    /**
     * Test method for initializeToken(), getSheepToken(), getWinkToken(), and getPillowToken() methods.
     * @Note Test related to ZzzToken is going to be inclueded in testGetZzzTokens() and testCatchZzz() method
     */
    @Test
    public void testInitializeTokens() {
        player.initializeTokens();
        ISheepToken sheep = player.getSheepToken();
        IWinkToken wink = player.getWinkToken();
        IPillowToken pillow = player.getPillowToken();

        //Test Case 1: Check whether SheepToken, WinkToken and PillowToken are valid: Calss Type, owner, color.
        assertTrue(sheep instanceof SheepToken);
        assertTrue(wink instanceof WinkToken);
        assertTrue(pillow instanceof PillowToken);

        assertEquals(sheep.getPlayer(), player);
        assertEquals(wink.getPlayer(), player);
        assertEquals(pillow.getPlayer(), player);

        
    }

}
