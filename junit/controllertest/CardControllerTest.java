/**
 * JUnit test class for CardController class
 * @author Dylan Kim
 * @version 0.5
 */
package controllertest;
import model.Card;
import model.CardFactory;
import model.SheepCard;
import view.CardView;
import controller.CardController;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardControllerTest {
    private CardController controller;
    private CardFactory cardFactory = new CardFactory();

    @BeforeEach
    public void setUp(){
        controller = new CardController();
    }

    @Test
    public void testGetView() {
        assertNotNull(controller.getView());
        assertTrue(controller.getView() instanceof CardView);
    }
    /**
     * Test method for parseModel() and setCard() methods
     */
    @Test
    public void testGetFirstCardDescription() {
        // Test case 1: the array card is null, haven't been set yet.
        Card[] cards = null;
        try {
            controller.setCard(cards);
            fail("Parameterized array is null. Should have thrown NullPointerException");
        } catch (NullPointerException npe) {
            // This is expected
        }

        // Test case 2: the array card is not null, but has length of zero
        cards = new Card[0];
        try {
            controller.setCard(cards);
            fail("Parameterized array has length of zero. Should have thrown IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException iobe) {
            // This is expected
        }

        // Test case 3: the array card is not null and has valid length
        SheepCard card = cardFactory.createSheepCard("Move 3 Spaces or Catch 1 Zzz");
        cards = new Card[]{card};
        controller.setCard(cards);
        assertEquals("Move 3 Spaces or Catch 1 Zzz", controller.getFirstCardDescription());
    }

    /**
     * Test methods below are interacting with view objects, thus they are not tested in this class
     */
    @Test
    public void testUpdateView() {

    }

    @Test
    public void testDisplayView() {
        //
    }
}
