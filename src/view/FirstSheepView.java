/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package view;


public class FirstSheepView implements Viewable {
    private String contents = "";

    /**
     * Displays to user
     */
    @Override
    public void display() {
        System.out.println("Current First Sheep is: " + contents);
    }

    /**
     * Receives input from controller and sets it to contents
     *
     * @param input the received input
     */
    @Override
    public void inputFromController(String input) {
        contents = input;
    }


}
