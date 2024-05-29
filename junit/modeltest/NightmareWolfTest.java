/**
 * JUnit class for NightmareWolf Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Fence;
import model.GameBoard;
import model.IFence;
import model.IFirstSheep;
import model.IGameBoard;
import model.INightmareCardBuilder;
import model.INightmareToken;
import model.IPlayer;
import model.IRacingPhase;
import model.ISheepToken;
import model.NightmareCard;
import model.NightmareCardBuilder;
import model.NightmareReferenceCard;
import model.RacingPhase;
import java.util.ArrayList;
import model.FirstSheep;

public class NightmareWolfTest {
    private NightmareReferenceCard referenceCard;
    private IGameBoard gameBoard;
    private IPlayer player;
    private ISheepToken sheep;
    private IPlayer player2;
    private ISheepToken sheep2;
    private INightmareCardBuilder nBuilder;
    private NightmareCard nCard;
    private IFence fence;
    private IRacingPhase racingPhase;
    private IFirstSheep firstSheep;

    
    @BeforeEach
    public void setUp() {
        ArrayList<Integer> bedTimes = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        bedTimes.add(1);
        bedTimes.add(2);
        names.add("name");
        names.add("name2");
        firstSheep = new FirstSheep();
        firstSheep.receivePlayersInfo(bedTimes, names);
        fence = new Fence();
        racingPhase = new RacingPhase();
        fence.setPhase(racingPhase);
        racingPhase.setFirstSheep(firstSheep);
        referenceCard = new NightmareReferenceCard(1);
        gameBoard = new GameBoard();
        player = firstSheep.getTurnSequence().get(0);
        player2 = firstSheep.getTurnSequence().get(1);
        player.initializeTokens();
        player2.initializeTokens();
        sheep = player.getSheepToken();
        sheep.setBoard(gameBoard);
        sheep2 = player.getSheepToken();
        sheep2.setBoard(gameBoard);
        sheep.setFence(fence);
        sheep2.setFence(fence);
        nBuilder = new NightmareCardBuilder();
        
    }

    /**
     * Test method for InitializeToken() and getNightmareToken() methods
     */
    @Test
    public void testInitializeToken() {
        referenceCard.initializeToken(gameBoard);
        assertTrue(referenceCard.getNightmareToken() instanceof INightmareToken);
    }

    /**
     * Test method for getDescription() from Card abstract class
     * @LRicker: Could not understand the necessity of setDescription method. 
     *           There is no change of changing description since each card already has a fixed description, 
     *           thus that description could be justpassed to constructor and automatically set description and not 
     *           allowing to change once it is instantiated. Just putting this in the constructor provides additional
     *           advantage, which is we would not have to check whether the paramaterized description is valid or not.
     *           Right now, there is no way for the program to detect whether the correct description is assigned.
     */
    @Test
    public void testGetDescription() {
        /**
         * Test Case 1: when getDescription() is called before the description is actually set. It will store the empty String ""
         * Expect: IllegalStateException
         */
        try {
            referenceCard.getDescription();
        } catch (IllegalStateException ise) {
            /**
             * Test Case 2: When setDescription() is called, but the parameterized description value is not valid
             */
            String invalidDescription = "This is not a valid description";
            try {
                referenceCard.setDescription(invalidDescription);
            } catch (IllegalArgumentException iae) {
                /**
                 * Test Case 3: When setDescription() is called and the parameterized description value is valid
                 */
                String validDescription = "THAT NIGHTMARE WOLF IS NO BIG DEAL. THREE" +
                " EYES AND IT STILL DOESN'T SEE US SNEEKING PAST! NOPE, DEFINITELY NOT SCARY AT" +
                " ALL *HOOOOOOOOOOWL* THE WOLF IS RIGHT BEHIND ME ISN'T IT?!?!";
                referenceCard.setDescription(validDescription);
                assertEquals(referenceCard.getDescription(), validDescription);

            }
            fail("The parameterized description value is not equal to expected description");
        }
    }

    
}
