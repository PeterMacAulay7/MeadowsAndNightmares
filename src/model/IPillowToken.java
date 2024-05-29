/**
 * Represents a PillowToken
 * Knows it's IPlayer
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface IPillowToken extends IToken {

    /**
     * gets the player who owns the IPillowToken
     * @return player associated with pillow token
     */
    public IPlayer getPlayer();

    /**
     * sets the player who owns the IPillowToken
     * @param player associated with pillow token
     */
    public void setPlayer(IPlayer player);

    /**
     * sets the token to the default position
     */
    public void setToDefaultPosition();


}
