/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * if you are scared, gain 3 winks
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class ActionHero extends DreamTile {
    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public ActionHero() {
        initialZzzsRequired = 1;
        isInfinite = true;
        description = "ActionHero: If You Are Scared, Gain 3 Winks";
        tokens = new ArrayList<IZzzToken>();
    }

    /**
     * if player's sheep is scared.
     * player will gain 3 winks
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
        //if player's ISheepToken is scared
        if (player.getSheepToken().getScares() > 0) {
            //player gains 3 winks
            player.setWinks(player.getWinks() + 3);
            player.getWinkToken().changeInPosition(3);
        }
        removeTokens(player, initialZzzsRequired, tokens, isInfinite);
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
    @Override
    public void addToken(IZzzToken zzzToken) {
        tokens.add(zzzToken);
    }



}
