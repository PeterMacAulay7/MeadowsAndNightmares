/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package controller;

import model.Card;
import view.CardView;
import view.Viewable;


public class CardController implements ICardController {
    private final Viewable view;
    private Card[] card;

    public CardController() {
        this.view = new CardView();
    }

    /**
     * Instructs the view to update its state
     */
    @Override
    public void updateView() {
        view.inputFromController(getFirstCardDescription());
    }

    /**
     * Returns the Card's description for the Card at the 0 slot of card array
     *
     * @return description of Card at the 0 slot of card array
     */
    @Override
    public String getFirstCardDescription() {
        if (card.length == 0){
            throw new IndexOutOfBoundsException();
        }
        if(card != null) {
            return card[0].getDescription();
        }
        else{
            throw new IndexOutOfBoundsException("Index Out Of Bounds");
        }
    }

    /**
     * Instructs the view to display itself
     */
    @Override
    public void displayView() {
        view.display();
    }

    /**
     * Retrieves the view
     *
     * @return the view
     */
    @Override
    public Viewable getView() {
        return view;
    }

    /**
     * Setter method
     *
     * @param card the model object relevant
     */
    @Override
    public void setCard(Card[] card) {
        if (card.length == 0){
            throw new IndexOutOfBoundsException();
        }
        if (card != null) {
            this.card = card;
        }
    }
}
