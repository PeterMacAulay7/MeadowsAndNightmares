/**
 * Determines the winner and standings of a round
 * Determines overall standings for game
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.ArrayList;
import java.util.Collections;

public class ReferenceTile implements IReferenceTile {
    int numberOfPlayers;
    private ArrayList<IPlayer> players;
    private final int ZERO = 0;
    private final int FIRST_PLACE = 0;
    private final int SECOND_PLACE = 1;
    private final int THIRD_PLACE = 2;
    private final int ONE_PLAYER = 1;
    private final int TWO_PLAYER = 2;
    private final int THREE_PLAYER = 3;
    private final int FOUR_PLAYER = 4;



    public ReferenceTile() {
        players = new ArrayList<>();
    }

    /**
     * moves each IPlayers IPillowToken based on how close their IPillowToken is to their IWinkToken
     * at the end of an IRacingPhase
     */
    @Override
    public void movePillows() {
        //TODO: need to consider the case where a wink token is farther ahead of a pillow token (moved past it) rather than mere distance.
        // Sort players based on the distance and priority of wink tokens
        Collections.sort(players, (p1, p2) -> {
            int distance1 = calculateDistanceToPillow(p1);
            int distance2 = calculateDistanceToPillow(p2);
            // Compare distances
            int distanceComparison = Integer.compare(distance1, distance2);
            // If the distances are equal, prioritize the player with the higher wink token value
            if (distanceComparison == ZERO) {
                return Integer.compare(p2.getWinks(), p1.getWinks());
            } else {
                return distanceComparison;
            }
        });

        for (int i = 0; i < numberOfPlayers; i++) {
            IPlayer player = players.get(i);
            int moveAmount;
            //if player is out and player solo
            if (player.getIsOut() == true && numberOfPlayers == 1) {
                //pillow moves 0 spaces
                moveAmount = 0;
                //if player is out and playing multi-player
            } else if (player.getIsOut() == true) {
                //move pillow 3 spaces
                moveAmount = -3;
            } else {
                switch (numberOfPlayers) {
                    //1 player
                    case ONE_PLAYER:
                        moveAmount = player.getWinks() % 5;
                        break;
                    //2 players
                    case TWO_PLAYER:
                        if (i == FIRST_PLACE) {
                            //first moves 8 spaces
                            moveAmount = -8;
                        } else {
                            //second moves 5 spaces
                            moveAmount = -5;
                        }
                        break;
                    //3 players
                    case THREE_PLAYER:
                        if (i == FIRST_PLACE) {
                            //first moves 10 spaces
                            moveAmount = -10;
                        } else if (i == SECOND_PLACE) {
                            //second moves 7 spaces
                            moveAmount = -7;
                        } else {
                            //third move 5 spaces
                            moveAmount = -5;
                        }
                        break;
                    case FOUR_PLAYER:
                        if (i == FIRST_PLACE) {
                            //first moves 10 spaces
                            moveAmount = -10;
                        } else if (i == SECOND_PLACE) {
                            //second moves 8 spaces
                            moveAmount = -8;
                        } else if (i == THIRD_PLACE) {
                            //third moves 6 spaces
                            moveAmount = -6;
                        } else {
                            //fourth moves 5 psaces
                            moveAmount = -5;
                        }
                        break;
                    default:
                        throw new IllegalAccessError();
                }
            }
            //moves players pillow token amout that they should be awarded
            player.getPillowToken().changeInPosition(moveAmount);
        }
    }

    /**
     * calculates the distance an IPlayer's IPillowToken is from that IPlayer's
     * IWinkToken
     * @param pillowToken 
     * @param winkToken
     * @return distance from a player's pillow token to their wink token
     */
    @Override
    public int calculateDistanceToPillow(IPlayer player) {
        int distanceToPillow = 0;

        // if (winkToken.getPlayer() == pillowToken.getPlayer()) {
        //     distanceToPillow = Math.abs(pillowToken.getPositionOnBoard() - winkToken.getPositionOnBoard());
        // } else {
        //     throw new IllegalArgumentException("PillowToken and WinkToken need to have the same owner");
        // }
        
        distanceToPillow = Math.abs(player.getPillowToken().getPositionOnBoard() - player.getWinks());
        return distanceToPillow;
    }

    /**
     * returns the number of IPlayers
     * @return int
     */
    @Override
    public int getNumPlayers() {
        return players.size();
    }

    /**
     * sets the ArrayList<IPlayer> with the IPlayers who are playing
     * @param players
     */
    @Override
    public void setPlayersList(ArrayList<IPlayer> players) {
        this.players = players;
        numberOfPlayers = players.size();
    }


}
