/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package view;


public class GameBoardView implements Viewable {
    private String contents = "";


    /**
     * Displays to user
     */
    @Override
    public void display() {
        System.out.println(contents);
    }

    /**
     * Setter for contents
     *
     * @param input the received input
     */
    @Override
    public void inputFromController(String input) {
        contents = input;
    }

    /**
     * Displays the market for the Resting Phase
     *
     * @param in the market
     */
    public void displayMarket(String in) {
        System.out.println("Dream Tile Market: ");
        System.out.println(in + "\n");

    }
}
