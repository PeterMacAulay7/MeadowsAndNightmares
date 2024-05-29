/**
 * Represents a NightmareToken
 * Can Scare and ScareAdjacent
 * Sets an instance of IFence to be able to tell the fence when it crosses it
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.Iterator;
import java.util.List;

public class NightmareToken implements INightmareToken {
    private final int DEFAULT_POSITION = 10;
    private int positionOnBoard;
    private IBoard board;
    private IFence fence;
    private final IScare scare;
    private boolean madeFirstMove;
    private final int MINIMUM = 0;
    private final int MAX_ON_POSITION_ON_BOARD = 9;
    private final int CORRECTION_BY_ONE = 1;

    /**
     * creates new Scare object
     */
    public NightmareToken() {
        this.scare = new Scare();
    }

    /**
     * set an instance of IFence
     * @param fence associated with nightmare token
     */
    @Override
    public void setFence(IFence fence) {
        this.fence = fence;

    }

    /**
     * changes positionOnBoard by positionalChange.
     * Applies changes to board.
     * Tells fence when it has passed it
     * @param positionalChange of nightmare token
     * @return if nightmare token passed the fence
     */
    @Override
    public boolean changeInPosition(int positionalChange) {
        boolean passed = false;
        // Let's remove it from the arraylist first
        board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        if (positionOnBoard == DEFAULT_POSITION) {
            positionOnBoard = MINIMUM;
            positionOnBoard += positionalChange - CORRECTION_BY_ONE;
        } else {
            positionOnBoard += positionalChange;
        }

        if (positionOnBoard > MAX_ON_POSITION_ON_BOARD && madeFirstMove) {
            passed = true;
            fence.passedThrough(this);
            positionOnBoard = positionOnBoard % DEFAULT_POSITION;
        } else if (positionOnBoard < MINIMUM) {
            positionOnBoard = MINIMUM;
        }

        // Then we add it to the appropriate position
        board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList
        madeFirstMove = true;
        return passed;

    }

    /**
     * returns positionOnBoard
     * @return position on the board
     */
    @Override
    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * returns the instance of IBoard
     * @return board associated with nightmare token
     */
    @Override
    public IBoard getBoard() {
        return board;
    }

    /**
     * sets an instance of IBoard
     * @param inBoard -associated with nightmare token
     */
    @Override
    public void setBoard(IBoard inBoard) {
        this.board = inBoard;
    }

    /**
     * scares a Scareable object
     * @param scareable object to scare
     */
    public void scare(Scareable scareable) {
        if (getPositionOnBoard() != DEFAULT_POSITION) {
            scare.scareScareable(scareable);
        }
    }

    /**
     * scares all the Scareable objects on the spaces ahead and behind the nightmare
     * on the GameBoard
     */
    public void scareAdjacent() {
        //PRECONDITION: Must not be at the 10th position (inclusive of 0)
        if (positionOnBoard != 10) {

            //gets the board
            List<List<IToken>> boardTokens = getBoard().getBoard();

            //gets the space nightmareToken is on
            List<IToken> space = boardTokens.get(getPositionOnBoard());
            //for each IToken on the space the nightmare is on
            if(space != null){
                for (IToken token : space) {
                    //if they are scareable
                    if (token instanceof Scareable scareable) {
                        //scares the IToken
                        scare(scareable);
                    }
                }
            }

            //gets the space behind the nightmare
            List<IToken> spaceBehind = null;
            if(getPositionOnBoard()-1 > 0){
                spaceBehind = boardTokens.get(getPositionOnBoard() - 1);
            }
            else{
                spaceBehind = boardTokens.get(9);
            }
            //for each IToken on the space behind the nightmare
            if (spaceBehind != null) {
                Iterator<IToken> iterator = spaceBehind.iterator();
                while (iterator.hasNext()) {
                    IToken token = iterator.next();
                    //if they are scareable
                    if (token instanceof Scareable scareable) {
                        //scares the IToken
                        scare(scareable);
                    }
                }
            }

            //gets the space ahead of the nightmare
            List<IToken> spaceAhead = boardTokens.get((getPositionOnBoard() + 1) % 10);
            //for each IToken on the space behind the nightmare
            if(spaceAhead != null){
                for (IToken token : spaceAhead) {
                    //if they are scareable
                    if (token instanceof Scareable scareable) {
                        //scares the IToken
                        scare(scareable);
                    }
                }
            }
        }
            
    }


    /**
     * sets NightmareToken to the DEFAULT_POSITION.
     * Applies changes to board
     */
    @Override
    public void setToDefaultPosition() {
        madeFirstMove = false;


        for (int i = 0; i < board.getBoard().size(); i++) {
            List<IToken> tokens = board.getBoard().get(i);
            tokens.remove(this);

        }

        //board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        //removes the token from the board
        if (positionOnBoard > 11) {
            positionOnBoard = positionOnBoard % DEFAULT_POSITION;
        }

        //board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        positionOnBoard = DEFAULT_POSITION;

        // Then we add it to the appropriate position
        board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList


    }

}
