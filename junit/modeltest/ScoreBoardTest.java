/**
 * JUnit class for ScoreBoard Class
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.IScoreBoard;
import model.ScoreBoard;

public class ScoreBoardTest {
    @Test
    public void testGetBoard() {
        IScoreBoard scoreBoard = new ScoreBoard();
        assert(scoreBoard.getBoard() != null);
        //In ScoreBoard, there are tile from 0 to 40, thus total size should be 41
        assertEquals(scoreBoard.getBoard().size(), 40);
    }
}
