/**
 * Represents the players of the game
 * Can Draw and Resolve Cards
 * Has Reference to it's ITokens
 * Holds it's number of Winks
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

import model.exceptions.ExceededAllowedAmountOfPlayers;

import java.util.ArrayList;

public interface IPlayer {
    
    public void initializeTokens();

    public void addCard(SheepCard card) throws ExceededAllowedAmountOfPlayers;

    public void addCard(NightmareCard card);

    public int getNumCards();

    public boolean resolve(int cardNum, int choice); // Note: Return type (T, T) should be specified

    public int getWinks();

    /**
     * sets the Player's winks to the given value if it is above 0
     * @param winks
     */
    public void setWinks(int winks);

    public boolean getIsOut();

    /**
     * sets the if the player is out or not
     * @param isOut state of player
     */
    public void setIsOut(boolean isOut);

    public IActionable getActionable();

    /**
     * sets and instance of actionable
     * @param actionable object associated with player
     */
    public void setActionable(IActionable actionable);

    /**
     * adds IZzzTokens to the players IZzzToken list
     * @param zzztoken to be added to players list of Zzz tokens not being used
     */
    public void addZzzToken(IZzzToken zzztoken);

    /**
     * removes an IZzzToken from the players IZzzToken list
     */
    public IZzzToken removeZzzToken();

    /**
     * returns the players IZzzToken list
     * @return list of players Zzz tokens not being used 
     */
    public ArrayList<IZzzToken> getZzzTokens();

    /**
     * gets the Player's sheepToken
     * @return player's sheep token
     */
    public ISheepToken getSheepToken();

    /**
     * gets the Player's WinkToken
     * @return player's wink token
     */
    public IWinkToken getWinkToken();

    /**
     * gets the Player's PillowToken
     * @return player's pillow token
     */
    public IPillowToken getPillowToken();

    /**
     * gets the Player's reference to NightmareReferenceCard
     * @return NightmareReferenceCard associated with player
     */
    public NightmareReferenceCard getNightmareReferenceCard();

    /**
     * sets the Player's reference to NightmareReferenceCard
     * @param card nightmare reference card associated with player
     */
    public void setNightmareReferenceCard(NightmareReferenceCard card);

    /**
     * returns the Player's cards array
     * @return player's cards in their hand
     */
    public SheepCard[][] getHand();

    /**
     * returns the name of the Player
     * @return player's name
     */
    public String getName();

    /**
     * Allows Player to catch Zzzs
     * @param index of the DreamTile the player is catching Zzzs on
     * @param numZzzs that player is catching
     */
    public void catchZzzs(int index, int numZzzs);

    /**
     * returns if a player has called it a night
     * @return
     */
    public boolean getCalledItANight();

    /**
     * sets the boolean for if a player called it a night
     */
    public void setCalledItANight(boolean inBoolean);

}
