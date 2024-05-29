/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * Move backward 1 space. You may become brave
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class StepBack extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public StepBack() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "StepBack: Move Backward 1 Space. You May become Brave";
    }

    /**
     * Moves player's ISheepToken backwards 1 space
     * makes player's ISheepToken brave by decrementing their number of scares
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
            //Moves player's ISheepToken backwards 1 space
            if(player.getSheepToken().getPositionOnBoard() > 0)
            player.getSheepToken().changeInPosition(-1);

            if(player.getSheepToken().getScares() > 0) {
                //makes player's ISheepToken brave by decrementing their number of scares
                player.getSheepToken().decrementScare();
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
