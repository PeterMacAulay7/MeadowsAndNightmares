/**
 * Builds SheepCards
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class CardBuilder implements ICardBuilder {
    private SheepCard sheepCard;
    private int movableDistance;
    private int winks;
    private int catchableZzz;
    private int minimumPlayersRequired;
    private String description;
    private boolean hasAndStatement;
    private boolean hasSecondMoveOption;
    private int otherMovableDistance;

    /**
     *builds SheepCards
     * @return new SheepCard
     */
    public SheepCard buildSheepCard() {
        sheepCard = new SheepCard();
        sheepCard.setCatchableZzz(catchableZzz);
        sheepCard.setMovableDistance(movableDistance);
        sheepCard.setWinks(winks);
        sheepCard.setMinimumPlayersRequired(minimumPlayersRequired);
        sheepCard.setDescription(description);
        sheepCard.setHasSecondMoveOption(hasSecondMoveOption);
        sheepCard.setOthermovableDistance(otherMovableDistance);
        sheepCard.setHasAndStatement(hasAndStatement);
        return sheepCard;
    }

    /**
     * sets movableDistance on card
     * @param movableDistance distance movable by user of Card
     * @return ICardBuilder object with movable distance of given value
     */
    @Override
    public ICardBuilder withMovableDistance(int movableDistance) {
        this.movableDistance = movableDistance;
        return this;
    }

    /**
     * sets gainable winks on card
     * @param winks number of winks gainable by user of Card
     * @return ICardBuilder object with winks of given value
     */
    @Override
    public ICardBuilder withWinks(int winks) {
        this.winks = winks;
        return this;
    }

    /**
     * sets catchable Zzzs on card
     * @param catchableZzz number of catchable Zzzs by user of Card
     * @return ICardBuilder object with catchable Zzzs of given value
     */
    @Override
    public ICardBuilder withCatchableZzz(int catchableZzz) {
        this.catchableZzz = catchableZzz;
        return this;
    }

    /**
     * sets description on card
     * @param description of card
     * @return ICardBuilder object with description of given value
     */
    @Override
    public ICardBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * sets if card has an and or an or
     * @param hasAndStatement main decision of card is and or or
     * @return ICardBuilder object with a main decision
     */
    @Override
    public ICardBuilder withHasAndStatement(boolean hasAndStatement) {
        this.hasAndStatement = hasAndStatement;
        return this;
    }

    /**
     * sets if a card has a second or for movable distances
     * @param hasSecondMoveOption second decision on Card for another movable distance
     * @return ICardBuilder object with a second possible decision for moving
     */
    @Override
    public ICardBuilder withHasSecondMoveOption(boolean hasSecondMoveOption) {
        this.hasSecondMoveOption = hasSecondMoveOption;
        return this;
    }

    /**
     * sets the minimum players requires to use the card
     * @param minimumPlayersRequired players required for card to be used
     * @return ICardBuilder object with a minimum players required
     */
    @Override
    public ICardBuilder withMinimumPlayersRequired(int minimumPlayersRequired) {
        this.minimumPlayersRequired = minimumPlayersRequired;
        return this;
    }

    /**
     * sets anohter movable distance on the card
     * @param otherMovableDistance other possible movable distance on card
     * @return ICardBuilder object with second movable distance for user
     */
    @Override
    public ICardBuilder withOtherMovableDistance(int otherMovableDistance) {
        this.otherMovableDistance = otherMovableDistance;
        return this;
    }
}
