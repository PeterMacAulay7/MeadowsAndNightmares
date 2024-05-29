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

public interface IRacingPhase extends Phase {

    /**
     * ends the phase
     */
    public void endPhase();

    /**
     * calls Resetable to reset the phase for the next racing phase
     * @param players will be reset along with other objects associated with them
     * @param referenceCard will be used to reset nightmare token
     * @param deck will be reset
     */
    public void reset(ArrayList<IPlayer> players, NightmareReferenceCard referenceCard, ICardDeck deck);

    /**
     * returns the instance of IReferenceTile
     * @return reference tile associated with the racing phase
     */
    public IReferenceTile getReferenceTile();

    /**
     * sets an instance of IReferenceTile
     * @param referenceTile associated with the racing phase
     */
    public void setReferenceTile(IReferenceTile referenceTile);

    /**
     * returns the instance of IFence
     * @return fence associated with the racing phase
     */
    public IFence getFence();

    /**
     * sets and instance of IFence
     * @param fence associated with the racing phase
     */
    public void setFence(IFence fence);

    /**
     * forces an IPlayer to take an action
     * @param player to have action taken on
     */
    public void takeAction(IPlayer player);

    /**
     *returns is the Phase is over
     * @return if the phase is over
     */
    public boolean isPhaseOver();

    /**
     * sets and instance of ICardDeck
     * @param deck associated with the racing phase
     */
    public void setDeck(ICardDeck deck);

    /**
     * returns the instance of IFirstSheep
     * @return first sheep associated with racing phase
     */
    public IFirstSheep getFirstSheep();

    /**
     * sets instance of IFirstSheep
     * @param firstSheep
     */
    public void setFirstSheep(IFirstSheep firstSheep);

    /**
     * sets an instance of NightmareReferenceCard
     * @param in -nightmare refernce card associated with the racing phase
     */
    public void setNightmareReferenceCard(NightmareReferenceCard in);

    /**
     * sets and instance of Resetable
     * @param in -resetable object associated with the racign phase
     */
    public void setResetable(Resetable in);

}
