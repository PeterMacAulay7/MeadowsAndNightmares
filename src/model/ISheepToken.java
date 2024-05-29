/**
 * Represents a sheep token
 * along with IToken responsibilities,
 * it can be scared.
 * it can get stuck at a spot on the board
 * it sets an instance of IFence to tell it when it has passed the fence on the board
 * determines if it is active on the board
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface ISheepToken extends IToken, Scareable, Stuckable{
    
    public IPlayer getPlayer();

    public int getScares();

    public boolean getIsStuck();

    /**
     * sets the ISheepTokens getIsStuck boolean
     * @param in -whether sheep token is stuck or not
     */
    public void setIsStuck(boolean in);

    public boolean getIsActive();

    /**
     * sets the token to the default position
     */
    public void setToDefaultPosition();

    /**
     * returns the instance of IGameBoard
     * @return gameboard associated with sheep token
     */
    public IGameBoard getBoard();

    /**
     * sets an instance of IFence
     * @param fence associated with sheep token
     */
    public void setFence(IFence fence);

    /**
     * sets the sheep's scares to the default value
     */
    public void resetScares();
}
