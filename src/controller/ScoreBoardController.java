/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package controller;

import model.*;
import view.ScoreBoardView;
import view.Viewable;

import java.util.List;

public class ScoreBoardController implements IScoreBoardController {
    private final Viewable view;
    private IScoreBoard board;
    private IReferenceTile referenceTile;


    public ScoreBoardController() {
        this.view = new ScoreBoardView();
    }


    /**
     * Instructs the view to update itself
     */
    @Override
    public void updateView() {
        view.inputFromController(parseModel());
    }

    /**
     * Parses the model
     *
     * @return the parsed model
     */
    @Override
    public String parseModel() {

        if (board == null){
            throw new NullPointerException("Board not set");
        }

        // Processing the information of the board, then storing it as a basetype of String.
        StringBuilder stringBuilder = new StringBuilder();

        //    // Decorative border
        //    stringBuilder.append("╔"); // Top-left corner
        //    for (int k = 0; k < (board.getBoard().size() + 1 + 3) * 2; k++) {
        //        stringBuilder.append("═"); // Horizontal line
        //    }
        //    stringBuilder.append("╗\n"); // Top-right corner and newline

        // Preparation for the Reference Tile
        stringBuilder.append("Reference Tile Rules Active for: ");
        if (referenceTile != null) {
            stringBuilder.append(referenceTile.getNumPlayers()).append(" players.");
        }
        stringBuilder.append("\n");

        for (int i = 0; i < board.getBoard().size(); i++) {
            List<IToken> tokens = board.getBoard().get(i);

            if (!tokens.isEmpty()) {
                stringBuilder.append(i + 1).append("(");

                for (IToken token : tokens) {

                    // Get the type of token to present it with a letter
                    String identifier = "?";
                    if (token instanceof IWinkToken) {
                        identifier = "W";
                    } else if (token instanceof IPillowToken) {
                        identifier = "P";
                    }

                    stringBuilder
                           .append(identifier);
                }
                stringBuilder.append(")");
            } else {
                // Append empty space if there are no tokens
                stringBuilder.append(i + 1).append(" ");
            }

            //     // Decorative border
            //     stringBuilder.append("╚"); // Bottom-left corner
            //     for (int k = 0; k < (board.getBoard().size() + 1 + 3) * 2; k++) {
            //         stringBuilder.append("═"); // Horizontal line
            //     }
            //     stringBuilder.append("╝"); // Bottom-right corner
        }
        return stringBuilder.toString();
    }

    /**
     * Instructs the view to display itself
     */
    @Override
    public void displayView() {
        view.display();
    }

    /**
     * Getter for the view object assocaited with this
     *
     * @return the view
     */
    @Override
    public Viewable getView() {
        return view;
    }

    @Override
    /**
     * Setter for the Board
     * @param board the memory reference to set to
     */
    public void setBoard(IScoreBoard board) {
        this.board = board;
    }

    /**
     * Setter for the Reference Tile
     *
     * @param referencetile the memory reference to set to
     */
    @Override
    public void setReferenceTile(IReferenceTile referencetile) {
        this.referenceTile = referencetile;

    }

    /**
     * Getter for the Reference Tile memory reference
     *
     * @return the reference tile memory reference
     */
    @Override
    public IReferenceTile getReferenceTile() {
        return referenceTile;
    }


}
