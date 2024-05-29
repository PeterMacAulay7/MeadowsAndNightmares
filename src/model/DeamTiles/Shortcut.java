/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * Move to the next space with a DreamTile that has 3 or more Zzz from any one player
 */

package model.dreamtiles;

import model.IPlayer;
import model.IZzzToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shortcut extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public Shortcut() {
        this.initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        this.isInfinite = true;
        this.description = "Shortcut: Move To The Next Space With A DreamTile That Has 3 Or More Zzzs From Any One Player";
    }

    /**
     * Loops through DreamTiles starting at the player's ISheepToken's current location.
     * Finds a DreamTiles with 3 or more IZzzTokens on it.
     * Finds which players own which tokens on the DreamTile.
     * If a single player owns three of the IZzzTokens.
     * player's sheepToken moves to that DreamTile.
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
        DreamTile[] dreamTileArray = player.getSheepToken().getBoard().getDreamTileArray();
        boolean desiredDreamTileFound = false;
        int currentTilePosition = player.getSheepToken().getPositionOnBoard();
        int desiredDreamTilePosition = -1; // Initialize with an invalid position

        // Loop through DreamTiles starting from the tile next to the player's current position
        for (int i = currentTilePosition + 1; i < dreamTileArray.length && !desiredDreamTileFound; i++) {
            if(dreamTileArray[i] != null) {
                DreamTile dT = dreamTileArray[i];
                List<IZzzToken> tokens = dT.getTokens();


                //if tokens at this DreamTile is greater than or equal to 3
                if (tokens.size() >= 3) {
                    // Count tokens per player
                    Map<IPlayer, Integer> tokenCountPerPlayer = new HashMap<>();

                    //for each IZzzToken in tokens
                    for (IZzzToken zzz : tokens) {
                        if(zzz != null) {
                            //gets the player who owns the IZzzToken
                            IPlayer tokenPlayer = zzz.getPlayer();
                            //puts it in tokenCountPerPlayer
                            tokenCountPerPlayer.put(tokenPlayer, tokenCountPerPlayer.getOrDefault(tokenPlayer, 0) + 1);
                        }
                    }

                    // Check if any player has 3 or more tokens
                    for (Integer count : tokenCountPerPlayer.values()) {
                        if (count >= 3) {
                            desiredDreamTileFound = true;
                            desiredDreamTilePosition = i;
                            break; // Found a tile that meets the condition, no need to check further
                        }
                    }
                }
            }
        }

        // If a desired DreamTile is found, update the player's sheep token position
        if (desiredDreamTileFound) {
            player.getSheepToken().changeInPosition(desiredDreamTilePosition - player.getSheepToken().getPositionOnBoard());
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

