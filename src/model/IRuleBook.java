/**
 * Holds the rules of the game
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public interface IRuleBook {

    /**
     * returns the rules
     * @return rules
     */
    public String getRules();

    /**
     * sets the rules
     * @param rules of the game
     */
    public void setRules(String rules);
}
