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

import model.dreamtiles.DreamTile;
import model.exceptions.ExceededAllowedAmountOfPlayers;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Player implements IPlayer {
    private int winks;
    private boolean isOut;
    private NightmareReferenceCard nightmareReference;
    private ArrayList<IZzzToken> zzzTokens;
    private ISheepToken sheepToken;
    private IWinkToken winkToken;
    private IPillowToken pillowToken;
    private IActionable actionable;
    private final SheepCard[][] cards;
    private final int NIGHTMARE_CHOICE = 0;
    private final int NIGHTMARE_CARD_NUM = 3;
    private NightmareCard currentNightmareCard;

    private final String name;
    private boolean calledItANight;

    /**
     * Constructor for player object.
     * @param name of player.
     */
    public Player(String name) {
        this.name = name;
        this.calledItANight = false;
        cards = new SheepCard[2][1];
    }

    /**
     * initializes Players tokens
     * e.g., SheepToken, WinkToken, PillowToken, ZzzTokens
     * sets winks, isOut
     */
    @Override
    public void initializeTokens() {
        winks = 0;
        isOut = false;
        zzzTokens = new ArrayList<IZzzToken>();
        for (int i = 0; i < 10; i++) {
            IZzzToken token = new ZzzToken(this);
            token.setPlayer(this);
            zzzTokens.add(token);
        }
        sheepToken = new SheepToken(this);
        pillowToken = new PillowToken(this);
        pillowToken.setPlayer(this);
        winkToken = new WinkToken(pillowToken, (this));
        winkToken.setPlayer(this);

    }

    /**
     * adds a Card to the cards array
     * @param card  to be added to players hand
     * @throws ExceededAllowedAmountOfPlayers
     */
    @Override
    public void addCard(SheepCard card) throws ExceededAllowedAmountOfPlayers {
        if (cards[0][0] == null) {
            cards[0][0] = card;
        } else if (cards[1][0] == null) {
            cards[1][0] = card;
        } else {
            throw new ExceededAllowedAmountOfPlayers("Attempted to add more cards than is legally allowed in hand.");
        }
    }

    /**
     * plays a nightmare card
     * @param card
     */
    @Override
    public void addCard(NightmareCard card){
        currentNightmareCard = card;
        resolve(NIGHTMARE_CARD_NUM, NIGHTMARE_CHOICE);
    }

    /**
     * returns number of cards
     * @return number of cards in players hand
     */
    @Override
    public int getNumCards() {
        int numCards = 0;
        if (cards != null) {
            for (int row = 0; row < cards.length; row++) {
                for (int col = 0; col < cards[row].length; col++) {
                    if (cards[row][col] != null) {
                        numCards++;
                    }
                }
            }
        }
        return numCards;
    }


    /**
     * TODO: This must be implemented in order for a turn to process
     * @implNote This should use an actionAble object after deciding on the behaviour
     */


    /**
     * takes in two integers. cardNum corresponds to the card the user wants to resolve
     * choice refers to the action the user wants to choose from the card.
     * This method will get the card to resolve and then determine what kind of card it is.
     * The method will then call actionable to take the nessesary steps to complete the action.
     * @param cardNum -which card from the hand will be player
     * @param choice choice of action on card
     */
    @Override
    public boolean resolve(int cardNum, int choice) {
        //if the desired card is in the first card space
        if (cardNum == 1) {

            //if the first card space is not null
            if (cards[0][0] != null) {

                //card choice is equal to the first card in the players possesion
                SheepCard cardChoice = cards[0][0];

                //set first card space to null
                cards[0][0] = null;
                return actionable.actionWithSheepCard(cardChoice, this, choice);
            } else {
                //else throw exception
                throw new NoSuchElementException("No card was found");
            }

            //if the desired card is in the second space
        } else if (cardNum == 2) {

            if (cards[1][0] != null) {

                //card choice is equal to the second card in the players possesion
                SheepCard cardChoice = cards[1][0];

                //sets second card space to null
                cards[1][0] = null;
                return actionable.actionWithSheepCard(cardChoice,this, choice);

            } else {
                //else throw exception
                throw new NoSuchElementException("No card was found");
            }

        }else if(cardNum == NIGHTMARE_CARD_NUM){
            if(currentNightmareCard != null) {
                return nightmareReference.resolve(currentNightmareCard);
            }
            else{
                throw new NoSuchElementException("No card was found");
            }
        }
        else {
            //if invalid cardNum was input throw exception
            throw new IllegalArgumentException("Not a viable choice for cardNum");
        }
    }

    /**
     * returns Player's winks
     * @return player's number of winks
     */
    @Override
    public int getWinks() {
        return winks;
    }

    /**
     * sets the Player's winks
     * @param newWinks -new number of player's winks
     */
    @Override
    public void setWinks(int newWinks) {
        if (newWinks >= 0) {
            winks = newWinks;
        } else {
            throw new IllegalArgumentException("winks need to be above 0");
        }
    }

    /**
     * returns isOut
     * @return if player is out or not
     */
    @Override
    public boolean getIsOut() {
        return isOut;
    }

    /**
     * sets isOut
     * @param isOut determines if player is out
     */
    public void setIsOut(boolean isOut) {
        this.isOut = isOut;

    }

    /**
     * adds IZzzTokens to the Player's zzzTokens ArrayList
     * @param token to be added to player's unused Zzz token supply
     */
    @Override
    public void addZzzToken(IZzzToken token) {
        zzzTokens.add(token);
    }

    public IZzzToken removeZzzToken() throws NoSuchElementException {
        if (!zzzTokens.isEmpty()) {
            return zzzTokens.remove(0);
        } else {
            throw new NoSuchElementException("Player has all Zzz tokens on the board, can not remove any more.");
        }
    }

    /**
     * returns the Player's zzzTokens ArrayList
     * @return player's unused Zzz tokens in the player's supply
     */
    @Override
    public ArrayList<IZzzToken> getZzzTokens() {
        return zzzTokens;
    }

    /**
     * returns the instance of ISheepToken
     * @return player's sheep token
     */
    @Override
    public ISheepToken getSheepToken() {
        return sheepToken;
    }

    /**
     * returns the instance of IWinkToken
     * @return player's wink token
     */
    @Override
    public IWinkToken getWinkToken() {
        return winkToken;
    }

    /**
     * returns the instance of IPillowToken
     * @return player's pillow token
     */
    @Override
    public IPillowToken getPillowToken() {
        return pillowToken;
    }

    /**
     * returns the Players cards
     * @return cards in player's hand
     */
    @Override
    public SheepCard[][] getHand() {
        return cards;
    }


    /**
     * returns name of Player
     * @return player's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * returns the instance of IActionable
     * @return actionable object associated with player
     */
    @Override
    public IActionable getActionable() {
        return actionable;
    }

    /**
     * sets an instance of Actionable
     * @param actionable object
     */
    @Override
    public void setActionable(IActionable actionable) {
        this.actionable = actionable;

    }

    /**
     * returns the instance of NightmareReferenceCard
     * @return NightmareReferenceCard assoicated with player
     */
    @Override
    public NightmareReferenceCard getNightmareReferenceCard() {
        return nightmareReference;
    }

    /**
     * sets an instance of NightmareReferenceCard
     * @param card -nightmare reference card associated with player
     */
    @Override
    public void setNightmareReferenceCard(NightmareReferenceCard card) {
        nightmareReference = card;
    }

    /**
     * takes in an index that will get a dreamTile from the DreamTileArray in gameboard.
     * adds the number of Zzzs specified from the players ArrayList to the DreamTiles
     * @param index of DreamTile on board that player is catching Zzzs on
     * @param numZzzs player is catching
     */
    public void catchZzzs(int index, int numZzzs) {
        //gets the DreamTileArray from board
        DreamTile[] dreamTiles = getSheepToken().getBoard().getDreamTileArray();
        //chooses a dreamTile
        DreamTile chosenDreamTile = dreamTiles[index];
        //gets number of Zzzs required to catch Zzzs on chosen DreamTile
        int numZzzsRequired = chosenDreamTile.getInitialZzzsRequired();
        //if the number of Zzzs the player chose is greater than or equal to the required number of Zzzs for the DreamTile
        if (numZzzs >= numZzzsRequired) {
            for (int i = 0; i < numZzzs; i++) {
                //add a Zzz from the players ArrayList to the DreamTile's ArrayList
                chosenDreamTile.addToken(removeZzzToken());
            }
        } else {
            throw new IllegalArgumentException("Cannot use less Zzzs than the required amount for a DreamTile");
        }

    }

    public void setCalledItANight(boolean inBoolean){
        this.calledItANight = inBoolean;
    }

    public boolean getCalledItANight(){
        return calledItANight;
    }

}
