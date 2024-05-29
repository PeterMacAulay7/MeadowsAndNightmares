/**
 * JUnit class for GameInformation Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.GameInformation;
import model.IGameInformation;

public class GameInformationTest {
    private IGameInformation gameInfo;

    @BeforeEach
    public void setUp(){
        gameInfo  = new GameInformation();
    }

    /**
     * Test Following methods:
     * 1. generateModel()
     * 2. getFirstSheep()
     * 3. getRestingPhase()
     * 4. getReferenceTile()
     * 5. getGameBoard()
     * 6. getScoreBoard()
     */
    @Test
    public void testGenerateModel() {
        gameInfo.generateModel(1);

        /**
         * Test case: Check the existence of all instances
         */
        assertNotNull(gameInfo.getRuleBook());
        assertNotNull(gameInfo.getReferenceTile());
        assertNotNull(gameInfo.getFirstSheep());
        assertNotNull(gameInfo.getGameBoard());
        assertNotNull(gameInfo.getScoreBoard());
        assertNotNull(gameInfo.getDreamTileDeck());
    }


}
