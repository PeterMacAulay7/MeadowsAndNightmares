/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package view;

public class CardView implements Viewable {
    private String contents = "";


    /**
     * Displays the contents
     */
    @Override
    public void display() {
        System.out.println(contents);
    }

    /**
     * Takes input from the controller
     *
     * @param input the received input
     */
    @Override
    public void inputFromController(String input) {
        contents = input;
    }


}
