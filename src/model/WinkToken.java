/**
 * Wink Token representation class
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class WinkToken implements IWinkToken {
    private final int DEFAULT_POSITION = 0;
    private int positionOnBoard;
    private IScoreBoard board;
    private final IPillowToken pillowToken;
    private IPlayer player;
    private final int  ZERO_SPACE = 0;

    public WinkToken(IPillowToken pillowToken, IPlayer player) {
        this.pillowToken = pillowToken;
        this.player = player;
    }


    /**
     * Changes the Token's position in the Board
     * @param positionalChange the amount of positional change from current location in the board
     * @return true if passed the fence, false otherwise.
     */
    @Override
    public boolean changeInPosition(int positionalChange) {
        // Lets remove it from the arraylist first
        board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        positionOnBoard += positionalChange - ZERO_SPACE;
        if (positionOnBoard > 39) {
            positionOnBoard = 39;
        } else if (positionOnBoard < 0) {
            positionOnBoard = 0;
        }

        // Then we add it to the appropriate position
        board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList
        return false;
    }

    /**
     * Getter for the position on the board
     * @return the current position on the board
     */
    @Override
    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Getter for the board
     * @return board associated with wink token
     */
    @Override
    public IBoard getBoard() {
        return board;
    }

    /**
     * Setter for the board
     * @param inBoard associated with wink token
     */
    @Override
    public void setBoard(IBoard inBoard) {
        this.board = (IScoreBoard) inBoard;
    }



    /**
     * Calculates if the pillow was reached or passed
     * @return true if passed or reached pillow, false otherwise
     */
    public boolean reachedOrPassedPillowToken() {
        return getPositionOnBoard() >= pillowToken.getPositionOnBoard();
    }

    /**
     * Getter for the player
     * @return player associated with wink token
     */
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * Setter for the player
     * @param player associated with wink token
     */
    @Override
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    /**
     * Sets token to default position
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
