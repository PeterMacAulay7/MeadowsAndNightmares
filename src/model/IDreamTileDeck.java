/**
 * specialized deck for dreamtiles
 * and reveals the market
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import model.dreamtiles.DreamTile;
import java.util.ArrayList;

public interface IDreamTileDeck {

    /**
     * creates the DreamTile deck
     */
    public void createDeck();

    /**
     * returns the market array
     * @return array of dreamtiles
     */
    public DreamTile[] revealMarket();

    /**
     * returns the DreamTile Deck
     */
    public ArrayList<DreamTile> getDeck();

    /**
     * draws a DreamTile from the deck
     * @return
     */
    public DreamTile draw();

}
