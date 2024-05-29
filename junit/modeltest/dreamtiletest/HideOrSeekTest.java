/**
 * JUnit test class for Dreamtile, HideOrSeek
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Fence;
import model.GameBoard;
import model.IFence;
import model.IGameBoard;
import model.INightmareToken;
import model.IPlayer;
import model.IScoreBoard;
import model.ISheepToken;
import model.IWinkToken;
import model.IZzzToken;
import model.NightmareReferenceCard;
import model.Player;
import model.ScoreBoard;
import model.dreamtiles.DreamTile;
import model.dreamtiles.HideOrSeek;
import model.IRacingPhase;
import model.RacingPhase;
import model.IFirstSheep;
import model.FirstSheep;

public class HideOrSeekTest {
    @Test
    public void testActivateTile(){
        IPlayer player = new Player("name");
        player.initializeTokens();
        DreamTile tile  = new HideOrSeek();
        IWinkToken wink = player.getWinkToken();

        IScoreBoard scoreBoard = new ScoreBoard();
        wink.setBoard(scoreBoard);
        IFirstSheep firstSheep = new FirstSheep();
        ISheepToken sheep = player.getSheepToken();
        IRacingPhase racingPhase = new RacingPhase();
        racingPhase.setFirstSheep(firstSheep);
        IGameBoard gameBoard = new GameBoard();
        sheep.setBoard(gameBoard);
        IFence fence = new Fence();
        sheep.setFence(fence);
        fence.setPhase(racingPhase);
        sheep.changeInPosition(1);
        NightmareReferenceCard referenceCard = new NightmareReferenceCard(0);
        referenceCard.initializeToken(gameBoard);
        INightmareToken nightmare = referenceCard.getNightmareToken();
        player.setNightmareReferenceCard(referenceCard);

        nightmare.setBoard(gameBoard);
        nightmare.setFence(fence);


        nightmare.changeInPosition(1);
        IZzzToken zzz = player.removeZzzToken();

        //Test Case 1: There is no ZzzToken on the tile

        tile.activateTile(player);
        assertEquals(wink.getPositionOnBoard(), 0);
        assertEquals(sheep.getPositionOnBoard(), 1);
        
        //Test Case 2: There is ZzzToken on the tile, there is no NightmareToken on the board

        nightmare.setToDefaultPosition();
        tile.addToken(zzz);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(), 0);
        assertEquals(sheep.getPositionOnBoard(), 1);
        
        //Test Case 3: There is ZzzToken on the tile, NightmareToken is on the odd space-> gain 2winks
        tile.addToken(zzz);
        nightmare.changeInPosition(2);
        assertEquals(tile.getTokens().size(), 1);
        assertEquals(wink.getPositionOnBoard(), 0);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(), 2);
        assertEquals(sheep.getPositionOnBoard(), 1);
        
        //Test Case 4: There is ZzzToken on the tile, NightmareToken is on the even space-> move 2
        tile.addToken(zzz);
        nightmare.changeInPosition(1);
        assertEquals(tile.getTokens().size(), 1);
        tile.activateTile(player);
        assertEquals(tile.getTokens().size(), 0);
        assertEquals(wink.getPositionOnBoard(), 2);
        assertEquals(sheep.getPositionOnBoard(), 3);
    }
}
