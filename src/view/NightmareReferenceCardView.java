/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package view;


public class NightmareReferenceCardView implements Viewable {

    private String contents = "";


    /**
     * Displays the ocntents
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


}
