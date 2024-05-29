/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;


import java.util.List;

public class SheepToken implements ISheepToken {
    private final int DEFAULT_POSITION = 10;
    private int scares;
    private boolean isStuck;
    private final IPlayer player;
    private IFence fence;
    private int positionOnBoard;
    private IGameBoard board;
    private final int DEFAULT_SCARES = 0;
    private final int MAX_POSTION_ON_BOARD = 9;
    private final int MINIMUM = 0;
    private final int CORRECTION_BY_ONE = 1;

    public SheepToken(IPlayer player) {
        this.player = player;
    }

    /**
     * Gets the player
     *
     * @return player associated with sheep token
     */
    @Override
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * Gets the number of times the token got scared
     *
     * @return scares 
     */
    @Override
    public int getScares() {
        return scares;
    }

    /**
     * Gets whether the token is stuck or not
     *
     * @return if sheep token is stuck or not
     */
    @Override
    public boolean getIsStuck() {
        return isStuck;
    }

    /**
     * Setter for isStuck
     *
     * @param in -determines if sheep token is stuck or not
     */
    @Override
    public void setIsStuck(boolean in) {
        this.isStuck = in;
    }

    /**
     * Getter for isOut
     *
     * @return if player is out or not
     */
    @Override
    public boolean getIsActive() {
        return player.getIsOut();
    }

    /**
     * Setter for the Fence
     *
     * @param fence associated with sheep token
     */
    @Override
    public void setFence(IFence fence) {
        this.fence = fence;
    }

    /**
     * Changes the Token's position in the Board
     *
     * @param positionalChange the amount of positional change from current location in the board
     * @return true if passed the fence, false otherwise.
     */
    @Override
    public boolean changeInPosition(int positionalChange) {
        if(!isStuck) {
            boolean passed = false;

            // Let's remove it from the arraylist first
            board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

            // Additional fix
            if (positionOnBoard == DEFAULT_POSITION) {
                positionOnBoard = MINIMUM;
                positionOnBoard += positionalChange - CORRECTION_BY_ONE;
            } else {
                positionOnBoard += positionalChange;
            }


            if (positionOnBoard > MAX_POSTION_ON_BOARD) {
                passed = true;
                fence.passedThrough(this);
                positionOnBoard = positionOnBoard % DEFAULT_POSITION;
            } else if (positionOnBoard < MINIMUM) {
                positionOnBoard = MINIMUM;
            }

            // Then we add it to the appropriate position
            board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList

            //if the sheep is not at the Default space
            if ((positionOnBoard != DEFAULT_POSITION)) {
                for (List<IToken> list : board.getBoard()) {
                    for (IToken token : list) {
                        if (token instanceof INightmareToken) {
                            if ((token.getPositionOnBoard() == positionOnBoard) && (token.getPositionOnBoard() != DEFAULT_POSITION)) {
                                incrementScare();
                            }
                        }
                    }
                }
            }

            return passed;
        }
        else{
            isStuck = false;
            return false;
        }
    }

    /**
     * Getter for current position on board
     *
     * @return position on board
     */
    @Override
    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Getter for the Board
     *
     * @return IGameBoard associated with sheep token
     */
    @Override
    public IGameBoard getBoard() {
        return board;
    }

    /**
     * Setter for board
     *
     * @param inBoard the memory reference to set the board to
     */
    @Override
    public void setBoard(IBoard inBoard) {
        this.board = (IGameBoard) inBoard;
    }

    /**
     * Increments the number of scares
     *
     * @implNote if the sheep token has 2 or more scares, also sets player is out and removes token from board.
     */
    @Override
    public void incrementScare() {
        scares++;

        if (scares >= 2) {
            player.setIsOut(true);
            setToDefaultPosition();
        }
    }

    /**
     * Decrements number of scares
     */
    @Override
    public void decrementScare() {
        scares--;
    }

    /**
     * Resets number of scares
     * TODO: this must be called whenever the racing phase ends
     */
    @Override
    public void resetScare() {
        scares = 0;
    }



    /**
     * Sets to default position
     */
    @Override
    public void setToDefaultPosition() {
        //removes the token from the board
        board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        positionOnBoard = DEFAULT_POSITION;

        // Then we add it to the appropriate position
        board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList
    }

    /**
     * sets the sheep's scares to the default value
     */
    public void resetScares(){
        scares = DEFAULT_SCARES;
    }

    /**
     * changes the state of isStuck variable
     */
    public void swapStuck(){
        if(isStuck == false){
            isStuck = true;
        }
        else{
            isStuck = false;
        }
    }

}
