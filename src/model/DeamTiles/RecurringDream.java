/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * Gain 2 winks for each DreamTile you have 3 or more Zzzs on
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class RecurringDream extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public RecurringDream() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "RecurringDream: Gain 2 Winks For Each DreamTile You Have 3 Or More Zzzs On";
    }

    /**
     * finds all dreamtiles that the player has 3 or more IZzzTokens on it.
     * player gains 2 winks for each DreamTile with 3 or more IZzzTokens on it.
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


        // for each DreamTile in DreamTileArray
        for (DreamTile dT : DreamTileArray) {
            //if player has 3 IZzzTokens on dT
            if (dT != null) {
                if (dT.getPlayerZzzs(player).size() >= 3) {
                    //player gains 2 winks
                    player.setWinks(player.getWinks() + 2);
                    player.getWinkToken().changeInPosition(2);
                }
            }
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
