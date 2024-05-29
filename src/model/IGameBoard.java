/**
 * represents the GameBoard
 * along with IBoard properties it also has a DreamTileArray to hold DreamTiles
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;


import model.dreamtiles.DreamTile;

public interface IGameBoard extends IBoard {

    /**
     * returns the DreamTile Array
     * @return array of DreamTiles on gameboard
     */
    public DreamTile[] getDreamTileArray();

    /**
     * returns the DreamTIleDeck
     * @return object of IDreamTileDeck
     */
    public IDreamTileDeck getDreamTileDeck();

    /**
     * sets the DreamTile Deck
     * @param deck DreamTile deck associated with gameboard
     */
    public void setDreamTileDeck(IDreamTileDeck deck);

    /**
     * reveals DreamTile market
     */
    public DreamTile[] revealMarket();


}
