/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package view;


import java.util.ArrayList;

public class GameView implements IGameView {
    private final ArrayList<Viewable> views;
    private String contents = "";


    public GameView() {
        this.views = new ArrayList<>();
    }

    /**
     * Displays the rule book
     *
     * @param ruleBook the book to display
     */
    public void displayRuleBook(String ruleBook) {
        System.out.println(ruleBook);
    }

    /**
     * Displays contents to user as well as all the views it is composed of
     */
    @Override
    public void display() {
        System.out.println(contents);
        for (Viewable view : views) {
            view.display();
        }
    }


    /**
     * Setter for contents
     *
     * @param input the received input
     */
    @Override
    public void inputFromController(String input) {
        contents = input;
    }


    /**
     * Adds a view to the composition
     *
     * @param view the view object to add
     */
    @Override
    public void addView(Viewable view) {
        views.add(view);
    }
}
