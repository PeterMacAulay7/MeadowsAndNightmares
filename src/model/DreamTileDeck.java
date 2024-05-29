/**
 * specialized version of Deck to hold DreamTiles
 * displays market of DreamTiles
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import model.dreamtiles.DreamTile;
import model.dreamtiles.DreamTileFactory;
import model.dreamtiles.IDreamTileFactory;

import java.util.ArrayList;
import java.util.Random;

public class DreamTileDeck implements IDreamTileDeck {
    private final ArrayList<DreamTile> deck;
    private final DreamTile[] marketArray;
    private final IDreamTileFactory dreamTileFactory;
    private Random rand;

    /**
     * creates new deck ArrayList<DreamTile>, DrawFromDeck, dreamTileFactory, and DreamTile[4]
     */
    public DreamTileDeck() {
        deck = new ArrayList<DreamTile>();
        marketArray = new DreamTile[4];
        dreamTileFactory = new DreamTileFactory();
        rand = new Random();
    }

    /**
     * creates all DreamTiles and adds them to the deck ArrayList
     */
    public void createDeck() {
        String[] dreamTileNames = dreamTileFactory.getDreamTileNames();

        for (String dreamTileName : dreamTileNames) {
            DreamTile dreamTile = dreamTileFactory.createDreamTile(dreamTileName);
            deck.add(dreamTile);
        }
    }

    /**
     * returns the DreamTile deck
     * @return all DreamTiles in the deck via ArrayList
     */
    public ArrayList<DreamTile> getDeck() {
        return deck;
    }

    /**
     * reveals 4 DreamTiles
     */
    @Override
    public DreamTile[] revealMarket() {
        for (int i = 0; i < marketArray.length; i++) {
            if (marketArray[i] == null) {
                marketArray[i] = draw();
            }
        }
        return marketArray;
    }

    /**
     * draws a DreamTile from the deck
     * @return DreamTile
     */
    public DreamTile draw() {
        int randomIndex = rand.nextInt(deck.size());
        return deck.remove(randomIndex);
    }
}
