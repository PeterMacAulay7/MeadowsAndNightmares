/**
 * Interface for the Game controller's behaviour
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package controller;

import model.exceptions.ExceededAllowedAmountOfPlayers;
import view.Viewable;

public interface IGameController {

    /**
     * Initializes the game by first constructing the Controllers, requesting input from User to determine FirstSheep,
     * then creating the first model object, then passing the memory references of appropriate model objects to their respective Controllers
     */
    public void initializeGame() throws ExceededAllowedAmountOfPlayers, InterruptedException;

    /**
     * Informs all relevant controllers to update their view, then requests the Game view to display itself
     */
    public void displayGameView();

    /**
     * Requests the GameView to display the rulebook
     * @return rulebook
     */
    public String displayRuleBook();

    /**
     * Adds a Viewable to the GameView's viewable list in order to make a composite
     * @param view the Viewable object to add
     */
    public void addViewToGameView(Viewable view);

}
