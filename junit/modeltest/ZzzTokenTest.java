/**
 * JUnit class for ZzzToken Class
 * @author Dylan Kim
 * @version 0.5
 */


package modeltest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.IPlayer;
import model.IZzzToken;
import model.Player;
import model.ZzzToken;

public class ZzzTokenTest {
    private IPlayer player;
    private IZzzToken zzz;
    @BeforeEach
    public void setUp(){
        
        player = new Player("name");
        zzz = new ZzzToken(player);
    }

    @Test
    public void testSetGetInfinite() {
        zzz.setInfinite(true);
        assertEquals(zzz.getInfinite(), true);
        zzz.setInfinite(false);
        assertEquals(zzz.getInfinite(), false);        
    }

    @Test
    public void testSetGetPlayer() {
        zzz.setPlayer(null);
        assertEquals(zzz.getPlayer(), null);
        zzz.setPlayer(player);
        assertEquals(zzz.getPlayer(), player);
    }
}
