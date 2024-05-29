/**
 * This is a card builder for objects of ICard.
 * builder was chosen due to Sheep and Nightmare cards complex constructors.
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface ICardBuilder {

    /**
     * builds a SheepCard object
     * @return new sheep card
     */
    public SheepCard buildSheepCard();


    /**
     * adds ability for user of card to move the inputted distance
     * @param distance distance movable by user of Card
     * @return ICardBuilder object with movable distance of given value
     */
    public ICardBuilder withMovableDistance(int distance);

    /**
     * adds ability for user of card to gain the inputted amount of winks
     * @param winks number of winks gainable by user of Card
     * @return ICardBuilder object with winks of given value
     */
    public ICardBuilder withWinks(int winks);

    /**
     * adds ability for user of card to catch the inputted amout of Zzzs
     * @param catchableZzz number of catchable Zzzs by user of Card
     * @return ICardBuilder object with catchable Zzzs of given value
     */
    public ICardBuilder withCatchableZzz(int catchableZzz);

    /**
     * adds ability for card to have a description
     * @param description description of card
     * @return ICardBuilder object with a given description
     */
    public ICardBuilder withDescription(String description);

    /**
     * adds ability for card to have an and or, or statement
     * @param and main decision of card is and or or
     * @return ICardBuilder object with a main decision
     */
    public ICardBuilder withHasAndStatement(boolean and);

    /**
     * add ability for card to have a or statement for multiple
     * movable distances
     * @param moveOr second decision on Card for another movable distance
     * @return ICardBuilder object with a second decision for movable distances
     */
    public ICardBuilder withHasSecondMoveOption(boolean moveOr);

    /**
     * add ability to have a second movable distance
     * @param otherMovableDistance second movable distance 
     * @return ICardBuilder object with second movable distance
     */
    public ICardBuilder withOtherMovableDistance(int otherMovableDistance);

    /**
     * adds minimum number of players required for card to be in play
     * @param minimumPlayersRequired players required to use card
     * @return ICardBuilder object with minimum players required
     */
    public ICardBuilder withMinimumPlayersRequired(int minimumPlayersRequired);
}
