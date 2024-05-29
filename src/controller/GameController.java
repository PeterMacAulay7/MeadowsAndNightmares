/**
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package controller;

import model.*;
import model.dreamtiles.DreamTile;
import model.exceptions.ExceededAllowedAmountOfPlayers;
import view.GameView;
import view.IGameView;
import view.InputView;
import view.Viewable;

import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameController implements IGameController {
    private final IGameView gameView;
    private final InputView input;
    private IFirstSheepController firstSheepController;
    private IGameBoardController gameBoardController;
    private IScoreBoardController scoreBoardController;
    private INightmareReferenceCardController nightmareReferenceCardController;
    private ICardController cardController1;
    private ICardController cardController2;
    private IGameInformation gameInformation;
    private boolean isSinglePlayerMode;
    private final int  MAX_NUM_PLAYERS = 4;
    private final int  MAX_NUM_NIGHTMARES = 3;
    private final int  MIN_NUM_NIGHTMARES = 1;
    private final int MAX_TIME = 24;
    private final int MIN_TIME = 0;
    private final int DEFAULT_RESTING_PHASE_ZZZS = 2;
    private final int SINGLE_PLAYER = 1;
    private final int ADJUST_FOR_ZERO_INDEX = 1;
    private final int MAX_CARDS = 2;
    private final int ERROR_VALUE = -1;
    private final int ZERO = 0;
    private ArrayList<Integer> bedTimes;
    ArrayList<String> names;
    private int choice;

    // Constructor
    public GameController() {

        // Initialize objects
        this.gameView = new GameView();
        this.input = new InputView();
        addViewToGameView(input);
        bedTimes  = new ArrayList<>();
        names = new ArrayList<>();
        isSinglePlayerMode = true;
    }

    /**
     * Initializes the controllers and GameInformation.
     * begins the game.
     *
     * @throws ExceededAllowedAmountOfPlayers if we exceed the amount of players allowed to play
     * @throws InterruptedException     if the timer had been interrupted for taking an action
     */
    @Override
    public void initializeGame() throws ExceededAllowedAmountOfPlayers, InterruptedException {

        //gets players information e.i., bedtimes and names
        acquirePlayerInformation();

        //displays to the user when they entered
        input.inputFromController("You entered the following information\n" + "bed times: " + bedTimes + "\n" + "Player names: " + names + "\n");
        input.display();

        //Initializes controllers and adds views to GameView
        initializeControllersAndSetViews();

        // Creating model by only creating a single model object which allows the model to create itself.
        this.gameInformation = new GameInformation();

        //gets the choice of nightmare for the game
        int choice = getNightmareChoice();

        //assigns model objects to their respective controllers
        assignModelToController(choice);

        // start game
        beginGame();
    }

    /**
     * Ensures two dream tiles are placed at the 5th and 10th position on the baord.
     * Play turns until the game is over
     * @throws ExceededAllowedAmountOfPlayers
     * @throws InterruptedException
     */
    private void beginGame() throws ExceededAllowedAmountOfPlayers, InterruptedException {
        boolean firstRoundDreamTilesPlaced = true;
        displayNightmare();
        while (!isGameOver()) {

            //Place two DreamTiles at the start of the game.
            //The game requires a placement at halfway on the board and at the end of the board
            if (firstRoundDreamTilesPlaced) {
                firstRoundDreamTilesPlaced = gameInformation.placeFirstDreamTiles();
            }

            /*
             plays a turn 
             */
            playTurn();
        }
        // Game over
        input.inputFromController("Game ended! Thank you for playing.");
        displayInput();
        input.closeScanner();        
    }

    /**
     * plays a turn.
     * displays the game status.
     * inacts a turns with the current player depending on which phase is active.
     * increments the turn
     * @throws ExceededAllowedAmountOfPlayers
     * @throws InterruptedException
     */
    private void playTurn() throws ExceededAllowedAmountOfPlayers, InterruptedException {
        IPlayer currentPlayer = gameInformation.getCurrentPlayer();
        ArrayList<IPlayer> players = gameInformation.getTurnSequence();
        displayGameStatus(currentPlayer);
        if (!gameInformation.checkRacingPhaseOver()) {
            playRacingPhase(currentPlayer, players);
        } else {
            playRestingPhase(currentPlayer, players);
        }
        gameInformation.incrementTurn();
    }

    /**
     * displays the current status of the game
     * @param currentPlayer
     */
    private void displayGameStatus(IPlayer currentPlayer) {
        // Display the current game status including player's turn and phase
        gameView.inputFromController("It is " + currentPlayer.getName() + "'s turn. You have " + currentPlayer.getZzzTokens().size() + " Zzz's in your supply.");
        displayGameView();
    }

    /**
     * completes one turn of the racing phase.
     * requires current player to have a full hand(2 cards in hand).
     * plays nightmare card immediately if it is drawn
     * player will choose a card and an action on the card to play.
     * player can choose to keep playing or call it a night.
     * @param currentPlayer -the player who is currently taking action
     * @param players -a list of all the players in the game
     * @throws InterruptedException
     */
    private void playRacingPhase(IPlayer currentPlayer, ArrayList<IPlayer> players) throws InterruptedException {

        /*
        EXPERIMENTAL DRAWING
         */
        Random rand = new Random();

        // lists
        ArrayList<SheepCard> sheepCards = gameInformation.getSheepCardDeck();
        ArrayList<NightmareCard> nightmareCards = gameInformation.getNightmareCardDeck();

        int totalCards = 0;
        float sheepCardUpperBound = 0;

        // Implement racing phase logic
        cardController1.setCard(gameInformation.getCurrentHand()[0]);
        cardController2.setCard(gameInformation.getCurrentHand()[1]);

        //checks to see if racing phase is over
        if (gameInformation.checkRacingPhaseOver()){
            // we know the game is over, move to resting phase
            gameInformation.endRacingPhase();
            return;
        }

        //gets players out
        int outPlayersCount = gameInformation.getPlayersOut();
        int playersCalledItANight = gameInformation.getPlayersCalledItANight();
        if (outPlayersCount + playersCalledItANight < players.size()) {

            // if current player isn't out or called it a night
            if (!currentPlayer.getIsOut() && !currentPlayer.getCalledItANight()) {

                boolean discarded = false;
                while (currentPlayer.getNumCards() < MAX_CARDS) {

                    /*
                       EXPERIMENTAL RANDOM DRAWING CONTINUED
                     */
                    totalCards = sheepCards.size() + nightmareCards.size();
                    sheepCardUpperBound = 0.0f;
                    if (totalCards != 0) {
                        sheepCardUpperBound = (float) sheepCards.size() / totalCards;
                    }
                    float rolledProbability = rand.nextFloat(0, 1 + Float.MIN_VALUE);



                    if (rolledProbability <= sheepCardUpperBound){
                        // draw a sheep card
                       gameInformation.drawSheepCard(currentPlayer);
                    }
                    else if (rolledProbability > sheepCardUpperBound){
                        playNightmareCard(currentPlayer);
                        return;
                    }

                    //if only one player is not out or called it a night
                    if ((outPlayersCount + playersCalledItANight + 1 == players.size())) {
                        //discard a card
                        discarded = discardCard(currentPlayer, discarded);
                    }
                } // all cards added

                if(!currentPlayer.getIsOut() && !currentPlayer.getCalledItANight()){
                    // Player gets to pick what card to play
                    displayHand();
                    
                    //gets the which card from the hand will be played
                    int cardNum = chooseCard();

                    //sets chosen card as variable
                    SheepCard card = currentPlayer.getHand()[cardNum - ADJUST_FOR_ZERO_INDEX][0];
                    
                    //gets the choice of action to be played for the card
                    int choice = getCardChoice(currentPlayer, card);

                    //Takes in user input to determine number of Zzzs and which DreamTile to place said Zzzs on
                    if ((choice == 2 && card.getCatchableZzz() > 0 && !card.getHasSecondMoveOption()) || (choice == 3 && card.getCatchableZzz() > 0 && card.getHasSecondMoveOption())) {
                        //Allows player to catch up to the amount of Zzzs on the card they drew
                        catchZzzsInRacingPhase(currentPlayer, card);                     
                    }
                    
                    //displays which choice from the card is being taken action on
                    input.inputFromController("Performing the number " + choice + " action on card: " + card.getDescription());
                    displayInput();
                    TimeUnit.SECONDS.sleep(gameInformation.getSheepCardPlayDelay());

                    //plays card
                    //if sheep passed the fence;
                    if (currentPlayer.resolve(cardNum, choice)) {
                        //ask user if they'd like to continue or call it a night
                        continueOrCallItANight(currentPlayer);
                    }
                    useDreamTile(currentPlayer);
                }
            }
        }
    }

    /**
     * One turn of the resting phase.
     * Makes current player either place a dream tile or catch 2 Zzzs.
     * If a dream tile is placed the player then catches Zzzs on it.
     * If the game board cannot fit any more dream tiles then the player will have to catch 2 Zzzs
     * @param currentPlayer -player who is currently taking action
     * @param players -a list of all players in the game
     */
    private void playRestingPhase(IPlayer currentPlayer, ArrayList<IPlayer> players) {
        gameInformation.endRacingPhase();
        ArrayList<Integer> occupiedDreamTileSpots = new ArrayList<>();

        //for each player
        for (IPlayer player : players) {
            //input.inputFromController("It is " + currentPlayer.getName() + "'s turn\n");
            //displayGameView();
            gameBoardController.revealMarket();

            //gets list of occupied DreamTile spots on the game board
            occupiedDreamTileSpots = gameInformation.occupiedDreamTileSpots();

            //gets the current DreamTiles on the game board
            DreamTile[] DreamTileArray = gameInformation.getDreamTileArray();

            //gets the current number of open spots for DreamTiles on the game board
            int openSpotCount = occupiedDreamTileSpots.size();

            //initialize local variables
            int marketChoice = ERROR_VALUE;
            int placementIndex = ERROR_VALUE;

            //if there are open spots for DreamTiles on the gameboard
            if(openSpotCount < DreamTileArray.length){

                //gets the DreamTile chosen from the market
                marketChoice = getMarketChoice();

                //gets the index the player wishes to place the DreamTile at
                placementIndex = getDreamTilePlacementIndex();

                //if marketChoice and placementIndex aren't invalid values
                if(marketChoice != ERROR_VALUE && placementIndex != ERROR_VALUE){
                    //place chosen DreamTile at chosen index
                    placeDreamTile(currentPlayer, placementIndex, marketChoice);
                }
            }
            else{
                catchZzzsInRestingPhase(currentPlayer);
            }

            // set player status to no longer out
            player.setIsOut(false);
            player.getSheepToken().resetScares();

        }

        //Create new deck to ensure that racing phase can be played for a whole round
        gameInformation.createCardDeck();
        
    }

    private boolean isGameOver() {
        // Check if the game is over
        int amountOfPlayersWhoReachedPillow = ZERO;
        for (IPlayer player: gameInformation.getTurnSequence()){
            if (player.getWinkToken().getPositionOnBoard() >= player.getPillowToken().getPositionOnBoard()){
                amountOfPlayersWhoReachedPillow++;
            }
        }
        if (amountOfPlayersWhoReachedPillow >= gameInformation.getTurnSequence().size()){
            return true;
        }
        return false;
    }

    // Helper methods to validate user input

    /**
     * Helper for validating user card choice
     *
     * @param userInput card choice
     * @return true if valid, false otherwise
     */
    private boolean isValidCardChoice(String userInput) {
        return "1".equals(userInput) || "2".equals(userInput);
    }

    /**
     * Helper for validating user choice
     *
     * @param userInput choice
     * @return true if valid, false otherwise
     */
    private boolean isValidMarketChoice(String userInput) {
        return "1".equals(userInput) || "2".equals(userInput) || "3".equals(userInput) || "4".equals(userInput) || "5".equals(userInput);
    }

    /**
     * Determines if user input is a valid input
     * @param sleepInput
     * @return if bed inputted bedtime is valid
     */
    private boolean isValidBedTime(String sleepInput){
        if (sleepInput.isEmpty()) {
            return false; // Return false if userInput is empty
        }

        String[] sleepValues = sleepInput.split(",");
        if (sleepValues.length > MAX_NUM_PLAYERS){
        throw new ExceededAllowedAmountOfPlayers("Exceeded amount of players allowed to play the game");
        }

        for (String sleepValue : sleepValues) {
            try{
                int bedTime = Integer.parseInt(sleepValue.trim());
                //if the current bedTime is greater than 24 or less than 0
                if(bedTime > MAX_TIME || bedTime < MIN_TIME){
                    //return false because it is an invalid bedtime
                    return false;
                }
                bedTimes.add(bedTime);
            } catch (NumberFormatException e) {

                return false; // Return false if userInput cannot be parsed to an integer
            }
            
        }
        
        return true;
    }

    /**
     * Determines if user entered "yes" with any variation of capital or non-capital letters.
     * Returns true if user did enter a form of "yes"
     * @param userInput
     * @param dreamTile
     * @param player
     * @return Returns true if user did enter a form of "yes"
     */
    private boolean isValidChoice(String userInput, DreamTile dreamTile, IPlayer player){
        if(userInput.isEmpty()){
            return false;
        }

        if(userInput.equalsIgnoreCase("yes")){
            dreamTile.activateTile(player);
            input.inputFromController("Activating " + dreamTile.getDescription());
            displayInput();
        }
        return true;
    }

    /**
     * determines if the number of players matches the number of bed times given
     * @return if inputted names are valid
     */
    private boolean isValidNames(String nameInput){
        //if nameInput is empty return false
        if(nameInput.isEmpty()){
            return false;
        }

        //creates an array of all names given
        String[] namesOfPlayersValues = nameInput.split(",");

        //if the number of names given is not equal to the number of bedTimes given return false
        if(namesOfPlayersValues.length != bedTimes.size()){
            return false;
        }

        //adds each name to the names list
        for (String name : namesOfPlayersValues) {
            names.add(name.trim());
        }

        return true;
    }

    /**
     * determines if the Nightmare choice made by the user was a valid choice
     * @param userInput
     * @return true if user inputted a valid choice for the nightmare
     */
    private boolean isValidNightmareChoice(String userInput){
        if(userInput.isEmpty()){
            return false;
        }
        try{
            choice = Integer.parseInt(userInput);
            if(choice > MAX_NUM_NIGHTMARES || choice < MIN_NUM_NIGHTMARES){
                return false;
            }
        } catch (NumberFormatException e) {
            return false; // Return false if userInput cannot be parsed to an integer
        }
        return true;
    }

    /**
     * Helper for validating user choice
     *
     * @param userInput choice
     * @return true if valid, false otherwise
     */
    private boolean isValidIndex(String userInput, int catchableZzzs) {
        if (userInput.isEmpty()) {
            return false; // Return false if userInput is empty
        }

        try {
            int index = Integer.parseInt(userInput);
            DreamTile pickedTile = gameInformation.getDreamTileArray()[index-ADJUST_FOR_ZERO_INDEX];
            if (!gameInformation.getRestingPhase().occupiedDreamTileSpots().contains(index - ADJUST_FOR_ZERO_INDEX)) {
                return false;
            }
            else if(catchableZzzs < pickedTile.getInitialZzzsRequired()){
                input.inputFromController("The card does not allow you to catch the amout of Zzzs nessecary to use this DreamTile");
                displayInput();
                return false;
            }


        } catch (NumberFormatException e) {
            return false; // Return false if userInput cannot be parsed to an integer
        }

        return true;
    }

    /**
     * Helper for validating user choice
     *
     * @param userInput choice
     * @return true if valid, false otherwise
     */
    private boolean isValidNumZzzs(String userInput, int catchableZzzs) {
        if (userInput.isEmpty()) {
            return false; // Return false if userInput is empty
        }

        try {
            int numZzzs = Integer.parseInt(userInput);
            if (numZzzs < ZERO || numZzzs > catchableZzzs) {
                return false; // Return false if index is out of range
            }
            

        } catch (NumberFormatException e) {
            return false; // Return false if userInput cannot be parsed to an integer
        }

        return true;
    }

    /**
     * Determines if the user input a valid choice for an action on their card
     *
     * @param userInput choice
     * @return true if user has input a valid choice for an action from their card, false otherwise
     */
    private boolean isValidCardAction(String userInput, SheepCard card) {
        if (!card.getHasSecondMoveOption()) {
            return "1".equals(userInput) || "2".equals(userInput);

        } else if (card.getWinks() == ZERO && card.getCatchableZzz() == ZERO) {
            return "1".equals(userInput);
        } else {
            return "1".equals(userInput) || "2".equals(userInput) || "3".equals(userInput);
        }
    }

    /**
     * Determines if the user's input is a valid place to place a DreamTile
     * @param userInput
     * @return true if it is a valid place, false otherwise
     */
    private boolean isValidDreamTilePlacement(String userInput){
        if(userInput.isEmpty()){
            return false;
        }
        try{
            //parse userInput to int
            int attemptedSpot = Integer.parseInt(userInput);
        
            //gets list of occupied DreamTile spots
            ArrayList<Integer> occupiedDreamTileSpots = gameInformation.occupiedDreamTileSpots();
            for(int i : occupiedDreamTileSpots){
                //attempted placement spot equals an occupied spot
                if(i == attemptedSpot){
                    return false;
                }
            }
            //if attempted spot is not on the board
            if(attemptedSpot > 9 || attemptedSpot < 0){
                return false;
            }
        }
        catch (NumberFormatException e) {
            return false; // Return false if userInput cannot be parsed to an integer
            
        }
        return true;
    }

    /**
     * Display method for the main UI
     */
    @Override
    public void displayGameView() {
        //update views
        firstSheepController.updateView();
        gameBoardController.updateView();
        scoreBoardController.updateView();
        gameView.display();
    }

    /**
     * Displays only the hand
     */
    private void displayHand() {
        //updates views
        cardController1.updateView();
        cardController2.updateView();
        cardController1.displayView();
        cardController2.displayView();
    }

    /**
     * Displays the input from the model or controller
     */

    private void displayInput() {
        input.display();
    }

    /**
     * Displays the rulebook
     *
     * @return
     */
    @Override
    public String displayRuleBook() {
        return gameInformation.getRuleBook().getRules();
    }

    public void displayNightmare(){
        nightmareReferenceCardController.updateView();
        nightmareReferenceCardController.displayView();
    }


    /**
     * Adds to the Game view
     *
     * @param view the Viewable object to add
     */
    @Override
    public void addViewToGameView(Viewable view) {
        gameView.addView(view);
    }

    /**
     * acquire information about players to initialize the game
     */
    private void acquirePlayerInformation() {
        acquireBedTimes();
        acquirePlayerNames();
    }
    
    /**
     * gets player's bedtimes from last night and stores the information
     */
    private void acquireBedTimes() {
        if( isSinglePlayerMode == false){
            String sleepInput = "";
            // Acquire total number of players, and their hours of sleep, store as variable.
            while(!isValidBedTime(sleepInput)){
                input.inputFromController("Enter last night's bed times (comma-separated and in 24 hour format) in order of earliest bed time to latest: \nExample: 18, 22, 19\n");
                displayInput();
                sleepInput = input.userInput();
            }
        }
        else{
            bedTimes.add(SINGLE_PLAYER);
        }
    }
    
    /**
     * gets the player's names and stores the information
     */
    private void acquirePlayerNames() {
        String namesOfPlayers = "";
        //Acquire player names
        while(!isValidNames(namesOfPlayers)){
            input.inputFromController("Enter the player's name: \n");
            displayInput();
            namesOfPlayers = input.userInput();
        }
    }

    /**
     * initializes the controllers and game information
     */
    private void initializeControllersAndSetViews() {
        initializeControllers();
        SetViews();
    }
    
    /**
     * initializes controllers
     */
    private void initializeControllers() {
        // Initialize controllers

        cardController1 = new CardController();
        cardController2 = new CardController();

        gameBoardController = new GameBoardController();
        nightmareReferenceCardController = new NightmareReferenceCardController();
        scoreBoardController = new ScoreBoardController();
        firstSheepController = new FirstSheepController();
    }
    
    /**
     * Adds views to gameview to form a composition in GameView
     */
    private void SetViews() {
        // Acquiring Viewable memory references to form a composition in GameView
        gameView.addView(cardController1.getView());
        gameView.addView(cardController2.getView());
        gameView.addView(firstSheepController.getView());
        gameView.addView(gameBoardController.getView());
        gameView.addView(scoreBoardController.getView());
        gameView.addView(input);
    }

    /**
     * gets user's choice of nightmare for the game
     * @return
     */
    private int getNightmareChoice(){
        String nightmareInput = "";
        //gets the choice for the nightmare
        while(!isValidNightmareChoice(nightmareInput)){
            input.inputFromController("Enter your nightmare choice by using the number:\n1 for Nightmare Wolf\n2 for Bump In the Night\n3 for Sinister Spider\n: ");
            displayInput();
            nightmareInput = input.userInput();
        }
        return Integer.parseInt(nightmareInput);
    }

    /**
     * calls generate model to generate model objects
     * assigns model objects to their respective controller
     * @param choice -chosen Nightmare for the game
     */
    public void assignModelToController(int choice){
        //generates the model
        gameInformation.generateModel(choice);


        // Provide a memory reference of relevant model objects to their Controllers
        firstSheepController.setFirstSheep(gameInformation.getFirstSheep());

        // Generate players and First Sheep
        firstSheepController.passPlayersInfo(bedTimes, names);

        nightmareReferenceCardController.setReferenceCard(gameInformation.getNightmareReferenceCard());
        gameBoardController.setBoard(gameInformation.getGameBoard());
        scoreBoardController.setBoard(gameInformation.getScoreBoard());
        cardController1.setCard(gameInformation.getCurrentHand()[0]);
        cardController2.setCard(gameInformation.getCurrentHand()[1]);

        // Continue generating model now that we made the connections and fed relevant input

        gameInformation.generateModelStageTwo();
        scoreBoardController.setReferenceTile(gameInformation.getReferenceTile());
    }
    
    /**
     * plays a Nightmare card and notifies player
     * @param currentPlayer
     * @throws InterruptedException
     */
    private void playNightmareCard(IPlayer currentPlayer) throws InterruptedException{
        //gameInformation.drawNightmareCard(currentPlayer);
        NightmareCard card = gameInformation.getCardDeck().drawNightmareCard();
        currentPlayer.addCard(card);

        //displays card action to users
        input.inputFromController("Nightmare action: " + card.getDescription());
        displayInput();
        TimeUnit.SECONDS.sleep(gameInformation.getNightmareCardPlayDelay()); // In case more than one nightmare card ends up being played this will be useful
    }

    /**
     * discards a card from the players hand
     * @param currentPlayer
     * @param discarded -boolean to determine if a card has been discarded this round
     * @return the state of the discarded boolean
     * @throws InterruptedException
     */
    private boolean discardCard(IPlayer currentPlayer, boolean discarded) throws InterruptedException{
        if (!discarded && currentPlayer.getNumCards() >= SINGLE_PLAYER) {
            // The condition is met, so we execute the following block
            currentPlayer.getHand()[0][0] = null;
            input.inputFromController("Discarded one card because you are the only player active.");
            input.display();
            TimeUnit.SECONDS.sleep(gameInformation.getSheepCardPlayDelay());

            // Set discarded to true after executing the block
            discarded = true;
        }
        return discarded;
    }

    /**
     * gets the users choice for the action they will take from the options given on their card
     * @param currentPlayer
     * @param card
     * @return chosen action number
     */
    private int getCardChoice(IPlayer currentPlayer, SheepCard card){
        String userInput = "";
        //takes in user input to decide the action of the card
        while (!isValidCardAction(userInput, card)) {
            //if there is one choice on card
            if (card.getCatchableZzz() == ZERO && card.getWinks() == ZERO && !card.getHasSecondMoveOption()) {
                
                input.inputFromController("Enter 1 to move " + card.getMovableDistance() + " spaces");
            }
            // there are only two move options on the card
            else if(card.getHasSecondMoveOption() && card.getCatchableZzz() == ZERO && card.getWinks() == ZERO){
                input.inputFromController("Enter 1 to move " + card.getMovableDistance() + " spaces, or enter 2 to move " + card.getOthermovableDistance());
            }
            //if there is 2 choices on card
            else if (!card.getHasSecondMoveOption() || (card.getHasSecondMoveOption() == true && card.getWinks() == 0 && !card.getHasSecondMoveOption())) {
                if(card.getCatchableZzz() == ZERO){
                    input.inputFromController("Enter 1 to move " + card.getMovableDistance() + " spaces, or enter 2 to gain " + card.getWinks() + " winks");
                }
                else{
                    input.inputFromController("Enter 1 to move " + card.getMovableDistance() + " spaces, or enter 2 to catch " + card.getCatchableZzz() + " Zzzs");
                }
            }
            //if there is 3 choices on card
            else {
                if(card.getCatchableZzz() == ZERO){
                    input.inputFromController("Enter 1 to move " + card.getMovableDistance() + " spaces, or enter 2 to move " + card.getOthermovableDistance() + " spaces, or enter 3 to gain " + card.getWinks() + " winks");
                }
                else{
                    input.inputFromController("Enter 1 to move " + card.getMovableDistance() + " spaces, or enter 2 to move " + card.getOthermovableDistance() + " spaces, or enter 3 to catch " + card.getCatchableZzz() + " Zzzs");
                }
            }
            displayInput();

            userInput = input.userInput();
        }
        return Integer.parseInt(userInput);
    }

    /**
     * allows user to choose which card in their hand to play
     * @return chosen card number
     */
    private int chooseCard(){
        String userInput = "";
        while (!isValidCardChoice(userInput)) {
            input.inputFromController("Input the corresponding integer number. \n1. Play the first card \n2. Play the second card\n");
            displayInput();
            userInput = input.userInput();
        }
        return Integer.parseInt(userInput);
    }
    
    /**
     * allows the user to catch Zzzs in the racing phase based on what the card they drew says
     * @param currentPlayer
     * @param card -contains the information of how many Zzzs can be caught
     */
    private void catchZzzsInRacingPhase(IPlayer currentPlayer, SheepCard card){
        //takes in user input for DreamTile index
        String userInput = "";
        while (!isValidIndex(userInput, card.getCatchableZzz())) {
            input.inputFromController("Enter the index number of the Dream Tile you'd like to catch Zzz's on");
            displayInput();
            userInput = input.userInput();
        }

        //converts userinput to integer
        int index = (Integer.parseInt(userInput)) - ADJUST_FOR_ZERO_INDEX;
        currentPlayer.getActionable().setIndex(index);                    
        //takes in user input for number of Zzzs
        userInput = "";
        while (!isValidNumZzzs(userInput, card.getCatchableZzz())) {
            input.inputFromController("Enter the number of Zzz tokens you wish to catch.");
            displayInput();
            userInput = input.userInput();
        }
        int numZzzs = Integer.parseInt(userInput);
        currentPlayer.getActionable().setNumZzzs(numZzzs);
    }

    /**
     * determines if a player would like to call it a night or continue playing the round
     * @param currentPlayer
     */
    private void continueOrCallItANight(IPlayer currentPlayer){
        //displayGameView();
        displayInput();
        input.inputFromController("Your sheep passed the Fence. Would you like to end the night (enter 'night') or continue playing (enter 'continue')?");
        boolean validInput = false;
        String userInput = "";
        while (!validInput) {
            displayInput();
            userInput = input.userInput();
            
            if ("night".equalsIgnoreCase(userInput)) {
                // End the night
                currentPlayer.setCalledItANight(true);
                currentPlayer.getSheepToken().setToDefaultPosition();
                validInput = true;
                return;
            } else if ("continue".equalsIgnoreCase(userInput)) {
                // Continue playing
                validInput = true;
            } else {
                // Invalid input, display an error message
                input.inputFromController("Invalid input. Please enter 'night' or 'continue'.");
                displayInput();
            }
        }
    }

    /**
     * Allows user to use a DreamTile that their Sheep is standing on and they have placed Zzz's on
     * @param currentPlayer
     */
    private void useDreamTile(IPlayer currentPlayer){
        //gets DreamTile that user's sheep is on
        DreamTile currentDreamTile = gameInformation.getDreamTileArray()[currentPlayer.getSheepToken().getPositionOnBoard()];
        
        //if DreamTile is not null
        if( currentDreamTile != null){
            String userInput = "";
            //if player has enough Zzzs on the DreamTile to use it
            if((currentDreamTile.getPlayerZzzs(currentPlayer).size() >= currentDreamTile.getInitialZzzsRequired()) && currentDreamTile != null){
                
                //asks user if they would like to use the DreamTile they are standing on
                while(!isValidChoice(userInput, currentDreamTile, currentPlayer)){
                    input.inputFromController("Would you like to use your DreamTile on this spot: if so type yes. you currently have " + currentDreamTile.getPlayerZzzs(currentPlayer).size() + " Zzz's on this tile");
                    displayInput();
                    userInput = input.userInput();
                }
            }
        }
    }

    /**
     * gets the user's choice of DreamTile from the market
     * @return the user's choice from the market
     */
    private int getMarketChoice(){
        String userInput = "";
        //checks if user input is a valid position on in the market and there are open spots to place a dreamTile
        while (!isValidMarketChoice(userInput)) {
            // take user input to choose DreamTile from market
            input.inputFromController("Choose a DreamTile from the market by inputting 1 to 4");
            displayInput();
            userInput = input.userInput();
        }
        //returns market choice in integer form and adjusted for the array compared to what the user sees on the display
        return Integer.parseInt(userInput) - ADJUST_FOR_ZERO_INDEX;
    }

    /**
     * gets the user's choice of where to place their DreamTile on the game board
     * @return index user picked to place DreamTile on game board
     */
    private int getDreamTilePlacementIndex(){
        String userInput = "";
        
        //checks if the user input is a valid index to place a dreamTile and there are open spots to place a dreamTile
        while(!isValidDreamTilePlacement(userInput)){
            //take user input to choose a place to place the DreamTile
            input.inputFromController("Choose an index from 1-10 that doesn't already have a DreamTile to place your DreamTile on");
            displayInput();
            userInput = input.userInput();
        }
        //returns placement index in integer form and adjusted for the array compared to what the user sees on the display
        return Integer.parseInt(userInput) - ADJUST_FOR_ZERO_INDEX;
    }

    /**
     * places a DreamTile on the board given a place to put it and a DreamTile to place
     * @param currentPlayer 
     * @param placementIndex -index chosen to place the DreamTile
     * @param marketChoice -Chosen DreamTile to place
     */
    private void placeDreamTile(IPlayer currentPlayer, int placementIndex, int marketChoice){

        //place DreamTile
        gameInformation.getRestingPhase().placeTile(placementIndex, gameInformation.getMarket()[marketChoice]);

        //gets reference to placed DreamTile
        DreamTile dreamTile = gameInformation.getDreamTileArray()[placementIndex];

        //if the player has Zzzs to use and the DreamTile doesn't require more than the DEFAULT_RESTING_PHASE_ZZZS
        if(currentPlayer.getZzzTokens().size() > 1 && dreamTile.getInitialZzzsRequired() <= DEFAULT_RESTING_PHASE_ZZZS){

            //player catches DEFAULT_RESTING_PHASE_ZZZS amount of Zzzs on the DreamTile they places
            currentPlayer.catchZzzs(placementIndex, DEFAULT_RESTING_PHASE_ZZZS);

            //displays to user that Zzzs were caught on the DreamTile they placed
            input.inputFromController("You caught " + DEFAULT_RESTING_PHASE_ZZZS + " Zzz's on the DreamTile you picked!");
        }
        //if DreamTile requires more Zzzs than the DEFAULT_RESTING_PHASE_ZZZS amount
        else if(dreamTile.getInitialZzzsRequired() > DEFAULT_RESTING_PHASE_ZZZS){

            //displays to user that the DreamTile requires more Zzzs than can be placed right now
            input.inputFromController("DreamTile requires more than " + DEFAULT_RESTING_PHASE_ZZZS + " Zzz's to be placed. You can catch Zzz's on it later");
        }
        //Player does not have enough Zzzs
        else{
            input.inputFromController("You do not have enough Zzz's to catch Zzz's on this tile");
        }
        //Displays messages
        displayInput();
    }

    /**
     * player can catch up to 2 Zzzs
     * @param currentPlayer
     */
    private void catchZzzsInRestingPhase(IPlayer currentPlayer){
        int zzzCount = 0;
        String userInput = "";
        while(zzzCount < DEFAULT_RESTING_PHASE_ZZZS){
            userInput = "";
            while(!isValidIndex(userInput, DEFAULT_RESTING_PHASE_ZZZS)){
                input.inputFromController("Please enter a DreamTile to catch Zzz's on");
                userInput = input.userInput();
                displayInput();;
            }
            int position = Integer.parseInt(userInput);

            userInput = "";
            while(!isValidNumZzzs(userInput, DEFAULT_RESTING_PHASE_ZZZS)){
                input.inputFromController("Please enter how many Zzz's you'd like to catch on this tile. (you can catch to total this round)");
                userInput = input.userInput();
                displayInput();
            }
            int numZzzs = Integer.parseInt(userInput);
            currentPlayer.catchZzzs(position, numZzzs);
            zzzCount += numZzzs;
        }
    }
}
