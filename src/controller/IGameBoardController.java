/**
 * Interface for the Game Board's controller behaviour
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.6
 */
package controller;

import model.IGameBoard;
import view.Viewable;

public interface IGameBoardController {

    /**
     * Feeds the view new information, updating its state.
     */
    public void updateView();

    /**
     * Parses the appropriate model parts for the view
     * @return the parsed relevant model parts
     */
    public String parseModel();

    /**
     * Instructs the view to display itself
     */
    public void displayView();

    /**
     * Returns the view object to the caller
     * @return the Viewable object
     */
    public Viewable getView();

    void setBoard(IGameBoard board);

    /**
     * Reveals the market for Resting Phase
     */
    public void revealMarket();

}
