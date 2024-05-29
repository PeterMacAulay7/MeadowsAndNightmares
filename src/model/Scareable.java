/**
 * increments and decrements a Scareable object's number of Scares
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface Scareable {

    /**
     * increments number of scares
     */
    void incrementScare();

    /**
     * decrements number of scares
     */
    void decrementScare();

    /**
     * resets the number of scares
     */
    void resetScare();
}
