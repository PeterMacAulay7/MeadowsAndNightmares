/**
 * Represents a wink token
 * has an IPlayer who owns it
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface IWinkToken extends IToken {

    /**
     * determines if the IWinkToken has reached the IPillowToken with the same IPlayer
     * @return whether wink token has passed the pillow token
     */
    public boolean reachedOrPassedPillowToken();

    /**
     * returns the instance of IPlayer
     * @return the wink tokens player
     */
    public IPlayer getPlayer();

    /**
     * sets an instance of IPlayer as an owner of the IWinkToken
     * @param player associated with wink token
     */
    public void setPlayer(IPlayer player);

    /**
     * sets the token to the default position
     */
    public void setToDefaultPosition();

}
