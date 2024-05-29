/**
 * Test Class for SheepCard class and CardBuilder class
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Card;
import model.CardBuilder;
import model.ICardBuilder;
import model.SheepCard;

public class SheepCardTest {
    ICardBuilder cardBuilder;
    Card validCard;
    Card invalidCard;

    @BeforeEach
    public void setUp() {
        cardBuilder = new CardBuilder();

    }

    /**
     * Test methods for CardDeck class
     */

    /**
     * Test case 1: All arguments are valid and successfully constructed the object
     */
    @Test
    public void testBuildValidSheepCard(){
        
        try {
            validCard = new CardBuilder()
                    .withMovableDistance(3)
                    .withCatchableZzz(1)
                    .withHasAndStatement(false)
                    .withMinimumPlayersRequired(2)
                    .withDescription("Move 3 Spaces or Catch 1 Zzz")
                    .buildSheepCard();
        
        } catch (IllegalArgumentException iae) {
            fail("Valid method calls, should not throw exception");
        }
        assertTrue(validCard instanceof SheepCard);
    }

    /**
     * Test case 2: assign negative distance
     */
    @Test
    public void testBuildInvalidSheepCard1(){
        try {
            cardBuilder = cardBuilder.withMovableDistance(-3);
        } catch (IllegalArgumentException iae) {
            fail("Invalid distance value, shouldn't be negative");
        }

    }

    /**
     * Test case 3: assign negative amount of ZzzToken
     */
    @Test
    public void testBuildInvalidSheepCard2(){
        try {
            cardBuilder = cardBuilder.withCatchableZzz(-1);
        } catch (IllegalArgumentException iae) {
            fail("Invalid number of ZzzToken value, shouldn' be negative");
        }

    }

    /**
     * Test case 4: assign inappropriate value for minimumPlayersRequired
     */
    @Test
    public void testBuildInvalidSheepCard3(){
        try {
            cardBuilder = cardBuilder.withMinimumPlayersRequired(1);
        } catch (IllegalArgumentException iae) {
            try {
                cardBuilder = cardBuilder.withMinimumPlayersRequired(5);
            } catch (IllegalArgumentException iae2) {
                fail("Invalid number of ZzzToken value, shouldn' be negative");
            }
            fail("Invalid number of ZzzToken value, shouldn' be negative");
        }

    }


    //----------------------------------------------------------------------------
    /**
     * Test method for Card class: all setter and getter methods
     * @LRicker: Please check the sourcecode of CardDeck class. In that class, all cards are initialized as type
     *  "Card" (Ex, Card card1 = CardBuilder...buildSheepCard()). This is purposely done to resolve DIP. However, 
     *  since abstract class Card doesn't contain any methods regarding SheepCard, specifically. For example, in the
     *  code below, if I initialize 'sCard' as Card, then I can't check getMovableDistance(), getCatchableZzz(), etc,
     *  Since Card class doesn't contain this. This might be considered as a inappropriate design. Right now, I've 
     *  create 'sCard' as SheepCard to check those methods. 
     */
    @Test
    public void testSheepCard() {
        SheepCard sCard = new CardBuilder()
                    .withMovableDistance(3)
                    .withCatchableZzz(1)
                    .withHasAndStatement(false)
                    .withMinimumPlayersRequired(2)
                    .withDescription("Move 3 Spaces or Catch 1 Zzz")
                    .withHasSecondMoveOption(true)
                    .buildSheepCard();
        
        assertEquals(sCard.getMovableDistance(), 3);
        assertEquals(sCard.getCatchableZzz(), 1);
        assertFalse(sCard.getHasAndStatement());
        assertEquals(sCard.getMinimumPlayersRequired(), 2);
        assertNotNull(sCard.getDescription());
        assertTrue(sCard.getHasSecondMoveOption());
    }
}
