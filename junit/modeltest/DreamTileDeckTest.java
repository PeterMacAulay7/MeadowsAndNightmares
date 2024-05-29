/**
 * JUnit class for DreamTileDeck Class
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.DreamTileDeck;
import model.IDreamTileDeck;
import model.dreamtiles.DreamTile;

public class DreamTileDeckTest {
    private IDreamTileDeck dreamTileDeck = new DreamTileDeck();;

    @BeforeEach
    public void setUp() {
        dreamTileDeck = new DreamTileDeck();
    }

    /**
     * Test method for getDeck() and createDeck() methods
     */
    @Test
    public void testCreateAndGetDeck() {
        assertEquals(dreamTileDeck.getDeck().size(), 0);
        dreamTileDeck.createDeck();
        assertEquals(dreamTileDeck.getDeck().size(), 30);
    }

    /**
     * Test method for revealMarket()
     */
    @Test
    public void testRevealMarket() {
        dreamTileDeck.createDeck();
        DreamTile[] market = dreamTileDeck.revealMarket();
        /**
         * Test Case 1: check each element in the market is valud
         */
        for (int i=0; i<4;i++){
            assertNotNull(market[i]);
            assertTrue(market[i] instanceof DreamTile);
        }

        /**
         * Test Case 2: pretend that one of them is placed on the gameboard and market is refilled
         * Check whether it is actually changed comaped to the previous one.
         */
        DreamTile tempDreamTile = market[0];
        market[0]=null;
        market = dreamTileDeck.revealMarket();
        assertNotEquals(market[0].getDescription(), tempDreamTile.getDescription());

        
    }

    @Test
    public void testDraw(){
        dreamTileDeck.createDeck();
        DreamTile dt = dreamTileDeck.draw();

        assertTrue(dt != null);

    }

}
