/**
 * A specialized version of Deck specifically for cards
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.ArrayList;

public interface ICardDeck{

    /**
     * Creates an ArrayList comprised of a types of object.
     */
    public void createDeck(ArrayList<IPlayer> players, NightmareReferenceCard referenceCard);

    /**
     * returns the deck ArrayList
     * @return all cards in deck via ArrayList of type Card
     */
    public ArrayList<SheepCard> getSheepCardDeck();

    public ArrayList<NightmareCard> getNightmareCardDeck();

    public SheepCard drawSheepCard();

    public NightmareCard drawNightmareCard();

}
