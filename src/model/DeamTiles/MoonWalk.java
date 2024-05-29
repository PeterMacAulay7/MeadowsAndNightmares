/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * gain 2 winks. Move Backwards 2 spaces
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;
import model.ISheepToken;

import java.util.ArrayList;

public class MoonWalk extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public MoonWalk() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "MoonWalk: Gain 2 Winks. Move Backwards 2 Spaces";
    }

    /**
     * player gains 2 winks.
     * player's ISheepToken moves 2 spaces backward
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }

        //player gains 2 winks
        player.setWinks(player.getWinks() + 2);
        player.getWinkToken().changeInPosition(2);

        ISheepToken sheepToken = player.getSheepToken();
        //players ISheepToken moves two spaces backward
        if(sheepToken.getPositionOnBoard() >= 3) {
            sheepToken.changeInPosition(-2);
        }
        else if(sheepToken.getPositionOnBoard() == 2) {
            sheepToken.changeInPosition(-1);
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
