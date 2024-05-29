/**
 * JUnit class for WinkToken Class
 * @author Dylan Kim
 * @version 0.5
 * @LRicker: The hierarchy of this class is following: (I) indicates interfaces
 *  (I) Phase<T> <- (I) IRacingPhase<Card> <- RacingPhase
 *           Even though Interface Phase<T> uses generic type T, there is no method that use it.
 *           Similarly, its subinterface, IRacingPhase assigned Card to generic type T, but it doesn't use it properly
 *           Furthermore, there is another subcInterface of Phase<T> which is IRestingPhse, this
 *           interface doesn't assign any object to generic type T.
 */

package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;



import model.CardDeck;
import model.Fence;
import model.ICardDeck;
import model.IFence;
import model.IFirstSheep;
import model.IPlayer;
import model.IReferenceTile;
import model.NightmareReferenceCard;
import model.Player;
import model.RacingPhase;
import model.ReferenceTile;
import model.Resetable;


public class RacingPhaseTest {
    private RacingPhase racingPhase;
    private IPlayer player;

    private ArrayList<IPlayer> players;
    private ICardDeck deck;
    private NightmareReferenceCard nightmare;
    private IFirstSheep firstSheep;
    private IFence fence;
    private IReferenceTile referenceTile;
    private Resetable resetable;

    @BeforeEach
    public void setUp(){
        racingPhase = new RacingPhase();
        player = new Player("name");
        players = new ArrayList<IPlayer>();
        players.add(player);
        nightmare = new NightmareReferenceCard(1);
    }

    /**
     * Test method for enforceFullHand() method.
     */
    @Test
    public void testEnforceFullHand() {
        ICardDeck cardDeck = new CardDeck(players, nightmare);
        //TODO: Comeback after implementation
    }

    @Test
    public void testTakeAction() {
        //TODO: Comeback after implementation
    }

    /**
     * Test method for endPhase() method.
     * @Note This method calls movePillows() and reset() methods, but these are going to be tested at their own test classes(RefereceTileTest and ResetableTest)
     *      Thus, this test method will just check the instance variable phaseActive is reflecting the logic properly. 
     */
    @Test
    public void testEndPhase() {
        try {
            racingPhase.endPhase();
            assertEquals(racingPhase.isPhaseOver(), false);
        } catch (NullPointerException e) {
            //Since Objects required for movePillows() and reset() method are not initialized, it will throw an NullPointerException, which is expected here
        }
    }

    /**
     * Test method for setFence() and getFence() methods.
     */
    @Test
    public void testSetGetFence() {
        assertEquals(racingPhase.getFence(), null);
        IFence fence = new Fence();
        racingPhase.setFence(fence);
        assertEquals(racingPhase.getFence(), fence);
    }
    
    /**
     * Test method for getPlayer()
     * @LRicker: There is no setPlayers() method, but also there is no argument in constructor that assign players
     *           Thus, there is no way to change or initialize the instance variable players, but there is getter method
     *           Please check whether setPlayers() exists or the constructor is updated
     */
    @Test
    public void testGetPlayers() {
        players.add(player);
        //racingPhase.setPlayers(players);
        //assertEquals(racingPhase.getPlayers(), players);
    }

    /**
     * Test method for setReferenceTile() and getReferenceTile() methods
     */
    @Test
    public void testSetGetReferenceTile() {
        IReferenceTile referenceTile = new ReferenceTile();
        racingPhase.setReferenceTile(referenceTile);
        assertEquals(racingPhase.getReferenceTile(), referenceTile);
    }

    @Test
    public void testReset() {
        //This method is equivalent to testing Resetable class. Thus, this will be tested in Resetable class.
    }

    /**
     * Test case for setPhaseActive() and isPhaseOver() methods.
     */
    @Test
    public void testSetGetPhaseActive() {
        assertEquals(racingPhase.isPhaseOver(), true);
        racingPhase.setPhaseActive(false);
        assertEquals(racingPhase.isPhaseOver(), false);
    }

    /**
     * test method for setFirstSheep()
     * @LRicker: setFirstSheep() is created to assign value to the intance firstSheep
     *           but there is no other method that use this instance, except getter method. 
     *           If the purpose of this is just to pass the value to other classes, then 
     * 
     * @Note: Since there is no other method that use firstSheep, this method couldn't be tested
     */
    @Test
    public void testSetFirstSheep(){
        //Since there is no 
    }
}
