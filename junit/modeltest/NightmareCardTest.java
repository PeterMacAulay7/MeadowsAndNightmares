/**
 * JUnit class for NightmareCard Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Card;
import model.INightmareCardBuilder;
import model.NightmareCard;
import model.NightmareCardBuilder;

/**
 * Teste class for NightmareCard and NightmareCard classes
 */
public class NightmareCardTest {
    private INightmareCardBuilder nBuilder;
    Card invalidCard;


    @BeforeEach
    public void setUp(){
        nBuilder = new NightmareCardBuilder();
    }

    /**
     * Test method for buildNightamreCard() in NightmareCardBuilder class.
     * @LRicker: Please check the sourcecode of CardDeck class. In that class, all cards are initialized as type
     *  "Card" (Ex, Card card1 = CardBuilder...buildSheepCard()). This is purposely done to resolve DIP. However, 
     *  since abstract class Card doesn't contain any methods regarding NightmareCard, specifically. For example, in the
     *  code below, if I initialize 'validCard' as Card, then I can't check getJump(), getScareAdjacent(), etc,
     *  Since Card class doesn't contain this. This might be considered as a inappropriate design. Right now, I've 
     *  created 'validCard' as NightmareCard to check those methods.
     */
    @Test
    public void testCreateValidCard(){
        try {
            NightmareCard validCard = nBuilder.withScareAdjacent(true)
                    .withJump(false)
                    .withDescription("The Nightmare Scares All Sheep On It's Space And Adjacent Spaces")
                    .buildNightmareCard();

            assertEquals(validCard.getJump(), false);
            assertEquals(validCard.getScareAdjacent(), true);
            assertNotNull(validCard.getDescription());

        } catch (IllegalArgumentException iae) {
            fail("Valid method calls, should not throw exception");
        }
    }
}
