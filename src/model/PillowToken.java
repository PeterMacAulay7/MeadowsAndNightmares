/**
 * Represents a PillowToken
 * Knows it's IPlayer
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class PillowToken implements IPillowToken {
    private final int DEFAULTPOSITION = 39;
    private IPlayer player;
    private int positionOnBoard;
    //private String color;
    private IBoard board;

    /**
     * sets the IPlayer and color of PillowToken
     * @param player associated with pillow token
     */
    public PillowToken(IPlayer player) {
        this.player = player;
        //this.color = player.getColor();
    }

    /**
     * changes positionOnBoard by positionalChange
     * applies change to board
     * @param positionalChange of pillow token
     * @return false
     */
    @Override
    public boolean changeInPosition(int positionalChange) {
        // Let's remove it from the arrayList first
        board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        // Update position taking into account wrap-around behavior
        positionOnBoard = (positionOnBoard + positionalChange) % board.getBoard().size();
        if (positionOnBoard < 0) {
            positionOnBoard += board.getBoard().size();
        }

        // Then we add it to the appropriate position
        board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList
        return false;
    }

    /**
     * returns positionOnBoard
     * @return position on board
     */
    @Override
    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * returns the instance of IBoard
     * @return board associated with pillow token
     */
    @Override
    public IBoard getBoard() {
        return board;
    }

    /**
     * sets an instance of IBoard
     * @param inBoard -board associated with pillow token
     */
    @Override
    public void setBoard(IBoard inBoard) {
        this.board = inBoard;
    }

    /**
     * returns the instance of IPlayer
     * @return player associated with pillow token
     */
    @Override
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * sets an instanc of IPlayer
     * @param player associated with pillow token
     */
    @Override
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    /**
     * returns the color of PillowToken
     * @return color of pillow token
     */
 //   @Override
 //   public String getColor() {
 //       return color;
 //   }

    /**
     * sets the color of PillowToken
     * @param color of pillow token
     */
 //   @Override
 //   public void setColor(String color) {
 //       this.color = color;
 //   }

    /**
     * sets positionOnBoard to DEAFULT_POSITION
     * Applies changes to board
     */
    @Override
    public void setToDefaultPosition() {
        //removes the token from the board
        board.getBoard().get(positionOnBoard).remove(this); // remove token from inner arrayList

        positionOnBoard = DEFAULTPOSITION;

        // Then we add it to the appropriate position
        board.getBoard().get(positionOnBoard).add(this); // add token to inner arrayList
    }
}
