/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * Gain 2 winks. If your pillow is the highest on the scoreboard, gain 2 more winks
 */

package model.dreamtiles;

import model.IPillowToken;
import model.IPlayer;
import model.IToken;
import model.IZzzToken;

import java.util.ArrayList;
import java.util.List;

public class SweetDream extends DreamTile {

    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public SweetDream() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "SweetDream: Gain 2 Winks. If Your Pillow Token is The Highest On The ScoreBoard, Gain 2 More Winks";
    }

    /**
     * Gain 2 winks. If your pillow is the highest on the scoreboard, gain 2 more winks
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
        player.setWinks(player.getWinks() + 2);
        player.getWinkToken().changeInPosition(2);

        List<List<IToken>> boardTokens = player.getPillowToken().getBoard().getBoard();
        int playerPillowPosition = player.getPillowToken().getPositionOnBoard();
        boolean hasHighestPosition = true; // Assume the player has the highest position until proven otherwise

        // Iterate through each space and each token in the space
        for (List<IToken> space : boardTokens) {
            for (IToken token : space) {
                if (token instanceof IPillowToken) {
                    if (token != null) {
                        int tokenPosition = token.getPositionOnBoard();
                        // If any other pillowToken has a higher or equal position, set hasHighestPosition to false
                        if (tokenPosition > playerPillowPosition) {
                            hasHighestPosition = false;
                            break; // No need to continue checking if we find a higher position
                        }
                    }
                }
            }
        }

        // Act based on whether the player has the highest position or not
        if (hasHighestPosition) {
            player.setWinks(player.getWinks() + 2);
            player.getWinkToken().changeInPosition(2);
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
