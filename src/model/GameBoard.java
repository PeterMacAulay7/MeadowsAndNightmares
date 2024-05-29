/**
 * This class contains a List of List of ITokens called BoardList. Serves as the Main Game Board for the game.
 *
 * @implSpec Each List in BoardList may or may not contain an IToken. The GameBoardView can access this Board and pre-process it so that the GameBoardView can output it appropriately.
 * This GameBoard will always be of size 10.
 * This class also contains the Dream Tile Array, which can also be revealed in the GameBoardView in conjunction with the boardList's contents.
 * The Dream Tile Array will also contain Zzz tokens inside it. Those should also be processed and drawn by the GameBoardView.
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import model.dreamtiles.DreamTile;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements IGameBoard {
    private static final int SIZE = 11;
    private final DreamTile[] dreamTilesArray = new DreamTile[SIZE];
    private IDreamTileDeck dreamTileDeck;
    private final List<List<IToken>> boardList;


    /**
     * Constructor for GameBoard.
     * @implSpec The Tokens of players that have not taken a turn yet will be placed in position 10. Every other move operation will include mod 10.
     */
    public GameBoard() {

        boardList = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            boardList.add(new ArrayList<IToken>());
        }
    }

    /**
     * returns the boardList
     * @return  lists of ITokens on the gameboard representing spaces
     */
    @Override
    public List<List<IToken>> getBoard() {
        return boardList;
    }

    /**
     * returns the DreamTileArray
     * @return array of DreamTiles on gameboard
     */
    public DreamTile[] getDreamTileArray() {
        return dreamTilesArray;
    }

    /**
     * returns the dreamTileDeck
     * @return object of IDreamTileDeck
     */
    public IDreamTileDeck getDreamTileDeck() {
        return dreamTileDeck;
    }

    /**
     * sets the instance of IDreamTileDeck
     * @param deck deck associated with gameboard
     */
    @Override
    public void setDreamTileDeck(IDreamTileDeck deck) {
        this.dreamTileDeck = deck;
    }

    @Override
    public DreamTile[] revealMarket(){
        return dreamTileDeck.revealMarket();
    }
}
