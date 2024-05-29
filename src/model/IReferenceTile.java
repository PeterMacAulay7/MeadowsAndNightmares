/**
 * Determines the winner and standings of a round
 * Determines overall standings for game
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

import java.util.ArrayList;

public interface IReferenceTile {
    /**
     * moves each IPlayers IPillowToken based on how close their IPillowToken is to their IWinkToken
     * at the end of an IRacingPhase
     */
    public void movePillows();

    /**
     * calculates the distance an IPlayer's IPillowToken is from that IPlayer's
     * IWinkToken
     * @param pillowToken
     * @param winkToken
     * @return
     */
    public int calculateDistanceToPillow(IPlayer player);

    /**
     * returns the number of IPlayers
     * @return number of players playing
     */
    public int getNumPlayers();

    /**
     * sets the ArrayList<IPlayer> with the IPlayers who are playing
     * @param players players playing
     */
    public void setPlayersList(ArrayList<IPlayer> players);
}
