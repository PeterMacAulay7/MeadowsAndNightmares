/**
 * JUnit class for BumpInTheNight Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;


import model.Fence;
import model.FirstSheep;
import model.GameBoard;
import model.IFence;
import model.IFirstSheep;
import model.IGameBoard;
import model.INightmareToken;
import model.IPlayer;
import model.ISheepToken;
import model.NightmareCard;
import model.CardFactory;
import model.NightmareReferenceCard;

public class BumpInTheNightTest {
    private NightmareReferenceCard referenceCard;
    private IGameBoard gameboard;
    private IFirstSheep fs;
    private IPlayer player;
    private IPlayer player2;
    private ISheepToken sheep;
    private ISheepToken sheep2;
    private CardFactory cardFactory;

    @BeforeEach
    public void setUp(){
        fs = new FirstSheep();
        referenceCard = new NightmareReferenceCard(2);
        gameboard = new GameBoard();
        ArrayList<Integer> bedtimes = new ArrayList<>(Arrays.asList(1,2));
        ArrayList<String> names = new ArrayList<>(Arrays.asList("name","name2"));
        fs.receivePlayersInfo(bedtimes, names);
        ArrayList<IPlayer> players = fs.getTurnSequence();
        player = players.get(0);
        player2 = players.get(1);
        player.initializeTokens();
        player2.initializeTokens();
        sheep = player.getSheepToken();
        sheep2 = player2.getSheepToken();
        sheep.setBoard(gameboard);
        IFence fence = new Fence();
        sheep.setFence(fence);
        sheep2.setBoard(gameboard);
        sheep2.setFence(fence);
        cardFactory = new CardFactory();
    }

    /**
     * Test method for testInitializeToken, getNightmareToken() and methods.
     */
    @Test
    public void testInitializeToken() {
        /**
         * Test Case 1: NightmareToken is not initialized
         */
        assertNotNull(referenceCard.getNightmareToken());
        
        /**
         * Test Case 2: NightmareToken is initialized
         */
        referenceCard.initializeToken(gameboard);
        assertTrue(referenceCard.getNightmareToken() instanceof INightmareToken);
    }

    @Test
    public void testResolve(){
        referenceCard.initializeToken(gameboard);
        INightmareToken nToken = referenceCard.getNightmareToken();
        nToken.changeInPosition(1);
        sheep.changeInPosition(2);
        sheep2.changeInPosition(3);

        NightmareCard card = cardFactory.createNightmareCard("The Nightmare Jumps 2 Spaces Forward");
        /**
         * Test case 1: Valid movement. Since bump in ht enight jumps, sheep shouldn't be scared, only sheep2 should
         */
        referenceCard.resolve(card);
        assertEquals(sheep.getScares(), 0);
        assertEquals(sheep2.getScares(), 1);
        
    }

}
