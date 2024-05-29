/**
 * Resets players, nightmareReferenceCard, and, cardDeck
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model;

import java.util.ArrayList;

public class Resetable implements IResetable {

    /**
     * Resets players, NightmareReferenceCard, and, cardDeck
     * @param players to be reset along with other objects associated with it
     * @param nightmareReferenceCard to reset nightmare token
     * @param cardDeck to be reset
     */
    public void reset(ArrayList<IPlayer> players, NightmareReferenceCard nightmareReferenceCard, ICardDeck cardDeck) {
        int numPlayers = players.size();
        for (IPlayer player : players) {
            if (player.getIsOut()) {
                player.setIsOut(false);
                
            }
            if(numPlayers > 1){
                player.setWinks(0);
                resetTokenPosition(player.getWinkToken());

            }
            player.setCalledItANight(false);
            //int winkTokenPosition = player.getWinkToken().getPositionOnBoard();
            //player.getWinkToken().setToDefaultPosition();
            //player.getWinkToken().changeInPosition(-winkTokenPosition); // reset to position 0
            ISheepToken playerSheep = player.getSheepToken();
            resetTokenPosition(playerSheep);
            //int sheepPositionOnBoard = playerSheep.getPositionOnBoard();
            //if (sheepPositionOnBoard != 10) {
            //    playerSheep.changeInPosition(-sheepPositionOnBoard); // reset to position 0
            //}
            //playerSheep.setToDefaultPosition();
        }


        // Nightmare Token reset
        nightmareReferenceCard.getNightmareToken().setToDefaultPosition();
        //resetTokenPosition(nightmareToken);
        //nightmareToken.setToDefaultPosition();
        //if (nightmareToken.getPositionOnBoard() != 10) {
        //nightmareToken.changeInPosition(-nightmareToken.getPositionOnBoard());
        //}
        if (nightmareReferenceCard instanceof ISinisterSpider) {
            IWebToken webToken = ((ISinisterSpider) nightmareReferenceCard).getWebToken();
            //if (webToken.getPositionOnBoard() != 10) {
            //   webToken.changeInPosition(-webToken.getPositionOnBoard());
            //}
            resetTokenPosition(webToken);
        }

        // Card Deck reset
        resetCardDeck(players, nightmareReferenceCard, cardDeck);

    }

    /**
     * Performs a reset ont he Card Deck by re-initializing it
     * @param cardDeck the deck of cards at play
     */
    private void resetCardDeck(ArrayList<IPlayer> players, NightmareReferenceCard nightmareReferenceCard, ICardDeck cardDeck) {
        ICardDeck newCardDeck = new CardDeck(players, nightmareReferenceCard);
        cardDeck = newCardDeck; // TODO: may have to return the cardDeck or have a reset method in the CardDeck
    }


    /**
     * sets the given IToken's location to DEFAULT_POSTION
     * @param token to be reset
     */
    @Override
    public void resetTokenPosition(IToken token) {
        token.setToDefaultPosition();
    }

}
