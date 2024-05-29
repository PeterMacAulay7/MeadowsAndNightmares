/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package controller;

import model.*;
import model.dreamtiles.DreamTile;
import view.GameBoardView;
import view.Viewable;

import java.util.List;

public class GameBoardController implements IGameBoardController {
    private final GameBoardView view;
    private IGameBoard board;

    public GameBoardController() {
        this.view = new GameBoardView();
    }

    /**
     * Parses model to view
     *
     * @return the parsed model as a String
     */
    @Override
    public String parseModel() {

        if (board == null){
            throw new NullPointerException("Board not set");
        }

        StringBuilder stringBuilder = new StringBuilder();

        // Decorative border
        stringBuilder.append("╔"); // Top-left corner
        for (int k = 0; k < (board.getBoard().size() + 1 + 3) * 2; k++) {
            stringBuilder.append("═"); // Horizontal line
        }
        stringBuilder.append("╗\n"); // Top-right corner and newline


        // Processing the information of the board, then storing it as a basetype of String.
        for (int i = 0; i < board.getBoard().size(); i++) {


            List<IToken> tokens = board.getBoard().get(i);
            // Check if there are tokens present before appending the enclosing symbols
            if (!tokens.isEmpty()) {
                if (i != 10) {
                    stringBuilder.append(i + 1).append("[");
                } else {
                    stringBuilder.append("F").append("[");
                }
                for (IToken token : tokens) {

                    // Get the type of token to present it with a letter
                    String identifier = "";
                    if (token instanceof SheepToken) {
                        if ((((SheepToken) token).getScares() > 0)) {
                            identifier = "s";
                        } else {
                            identifier = "S";
                        }
                    } else if (token instanceof NightmareToken) {
                        identifier = "N";
                    } else {
                        identifier = "W";
                    }

                    stringBuilder.append(identifier);
                }
                stringBuilder.append("]");
            } else {
                // Append empty space if there are no tokens
                if (i != 10) {
                    stringBuilder.append(i + 1).append(" ");
                }
            }
        }

        stringBuilder.append("\n");
        // Decorative border
        stringBuilder.append("╚"); // Bottom-left corner
        for (int k = 0; k < (board.getBoard().size() + 1 + 3) * 2; k++) {
            stringBuilder.append("═"); // Horizontal line
        }
        stringBuilder.append("╝"); // Bottom-right corner


        // Separate line for dreamtiles
        stringBuilder.append("\n");
        for (int i = 0; i < board.getDreamTileArray().length; i++) {
            DreamTile dreamTile = board.getDreamTileArray()[i];
            if (dreamTile != null) {
                stringBuilder.append("D-" + (i + 1)).append(" ");
                for (IZzzToken token : dreamTile.getTokens()) {
                    if (token.getInfinite()) {
                        stringBuilder.append("∞");
                    } else {
                        stringBuilder.append("Z");
                    }
                }
            }
        }


        return stringBuilder.toString();
    }


    /**
     * Instructs view to update itself
     */
    @Override

    public void updateView() {
        view.inputFromController(parseModel());
    }

    /**
     * Instructs view to display itself
     */
    @Override

    public void displayView() {
        view.display();
    }


    /**
     * Getter for the view
     *
     * @return view
     */
    @Override

    public Viewable getView() {
        return view;
    }

    /**
     * Setter for the Board
     *
     * @param board
     */
    @Override

    public void setBoard(IGameBoard board) {
        this.board = board;
    }

    /**
     * Feeds the market data to view so it can display it
     * TODO: Incomplete, does not show it properly
     */
    @Override
    public void revealMarket() {
        StringBuilder sb = new StringBuilder();
        DreamTile[] market;
        market = board.revealMarket();
        for (int count = 0; count < market.length; count++) {
            DreamTile dreamTile = market[count];
            sb.append(count + 1 + ". " + dreamTile.getDescription() + " (" + dreamTile.getInitialZzzsRequired()  + " Zzz Required)" +"\n");
        }
        view.displayMarket(sb.toString());
    }


}