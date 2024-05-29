/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 * <p>
 * Move Forward to the next space containing another sheep
 */

package model.dreamtiles;

import model.IPlayer;
import model.ISheepToken;
import model.IToken;
import model.IZzzToken;

import java.util.ArrayList;
import java.util.List;

public class FindAFriend extends DreamTile {
    private final String description;
    private final int initialZzzsRequired;
    private final ArrayList<IZzzToken> tokens;
    private final boolean isInfinite;

    public FindAFriend() {
        initialZzzsRequired = 3;
        tokens = new ArrayList<IZzzToken>();
        isInfinite = false;
        description = "FindAFriend: Move Forward To The Next Space Containing Another Sheep";
    }

    /**
     * loops through boardTokens and determines where the nextSheep after player's sheep's location is.
     * moves player's sheep to nextSheep's location
     * @param player activating tile
     */
    @Override
    public void activateTile(IPlayer player) {

        if (getFirstSheep().getTurnSequence().size() == 1){
            return;
        }

        // Check if player has any zzz tokens
        if (getPlayerZzzs(player).size() == 0){
            return;
        }

        //list of lists that contains tokens at a space on the GameBaord
        List<List<IToken>> boardTokens = player.getSheepToken().getBoard().getBoard();

        boolean foundPlayerSheep = false;
        boolean foundNextSheep = false;
        int nextSheepPosition = -1;
        int sheepPosition = player.getSheepToken().getPositionOnBoard();


        while (!foundNextSheep) {
            //for each space in boardTokens
            for (int i = sheepPosition; i < 10; i++) {
                //for each token at the given space on the board
                for (IToken token : boardTokens.get(i)) {
                    if(token != null) {
                        //if player's sheep has been found and current token is a SheepToken
                        if (foundPlayerSheep && token instanceof ISheepToken) {

                            //set next sheep position to current token's position
                            nextSheepPosition = token.getPositionOnBoard();

                            //set found next sheep to true
                            foundNextSheep = true;
                        }
                        if (token.equals(player.getSheepToken())) {
                            foundPlayerSheep = true;
                        }
                    }
                }

            }
            if (foundNextSheep == false){
                for (int i = sheepPosition; i > 0; i--) {

                    for (IToken token : boardTokens.get(i)) {
                        if (token != null) {
                            //if player's sheep has been found and current token is a SheepToken
                            if (foundPlayerSheep && token instanceof ISheepToken) {

                                //set next sheep position to current token's position
                                nextSheepPosition = token.getPositionOnBoard();

                                //set found next sheep to true
                                foundNextSheep = true;
                            }
                            if (token.equals(player.getSheepToken())) {
                                foundPlayerSheep = true;
                            }
                        }
                    }
                    }
            }
        }
        if (nextSheepPosition < player.getSheepToken().getPositionOnBoard()) {
            player.getSheepToken().changeInPosition(10 + nextSheepPosition - player.getSheepToken().getPositionOnBoard());
        } else {
            player.getSheepToken().changeInPosition(nextSheepPosition - player.getSheepToken().getPositionOnBoard());
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
