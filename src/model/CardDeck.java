/**
 * TODO construct card descriptions using variables. Hardcoded for simplification at the moment
 * creates the appropriate deck for the game based on input
 * stores the cards chosen
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CardDeck implements ICardDeck {

    private ArrayList<SheepCard> sheepCardDeck;
    private ArrayList<NightmareCard> nightmareCardDeck;
    private ArrayList<SheepCard> twoPlayerDeck;
    private ArrayList<SheepCard> threePlayerDeck;
    private ArrayList<SheepCard> fourPlayerDeck;
    private ArrayList<NightmareCard> nightmareWolfDeck;
    private ArrayList<NightmareCard> bumpInTheNightDeck;
    private ArrayList<NightmareCard> sinisterSpiderDeck;
    private Random rand;
    private CardFactory cardFactory;
    private SheepCard sheepCard;
    private NightmareCard nightmareCard;

    private final int FOURPLAYERS = 4;
    private final int THREEPLAYERS = 3;
    private final int TWOPLAYERS = 2;
    private final int ONEPLAYER = 1;


    /**
     * chooses the sheep cards and nightmare cards that will be in the CardDeck that will
     * be used for the game
     * @param players used to get the number of players to create the correct deck
     * @param referenceCard used to get NightmareReferenceCard being used to create the correct deck
     */
    public CardDeck(ArrayList<IPlayer> players, NightmareReferenceCard referenceCard) {
        rand = new Random();
        cardFactory = new CardFactory();
        createDeck(players, referenceCard);

    }

    /**
     * creates the deck with the chosen sheep and nightmare cards
     */
    public void createDeck(ArrayList<IPlayer> players, NightmareReferenceCard referenceCard) {
        chooseSheepCards(players.size());
        chooseNightmareCards(referenceCard);
    }

    /**
     * returns the sheep card deck
     * @return all cards in deck via ArrayList of type Card
     */
    @Override
    public ArrayList<SheepCard> getSheepCardDeck() {
        return sheepCardDeck;
    }

    /**
     * returns the nightmare card deck
     * @return all cards in deck via ArrayList of type Card
     */
    public ArrayList<NightmareCard> getNightmareCardDeck() {
        return nightmareCardDeck;
    }

    /**
     * chooses the sheep cards for the deck based on the number of players playing
     * @param numPlayers creates the correct deck for the number of player playing
     */
    private void chooseSheepCards(int numPlayers) {
        sheepCardDeck = new ArrayList<>();
        //if FOURPLAYERS
        if (numPlayers == FOURPLAYERS) {
            create4PlayerDeck();
            sheepCardDeck = fourPlayerDeck;
            //if THREEPLAYERS
        } else if (numPlayers == THREEPLAYERS) {
            create3PlayerDeck();
            sheepCardDeck = threePlayerDeck;
            //if TWOPLAYERS  or ONEPLAYER
        } else if (numPlayers == TWOPLAYERS || numPlayers == ONEPLAYER) {
            create2PlayerDeck();
            sheepCardDeck = twoPlayerDeck;
            //incorrect number of players
        } else {
            throw new IllegalArgumentException("Unacceptable number input");
        }

    }

    /**
     * chooses nightmare cards based on input
     * @param referenceCard creates the correct deck for the NightmareReferenceCard being used this game
     */
    private void chooseNightmareCards(NightmareReferenceCard referenceCard) {
        //if NightmareWolf is chosen
        if (referenceCard.getNightmareChoice() == 1) {
            createNightmareWolfDeck();
            nightmareCardDeck = nightmareWolfDeck;
            //if BumpInTheNight is chosen
        } else if (referenceCard.getNightmareChoice() == 2) {
            createBumpInTheNightDeck();
            nightmareCardDeck = bumpInTheNightDeck;
            //if ISinisterSpider is chosen
        } else if (referenceCard.getNightmareChoice() == 3) {
            createSinisterSpiderDeck();
            nightmareCardDeck = sinisterSpiderDeck;
            //incorrect input
        } else {
            throw new IllegalArgumentException("Nightmare not recognized");
        }
    }

    /**
     * Creates the sheep card deck for a 2 player game
     */
    private void create2PlayerDeck() {
        
        twoPlayerDeck = new ArrayList<>();

        //add 7 "Move 3 Spaces or Catch 1 Zzz" cards to the 2 player deck
        for (int i = 0; i < 7; i++) {
            sheepCard = cardFactory.createSheepCard("Move 3 Spaces or Catch 1 Zzz");
            twoPlayerDeck.add(sheepCard);
        }

        //add 2 "Move 5 Spaces or Gain 2 Winks" cards to the 2 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 5 Spaces or Gain 2 Winks");
            twoPlayerDeck.add(sheepCard);
        }

        //add 2 "Move 7 Spaces" cards to the 2 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 7 Spaces");
            twoPlayerDeck.add(sheepCard);
        }

        // add 2 "Move 1 or 2 Spaces or Catch 1 Zzz" cards to the 2 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 1 or 2 Spaces or Catch 1 Zzz");
            twoPlayerDeck.add(sheepCard);
        }

        //add 2 "Move 1 space and Catch 1 Zzz" cards to the 2 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 1 Space and Catch 1 Zzz");
            twoPlayerDeck.add(sheepCard);
        }

        //add 7 "Move 4 Spaces or Catch 1 Zzz" cards to the 2 player deck
        for (int i = 0; i < 7; i++) {
            sheepCard = cardFactory.createSheepCard("Move 4 Spaces or Catch 1 Zzz");
            twoPlayerDeck.add(sheepCard);
        }

        //add 3 "Move 6 Spaces or Gain 3 Winks" cards to the 2 player deck
        for (int i = 0; i < 3; i++) {
            sheepCard = cardFactory.createSheepCard("Move 6 Spaces or Gain 3 Winks");
            twoPlayerDeck.add(sheepCard);
        }

        //add 3 "Move 2 Spaces or Catch 2 Zzzs" cards to the 2 player deck
        for (int i = 0; i < 3; i++) {
            sheepCard = cardFactory.createSheepCard("Move 2 Spaces or Catch 2 Zzzs");
            twoPlayerDeck.add(sheepCard);
        }

        //add 2 "Move 1 Space or Move 5 Spaces" cards to the 2 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 1 Space or Move 5 Spaces");
            twoPlayerDeck.add(sheepCard);
        }
    }

    /**
     * creates the sheep card deck for a 3 player game
     */
    private void create3PlayerDeck() {
        
        threePlayerDeck = new ArrayList<>();

        //add 2 "Move 4 Spaces or Catch 1 Zzz" cards to the 3 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 4 Spaces or Catch 1 Zzz");
            threePlayerDeck.add(sheepCard);
        }

        //add 2 "Move 5 Spaces or Gain 2 Winks" cards to the 3 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 5 Spaces or Gain 2 Winks");
            threePlayerDeck.add(sheepCard);
        }

        //add 2 "Move 1 or 2 Spaces or Catch 1 Zzz" cards to the 3 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 1 or 2 Spaces or Catch 1 Zzz");
            threePlayerDeck.add(sheepCard);
        }

        //add 2 "Move 3 Spaces or Catch 1 Zzz" cards to the 3 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 3 Spaces or Catch 1 Zzz");
            threePlayerDeck.add(sheepCard);
        }

        //add a "Move 6 Spaces or Gain 3 Winks" card to the 3 player deck
        sheepCard = cardFactory.createSheepCard("Move 6 Spaces or Gain 3 Winks");
        threePlayerDeck.add(sheepCard);

        //add a "Move 7 Spaces" card to the 3 player deck
        sheepCard = cardFactory.createSheepCard("Move 7 Spaces");
        threePlayerDeck.add(sheepCard);

        //creates the 2 player deck and adds each card in it to the three player deck
        create2PlayerDeck();
        Iterator<SheepCard> iterator = twoPlayerDeck.iterator();
        while (iterator.hasNext()) {
            SheepCard c = iterator.next();
            threePlayerDeck.add(c);
            iterator.remove();
        }
    }

    /**
     * creates a sheep card deck for a 4 player game
     */
    private void create4PlayerDeck() {
        
        fourPlayerDeck = new ArrayList<>();

        //add 2 "Move 3 Spaces or Catch 1 Zzz" cards to the 4 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 3 Spaces or Catch 1 Zzz");
            fourPlayerDeck.add(sheepCard);
        }

        //add 2 "Move 6 Spaces or Gain 3 winks" cards to the 4 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 6 Spaces or Gain 3 winks");
            fourPlayerDeck.add(sheepCard);
        }

        //add 2"Move 4 Spaces or Catch 1 Zzz" cards to the 4 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 4 Spaces or Catch 1 Zzz");
            fourPlayerDeck.add(sheepCard);
        }

        //add 2 "Move 1 or 2 Spaces or Catch 1 Zzz" cards to the 4 player deck
        for (int i = 0; i < 2; i++) {
            sheepCard = cardFactory.createSheepCard("Move 1 or 2 Spaces or Catch 1 Zzz");
            fourPlayerDeck.add(sheepCard);
        }

        //add a "Move 7 Spaces" card to the 4 player deck
        sheepCard = cardFactory.createSheepCard("Move 7 Spaces");
        fourPlayerDeck.add(sheepCard);

        //add a "Move 5 Spaces or Gain 2 Winks" card to the 4 player deck
        sheepCard = cardFactory.createSheepCard("Move 5 Spaces or Gain 2 Winks");
        fourPlayerDeck.add(sheepCard);

        //creates the three player deck and adds each sheepCard in it to the four player deck
        create3PlayerDeck();
        Iterator<SheepCard> iterator = threePlayerDeck.iterator();
        while (iterator.hasNext()) {
            SheepCard c = iterator.next();
            fourPlayerDeck.add(c);
            iterator.remove();
        }
    }

    /**
     * creates a NightmareCard deck if Nightmare is Nightmare Wolf
     */
    private void createNightmareWolfDeck() {
        
        nightmareWolfDeck = new ArrayList<>();
        
        //add 2 of this nightmareCard to the NightmareWolfDeck
        for (int i = 0; i < 2; i++) {
            nightmareCard = cardFactory.createNightmareCard("The Nightmare Scares All Sheep On It's Space And Adjacent Spaces");
            nightmareWolfDeck.add(nightmareCard);
        }

        //add 4 of this nightmareCard to the NightmareWolfDeck
        for (int i = 0; i < 4; i++) {
            nightmareCard = cardFactory.createNightmareCard("The Nightmare Moves 2 Spaces");
            nightmareWolfDeck.add(nightmareCard);
        }

        //add 4 of this nightmareCard to the NightmareWolfDeck
        for (int i = 0; i < 4; i++) {
            nightmareCard = cardFactory.createNightmareCard("The Nightmare Moves 1 Space");
            nightmareWolfDeck.add(nightmareCard);
        }
    }

    /**
     * Creates a NightmareCard deck for if Nightmare is SinisterSpider
     */
    private void createSinisterSpiderDeck() {

        sinisterSpiderDeck = new ArrayList<>();

        //adds 4 of this card to the Sinister Spider deck
        for (int i = 0; i < 4; i++) {
            nightmareCard = cardFactory.createNightmareCard("The Nightmare Moves To The Web Token And The Web Token Moves 2 Spaces InFront Of The Nightmare");
            sinisterSpiderDeck.add(nightmareCard);
        }

        //adds 6 of this nightmareCard to the Sinister Spider deck
        for (int i = 0; i < 6; i++) {
            nightmareCard = cardFactory.createNightmareCard("The Nightmare Moves To The Web Token And The Web Token Moves 3 Spaces InFront Of The Nightmare");
            sinisterSpiderDeck.add(nightmareCard);
        }
    }

    /**
     * Creates NightmareCard deck for if Nightmare is BumpInTheNight
     */
    private void createBumpInTheNightDeck() {

        bumpInTheNightDeck = new ArrayList<>();

        //adds 3 of this nightmareCard to the BumpInTheNight deck
        for (int i = 0; i < 3; i++) {
            nightmareCard = cardFactory.createNightmareCard("The Nightmare Jumps 1 Space Backward");
            bumpInTheNightDeck.add(nightmareCard);
        }

        //adds 4 of this nightmareCard to the BumpInTheNight deck
        for (int i = 0; i < 4; i++) {
            nightmareCard = cardFactory.createNightmareCard("The Nightmare Jumps 2 Spaces Forward");
            bumpInTheNightDeck.add(nightmareCard);
        }

        //adds 2 of this nightmareCard to the BumpInTheNight deck
        for (int i = 0; i < 2; i++) {
            nightmareCard = cardFactory.createNightmareCard("The Nightmare Jumps 3 Spaces Forward");
            bumpInTheNightDeck.add(nightmareCard);
        }

        //adds 1 of this nightmareCard to the BumpInTheNight deck
        nightmareCard = cardFactory.createNightmareCard("The Nightmare Jumps 1 Space Forward");
        bumpInTheNightDeck.add(nightmareCard);
    }

    /**
     * draws a sheep card from the sheep card deck
     * @return SheepCard
     */
    @Override
    public SheepCard drawSheepCard() {
        int randomIndex = rand.nextInt(sheepCardDeck.size());
        //returns a random card from the deck
        return sheepCardDeck.remove(randomIndex);
    }

    /**
     * draws a nightmare card from the nightmare card deck
     * @return NightmareCard
     */
    @Override
    public NightmareCard drawNightmareCard(){
        int randomIndex = rand.nextInt(nightmareCardDeck.size());
        //returns a random NightmareCard from the deck
        return nightmareCardDeck.remove((randomIndex));
    }



}
