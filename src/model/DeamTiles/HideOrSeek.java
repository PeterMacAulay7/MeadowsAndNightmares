/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * If the nightmare is on an off-numbered space, gain 2 winks. If it's on even-numbered space, move 2 spaces
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class HideOrSeek extends DreamTile {
    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public HideOrSeek() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "HideOrSeek: If The Nightmare Is On An Odd-Numbered Space, Gain 2 Winks. If It's On An Even-Nmbered Space Move 2 Spaces";
    }

    /**
     * if NightmareToken is on an odd numbered space: give player 2 winks
     * if NightmareToken is on an even numbered space: move player 2 spaces forward
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
        //NightmareToken position
        int nightmareTokenPosition = player.getNightmareReferenceCard().getNightmareToken().getPositionOnBoard();
        if (nightmareTokenPosition != 10){
            //if NightmareToken is on an even numbered space: move player's sheep 2 spaces forward
            if (nightmareTokenPosition % 2 == 0 && nightmareTokenPosition != 10) {
                player.getSheepToken().changeInPosition(2);
            }
            //if NightmareToken is on an od numbered space: give player 2 winks
            else if (nightmareTokenPosition != 10) {
                player.setWinks(player.getWinks() + 2);
                player.getWinkToken().changeInPosition(2);
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
