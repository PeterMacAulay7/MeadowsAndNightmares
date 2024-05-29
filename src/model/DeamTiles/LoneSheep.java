/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * If you have no Zzz on any other DreamTile within 2 spaces of this one Gain 3 winks
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class LoneSheep extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public LoneSheep() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "LoneSheep: If You Have No Zzzs On Any Other DreamTiles WIthin 2 Spaces Of This One Gain 3 Winks";
    }

    /**
     * finds position of current DreamTile and the DreamTile 2 spaces away.
     * if all dreamtiles between the two positions have no ZzzTokens from the player.
     * player gains 3 winks.
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {

        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }

        //list of lists that contains tokens at a space on the GameBoard
        DreamTile[] DreamTileArray = player.getSheepToken().getBoard().getDreamTileArray();

        boolean loneSheepFound = false;
        int position = 0;

        while (!loneSheepFound) {
            // for each DreamTile in DreamTileArray
            for (DreamTile dT : DreamTileArray) {
                if(dT != null) {
                    //if LoneSheep Tile is found and position
                    if (dT instanceof LoneSheep) {
                        loneSheepFound = true;
                    }
                }
                if (loneSheepFound == true) {
                    break;
                }
                position++;
            }
        }

        boolean hasZzzs = false;
        for (int i = position + 1; i <= position + 2; i++) {
            if(i <= 9) {
                if (DreamTileArray[i] != null) {
                    if (DreamTileArray[i].getPlayerZzzs(player).size() > 0) {
                        hasZzzs = true;
                    }
                }
            }
        }

        if (hasZzzs == false) {
            player.setWinks(player.getWinks() + 3);
            player.getWinkToken().changeInPosition(3);
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
