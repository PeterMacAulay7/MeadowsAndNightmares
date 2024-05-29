/**
 * used to scare Scareable objects
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface IScare {

    /**
     * takes in a Scareable object and increments it's number of scares
     * @param scareable object
     */
    public void scareScareable(Scareable scareable);
}
