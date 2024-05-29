/**
 * JUnit class for ReferenceTile Class
 * @author Dylan Kim
 * @version 0.5
 */


package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


import model.IPillowToken;
import model.IPlayer;
import model.IReferenceTile;
import model.IScoreBoard;
import model.IWinkToken;
import model.Player;
import model.ReferenceTile;
import model.ScoreBoard;

public class ReferenceTileTest {
    private IReferenceTile referenceTile;
    private ArrayList<IPlayer> players;

    private IPlayer player;
    private IPlayer player2;
    private IPlayer player3;
    private IPlayer player4;

    private IWinkToken wink;
    private IWinkToken wink2;
    private IWinkToken wink3;
    private IWinkToken wink4;
    private IPillowToken pillow;
    private IPillowToken pillow2;
    private IPillowToken pillow3;
    private IPillowToken pillow4;

    @BeforeEach
    public void setUp() {
        referenceTile = new ReferenceTile();

        player = new Player("name");
        player2 = new Player("name2");
        player3 = new Player("name3");
        player4 = new Player("name4");

        player.initializeTokens();
        player2.initializeTokens();
        player3.initializeTokens();
        player4.initializeTokens();

        wink = player.getWinkToken();
        wink2 = player2.getWinkToken();
        wink3 = player3.getWinkToken();
        wink4 = player4.getWinkToken();

        pillow = player.getPillowToken();
        pillow2 = player2.getPillowToken();
        pillow3 = player3.getPillowToken();
        pillow4 = player4.getPillowToken();

        IScoreBoard scoreBoard = new ScoreBoard();

        wink.setBoard(scoreBoard);
        wink2.setBoard(scoreBoard);
        wink3.setBoard(scoreBoard);
        wink4.setBoard(scoreBoard);
        wink.setToDefaultPosition();
        wink2.setToDefaultPosition();
        wink3.setToDefaultPosition();
        wink4.setToDefaultPosition();

        pillow.setBoard(scoreBoard);;
        pillow2.setBoard(scoreBoard);;
        pillow3.setBoard(scoreBoard);;
        pillow4.setBoard(scoreBoard);
        pillow.setToDefaultPosition();
        pillow2.setToDefaultPosition();
        pillow3.setToDefaultPosition();
        pillow4.setToDefaultPosition();


    }

    /**
     * Test method for calculateDistanceToPillow() method
     * 
     */
    @Test
    public void testCalculateDistanceToPillow() {

        IPillowToken pillow = player2.getPillowToken();

        // Test Case 1: comparing pillowToken and winkToken from different players,
        // expect to throw IllegalArgumentException
        try {
            referenceTile.calculateDistanceToPillow(player);
        } catch (IllegalArgumentException iae) {
            wink.changeInPosition(10);
            pillow.changeInPosition(20);

            // Test Case 2: winkToken locates at 10 and pillowToken locates at 20, Expect:
            // 10
            assertEquals(referenceTile.calculateDistanceToPillow(wink.getPlayer()), 10);

            // Test Case 3: winkToken and pillowToken locate at the same position, Expect: 0
            wink.changeInPosition(20);
            assertEquals(referenceTile.calculateDistanceToPillow(wink.getPlayer()), 0);

            // Test Case 4: winkToken locates at 30 and pillowToken locates at 20, Expect: -10
            wink.changeInPosition(30);
            assertEquals(referenceTile.calculateDistanceToPillow(wink.getPlayer()), -10);
        }
    }

    /**
     * Test method for movePillow() if there is only one player, specifically
     */
    @Test
    public void testMovePillowSingle() {
        ArrayList<IPlayer> players = new ArrayList<>();
        try {
            players.add(player);
        } catch (NullPointerException npe) {
            fail("ArrayList players is null");
        }

        referenceTile.setPlayersList(players);

        // Test Case 1: the player wake up, expect zero movement
        player.setIsOut(true);
        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 39);

        // Test Case 2: the player didn't wake up, expect 0 movement
        player.setIsOut(false);
        wink.changeInPosition(10);
        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 39);
    }

    /**
     * Test method for movePillow() if there are two players, specifically
     */
    @Test
    public void testMovePillowTWoPlayers() {
        ArrayList<IPlayer> players = new ArrayList<>();
        try {
            players.add(player);
        } catch (NullPointerException npe) {
            fail("ArrayList players is null");
        }
        players.add(player2);

        /**
         * Test case 1: Both players didn't wake up
         * Expected movement:
         * -Player1: 5
         * -Player2: 8
         */
        referenceTile.setPlayersList(players);
        wink.changeInPosition(1);
        wink2.changeInPosition(2);

        referenceTile.movePillows();

        assertEquals(pillow.getPositionOnBoard(), 31);
        assertEquals(pillow2.getPositionOnBoard(), 34);

        /**
         * Test case 1: One player has waken up
         * Expected movement:
         * -Player1: 8
         * -Player2: 3
         */
        wink.changeInPosition(5);
        wink2.changeInPosition(0);
        player2.setIsOut(true);

        pillow.changeInPosition(40);
        pillow2.changeInPosition(40);

        referenceTile.movePillows();

        assertEquals(pillow.getPositionOnBoard(), 23);
        assertEquals(pillow2.getPositionOnBoard(), 31);

        /**
         * Test case 1: Both players have waken up
         * Expected movement:
         * -Player1: 3
         * -Player2: 3
         */
        wink.changeInPosition(0);
        wink2.changeInPosition(0);
        player2.setIsOut(true);
        player.setIsOut(true);

        pillow.changeInPosition(40);
        pillow2.changeInPosition(40);

        referenceTile.movePillows();

        assertEquals(pillow.getPositionOnBoard(), 20);
        assertEquals(pillow2.getPositionOnBoard(), 28);        
    }
    
    /**
     * Test method for movePillow() if there are three players, specifically
     */
    @Test
    public void testMovePillowThreePlayers() {
        ArrayList<IPlayer> players = new ArrayList<>();

        try {
            players.add(player);
        } catch (NullPointerException npe) {
            fail("ArrayList players is null");
        }
        players.add(player2);
        players.add(player3);
        referenceTile.setPlayersList(players);

        /**
        * Test case 1: All didn't wake up
        * Expected movement:
        * -Player1: 5
        * -Player2: 7
        * -Player3: 10
        */
        wink.changeInPosition(1);
        wink2.changeInPosition(2);
        wink3.changeInPosition(3);

        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 29);
        assertEquals(pillow2.getPositionOnBoard(), 32);
        assertEquals(pillow3.getPositionOnBoard(), 34);

        /**
        * Test case 2: Player3 and Player2 have waken up
        * Expected movement:
        * -Player1: 10
        * -Player2: 3
        * -Player3: 3
        */
        wink.changeInPosition(1);
        wink2.changeInPosition(0);
        wink3.changeInPosition(0);

        pillow.changeInPosition(40);
        pillow2.changeInPosition(40);
        pillow3.changeInPosition(40);
        player3.setIsOut(true);
        player2.setIsOut(true);

        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 19);
        assertEquals(pillow2.getPositionOnBoard(), 29);
        assertEquals(pillow3.getPositionOnBoard(), 31);

        /**
        * Test case 3: All players have waken up
        * Expected movement:
        * -Player1: 3
        * -Player2: 3
        * -Player3: 3
        */
        wink.changeInPosition(0);
        wink2.changeInPosition(0);
        wink3.changeInPosition(0);

        pillow.changeInPosition(40);
        pillow2.changeInPosition(40);
        pillow3.changeInPosition(40);
        player3.setIsOut(true);
        player2.setIsOut(true);
        player.setIsOut(true);

        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 16);
        assertEquals(pillow2.getPositionOnBoard(), 26);
        assertEquals(pillow3.getPositionOnBoard(), 28);
    }

    /**
     * Test method for movePillow() if there are three players, specifically
     */
    @Test
    public void testMovePillowSingleFourPlayers() {
        ArrayList<IPlayer> players = new ArrayList<>();

        try {
            players.add(player);
        } catch (NullPointerException npe) {
            fail("ArrayList players is null");
        }
        players.add(player2);
        players.add(player3);
        players.add(player4);
        referenceTile.setPlayersList(players);

        /**
        * Test case 1: All didn't wake up
        * Expected movement:
        * -Player1: 5
        * -Player2: 6
        * -Player3: 8
        * -Player4: 10
        */
        wink.changeInPosition(1);
        wink2.changeInPosition(2);
        wink3.changeInPosition(3);
        wink4.changeInPosition(4);

        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 29);
        assertEquals(pillow2.getPositionOnBoard(), 31);
        assertEquals(pillow3.getPositionOnBoard(), 33);
        assertEquals(pillow4.getPositionOnBoard(), 34);

        /**
        * Test case 2: Player3 and Player2 have waken up
        * Expected movement:
        * -Player1: 8
        * -Player2: 3
        * -Player3: 3
        * -player4: 10
        */
        wink.changeInPosition(1);
        wink2.changeInPosition(0);
        wink3.changeInPosition(0);
        wink4.changeInPosition(2);

        pillow.changeInPosition(40);
        pillow2.changeInPosition(40);
        pillow3.changeInPosition(40);
        pillow4.changeInPosition(40);
        player3.setIsOut(true);
        player2.setIsOut(true);

        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 19);
        assertEquals(pillow2.getPositionOnBoard(), 28);
        assertEquals(pillow3.getPositionOnBoard(), 30);
        assertEquals(pillow4.getPositionOnBoard(), 29);

        /**
        * Test case 3: Player1,2,3 have waken up
        * Expected movement:
        * -Player1: 3
        * -Player2: 3
        * -Player3: 3
        * -player4: 10
        */
        wink.changeInPosition(0);
        wink2.changeInPosition(0);
        wink3.changeInPosition(0);
        wink4.changeInPosition(1);

        pillow.changeInPosition(40);
        pillow2.changeInPosition(40);
        pillow3.changeInPosition(40);
        pillow4.changeInPosition(40);
        player3.setIsOut(true);
        player2.setIsOut(true);
        player.setIsOut(true);

        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 16);
        assertEquals(pillow2.getPositionOnBoard(), 25);
        assertEquals(pillow3.getPositionOnBoard(), 27);
        assertEquals(pillow4.getPositionOnBoard(), 23);

        /**
        * Test case 4: All Players have waken up
        * Expected movement:
        * -Player1: 3
        * -Player2: 3
        * -Player3: 3
        * -player4: 3
        */
        wink.changeInPosition(0);
        wink2.changeInPosition(0);
        wink3.changeInPosition(0);
        wink4.changeInPosition(0);

        pillow.changeInPosition(40);
        pillow2.changeInPosition(40);
        pillow3.changeInPosition(40);
        pillow4.changeInPosition(40);
        player3.setIsOut(true);
        player2.setIsOut(true);
        /**
        * Test case 3: Player1,2,3 have waken up
        * Expected movement:
        * -Player1: 3
        * -Player2: 3
        * -Player3: 3
        * -player4: 10
        */
        wink.changeInPosition(0);
        wink2.changeInPosition(0);
        wink3.changeInPosition(0);
        wink4.changeInPosition(1);

        pillow.changeInPosition(40);
        pillow2.changeInPosition(40);
        pillow3.changeInPosition(40);
        pillow4.changeInPosition(40);
        player4.setIsOut(true);
        player3.setIsOut(true);
        player2.setIsOut(true);
        player.setIsOut(true);


        referenceTile.movePillows();
        assertEquals(pillow.getPositionOnBoard(), 13);
        assertEquals(pillow2.getPositionOnBoard(), 22);
        assertEquals(pillow3.getPositionOnBoard(), 24);
        assertEquals(pillow4.getPositionOnBoard(), 20);
    }

    /**
     * Test for SetPlayerList() anmd getNumPlayers() methods
     * @Note: It seems that this method should check whether the size of the
     *  ArrayList 'players' is valid or not(between 1~4). However, this process will be
     * executed in the FirstSheep class, thus this class is assuming that the 'players' is valid
     * ArrayList.   
     */
    @Test
    public void testSetPlayersList() {
        ArrayList<IPlayer> players = new ArrayList<>();

        try {
            players.add(player);
        } catch (NullPointerException npe) {
            fail("ArrayList players is null");
        }
        //Test Case 1: There is only one player
        referenceTile.setPlayersList(players);
        assertEquals(referenceTile.getNumPlayers(), 1);

        //Test Case 2: There are two players
        players.add(player2);
        referenceTile.setPlayersList(players);
        assertEquals(referenceTile.getNumPlayers(), 2);

        //Test Case 3: There are three players
        players.add(player3);
        referenceTile.setPlayersList(players);
        assertEquals(referenceTile.getNumPlayers(), 3);

        //Test Case 4: There are four players
        players.add(player4);
        referenceTile.setPlayersList(players);
        assertEquals(referenceTile.getNumPlayers(), 4);
    }
}
