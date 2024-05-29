/**
 * JUnit class for SinisterSpider Class
 * @author Dylan Kim
 * @version 0.5
 */

package modeltest;


import model.NightmareReferenceCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.WebToken;

public class SinisterSpiderTest {
    private NightmareReferenceCard spider;
    @BeforeEach
    public void setUp() {
        spider = new NightmareReferenceCard(3);
    }


    /**
     * Test method for createWebToken() and getWebToken() methods
     */
    @Test
    public void testWebToken() {
        assertEquals(spider.getWebToken(),null);
        spider.createWebToken();
        assert(spider.getWebToken() instanceof WebToken);

    }
}
