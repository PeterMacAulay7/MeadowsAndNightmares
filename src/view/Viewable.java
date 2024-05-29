/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */


package view;
// private String contents = "";

public interface Viewable {

    /**
     * Takes input from the controller
     * @param input the received input
     */
    public void inputFromController(String input);

    /**
     * Displays and formats
     */
    public void display();


}