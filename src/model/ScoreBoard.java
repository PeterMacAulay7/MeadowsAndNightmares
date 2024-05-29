/**
 * This class contains a List of List of ITokens. Serves as the Score Board for the game.
 *
 * @implSpec Each List in BoardList may or may not contain an IToken. The ScoreBoardController can access this Board and pre-process it so that the ScoreBoardView can output it appropriately.
 * This score board will always be of size 10
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;


import java.util.ArrayList;
import java.util.List;

public class ScoreBoard implements IScoreBoard {
    private static final int SIZE = 40;
    private final List<List<IToken>> boardList;

    /**
     * creates the boardList
     */
    public ScoreBoard() {
        boardList = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            boardList.add(new ArrayList<IToken>());
        }
    }

    /**
     * returns the boardList
     * @return lists of ITokens as spaces on the board
     */
    @Override
    public List<List<IToken>> getBoard() {
        return boardList;
    }


}
