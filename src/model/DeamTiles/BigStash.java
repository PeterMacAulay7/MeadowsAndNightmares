/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * Gain 2 winks for each Zzz you have here, then remove all your Zzz from here
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class BigStash extends DreamTile {
    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public BigStash() {
        initialZzzsRequired = 3;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = false;
        description = "BigStash: Gain 2 Winks For Each Zzz You have Here, Then Remove All Your Zzzs From Here";
    }

    /**
     * player will gain 2 winks for each Zzz they have on this DreamTile.
     * player will then have all their Zzzs on this tile removed
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
        //gets the number of Zzzs the player has on the DreamTile
        int zzzs = getPlayerZzzs(player).size();

        //gives player 2 winks for each IZzzToken they have on the DreamTile
        player.setWinks(player.getWinks() + (zzzs * 2));
        player.getWinkToken().changeInPosition(zzzs * 2);
        //Removes players IZzzTokens on the board
        while(getPlayerZzzs(player).size() > 0) {
            removeTokens(player, initialZzzsRequired, tokens, isInfinite);
        }

    }

    /**
     * gets DreamTile description
     */
    @Override
    public String getDescription(){
        return description;
    }

    /**
     * gets DreamTile initial Zzzs Required
     */
    @Override
    public int getInitialZzzsRequired(){
        return initialZzzsRequired;
    }

    /**
     * gets DreamTile's Zzz tokens on it
     */
    @Override
    public ArrayList<IZzzToken> getTokens(){
        return tokens;
    }

    /**
     * gets if DreamTile can accept infinite Zzzs
     */
    @Override
    public boolean getIsInfinite(){
        return isInfinite;
    }

    /**
     * returns IZzzTokens that a player has on a DreamTIle
     *
     * @param player accessing their DreamTiles
     * @return Zzzs player has on DreamTile
     */
    @Override
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


}
