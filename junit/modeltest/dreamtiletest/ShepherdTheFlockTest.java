/**
 * JUnit test class for Dreamtile, ShepherdTheFlock
 * @author Dylan Kim
 * @version 0.5
 */

 package modeltest.dreamtiletest;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import model.dreamtiles.DreamTile;
import model.dreamtiles.ShepherdTheFlock;
import java.util.ArrayList;

public class ShepherdTheFlockTest{
    private IPlayer player;
    private IPlayer player2;
    private IPlayer player3;
    private DreamTile tile;
    private ISheepToken sheep;
    private IWinkToken wink;
    private IPillowToken pillow;
    private IZzzToken zzz;
    private IFirstSheep firstSheep;
    private ArrayList<String> names;
    private ArrayList<Integer> bedtimes;


    @BeforeEach
    public void setUp(){
        names = new ArrayList<>();
        bedtimes = new ArrayList<>();
        names.add("name");
        names.add("name2");
        names.add("name 3");
        bedtimes.add(1);
        bedtimes.add(2);
        bedtimes.add(3);

        firstSheep = new FirstSheep();
        firstSheep.receivePlayersInfo(bedtimes, names);

        player = firstSheep.getTurnSequence().get(0);
        player2 = firstSheep.getTurnSequence().get(1);
        player3 = firstSheep.getTurnSequence().get(2);

        player.initializeTokens();
        tile = new ShepherdTheFlock();
        sheep = player.getSheepToken();
        wink = player.getWinkToken();
        pillow = player.getPillowToken();
        zzz = player.removeZzzToken();
    }

    @Test
    public void testActivateTile(){
		//TODO: comback after implementation

    }

}
