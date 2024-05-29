/**
 * JUnit class for Fence Class
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
import model.IFirstSheep;
import model.IGameBoard;
import model.IPillowToken;
import model.IPlayer;
import model.IRacingPhase;
import model.IResetable;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.NightmareToken;
import model.RacingPhase;
import model.Resetable;
import model.ScoreBoard;

public class FenceTest {
    private IPlayer player;
    private IPlayer player2;
    private IFirstSheep fs;
    private IPillowToken pillow;
    private IPillowToken pillow2;

    private ISheepToken sheep;
    private ISheepToken sheep2;

    private IWinkToken wink;
    private IWinkToken wink2;

    private Fence fence;

    private IRacingPhase racingPhase;

    private IGameBoard gameBoard;
    private IScoreBoard scoreBoard;

    @BeforeEach
    public void setUp(){
        gameBoard = new GameBoard();
        scoreBoard = new ScoreBoard();
        fs = new FirstSheep();
        ArrayList<String> names = new ArrayList<>(Arrays.asList("name1", "name2", "name3", "name4"));
        ArrayList<Integer> bedtimes = new ArrayList<>(Arrays.asList(1,2,3,4));
        fs.receivePlayersInfo(bedtimes, names);
        player = fs.getCurrentTurn();
        fs.incrementTurn();
        player2 = fs.getCurrentTurn();
        player.initializeTokens();
        player2.initializeTokens();

        pillow = player.getPillowToken();
        pillow.setBoard(scoreBoard);
        pillow2 = player2.getPillowToken();
        pillow2.setBoard(scoreBoard);

        wink = player.getWinkToken();
        wink.setBoard(scoreBoard);
        wink2 = player2.getWinkToken();
        wink2.setBoard(scoreBoard);

        sheep = player.getSheepToken();
        sheep.setBoard(gameBoard);
        sheep2 = player2.getSheepToken();
        sheep2.setBoard(gameBoard);

        fence = new Fence();

        racingPhase = new RacingPhase();
        racingPhase.setFirstSheep(fs);
        racingPhase.setFence(fence);
        fence.setPhase(racingPhase);
    }
    /**
     * Test method for nightmarePassed() and passThrough() method. 
     * @Note: Pretend that Player1 already called a night, but Player2 didn't
     *        Then, nightmarePassed() method should affect Player2 only, not Player1
     * 
     * @LRicker IFence do not offer all method that Fence has eventhough there is no other class that implements IFence 
     *          Due to that, even if I want to instantiate instance 'fence' as IFence to resolve DIP, I cannot do this 
     *          because I cannot use method that I should use.
     *          For testing purposes, the instance 'fence' is instantiated as 'Fence', not 'IFence'
     *          Furthermore, since this method is public but sheepPassed() is private, possibly there was miss implementation.
     */
    @Test
    public void testNightmarePassed() {
        fence.setPhase(racingPhase);
        player.setIsOut(true);
        player.setWinks(1);

        //both pillow should stay
        pillow.changeInPosition(-1);
        pillow2.changeInPosition(-2);

        //wink should stay, wink2 should become 0
        wink.changeInPosition(1);
        wink2.changeInPosition(2);

        /**
         * sheep should stay, sheep2 should become 10
         * @Note: Yes, it is kind of wierd that player called a night, but still sheep is on the board. This is purposely done
         * to really check that sheep is not affected by nightmarePassed
         */
        sheep.changeInPosition(1);
        sheep2.changeInPosition(2);

        fence.passedThrough(new NightmareToken());
        
        assertEquals(pillow.getPositionOnBoard(), 39);
        assertEquals(pillow2.getPositionOnBoard(), 38);

        assertEquals(player.getWinks(), 1);
        assertEquals(player2.getWinks(), 0);
        assertEquals(player.getIsOut(), true);
        assertEquals(player2.getIsOut(), true);

        assertEquals(wink.getPositionOnBoard(), 1);
        assertEquals(wink2.getPositionOnBoard(), 2);
        
        assertEquals(sheep.getPositionOnBoard(),1);
        assertEquals(sheep2.getPositionOnBoard(), 2);
    }
    /**
     * Test method for setNumberOfIsOutPlayers() method
     * @LRicker: At this point, the insance numberOfIsOutPlayers is not being used(and not even implemented yet), this method is not testable
     */
    @Test
    public void testSetNumberOfIsOutPlayers() {
        
        
    }

    /**
     * Test method of SheepPassed() and passesThrough() methods
     */
    @Test
    public void testSheepPassed() {
        fence.setPhase(racingPhase);

        player.setWinks(1);

        pillow.changeInPosition(-1);
        wink.changeInPosition(1);
        sheep.changeInPosition(1);


        fence.passedThrough(sheep);
        assertEquals(pillow.getPositionOnBoard(), 39);

        assertEquals(player.getWinks(), 6);

        assertEquals(wink.getPositionOnBoard(), 6);
        
        assertEquals(sheep.getPositionOnBoard(),1);
    }
}
