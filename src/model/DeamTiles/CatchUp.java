/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * If you currently have fewer winks than all other players gain4 winks
 */

package model.dreamtiles;

import model.IPlayer;
import model.ISheepToken;
import model.IToken;
import model.IZzzToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatchUp extends DreamTile {
    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public CatchUp() {
        initialZzzsRequired = 1;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = true;
        description = "CatchUp: If You Currently Have Fewer Winks Than All Other Players Gain 4 Winks";
    }

    /**
     * finds other player's winks.
     * compares other player's winks to player's winks.
     * if player has the lowest winks then they will gain 4 winks.
     *
     * @param Iplayer player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {
        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }
        int playerWInks = player.getWinks();

        Map<IPlayer, Integer> otherPlayerWinks = new HashMap<>();

        List<List<IToken>> boardTokens = player.getSheepToken().getBoard().getBoard();

        //for each space on the board
        for (List<IToken> space : boardTokens) {
            //for each token at the given space on the board
            for (IToken token : space) {
                if(token != null) {
                    if (token instanceof ISheepToken) {
                        if (!token.equals(player.getSheepToken())) {
                            IPlayer sheepOwner = ((ISheepToken) token).getPlayer();
                            otherPlayerWinks.put(sheepOwner, sheepOwner.getWinks());
                        }
                    }
                }
            }
        }

        for (Integer count : otherPlayerWinks.values()) {
            //if other player winks is less than player winks
            if (count < playerWInks) {
                break;
            } else {
                //gives player 4 winks
                player.setWinks(4);
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

