/**
 * Uses DreamTiles, SheepCards, or NightmareCards to perform actions on ITokens or objects associated with IPlayer
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import model.dreamtiles.DreamTile;

import java.util.List;

public class Actionable implements IActionable {
    private int index;
    private int numZzzs;
    private final int SINGLE_MOVE = 1;

    /**
     * sets index and numZzzs variables to default of -1
     */
    public Actionable() {
        index = -1;
        numZzzs = -1;
    }

    /**
     * takes in an affector of Type DreamTile that will be taking the action
     * takes in a target of type IPlayer that will be having the action taken on it
     *
     * @param affector affects the given target
     * @param target has actions taken on it by the affector
     */
    @Override
    public void actionWithDreamTile(DreamTile affector, IPlayer target) {
        affector.activateTile(target);
    }

    /**
     * takes in an affector of Type SheepCard that will be taking the action
     * takes in a target of type IPlayer that will be having the action taken on it
     * takes in an integer choice for the action that will be taken.
     *
     * @param affector affects the given target
     * @param target has actions taken on it by the affector
     * @return whether the Sheep Token passed the fence
     */
    @Override
    public boolean actionWithSheepCard(SheepCard affector, IPlayer target, int choice) {
        // Check if passed fence
        boolean passed = false;

        int movableDistance = affector.getMovableDistance();
        int winks = affector.getWinks();
        int catchableZzz = affector.getCatchableZzz();
        boolean hasAndStatement = affector.getHasAndStatement();
        int othermovableDistance = affector.getOthermovableDistance();
        boolean hasSecondMoveOption = affector.getHasSecondMoveOption();

        //if there is only one moveable distance
        if (!hasSecondMoveOption) {
            //if the card specifies to move and do another action
            if (hasAndStatement) {
                choice = 0;
                // System.out.println("movables distance: "+movableDistance);
                passed = target.getSheepToken().changeInPosition(movableDistance);
                target.setWinks(winks);
                target.getWinkToken().changeInPosition(winks);

                while (catchableZzz > 0) {
                    if (index >= 0 && index <= 9) {
                        target.catchZzzs(index, numZzzs);
                        catchableZzz -= numZzzs;
                    }
                }
            }
            //if the player want to move the movable distance
            else if (choice == 1) {
                //System.out.println("movables distance: "+movableDistance);

                passed = target.getSheepToken().changeInPosition(movableDistance);
                //if the player wants to gain winks or catch Zzzs
            } else if (choice == 2) {
                target.setWinks(winks);
                target.getWinkToken().changeInPosition(winks);
                while (catchableZzz > 0) {
                    if (index >= 0 && index <= 9) {
                        target.catchZzzs(index, numZzzs);
                        catchableZzz -= numZzzs;
                    }
                }
            } else {
                throw new IllegalArgumentException("Choice must be 1 or 2");
            }
            //if there is another movable distance
        } else {
            //if player chooses to move the first movable distance
            if (choice == 1) {
                // System.out.println("movables distance: "+movableDistance);

                passed = target.getSheepToken().changeInPosition(movableDistance);
                //if the player chooses the move the other movable distance
            } else if (choice == 2) {
                passed = target.getSheepToken().changeInPosition(othermovableDistance);
            } else if (choice == 3) {
                target.setWinks(winks);
                target.getWinkToken().changeInPosition(winks);
                while (catchableZzz > 0) {
                    if (index >= 0 && index <= 9) {
                        target.catchZzzs(index, numZzzs);
                        catchableZzz -= numZzzs;
                    }
                }
            } else {
                throw new IllegalArgumentException("Choice must be 1, 2 or 3");
            }
        }

        return passed;
    }

    /**
     * takes actions on a target or type nightmareToken
     * given the instructions from an affector of type NightmareCard
     *
     * @param affector affects the given target
     * @param target has actions taken on it by the affector
     * @return whether the Nightmare Token passed the fence
     */
    @Override
    public boolean actionWithNightmareCard(NightmareCard affector, INightmareToken target) {
        boolean passed = false;
        boolean jump = affector.getJump();
        boolean scareAdjacent = affector.getScareAdjacent();
        boolean moveToWebToken = affector.getMoveToWebToken();
        int movableDistance = affector.getMovableDistance();
        int webTokenMovableDistance = affector.getWebTokenMovableDistance();

        if (scareAdjacent) {
            target.scareAdjacent();
        }
        else if (moveToWebToken) {
            //sets nightmareToken's position to the webTokens current position
            ISinisterSpider spider = (ISinisterSpider) target;
            passed = target.changeInPosition(spider.getWebToken().getPositionOnBoard());
            //sets the webToken position to the new position
            spider.getWebToken().changeInPosition(webTokenMovableDistance);
        } else if (!jump) {
            for (int i = 0; i < movableDistance; i++) {
                //moves position up 1 space
                passed = target.changeInPosition(SINGLE_MOVE);
                //gets the board
                List<List<IToken>> boardTokens = target.getBoard().getBoard();
                //gets the current space the nightmareToken is on
                List<IToken> currentSpace = boardTokens.get(target.getPositionOnBoard());
                //for each IToken in the current space
                for (IToken token : currentSpace) {
                    //if token is scareable
                    if (token instanceof Scareable scareable) {
                        //scare the Scareable objects
                        target.scare(scareable);
                    }
                }
            }
        } else {
            passed = target.changeInPosition(movableDistance);
            List<List<IToken>> boardTokens = target.getBoard().getBoard();
            //gets the current space the nightmareToken is on
            List<IToken> currentSpace = boardTokens.get(target.getPositionOnBoard());
            //for each IToken in the current space
            for (IToken token : currentSpace) {
                //if token is scareable
                if (token instanceof Scareable scareable) {
                    //scare the Scareable objects
                    target.scare(scareable);
                }
            }
        }
        return passed;
    }

    /**
     * sets the index to a number greater than or equal to 0
     * and less than or equal to 9
     *
     * @param index
     */
    public void setIndex(int index) {
        if (index >= 0 && index <= 9) {
            this.index = index;
        } else {
            throw new IllegalArgumentException("index must be greater than or equal to 0 and less than or equal to 9");
        }
    }

    public void setNumZzzs(int numZzzs) {
        if (numZzzs >= 0) {
            this.numZzzs = numZzzs;
        } else {
            throw new IllegalArgumentException("numZzzs must be greater than or equal to 0");
        }
    }
}
