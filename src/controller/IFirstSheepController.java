/**
 * Interface for the First Sheep's controller's behaviour
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.6
 */

package controller;

import model.IFirstSheep;
import view.Viewable;

import java.util.ArrayList;

// IFirstSheepController interface
public interface IFirstSheepController {
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


    public void passPlayersInfo(ArrayList<Integer> bedtimes, ArrayList<String> names);


    public void setFirstSheep(IFirstSheep firstSheep);

    public IFirstSheep getFirstSheep();
}