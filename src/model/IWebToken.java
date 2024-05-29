/**
 * Represents a web token
 * captures sheep tokens on the board by making them Stuck
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface IWebToken extends IToken {

    /**
     * takes in a Stuckable object and sets their getIsStuck boolean
     * @param stuckable object 
     */
    public void capture(Stuckable stuckable);

    /**
     * sets the token to the default position
     */
    public void setToDefaultPosition();

}
