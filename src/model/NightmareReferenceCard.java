/**
 * Abstract class for NightmareReferenceCards
 * describes the Nightmare
 * creates the INightmareToken
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */
package model;

public class NightmareReferenceCard extends Card {
    private final INightmareToken nightmareToken;
    private final IActionable actionable;
    private String description;
    private IWebToken webToken;
    private int nightmareChoice;


    /**
     * initailizes Actionable, and INightmareToken
     * sets the color of the NightmareReferenceCard to default
     */
    public NightmareReferenceCard(int nightmareChoice) {
        this.actionable = new Actionable();
        nightmareToken = new NightmareToken();
        setDescription(nightmareChoice);
        this.nightmareChoice = nightmareChoice;
    }

    /**
     * sets the IBoard and color of the instance of INightmareToken
     * @param gameBoard associated with nightmare reference card
     */
    public void initializeToken(IBoard gameBoard) {
        nightmareToken.setBoard(gameBoard);
    }

    /**
     * returns the instnance of INightmareToken
     * @return nightmare token
     */
    public INightmareToken getNightmareToken() {
        return nightmareToken;
    }

    /**
     * resolves a NightmareCard by giving it to IActionable
     * @param card to resolve 
     * @return if nightmare token passed fence
     */
    public boolean resolve(NightmareCard card) {
        //calls actionable on nightmareToken using card
        return actionable.actionWithNightmareCard(card, nightmareToken);
    }

    public void setDescription(int nightmareChoice){
        if(nightmareChoice == 1){
            description = "THAT NIGHTMARE WOLF IS NO BIG DEAL. THREE" +
            " EYES AND IT STILL DOESN'T SEE US SNEEKING PAST! NOPE, DEFINITELY NOT SCARY AT" +
            " ALL *HOOOOOOOOOOWL* THE WOLF IS RIGHT BEHIND ME ISN'T IT?!?!";
        }
        else if(nightmareChoice == 2){
            description = "*THUD* WHAT WAS THAT SOUND? PROBABLY" +
            " NOTHING, THERE'S ONLY SHADOWS. *THUD* I THINK I HEARD A BUMP IN THE NIGHT AGAIN. " +
            "WAS IT CLOSER? MAYBE I SHOULD GO SEE IF THERE'S ANYTHING THERE... *THUD SQUEEELCH*" +
            " THERE WAS DEFINITLEY SOMETHING THERE!!!";
        }
        else if(nightmareChoice == 3){
            description = "THIS WEB IS PRETTY RELAXING," +
            " KINDA LIKE A HAMMOCK! AND I'M SURE I CAN GET FREE BEFORE THE SINISTER " +
            "SPIDER COMES BACK. *WEB SHAKES* IT IS RATHER STICKY, THOUGH. CAN SOMEONE HELP" +
            " ME OUT? *SHACKING INTENSIFIES* GET ME OUTTA HERE!!!";
        }
    }

    public String getDescription(){
        return description;
    }

    /**
     * Creates the Web Token
     */
    public void createWebToken() {

        webToken = new WebToken();

    }

    /**
     * Retrieves the Web Token
     *
     * @return webToken
     */
    public IWebToken getWebToken() {
        return webToken;
    }

    public int getNightmareChoice(){
        return nightmareChoice;
    }

}
