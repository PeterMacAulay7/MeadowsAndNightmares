/**
 * Represents a NightmareToken
 * Can Scare and ScareAdjacent
 * Sets an instance of IFence to be able to tell the fence when it crosses it
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface INightmareToken extends IToken {

    /**
     * sets the instance variable of IFence
     * @param fence associated with nightmare token
     */
    public void setFence(IFence fence);

    /**
     * scares a Scareable object
     * @param scareable object which can be scared
     */
    public void scare(Scareable scareable);

    /**
     * Scares all Scareable objects ahead and behind the nightmare
     * on the board
     */
    public void scareAdjacent();

    /**
     * sets the token to the default position
     */
    public void setToDefaultPosition();

}
