/**
 * determines what IToken passed through the fence position on the board
 * ends the phase if an INightmareToken crosses, sets winks if an ISheepToken crosses
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.ArrayList;

public class Fence implements IFence {
    private IRacingPhase racingPhase;



    /**
     * adds 5 winks to the IPlayer who owns the ISheepToken
     * moves the IWinkToken of the IPlayer who owns the ISheepToken 5 space
     * @param token sheep that passed the fence
     */
    @Override
    public void passedThrough(ISheepToken token) {
        IPlayer player = ((ISheepToken) token).getPlayer();
        player.setWinks(player.getWinks() + 5);
        player.getWinkToken().changeInPosition(5);
    }

    /**
     * sets all the IPlayers isOut to true
     */
    @Override
    public void passedThrough(INightmareToken token) {
        ArrayList<IPlayer> players = racingPhase.getFirstSheep().getTurnSequence();
        for (IPlayer player : players) {
            if (!player.getIsOut()) {
                player.setWinks(0);
                player.getPillowToken().changeInPosition(-(player.getPillowToken().getPositionOnBoard()));
                player.setIsOut(true);
            }
        }
    }

    /**
     * sets an instance of IRacingPhase
     * @param phase IRacingPhase associated with fence
     */
    @Override
    public void setRacingPhase(IRacingPhase phase) {
        this.racingPhase = phase;
    }


}
