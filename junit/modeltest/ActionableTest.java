/**
 * JUnit class for Actionable Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Actionable;
import model.CardBuilder;
import model.Fence;
import model.GameBoard;
import model.IActionable;
import model.ICardBuilder;
import model.IFence;
import model.IGameBoard;
import model.INightmareCardBuilder;
import model.INightmareToken;
import model.IPillowToken;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.NightmareCard;
import model.NightmareCardBuilder;
import model.NightmareReferenceCard;
import model.Player;
import model.ScoreBoard;
import model.SheepCard;

public class ActionableTest {

    private IActionable action;
    private NightmareReferenceCard wolf;
    private INightmareCardBuilder nBuilder;
    private IGameBoard gameBoard;
    private IScoreBoard scoreBoard;
    private IPlayer player;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private INightmareToken nightmare;
    
    @BeforeEach
    public void setUp(){
        gameBoard = new GameBoard();
        scoreBoard = new ScoreBoard();
        action = new Actionable();
        nBuilder = new NightmareCardBuilder();
        wolf = new NightmareReferenceCard(1);
        wolf.initializeToken(gameBoard);
        nightmare = wolf.getNightmareToken();
        nightmare.setBoard(gameBoard);
        wolf.getNightmareToken().setBoard(gameBoard);
        player = new Player("name");
        player.initializeTokens();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        wink.setBoard(scoreBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);
        sheep.setBoard(gameBoard);
        wink.setBoard(scoreBoard);
        pillow.setBoard(scoreBoard);
        

    }

    /**
     * Test method for actionWithDreamTile.
     * @Note: Since this method is just calling activateTile target, the actual result of each Dreamtile
     * Will be tested at each test classes. Instead, this method will check whether parameteres are valid
     * 
     */
    @Test
    public void testActionWithDreamTile() {
        try {
            action.actionWithDreamTile(null, null);
        } catch (NullPointerException npe) {
            //pass
        }
    }

    /**
     * Test method for actionwithSheepCard
     * @Note: I could not fully understand this method, so I could not write test code for every possible cases.
     */
    @Test
    public void testActionWithSheepCard() {
        ICardBuilder cardBuilder = new CardBuilder();
        /**
         * Test case 1: move sheepToken and it passes the fence
         */
        SheepCard moveSheep = cardBuilder.withMovableDistance(3)
                    .withCatchableZzz(1)
                    .withHasAndStatement(false)
                    .withMinimumPlayersRequired(2)
                    .withDescription("Move 3 Spaces or Catch 1 Zzz")
                    .buildSheepCard();
        
        sheep.changeInPosition(9);
        action.actionWithSheepCard(moveSheep, player, 1);
        assertEquals(sheep.getPositionOnBoard(), 2);
        assertEquals(player.getWinks(), 5);
        assertEquals(wink.getPositionOnBoard(), 5);

        
    }

    /**
     * Test method for setIndex
     */
    @Test
    public void testSetIndex() {
        /**
         * Test case 1 : assigning invalid value
         */
        try {
            action.setIndex(10);
        } catch (IllegalArgumentException e) {
            /**
             * Test case 2 : assigning valid value
             */
            try {
                action.setIndex(9);
            } catch (IllegalArgumentException iae) {
                fail("it is valid index. Should not have thrown IllegalArgumentException");
            }
        }
    }

    /**
     * Test method for setNumZzzs
     * @LRicker: I could not fully understand the reason why this method should exist as a separate method. I think it could be assigned as a parameter, for each method.
     */
    @Test
    public void testSetNumZzzs() {
        /**
        * Test case 1 : assigning negative value
        */
       try {
           action.setNumZzzs(-1);
       } catch (IllegalArgumentException e) {
           /**
            * Test case 2 : assigning valid value
            */
           try {
               action.setNumZzzs(9);
           } catch (IllegalArgumentException iae) {
               fail("it is valid index. Should not have thrown IllegalArgumentException");
           }
       }
    }
}
