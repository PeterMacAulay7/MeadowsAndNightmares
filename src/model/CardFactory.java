package model;

public class CardFactory {
    private final int MOVE_1_SPACE = 1;
    private final int MOVE_2_SPACES = 2;
    private final int MOVE_3_SPACES = 3;
    private final int MOVE_4_SPACES = 4;
    private final int MOVE_5_SPACES = 5;
    private final int MOVE_6_SPACES = 6;
    private final int MOVE_7_SPACES = 7;
    private final int MOVE_BACK_1_SPACES = -1;
    private final int CATCH_1_ZZZ = 1;
    private final int CATCH_2_ZZZS = 2;
    private final int GAIN_2_WINKS = 2;
    private final int GAIN_3_WINKS = 3;

    private SheepCard SheepCard;
    private NightmareCard nightmareCard;

    public SheepCard createSheepCard(String type) {
        if (type.equals("Move " + MOVE_3_SPACES + " Spaces or Catch " + CATCH_1_ZZZ + " Zzz")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_3_SPACES)
                    .withCatchableZzz(CATCH_1_ZZZ)
                    .withHasAndStatement(false)
                    .withDescription("Move " + MOVE_3_SPACES + " Spaces or Catch " + CATCH_1_ZZZ + " Zzz")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_5_SPACES + " Spaces or Gain " + GAIN_2_WINKS + " Winks")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_5_SPACES)
                    .withWinks(GAIN_2_WINKS)
                    .withHasAndStatement(false)
                    .withDescription("Move " + MOVE_5_SPACES + " Spaces or Gain " + GAIN_2_WINKS + " Winks")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_7_SPACES + " Spaces")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_7_SPACES)
                    .withDescription("Move " + MOVE_7_SPACES + " Spaces")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_1_SPACE + " or " + MOVE_2_SPACES + " Spaces or Catch " + CATCH_1_ZZZ + " Zzz")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_1_SPACE)
                    .withOtherMovableDistance(MOVE_2_SPACES)
                    .withCatchableZzz(CATCH_1_ZZZ)
                    .withHasSecondMoveOption(true)
                    .withHasAndStatement(false)
                    .withDescription("Move " + MOVE_1_SPACE + " or " + MOVE_2_SPACES + " Spaces or Catch " + CATCH_1_ZZZ + " Zzz")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_1_SPACE + " Space and Catch " + CATCH_1_ZZZ + " Zzz")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_1_SPACE)
                    .withCatchableZzz(CATCH_1_ZZZ)
                    .withHasAndStatement(true)
                    .withDescription("Move " + MOVE_1_SPACE + " Space and Catch " + CATCH_1_ZZZ + " Zzz")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_4_SPACES + " Spaces or Catch " + CATCH_1_ZZZ + " Zzz")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_4_SPACES)
                    .withHasAndStatement(false)
                    .withCatchableZzz(CATCH_1_ZZZ)
                    .withDescription("Move " + MOVE_4_SPACES + " Spaces or Catch " + CATCH_1_ZZZ + " Zzz")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_6_SPACES + " Spaces or Gain " + GAIN_3_WINKS + " Winks")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_6_SPACES)
                    .withHasAndStatement(false)
                    .withWinks(GAIN_3_WINKS)
                    .withDescription("Move " + MOVE_6_SPACES + " Spaces or Gain " + GAIN_3_WINKS + " Winks")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_2_SPACES + " Spaces or Catch " + CATCH_2_ZZZS + " Zzzs")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_2_SPACES)
                    .withHasAndStatement(false)
                    .withCatchableZzz(CATCH_2_ZZZS)
                    .withDescription("Move " + MOVE_2_SPACES + " Spaces or Catch " + CATCH_2_ZZZS + " Zzzs")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_1_SPACE + " Space or Move " + MOVE_5_SPACES + " Spaces")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_1_SPACE)
                    .withHasSecondMoveOption(true)
                    .withOtherMovableDistance(MOVE_5_SPACES)
                    .withDescription("Move " + MOVE_1_SPACE + " Space or Move " + MOVE_5_SPACES + " Spaces")
                    .buildSheepCard();
            return SheepCard;
        } 
        else if (type.equals("Move " + MOVE_1_SPACE + " or " + MOVE_2_SPACES + " Spaces or Catch " + CATCH_1_ZZZ + " Zzz")) {
            SheepCard = new CardBuilder()
                    .withMovableDistance(MOVE_1_SPACE)
                    .withHasSecondMoveOption(true)
                    .withOtherMovableDistance(MOVE_2_SPACES)
                    .withHasAndStatement(false)
                    .withCatchableZzz(CATCH_1_ZZZ)
                    .withDescription("Move " + MOVE_1_SPACE + " or " + MOVE_2_SPACES + " Spaces or Catch " + CATCH_1_ZZZ + " Zzz")
                    .buildSheepCard();
            return SheepCard;
        }
        else {
            throw new IllegalArgumentException("Unknown Card type: " + type);
        }
    }

    public NightmareCard createNightmareCard(String type) throws IllegalArgumentException{

        if(type.equals("The Nightmare Scares All Sheep On It's Space And Adjacent Spaces")){
            nightmareCard = new NightmareCardBuilder()
                    .withScareAdjacent(true)
                    .withJump(false)
                    .withDescription("The Nightmare Scares All Sheep On It's Space And Adjacent Spaces")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else if(type.equals("The Nightmare Moves " + MOVE_2_SPACES + " Spaces")){
            nightmareCard = new NightmareCardBuilder()
                    .withMovableDistance(MOVE_2_SPACES)
                    .withJump(false)
                    .withDescription("The Nightmare Moves " + MOVE_2_SPACES + " Spaces")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else if(type.equals("The Nightmare Moves " + MOVE_1_SPACE + " Space")){
            nightmareCard = new NightmareCardBuilder()
                    .withMovableDistance(MOVE_1_SPACE)
                    .withJump(false)
                    .withDescription("The Nightmare Moves " + MOVE_1_SPACE+ " Space")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else if(type.equals("The Nightmare Moves To The Web Token And The Web Token Moves " + MOVE_2_SPACES + " Spaces InFront Of The Nightmare")){
            nightmareCard = new NightmareCardBuilder()
                    .withMoveToWebToken(true)
                    .withWebTokenMovableDistance(MOVE_2_SPACES)
                    .withJump(false)
                    .withDescription("The Nightmare Moves To The Web Token" +
                            " And The Web Token Moves " + MOVE_2_SPACES + " Spaces InFront Of The Nightmare")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else if(type.equals("The Nightmare Moves To The Web Token And The Web Token Moves " + MOVE_3_SPACES + " Spaces InFront Of The Nightmare")){
            nightmareCard = new NightmareCardBuilder()
                    .withMoveToWebToken(true)
                    .withWebTokenMovableDistance(MOVE_3_SPACES)
                    .withJump(false)
                    .withDescription("The Nightmare Moves To The Web Token" +
                            " And The Web Token Moves " + MOVE_3_SPACES + " Spaces InFront Of The Nightmare")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else if(type.equals("The Nightmare Jumps " + MOVE_BACK_1_SPACES + " Space Backward")){
            nightmareCard = new NightmareCardBuilder()
                    .withMovableDistance(MOVE_BACK_1_SPACES)
                    .withJump(true)
                    .withDescription("The Nightmare Jumps " + MOVE_BACK_1_SPACES + " Space Backward")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else if(type.equals("The Nightmare Jumps " + MOVE_2_SPACES + " Spaces Forward")){
            nightmareCard = new NightmareCardBuilder()
                    .withMovableDistance(MOVE_2_SPACES)
                    .withJump(true)
                    .withDescription("The Nightmare Jumps " + MOVE_2_SPACES + " Spaces Forward")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else if(type.equals("The Nightmare Jumps " + MOVE_3_SPACES + " Spaces Forward")){
            nightmareCard = new NightmareCardBuilder()
                    .withMovableDistance(MOVE_3_SPACES)
                    .withJump(true)
                    .withDescription("The Nightmare Jumps " + MOVE_3_SPACES + " Spaces Forward")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else if(type.equals("The Nightmare Jumps " + MOVE_1_SPACE + " Space Forward")){
            nightmareCard = new NightmareCardBuilder()
                    .withMovableDistance(MOVE_1_SPACE)
                    .withJump(true)
                    .withDescription("The Nightmare Jumps " + MOVE_1_SPACE + " Space Forward")
                    .buildNightmareCard();
            return nightmareCard;
        }
        else {
            throw new IllegalArgumentException("Unknown Card type: " + type);
        }
    }
}
