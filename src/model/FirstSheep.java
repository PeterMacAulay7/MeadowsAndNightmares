/**
 * Acts as a way to keep track of players turn.
 * determines the first sheep for the round
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.ArrayList;

public class FirstSheep implements IFirstSheep {
    private IPlayer currentFirstSheep;
    private final ArrayList<IPlayer> players;

    public FirstSheep() {
        players = new ArrayList<>();
    }


    /**
     * Increments turn (moves to next player's turn)
     * @implNote this simply moves the first item to the last index in the array list as that's how this turn sequence is kept in track
     */
    @Override
    public void incrementTurn() {
        IPlayer currentPlayer = players.get(0);
        players.remove(0);
        players.add(currentPlayer);
    }

    @Override
    public IPlayer getCurrentTurn() {
        return players.get(0);
    }

    @Override
    public ArrayList<IPlayer> getTurnSequence() {
        return players;
    }


    /**
     * This method receives an input of player bed times (in 24 hour format), and makes a arraylist of IPlayer objects, then informs the user of the order of turns (with the earliest bed time being the first one to play)
     * TODO: Finish implementation, for now it merely assigns any order received as the initial turn order
     * @param bedtimes the inputted bed times
     */
    @Override
    public void receivePlayersInfo(ArrayList<Integer> bedtimes, ArrayList<String> names) {

        if (bedtimes.size() <= 0 | bedtimes.size() > 4) {
            throw new IllegalArgumentException("The game is only playable for 1 to 4 players inclusive.");
        }

        if(bedtimes.size() != names.size()){
            throw new IllegalArgumentException("There must be an equal amout of bedtimes and names");
        }

        if(bedtimes.size() + players.size() > 4){
            throw new IllegalArgumentException("The game is only playable for 1 to 4 players inclusive.");
        }

        for (int playerNum = 0; playerNum < bedtimes.size(); playerNum++) {
            IPlayer player = new Player(names.get(playerNum));
            player.initializeTokens();
            players.add(player);
            // TODO: Temporary until better logic is introduced
            if (playerNum == 0) {
                setFirstSheep(player);
            }
        }


    }


    /**
     * Dictates the first sheep based on absolute distance ahead of, or towards, the pillow.
     * If a player's winks are beyond the pillow token's position, then that is considered a higher score than being closer to the pillow.
     * If a player's winks are less than the pillow token's position, then that is considered a lower score.
     */
    @Override
    public void decideFirstSheep() {

        ArrayList<Integer> distances = new ArrayList<>();
        for (IPlayer player : players) {
            int distance = (player.getPillowToken().getPositionOnBoard()) - (player.getWinks());
            distances.add(distance);
        }


        int winnerOfPhase = 0;
        int previousPosition = 0;
        for (int positionOnArrayList = 1; positionOnArrayList < distances.size(); positionOnArrayList++) {
            int currentPosition = distances.get(positionOnArrayList);

            // Compare the current position with the previous position
            if (currentPosition > previousPosition) {
                winnerOfPhase = positionOnArrayList;
                previousPosition = currentPosition;
            }
        }
        setFirstSheep(players.get(winnerOfPhase));
    }


    /**
     * sets the current first sheep to the given IPlayer
     * @param player player being set as first sheep
     */
    @Override
    public void setFirstSheep(IPlayer player) {
        currentFirstSheep = player;
    }

    /**
     * returns the IPlayer who is the current first sheep
     * @return player who is currently first sheep
     */
    @Override
    public IPlayer getCurrentFirstSheep() {
        return currentFirstSheep;
    }

    /**
     * returns current player's hand
     * @return current player's cards in their hand
     */
    @Override
    public Card[][] getCurrentHand(){
        return getCurrentTurn().getHand();
    }


}
