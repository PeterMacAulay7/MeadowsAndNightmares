/**
 * Interface for the GameView's behaviour
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.6
 */

package view;

public interface IGameView extends Viewable {

    /**
     * Adds a view to the GameView
     * @param view the view object to add
     */
    void addView(Viewable view);

    /**
     * Displays the Rulebook
     * @param ruleBook the book to display
     */
    void displayRuleBook(String ruleBook);
}
