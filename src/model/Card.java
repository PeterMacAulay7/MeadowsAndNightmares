/**
 * Abstract class for all types of cards with a description
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public abstract class Card {
    private String description = "";

    /**
     * returns the description of the Card
     * @return description of a Card
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the Card
     * @param description description of Card
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
