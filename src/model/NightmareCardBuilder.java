/**
 * builds NightmareCard
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class NightmareCardBuilder implements INightmareCardBuilder {
    private NightmareCard nightmareCard;
    private String description;
    private boolean scareAdjacent;
    private boolean moveToWebToken;
    private boolean jump;
    private int movableDistance;
    private int webTokenMovableDistance;


    /**
     * build NightmareCard
     * @return
     */
    public NightmareCard buildNightmareCard() {
        nightmareCard = new NightmareCard();
        nightmareCard.setJump(jump);
        nightmareCard.setDescription(description);
        nightmareCard.setDistance(movableDistance);
        nightmareCard.setMoveToWebToken(moveToWebToken);
        nightmareCard.setScareAdjacent(scareAdjacent);
        nightmareCard.setWebTokenMovableDistance(webTokenMovableDistance);
        return nightmareCard;
    }

    /**
     * sets movableDistance
     * @param movableDistance on card
     * @return INightmareCardBuilder object with movable distance
     */
    @Override
    public INightmareCardBuilder withMovableDistance(int movableDistance) {
        this.movableDistance = movableDistance;
        return this;
    }

    /**
     * sets scareAdjacent
     * @param scareAdjacent user of card can scare scareable objects on adjacent spaces
     * @return INightmareCardBuilder object with ability to scare scareable objects on adjacent spaces
     */
    @Override
    public INightmareCardBuilder withScareAdjacent(boolean scareAdjacent) {
        this.scareAdjacent = scareAdjacent;
        return this;
    }

    /**
     * sets moveToWebToken
     * @param moveToWebToken user of card can move to web token
     * @return INightmareCardBuilder object with ability to move to web token
     */
    @Override
    public INightmareCardBuilder withMoveToWebToken(boolean moveToWebToken) {
        this.moveToWebToken = moveToWebToken;
        return this;
    }

    /**
     * sets jump
     * @param jump user of card can jump to spaces
     * @return INightmareCardBuilder object with ability to jump
     */
    @Override
    public INightmareCardBuilder withJump(boolean jump) {
        this.jump = jump;
        return this;
    }

    /**
     * sets description
     * @param description on card
     * @return INightmareCardBuilder object with description
     */
    @Override
    public INightmareCardBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * sets webTokenMovableDistance
     * @param webTokenMovableDistance user can move to web token
     * @return INightmareCardBuilder object with ability to move web token
     */
    @Override
    public INightmareCardBuilder withWebTokenMovableDistance(int webTokenMovableDistance) {
        this.webTokenMovableDistance = webTokenMovableDistance;
        return this;
    }
}
