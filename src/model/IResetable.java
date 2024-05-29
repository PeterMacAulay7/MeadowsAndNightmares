/**
 * Resets players, nightmareReferenceCard, and, cardDeck
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

import java.util.ArrayList;

public interface IResetable {

    /**
     * Resets players, NightmareReferenceCard, and, cardDeck
     *
     * @param players will be reset and objects associated with them
     * @param nightmareReferenceCard will reset nightmare token
     * @param cardDeck will be reset
     */
    public void reset(ArrayList<IPlayer> players, NightmareReferenceCard nightmareReferenceCard, ICardDeck cardDeck);

    /**
     * resets a  IToken's position to the DEAFULT_POSITION
     * @param token to be reset
     */
    public void resetTokenPosition(IToken token);

}