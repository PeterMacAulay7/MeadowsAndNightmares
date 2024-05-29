import controller.GameController;
import controller.IGameController;
import model.exceptions.ExceededAllowedAmountOfPlayers;

/**
 * Entry point for the program
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

class Main {
    public static void main(String[] args) throws ExceededAllowedAmountOfPlayers, InterruptedException {



        IGameController gameController = new GameController();
        gameController.initializeGame();
    }


}