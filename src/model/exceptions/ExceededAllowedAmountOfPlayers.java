/**
 * Exception class for total amount of players allowed.
 * Thrown during the selection of number of players if the number of players exceeds 4.
 * @author Faycal Kilali
 * @version 0.5
 */

package model.exceptions;

public class ExceededAllowedAmountOfPlayers extends RuntimeException {
    public ExceededAllowedAmountOfPlayers(String inString) {
        super(inString);
    }
}
