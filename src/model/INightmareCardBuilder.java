/**
 * Builds NightmareCards
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface INightmareCardBuilder {

    /**
     * builds a NightmareCard object
     * @return new NightmareCard
     */
    public NightmareCard buildNightmareCard();

    /**
     * adds ability for user of card to move the inputted distance
     * @param distance movable distance of the card
     * @return ICardBuilder object with a given movable distance
     */
    public INightmareCardBuilder withMovableDistance(int distance);

    /**
     * adds ability for user of card to scare on the adjacent spaces
     * @param scareAdjacent user of card can scare scareable objects on adjacent spaces
     * @return ICardBuilder object with ability to scare scareable objects on adjacent spaces
     */
    public INightmareCardBuilder withScareAdjacent(boolean scareAdjacent);

    /**
     * adds ability for user of card to move to where the web token is
     * @param moveToWebToken user of card can move to web token
     * @return ICardBuilder object with ability to move to web token
     */
    public INightmareCardBuilder withMoveToWebToken(boolean moveToWebToken);

    /**
     * adds ability for user to jump or not
     * @param jump user of card can jump spaces on the board
     * @return ICardBuilder object with ability to jump spaces on the board
     */
    public INightmareCardBuilder withJump(boolean jump);

    /**
     * adds ability for card to have a description
     * @param description description of card
     * @return ICardBuilderObject with description on Card
     */
    public INightmareCardBuilder withDescription(String description);

    /**
     * adds ability for user of card to move the web token
     * @param webTokenMovableDistance card can move web token a distance
     * @return ICardBuilder object with ability to move web token
     */
    public INightmareCardBuilder withWebTokenMovableDistance(int webTokenMovableDistance);
}
