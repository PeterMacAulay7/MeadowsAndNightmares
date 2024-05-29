/**
 * Represents the resting phase portion of the game
 * sets variables important to the IRestingPhase and
 * determines if the Phase is over
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

import model.dreamtiles.DreamTile;

import java.util.ArrayList;

public interface IRestingPhase extends Phase {

    /**
     * Allows an IPlayer to place a DreamTile using a specified index
     * @param position on board to place DreamTile
     * @param dreamtile to be place
     * @return
     */
    public boolean placeTile(int position, DreamTile dreamtile);


    /**
     * determines if the Phase is over
     * @return if pahse is over
     */
    public boolean isPhaseOver();

    /**
     * returns the instance of IGameBoard
     * @return gameboard associated with resting phase
     */
    public IGameBoard getBoard();

    /**
     * sets and instance of IGameBoard
     * @param inBoard -board associated with resting phase
     */
    public void setBoard(IGameBoard inBoard);

    /**
     * returns an ArrayList<Integer> of valid spots to place a DreamTile
     * @return valid spots to place a DreamTile
     */
    public ArrayList<Integer> occupiedDreamTileSpots();

}
