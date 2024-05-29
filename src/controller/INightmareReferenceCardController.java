/**
 * Interface for the behaviour of the Nightmare Reference Card's controller
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.6
 */

package controller;

import model.NightmareReferenceCard;
import view.Viewable;

public interface INightmareReferenceCardController {
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
     * Sets the memory reference to the relevant model object
     * @param referenceCard the nighmare reference card memory address to set to
     */
    public void setReferenceCard(NightmareReferenceCard referenceCard);
}
