/**
 * * This class takes in an affector , and the target. The affector performs and action on the target
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import model.dreamtiles.DreamTile;

public interface IActionable {

    /**
     * takes in an affector of Type DreamTilevthat will be taking the action
     * takes in a target that will be having the action taken on it
     * @param affector affects the given target
     * @param target has actions taken on it by the affector
     */
    public void actionWithDreamTile(DreamTile affector, IPlayer target);

    /**
     * takes in an affector of type NightmareCard that will be taking the action
     * takes in a target that will be having the action taken on it
     * @param affector affects the given target
     * @param target has actions taken on it by the affector
     */
    boolean actionWithNightmareCard(NightmareCard affector, INightmareToken target);

    /**
     * takes in an affector of type SheepCard that will be taking the action
     * takes in a target that will be having the action taken on it
     * @param affector affects the given target
     * @param target has actions taken on it by the affector
     * @return whether the Nightmare Token passed the fence
     */
    boolean actionWithSheepCard(SheepCard affector, IPlayer target, int choice);

    /**
     * sets the index variable to the given value
     * @param index
     */
    void setIndex(int index);

    /**
     * sets the numZzzs variable to the given value
     * @param numZzzs
     */
    void setNumZzzs(int numZzzs);
}
