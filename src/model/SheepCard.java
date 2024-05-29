/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class SheepCard extends Card {
    private int minimumPlayersRequired;
    private int movableDistance;
    private int winks;
    private int catchableZzz;
    private boolean hasAndStatement;
    private int othermovableDistance;
    private boolean hasSecondMoveOption;

    protected SheepCard() {
        minimumPlayersRequired = 0;
        movableDistance = 0;
        winks = 0;
        catchableZzz = 0;
        hasAndStatement = false;
        othermovableDistance = 0;
        hasSecondMoveOption = false;
    }

    /**
     * Gets the minimum number of players required
     *
     * @return minimumPlayersRequired to use card
     */
    public int getMinimumPlayersRequired() {
        return minimumPlayersRequired;
    }

    /**
     * Setter for minimum players required
     *
     * @param minimumPlayersRequired number of players required
     */
    public void setMinimumPlayersRequired(int minimumPlayersRequired) {
        this.minimumPlayersRequired = minimumPlayersRequired;
    }

    /**
     * Gets the number of winks
     *
     * @return gainable winks on card
     */
    public int getWinks() {
        return winks;
    }

    /**
     * Setter for number of winks
     *
     * @param winks the total number of winks
     */
    public void setWinks(int winks) {
        this.winks = winks;
    }

    /**
     * Getter for the movable distance
     *
     * @return movableDistance
     */
    public int getMovableDistance() {
        return movableDistance;
    }

    /**
     * Sets movable distance
     *
     * @param movableDistance the distance to set it to
     */
    public void setMovableDistance(int movableDistance) {
        this.movableDistance = movableDistance;
    }

    /**
     * Gets the number of catchable Zzzs
     *
     * @return catchableZzz
     */
    public int getCatchableZzz() {
        return catchableZzz;
    }

    /**
     * Setter for catchable Zzzs
     *
     * @param catchableZzz the number of Zzzs that can be caught
     */
    public void setCatchableZzz(int catchableZzz) {
        this.catchableZzz = catchableZzz;
    }

    /**
     * Gets whether the sheep card can perform an action that includes a conjunction
     *
     * @return if the card has an and or an or decision
     */
    public boolean getHasAndStatement() {
        return hasAndStatement;
    }

    /**
     * Setter for And instance variable
     *
     * @param hasAndStatement value
     */
    public void setHasAndStatement(boolean hasAndStatement) {
        this.hasAndStatement = hasAndStatement;
    }

    /**
     * Gets whether the card has more than one movable distance options
     *
     * @return othermovableDistance on card
     */
    public int getOthermovableDistance() {
        return othermovableDistance;
    }

    /**
     * Setter for otherMovableDistance instance variable
     *
     * @param othermovableDistance of card
     */
    public void setOthermovableDistance(int othermovableDistance) {
        this.othermovableDistance = othermovableDistance;
    }

    /**
     * Gets whether the card can move or perform another action
     *
     * @return true if it can, false otherwise
     */
    public boolean getHasSecondMoveOption() {
        return hasSecondMoveOption;
    }

    /**
     * Setter for moveOr instance variable
     *
     * @param hasSecondMoveOption value
     */
    public void setHasSecondMoveOption(boolean hasSecondMoveOption) {
        this.hasSecondMoveOption = hasSecondMoveOption;
    }

    /**
     * Setter for the description of the sheep card
     *
     * @param description of card
     */
    public void setDescription(String description) {
        super.setDescription(description);
    }

}
