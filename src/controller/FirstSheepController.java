/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package controller;

import model.IFirstSheep;
import view.FirstSheepView;
import view.Viewable;

import java.util.ArrayList;

public class FirstSheepController implements IFirstSheepController {


    private final Viewable view;
    private IFirstSheep firstSheep;

    public FirstSheepController() {
        this.view = new FirstSheepView();

    }

    /**
     * Instructs the view to update itself
     */
    public void updateView() {
        view.inputFromController(parseModel());
    }

    /**
     * Parses the model for the view
     *
     * @return
     */
    @Override
    public String parseModel() {
        return firstSheep.getCurrentFirstSheep().getName();
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
     *
     * @return view
     */
    @Override
    public Viewable getView() {
        return view;
    }

    /**
     * Receiver for player's bed times and names
     *
     * @param bedtimes of players
     * @param names    of the players
     */
    @Override
    public void passPlayersInfo(ArrayList<Integer> bedtimes, ArrayList<String> names) {
        firstSheep.receivePlayersInfo(bedtimes, names);
    }

    /**
     * Sets the first sheep
     *
     * @param firstSheep the player who is the first sheep
     */
    @Override
    public void setFirstSheep(IFirstSheep firstSheep) {
        this.firstSheep = firstSheep;
    }

    /**
     * Getter for the First Sheep
     *
     * @return firstSheep
     */
    @Override
    public IFirstSheep getFirstSheep() {
        return firstSheep;
    }

}


