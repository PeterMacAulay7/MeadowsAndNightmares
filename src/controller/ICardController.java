/**
 * Interface for the Card controller's behaviour
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.6
 */

package controller;

import model.Card;
import view.Viewable;

public interface ICardController {

    /**
     * Feeds the view new information, updating its state.
     */
    public void updateView();

    /**
     * Parses the appropriate model parts for the view
     * @return the parsed relevant model parts
     */
    public String getFirstCardDescription();

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
     * Setter for the model object of this controller
     * @param card the model object relevant
     */
    public void setCard(Card[] card);
}
