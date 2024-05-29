/**
 * Represents an Zzz token
 * has an IPlayer who owns it
 * can be set to an infinite Zzz via boolean
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface IZzzToken {

    /**
     * returns the instance of IPlayer
     * @return player associated with Zzz token
     */
    public IPlayer getPlayer();

    /**
     * sets and instance of IPlayer to be the owner of the IZzzTOken
     * @param player associated with Zzz token
     */
    public void setPlayer(IPlayer player);

    /**
     * returns getInfinite
     * @return if Zzz token is infinite
     */
    public boolean getInfinite();

    /**
     * sets getInfinite
     * @param infinite -whether Zzz token is infinite
     */
    public void setInfinite(boolean infinite);
}
