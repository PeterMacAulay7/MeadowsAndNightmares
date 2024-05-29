/**
 * Represents the Zzz token on the board
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class ZzzToken implements IZzzToken {

    private boolean infinite;
    private IPlayer player;

    public ZzzToken(IPlayer player) {
        this.infinite = false;
        this.player = player;
    }

    /**
     * Getter for the player who owns the Zzz token
     * @return the player who owns it
     */
    @Override
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * Setter for the player who owns the Zzz token
     * @param player who owns it
     */
    @Override
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    /**
     * Getter for whether the Zzz token is an infinite Zzz token or not
     * @return true if it is an infinite Zzz token, false otherwise.
     */
    @Override
    public boolean getInfinite() {
        return infinite;
    }

    /**
     * Setter for the infinite boolean instance variable
     * @param infinite value
     */
    @Override
    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

}
