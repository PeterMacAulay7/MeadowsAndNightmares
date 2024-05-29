/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * If you have Zzz on 2 or fewer dreamtiles, gain 4 winks
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class LucidDreams extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public LucidDreams() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "LucidDreams: If You Have Zzzs On 2 Or Fewer DreamTiles, Gain 4 Winks";
    }

    /**
     * Finds the number of dreamtiles player has IZzzTokens on.
     * If there are 2 or fewer dreamtiles that player has IZzzTokens on.
     * player gains 4 winks
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }

        //list of lists that contains tokens at a space on the GameBaord
        DreamTile[] DreamTileArray = player.getSheepToken().getBoard().getDreamTileArray();

        int DreamTilesWithZzzs = 0;

        // for each DreamTile in DreamTileArray
        for (DreamTile dT : DreamTileArray) {
            if(dT != null) {
                //if player has IZzzTokens on dT
                if (dT.getPlayerZzzs(player).size() > 0) {
                    DreamTilesWithZzzs++;
                }
            }
        }
        //if there are 2 or fewer dreamtiles that the player has IZzzTokens
        if (DreamTilesWithZzzs <= 2) {
            //player gains 4 winks
            player.setWinks(player.getWinks() + 4);
            player.getWinkToken().changeInPosition(4);
        }

        //removes tokens required to use the token from DreamTile and gives them back to the player
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
