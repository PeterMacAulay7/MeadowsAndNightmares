/**
 * does logic for when an IToken crosses the fence on the GameBoard
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface IFence {

    /**
     * when an ISheepToken passes through the player will gain 5 winks and have the option to call it a night.
     * @param token the passing token
     */
    public void passedThrough(ISheepToken token);

    /**
     * when an ISheepToken passes through the player will gain 5 winks and have the option to call it a night.
     * @param token the passing token
     */
    public void passedThrough(INightmareToken token);

    /**
     * changes the phase
     * @param phase IRacingPhase associated with the fence
     */
    public void setRacingPhase(IRacingPhase phase);

}
