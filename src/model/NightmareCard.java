/**
 * a specialized card for instructing INightmareToken
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class NightmareCard extends Card {
    private boolean jump;
    private boolean scareAdjacent;
    private boolean moveToWebToken;
    private int movableDistance;
    private int webTokenMovableDistance;

    /**
     * sets instance variables to default states
     */
    protected NightmareCard() {
        jump = false;
        scareAdjacent = false;
        moveToWebToken = false;
        movableDistance = 0;
        webTokenMovableDistance = 0;
    }

    /**
     * returns jump
     * @return if card has the ability to jump
     */
    public boolean getJump() {
        return jump;
    }

    /**
     * sets jump boolean on the card
     * @param jump -whether card has ability to jump
     */
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    /**
     * return scareAdjacent
     * @return wheter card can scare tokens on adjacent spaces
     */
    public boolean getScareAdjacent() {
        return scareAdjacent;
    }

    /**
     * sets scareAdjacent boolean on the card
     * @param scareAdjacent -whether card has ability to scare token on adjacent spaces 
     */
    public void setScareAdjacent(boolean scareAdjacent) {
        this.scareAdjacent = scareAdjacent;
    }

    /**
     * returns moveToWebToken
     * @return whether card can move to web token
     */
    public boolean getMoveToWebToken() {
        return moveToWebToken;
    }

    /**
     * sets moveToWebToken boolean on the card
     * @param moveToWebToken -whether care has ability to move to web token
     */
    public void setMoveToWebToken(boolean moveToWebToken) {
        this.moveToWebToken = moveToWebToken;
    }

    /**
     * returns movableDistance
     * @return movable distance of card
     */
    public int getMovableDistance() {
        return movableDistance;
    }

    /**
     * returns weTokenMovableDistance
     * @return web token movable distance
     */
    public int getWebTokenMovableDistance() {
        return webTokenMovableDistance;
    }

    /**
     * sets the webTokenMovableDistance on the card
     * @param webTokenMovableDistance -distance movable of web token
     */
    public void setWebTokenMovableDistance(int webTokenMovableDistance) {
        this.webTokenMovableDistance = webTokenMovableDistance;
    }

    /**
     * sets description on the card
     * @param description on card
     */
    public void setdescription(String description) {
        super.setDescription(description);
    }

    /**
     * sets the movableDistance on the card
     * @param movableDistance on card
     */
    public void setDistance(int movableDistance) {
        this.movableDistance = movableDistance;
    }

}
