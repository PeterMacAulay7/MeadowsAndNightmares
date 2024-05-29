/**
 * Interface for the Score Board controller's Behaviour
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.6
 */

package controller;

import model.IReferenceTile;
import model.IScoreBoard;
import view.Viewable;

public interface IScoreBoardController {
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

    /**
     * Sets the appropriate memory reference to the appropriate memory object of type Board
     * @param board the memory reference to set to
     */
    public void setBoard(IScoreBoard board);

    /**
     * Sets the appropriate memory reference to the appropriate memory object
     * @param referencetile the memory reference to set to
     */
    public void setReferenceTile(IReferenceTile referencetile);

    /**
     * Retrieves the Reference Tile
     * @return the memory reference of the reference tile
     */
    public IReferenceTile getReferenceTile();
}
