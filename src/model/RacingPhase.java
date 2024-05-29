/**
 * Represents the racing phase of the game
 * sets instances of objects for the racing phase
 * calls Resetable to reset objects after the racing phase ends
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.ArrayList;

public class RacingPhase implements IRacingPhase {
    boolean phaseActive = true;
    private ArrayList<IPlayer> players;
    private ICardDeck deck;
    private NightmareReferenceCard nightmareReferenceCard;
    private IFirstSheep firstSheep;
    private IFence fence;
    private IReferenceTile referenceTile;
    private Resetable resetable;

    /**
     * creates new Resetable object
     */
    public RacingPhase() {
        resetable = new Resetable();

    }

    /**
     * ends the Phase
     */
    @Override
    public void endPhase() {

        phaseActive = false;
        referenceTile.movePillows();
        reset(players, nightmareReferenceCard, deck);
    }

    /**
     * calls resetable to reset the Phase for the next racing phase
     * @param players
     * @param referenceCard
     * @param deck
     */
    public void reset(ArrayList<IPlayer> players, NightmareReferenceCard referenceCard, ICardDeck deck) {
        resetable.reset(players, referenceCard, deck);
    }

    /**
     * returns the instance of IReferenceTile
     * @return IReferenceTile
     */
    public IReferenceTile getReferenceTile() {
        return referenceTile;
    }

    /**
     * sets an instance of IReferenceTile
     * @param referenceTile
     */
    public void setReferenceTile(IReferenceTile referenceTile) {
        this.referenceTile = referenceTile;
    }

    /**
     * returns the instance of IFence
     * @return IFence
     */
    public IFence getFence() {
        return fence;
    }

    /**
     * sets and instance of IFence
     * @param fence
     */
    public void setFence(IFence fence) {
        this.fence = fence;
    }

    /**
     * forces the given IPlayer to take an action
     * @param player
     */
    @Override
    public void takeAction(IPlayer player) {

    }

    /**
     * determines if the phase is over
     * @return boolean
     */
    @Override
    public boolean isPhaseOver() {
        return phaseActive;
    }

    /**
     * sets phaseActive boolean
     * @param value
     */
    public void setPhaseActive(boolean value) {
        phaseActive = value;
    }

    /**
     * sets and instance of ICardDeck
     * @param deck
     */
    @Override
    public void setDeck(ICardDeck deck) {
        this.deck = deck;
    }

    /**
     * returns an instance of IFirstSheep
     * @return
     */
    public IFirstSheep getFirstSheep() {
        return firstSheep;
    }

    /**
     * sets an instance of IFirstSheep
     * @param firstSheep
     */
    @Override
    public void setFirstSheep(IFirstSheep firstSheep) {
        this.firstSheep = firstSheep;
        this.players = firstSheep.getTurnSequence();
    }

    /**
     * sets and instance of NightmareReferenceCard
     * @param nightmareReferenceCard
     */
    public void setNightmareReferenceCard(NightmareReferenceCard nightmareReferenceCard) {
        this.nightmareReferenceCard = nightmareReferenceCard;
    }

    /**
     * sets an instance of Resetable
     * @param resetable
     */
    public void setResetable(Resetable resetable) {
        this.resetable = resetable;
    }


}
