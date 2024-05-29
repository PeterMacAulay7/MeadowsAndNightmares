/**
 * This is a DreamTile factory which is used to make various versions
 * of dreamtiles. A factory design was chosen for it's ability to create
 * variations on a Generic class.
 *
 * @author Faycal Kilali, Peter MacAulay
 * @version 0.5
 */

package model.dreamtiles;

public class DreamTileFactory implements IDreamTileFactory {
    private String[] dreamTileNames = {"CoolKidsClub", "RecurringDream", "MoonWalk", "DreamJournal", "LoneSheep",
    "SnoozeMoves", "BounceAhead", "RushAhead", "PerfectLanding", "IntenseDreams",
    "FinalSprint", "ActionHero", "DeepSleep", "LucidDreams", "BigStash", "RestingSpot",
    "SweetDream", "Trampoline", "PipeDream", "CopyCat", "Shortcut", "SecondWind",
    "HideOrSeek", "RUUUUUUN", "FindAFriend", "RacingRival", "CatchUp", "StepBack",
    "ShepherdTheFlock", "DoubleDutch"};
    

    /**
     * Takes in a String for the type of DreamTile to be created.
     * Creates and returns the DreamTile that the String corresponds to
     * @param type of DreamTile to be created
     * @return new DreamTile
     */
    public DreamTile createDreamTile(String type) {
        if ("CoolKidsClub".equals(type)) {
            return new CoolKidsClub();
        } else if ("DoubleDutch".equals(type)) {
            return new DoubleDutch();
        } else if ("ShepherdTheFlock".equals(type)) {
            return new ShepherdTheFlock();
        } else if ("ActionHero".equals(type)) {
            return new ActionHero();
        } else if ("BounceAhead".equals(type)) {
            return new BounceAhead();
        } else if ("CatchUp".equals(type)) {
            return new CatchUp();
        } else if ("DeepSleep".equals(type)) {
            return new DeepSleep();
        } else if ("RushAhead".equals(type)) {
            return new RushAhead();
        } else if ("RUUUUUUN".equals(type)) {
            return new RUUUUUUN();
        } else if ("SecondWind".equals(type)) {
            return new SecondWind();
        } else if ("Shortcut".equals(type)) {
            return new Shortcut();
        } else if ("SnoozeMoves".equals(type)) {
            return new SnoozeMoves();
        } else if ("StepBack".equals(type)) {
            return new StepBack();
        } else if ("SweetDream".equals(type)) {
            return new SweetDream();
        } else if ("Trampoline".equals(type)) {
            return new Trampoline();
        } else if ("FinalSprint".equals(type)) {
            return new FinalSprint();
        } else if ("FindAFriend".equals(type)) {
            return new FindAFriend();
        } else if ("HideOrSeek".equals(type)) {
            return new HideOrSeek();
        } else if ("IntenseDreams".equals(type)) {
            return new IntenseDreams();
        } else if ("DreamJournal".equals(type)) {
            return new DreamJournal();
        } else if ("LucidDreams".equals(type)) {
            return new LucidDreams();
        } else if ("CopyCat".equals(type)) {
            return new CopyCat();
        } else if ("MoonWalk".equals(type)) {
            return new MoonWalk();
        } else if ("BigStash".equals(type)) {
            return new BigStash();
        } else if ("LoneSheep".equals(type)) {
            return new LoneSheep();
        } else if ("PerfectLanding".equals(type)) {
            return new PerfectLanding();
        } else if ("PipeDream".equals(type)) {
            return new PipeDream();
        } else if ("RestingSpot".equals(type)) {
            return new RestingSpot();
        } else if ("RecurringDream".equals(type)) {
            return new RecurringDream();
        } else if ("RacingRival".equals(type)) {
            return new RacingRival();
        } else {
            throw new IllegalArgumentException("Unknown DreamTile type: " + type);
        }
    }

    /**
     * returns the array of DreamTile names
     * @return DreamTile names
     */
    @Override
    public String[] getDreamTileNames(){
        return dreamTileNames;
    }
    
}
