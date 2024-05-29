package view;

import java.util.Scanner;

public class InputView implements Viewable {

    private String contents = "";
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Setter for the contents
     *
     * @param input the received input
     */
    @Override
    public void inputFromController(String input) {
        contents = input;
    }


    /**
     * Displays for the user
     */
    @Override
    public void display() {
        System.out.println(contents);
    }

    /**
     * Takes input from User
     *
     * @return the user input
     */
    public String userInput() {
        String input = scanner.nextLine();
        return input;
    }

    public void closeScanner(){
        scanner.close();
    }
}
