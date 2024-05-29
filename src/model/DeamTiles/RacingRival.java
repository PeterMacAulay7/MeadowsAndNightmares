/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * Move X spaces. X is equal to the number of the space the previous player is on
 */

package model.dreamtiles;

import model.IFirstSheep;
import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;

public class RacingRival extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public RacingRival() {
        initialZzzsRequired = 3;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = false;
        description = "RacingRival: Move X Spaces. X is Equal To The Number of The Space The Previous Player Is On";
    }

    /**
     * finds previous players index in turn sequence
     * moves player the number of the space the previous player is on
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
        //player's index in turn sequence
        int playerIndex = 0;
        //previous player's index in turn sequence
        int previousPlayerIndex = 0;

        IFirstSheep firstsheep = getFirstSheep();

        //turn sequence of players
        ArrayList<IPlayer> turnSequence = firstsheep.getTurnSequence();

        //loops through turn sequence
        for (int i = 0; i < turnSequence.size(); i++) {

            //if the player at i equals player
            if (turnSequence.get(i).equals(player)) {
                //player's index in turn sequence is found to be i
                playerIndex = i;
            }
        }
        int previousPlayerBoardLocation = 0;
        IPlayer previousPlayer = null;
        //if player's index is 0
        if (playerIndex <= 0) {
            //previous players index is set to the highest index as they would've went before player
            previousPlayerIndex = turnSequence.size() - 1;
            //gets the previous player
            previousPlayer = turnSequence.get(previousPlayerIndex);
            //gets the location of the previous player on the game board
            previousPlayerBoardLocation = previousPlayer.getSheepToken().getPositionOnBoard();
        } else {
            //previous players index is set to player index -1
            previousPlayerIndex = playerIndex - 1;
            previousPlayer = turnSequence.get(previousPlayerIndex);
            previousPlayerBoardLocation = previousPlayer.getSheepToken().getPositionOnBoard();

        }


        if (player != null && previousPlayer != null){
            //player's ISheepToken's moves the number of the space the previous player is on
            player.getSheepToken().changeInPosition(previousPlayerBoardLocation);

            //removes tokens required to use the token from DreamTile and gives them back to the player
            removeTokens(player, initialZzzsRequired, tokens, isInfinite);
        }
        else{
            throw new NullPointerException("Player is null");
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
     * @param player accessing their dreamtiles
     * @return Zzzs player has on DreamTile
     */
    public ArrayList<IZzzToken> getPlayerZzzs(IPlayer player){
        //return super.getPlayerZzzs(player);
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
