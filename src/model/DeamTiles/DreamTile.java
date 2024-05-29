/**
 * Abstract class for DreamTiles
 * has a description
 * ArrayList of IZzzTokens
 * InitialZzzsRequired
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model.dreamtiles;

import model.IFirstSheep;
import model.IPlayer;
import model.IScare;
import model.IZzzToken;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class DreamTile {
    private String description;
    private int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private boolean isInfinite;
    private IScare scare;
    private IFirstSheep firstSheep;

    protected DreamTile() {
        tokens = new ArrayList<IZzzToken>();
        description = "";
    }


    /**
     * activate tile
     * @param player activating tile
     */
    abstract public void activateTile(IPlayer player);

    /**
     * returns ZzzTokens on the DreamTile
     *
     * @return tokens on DreamTile
     */
    public ArrayList<IZzzToken> getTokens() {
        return tokens;
    }

    /**
     * return IFirstSheep
     *
     * @return IFirstSheep
     */
    public IFirstSheep getFirstSheep() {
        return firstSheep;
    }

    /**
     * set an instance of IFirstSheep so that DreamTile can access the player turn sequence if needed
     * @param firstSheep
     */
    public void setFirstSheep(IFirstSheep firstSheep) {
        this.firstSheep = firstSheep;
    }

    /**
     * returns boolean for if the DreamTile is and infinite Zzzs DreamTile or not
     *
     * @return if DreamTile is infinite
     */
    public boolean getIsInfinite() {
        return isInfinite;
    }

    /**
     * returns the initial number of Zzzs required for a player to use the DreamTile
     *
     * @return initial Zzzs required to use DreamTile
     */
    public int getInitialZzzsRequired() {
        return initialZzzsRequired;
    }

    /**
     * returns the description of the DreamTile
     *
     * @return description of DreamTile
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description of the DreamTile
     * @param description of DreamTile
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the instance of IScare
     *
     * @return IScare associated with DreamTile
     */
    public IScare getScare() {
        return scare;
    }

    /**
     * sets an instance of IScare so that DreamTile can scare if it needs to
     * @param scare associated with DreamTile
     */
    public void setScare(IScare scare) {
        this.scare = scare;
    }

    /**
     * returns IZzzTokens that a player has on a DreamTIle
     *
     * @param player accessing their DreamTiles
     * @return Zzzs player has on DreamTile
     */
    public ArrayList<IZzzToken> getPlayerZzzs(IPlayer player) {
        ArrayList<IZzzToken> playerZzzs = new ArrayList<>();
        for (IZzzToken zzz : tokens) {
            if (zzz.getPlayer().equals(player)) {
                playerZzzs.add(zzz);
            }
        }
        return playerZzzs;
    }

    /**
     * adds a IZzzToken to the tokens ArrayList
     *
     * @param zzzToken to be added to DreamTile
     */
    public void addToken(IZzzToken zzzToken) {
        tokens.add(zzzToken);
    }

    /**
     * removes a player's IZzToken from the tokens ArrayList for the cost of one move
     *
     * @param player taking Zzzs off DreamTile
     */

    public void removeTokens(IPlayer player, int initialZzzsRequired, ArrayList<IZzzToken> tokens, boolean isInfinite) {
        //super.removeTokens(player, initialZzzsRequired, tokens);
        int cost = 0; //cost of one use for DreamTile
        if (isInfinite) {
            //only one IZzztoken has to be removed if isInfinite is true
            cost = 1;
        } else {
            //else cost is the initialZzzsRequired
            cost = initialZzzsRequired;
        }
        Iterator<IZzzToken> iterator = tokens.iterator();
        while (iterator.hasNext()) {
            IZzzToken zzzToken = iterator.next();
            if (zzzToken.getPlayer() == player && cost >= 0) {
                iterator.remove();
                player.addZzzToken(zzzToken);
                cost--;
            }
        }
    }

}
