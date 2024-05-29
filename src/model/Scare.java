/**
 * increments the number of scares on a Scareable object
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

public class Scare implements IScare {

    /**
     * increments the number of scare on a Scareable object
     * @param scareable object to be scared
     */
    @Override
    public void scareScareable(Scareable scareable) {
        scareable.incrementScare();
    }
}
