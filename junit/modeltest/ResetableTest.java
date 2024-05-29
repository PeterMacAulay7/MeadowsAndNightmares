/**
 * JUnit class for Resetable Class
 * @author Dylan Kim
 * @version 0.5
 */
package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;



import model.CardDeck;
import model.Fence;
import model.GameBoard;
import model.ICardDeck;
import model.IFence;
import model.IGameBoard;
import model.INightmareToken;
import model.IPillowToken;
import model.IPlayer;
import model.IResetable;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.NightmareReferenceCard;
import model.Player;
import model.Resetable;
import model.ScoreBoard;

public class ResetableTest {
    private IResetable resetable;

    private ArrayList<IPlayer> players;

    private IPlayer player;
    private IPlayer player2;
    private IPlayer player3;
    private IPlayer player4;

    private IWinkToken wink;
    private IWinkToken wink2;
    private IWinkToken wink3;
    private IWinkToken wink4;

    private ISheepToken sheep;
    private ISheepToken sheep2;
    private ISheepToken sheep3;
    private ISheepToken sheep4;

    private IPillowToken pillow;
    private IPillowToken pillow2;
    private IPillowToken pillow3;
    private IPillowToken pillow4;

    private NightmareReferenceCard referenceCard;
    private INightmareToken nToken;

    private ICardDeck cardDeck;

    private IGameBoard gameBoard;

    private IScoreBoard scoreBoard;

    @BeforeEach
    public void setUp(){
        resetable = new Resetable();

        player = new Player("name");
        player2 = new Player("name2");
        player3 = new Player("name3");
        player4 = new Player("name4");
        
        players = new ArrayList<IPlayer>();
        players.add(player);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        
        IFence fence = new Fence();
        gameBoard = new GameBoard();
        scoreBoard = new ScoreBoard();

        player.initializeTokens();
        player2.initializeTokens();
        player3.initializeTokens();
        player4.initializeTokens();

        wink = player.getWinkToken();
        wink2 = player2.getWinkToken();
        wink3 = player3.getWinkToken();
        wink4 = player4.getWinkToken();
        wink.setBoard(scoreBoard);
        wink2.setBoard(scoreBoard);
        wink3.setBoard(scoreBoard);
        wink4.setBoard(scoreBoard);


        
        sheep = player.getSheepToken();
        sheep2 = player2.getSheepToken();
        sheep3 = player3.getSheepToken();
        sheep4 = player4.getSheepToken();
        sheep.setBoard(gameBoard);
        sheep2.setBoard(gameBoard);
        sheep3.setBoard(gameBoard);
        sheep4.setBoard(gameBoard);
        sheep.setFence(fence);
        sheep2.setFence(fence);
        sheep3.setFence(fence);
        sheep4.setFence(fence);

        pillow = player.getPillowToken();
        pillow2 = player2.getPillowToken();
        pillow3 = player3.getPillowToken();
        pillow4 = player4.getPillowToken();
        pillow.setBoard(scoreBoard);
        pillow2.setBoard(scoreBoard);
        pillow3.setBoard(scoreBoard);
        pillow4.setBoard(scoreBoard);

        referenceCard = new NightmareReferenceCard(1);
        referenceCard.initializeToken(gameBoard);
        nToken = referenceCard.getNightmareToken();

        cardDeck = new CardDeck(players, referenceCard);
    }

    @Test
    public void testReset() {


        sheep.changeInPosition(1);
        sheep2.changeInPosition(2);
        sheep3.changeInPosition(3);
        sheep4.changeInPosition(4);

        wink.changeInPosition(1);
        wink2.changeInPosition(2);
        wink3.changeInPosition(3);
        wink4.changeInPosition(4);

        pillow.changeInPosition(31);
        pillow2.changeInPosition(32);
        pillow3.changeInPosition(33);
        pillow4.changeInPosition(34);

        nToken.changeInPosition(9);

        resetable.reset(players, referenceCard, cardDeck);

        /**
         * Test 1: Check whether skeepTokens, NightmareToken and winkTokens go back to default position
         * Expected positions:
         *  -SheepToken & NightmareToken: 10
         *  -WinkToken: 0
         */
        assertEquals(sheep.getPositionOnBoard(), 10);
        assertEquals(sheep2.getPositionOnBoard(), 10);
        assertEquals(sheep3.getPositionOnBoard(), 10);
        assertEquals(sheep4.getPositionOnBoard(), 10);
        assertEquals(nToken.getPositionOnBoard(),10);

        assertEquals(wink.getPositionOnBoard(), 0);
        assertEquals(wink2.getPositionOnBoard(), 0);
        assertEquals(wink3.getPositionOnBoard(), 0);
        assertEquals(wink4.getPositionOnBoard(), 0);

        /**
         * Test 2: Check whether pillowTokens stay their current positions
         * Expected positions:
         * -pillow1: 31
         * -pillow2: 32
         * -pillow3: 33
         * -pillow4: 34
         */

        assertEquals(pillow.getPositionOnBoard(), 31);
        assertEquals(pillow2.getPositionOnBoard(), 32);
        assertEquals(pillow3.getPositionOnBoard(), 33);
        assertEquals(pillow4.getPositionOnBoard(), 34);

        // Test 3: Check whether card is created
        assert(cardDeck.getSheepCardDeck() !=null);
    }
}
