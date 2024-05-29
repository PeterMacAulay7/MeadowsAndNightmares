/**
 * This class serves as the main game flow class, as well as the exception class and the only controller to create a model object.
 *
 * @implSpec In future revisions, it may be best to move the beginGame method and its relevant logic to GameInformation or some other gameflow class within the model to better adhere to MVC.
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.6
 */

package controller;

import model.NightmareReferenceCard;
import view.NightmareReferenceCardView;
import view.Viewable;

public class NightmareReferenceCardController implements INightmareReferenceCardController {
    private NightmareReferenceCard referenceCard;
    private final Viewable view;


    public NightmareReferenceCardController() {
        this.view = new NightmareReferenceCardView();
    }

    /**
     * Instructs the view to update itself by feeding it information
     */
    @Override
    public void updateView() {
        view.inputFromController(referenceCard.getDescription());
    }

    /**
     * Parses the model
     * @return the parsed model
     */
    @Override

    public String parseModel() {
        return null;
    }

    /**
     * Instructs the view to display itself
     */
    @Override
    public void displayView() {
        view.display();
    }

    /**
     * Getter for the view
     * @return Viewable object
     */
    @Override

    public Viewable getView() {
        return view;
    }

    /**
     * Setter method
     * @param referenceCard the nighmare reference card memory address to set to
     */
    @Override
    public void setReferenceCard(NightmareReferenceCard referenceCard) {
        this.referenceCard = referenceCard;
    }
}

