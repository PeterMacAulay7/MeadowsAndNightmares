/**
 * Represents a Web Token
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class WebToken implements IWebToken {
    private final int DEFAULT_POSITION = 10;

    private int positionOnBoard;

    private IBoard board;

    /**
     * Changes the Token's position in the Board
     * @param positionalChange the amount of positional change from current location in the board
     * @return true if passed the fence, false otherwise.
     * @implNote this should not be relevant for passing fence, so may have to return false all the time, as this token
     * has no special behaviour when it passes the fence.
     */
    @Override
    public boolean changeInPosition(int positionalChange) {
        // Let's remove it from the arraylist first
        board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        if (positionOnBoard == DEFAULT_POSITION) {
            positionOnBoard += positionalChange - 1;
        } else {
            positionOnBoard += positionalChange;
        }

        if (positionOnBoard > 9) {
            positionOnBoard = positionOnBoard % DEFAULT_POSITION;
        } else if (positionOnBoard < 0) {
            positionOnBoard = 0;
        }

        // Then we add it to the appropriate position
        board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList
        return false;
    }

    /**
     * Gets the position on the board
     * @return position on board
     */
    @Override
    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Getter for the board
     * @return board associated with web token
     */
    @Override
    public IBoard getBoard() {
        return board;
    }

    /**
     * Setter for the board
     * @param inBoard assoicated with web token
     */
    @Override
    public void setBoard(IBoard inBoard) {
        this.board = inBoard;
    }

    /**
     * Captures a Stuckable token
     * @param stuckable the token that implements stuckable
     */
    @Override
    public void capture(Stuckable stuckable){
        stuckable.swapStuck();
    }

    /**
     * Resets to default position
     */
    @Override
    public void setToDefaultPosition() {
        //removes the token from the board
        board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        positionOnBoard = DEFAULT_POSITION;

        // Then we add it to the appropriate position
        board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList
    }
}
