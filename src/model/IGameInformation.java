/**
 * Generates the model
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;


import model.dreamtiles.DreamTile;

import java.util.ArrayList;

public interface IGameInformation {

    /**
     * generates the first part of the model
     * @param nightmareChoice nightmare chosen for current game
     */
    public void generateModel(int nightmareChoice);

    /**
     * generates the rest of the model
     */
    public void generateModelStageTwo();

    /**
     * returns the GameBoard
     * @return gameboard associated with the current game
     */
    public IGameBoard getGameBoard();

    /**
     * returns the ScoreBoard
     * @return scoreboard associated with the current game
     */
    public IScoreBoard getScoreBoard();

    /**
     * returns the ReferenceTile
     * @return reference tile associated with the current game
     */
    public IReferenceTile getReferenceTile();

    /**
     * returns FirstSheep
     * @return first sheep associated with the current game 
     */
    public IFirstSheep getFirstSheep();

    /**
     * returns NightmareReferenceCard
     * @return nightmare reference card associated with the current game
     */
    public NightmareReferenceCard getNightmareReferenceCard();

    /**
     * returns RuleBook
     * @return rulebook
     */
    public IRuleBook getRuleBook();

    /**
     * returns RacingPhase
     * @return racing phase associated with the current game
     */
    public IRacingPhase getRacingPhase();

    /**
     * returns RestingPhase
     * @return resting pahse associated with the current game
     */
    public IRestingPhase getRestingPhase();

    /**
     * returns DreamTileDeck
     * @return DreamTile deck associated with the current game
     */
    public IDreamTileDeck getDreamTileDeck();

    /**
     * return CardDeck
     * @return card deck assoicated with the current game
     */
    public ICardDeck getCardDeck();

    /**
     * gets the Sheep card deck
     * @return ArrayList containing Sheep card deck
     */
    public ArrayList<SheepCard> getSheepCardDeck();

    /**
     * gets the Nightmare card deck
     * @return ArrayList containing Nightmare card deck
     */
    public ArrayList<NightmareCard> getNightmareCardDeck();

    /**
     * returns the delay for playing a NightmareCard
     * @return delay for playing a Nightmare card
     */
    public int getNightmareCardPlayDelay();


    /**
     * returns the delay for playing a SheepCard
     * @return delay for playing a sheep card
     */
    public int getSheepCardPlayDelay();

    /**
     * returns the current player's hand
     * @reutrn current player's cards in hand
     */
    public Card[][] getCurrentHand();

    /**
     * places the first DreamTiles on the gameboard at the start of the game
     * @return if tiles were placed
     */
    public boolean placeFirstDreamTiles();

    /**
     * determines if the phase is over based on if all players are out
     */
    public boolean checkRacingPhaseOver();

    /**
     * returns the number of players who are out
     * @return the number of players out
     */
    public int getPlayersOut();

    /**
     * draws a sheep card from the deck for a given player
     */
    public SheepCard drawSheepCard(IPlayer currentPlayer);

    /**
     * draws a nightmare card from the deck for a given player
     */
    public NightmareCard drawNightmareCard(IPlayer currentPlayer);

    /**
     * finds the index of a given card in the current player's hand
     * @param card attempting to find
     * @return index of given card in current players hand
     */
    public int findCardInHand(Card card);

    /**
     * ends racing phase
     */
    public void endRacingPhase();

    /**
     * gets valid DreamTile indexes to place DreamTiles on
     * @return valid indexes to place DreamTiles on
     */
    public ArrayList<Integer> occupiedDreamTileSpots();

    /**
     * gets DreamTile Array from GameBoard
     * @return DreamTile array
     */
    public DreamTile[] getDreamTileArray();

    /**
     * returns current player
     * @return current player
     */
    public IPlayer getCurrentPlayer();

    /**
     * returns the turn sequence
     * @return turn sequence
     */
    public ArrayList<IPlayer> getTurnSequence();

    /**
     * increments current turn
     */
    public void incrementTurn();

    /**
     * returns number of players who called it a night
     * @return
     */
    public int getPlayersCalledItANight();

    /**
     * returns the state of the RacingPhase
     * @return RacingPhase activity state
     */
    public boolean isRacingPhaseOver();

    /**
     * creates the deck in CardDeck
     */
    public void createCardDeck();

    /**
     * returns the DreamTile market from DreamTileDeck
     * @return DreamTile[]
     */
    public DreamTile[] getMarket();
}
