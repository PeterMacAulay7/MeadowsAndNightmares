/**
 * Represents the resting phase portion of the game
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import model.dreamtiles.DreamTile;

import java.util.ArrayList;

public class RestingPhase implements IRestingPhase {

    private IGameBoard board;

    /**
     * returns the valid spots to place a DreamTile
     * @return spaces that DreamTile can be placed
     */
    public ArrayList<Integer> occupiedDreamTileSpots() {
        ArrayList<Integer> listOfValidDreamTileIndices = new ArrayList<>();
        DreamTile[] DreamTileArray = board.getDreamTileArray();
        for (int i = 0; i < DreamTileArray.length; i++) {
            if (DreamTileArray[i] != null) {
                listOfValidDreamTileIndices.add(i);
            }
        }
        return listOfValidDreamTileIndices;
    }

    /**
     * allows an IPlayer to place a DreamTile on the given position
     * @param position for DreamTile to be placed on
     * @param dreamTile to be placed
     * @return if a tile is placed or not
     */
    public boolean placeTile(int position, DreamTile dreamTile) {
        DreamTile[] dreamTiles = board.getDreamTileArray();
        if (dreamTiles[position] == null) {
            dreamTiles[position] = dreamTile;
            return true;
        }
        return false;
    }

    /**
     * determines if phase is over
     * @return if phase is over
     */
    @Override
    public boolean isPhaseOver() {
        return false;
    }

    /**
     * returns the instance of IGameBoard
     * @return IGameBoard associated with resting phase
     */
    @Override
    public IGameBoard getBoard() {
        return board;
    }

    /**
     * sets an instance of IGameBoard
     * @param inBoard assoicated with resting pahse
     */
    @Override
    public void setBoard(IGameBoard inBoard) {
        board = inBoard;
    }

}
