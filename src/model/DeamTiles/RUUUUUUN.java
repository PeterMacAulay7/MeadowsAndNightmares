/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * Move forward to the space in front of the nightmare
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class RUUUUUUN extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;
    private final int ONE_SPACE_FORWARD = 1;

    public RUUUUUUN() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "RUUUUUUN!: MoveForward To The Space In Front Of The Nightmare";
    }

    /**
     * finds NightmareToken's location
     * sets player's ISheepToken's location to the space infront of the NightmareToken
     * @param IPlayer player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
        //gets NightmareToken position
        int nightmarePosition = player.getNightmareReferenceCard().getNightmareToken().getPositionOnBoard();

        int sheepPosition = player.getSheepToken().getPositionOnBoard();

        int changeInPosition = nightmarePosition - sheepPosition + ONE_SPACE_FORWARD;
        //sets player's ISheepToken to the position in front of the NightmareToken
        player.getSheepToken().changeInPosition(changeInPosition);

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
