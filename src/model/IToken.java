/**
 * Abstract class for all tokens
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public interface IToken {

    /**
     * allows position to be changed positively or negatively by the given value
     * removes IToken from the board and adds it back with the new position
     * @param positionalChange
     * @return
     */
    public boolean changeInPosition(int positionalChange);

    /**
     * returns the IToken's position on the board
     * @return token's position on board
     */
    public int getPositionOnBoard();

    /**
     * returns the instance of IBoard
     * @return token's board associated with it
     */
    public IBoard getBoard();

    /**
     * sets an instance of IBoard
     * this will be the IToken's board they use
     * @param inBoard associated with token
     */
    public void setBoard(IBoard inBoard);

    /**
     * sets the token to the default position
     */
    public void setToDefaultPosition();




}
