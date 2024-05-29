/**
 * Acts as a way to keep track of players turn.
 * determines the first sheep for the round
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

import java.util.ArrayList;

public interface IFirstSheep {

    public void incrementTurn();

    public IPlayer getCurrentTurn();

    public ArrayList<IPlayer> getTurnSequence();

    /**
     * takes in information about all the players
     * @param bedtimes times the players went to bed last night
     * @param names players names
     */
    public void receivePlayersInfo(ArrayList<Integer> bedtimes, ArrayList<String> names);

    /**
     * allows an IPLayer to be set as first sheep
     * @param player player being set as first sheep
     */
    public void setFirstSheep(IPlayer player);

    /**
     * decides which player will be the first sheep
     */
    public void decideFirstSheep();

    /**
     * returns the current first sheep
     * @return current first sheep
     */
    public IPlayer getCurrentFirstSheep();

    /**
     * returns the current players hand
     * @return the current players cards in their hand
     */
    public Card[][] getCurrentHand();

}
