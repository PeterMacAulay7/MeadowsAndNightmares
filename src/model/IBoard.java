/**
 * Represents boards in sheepy time by
 * having a list of a list of ITokens
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.List;

public interface IBoard {

    /**
     * returns the ITokens board. This is a list comprised of lists holding IToken objects
     * @return lists of IToken on board representing spaces
     */
    public List<List<IToken>> getBoard();
}
