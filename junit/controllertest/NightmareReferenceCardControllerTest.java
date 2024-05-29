package controllertest;

import controller.INightmareReferenceCardController;
import controller.NightmareReferenceCardController;
import model.NightmareReferenceCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.NightmareReferenceCardView;

import static org.junit.jupiter.api.Assertions.*;


public class NightmareReferenceCardControllerTest {
    INightmareReferenceCardController controller;
    @BeforeEach
    public void setUp(){
        controller = new NightmareReferenceCardController();
    }


    @Test
    public void testGetView() {
        assertNotNull(controller.getView());
        assertTrue(controller.getView() instanceof NightmareReferenceCardView);
    }

    /**
     * @LRicker: I cannot understant why parseModel method exists even though it does nothing
     */
    @Test
    public void testParseModel() {

    }

    /**
     * Test method for setReferenceCard();
     * @Note: Impossible to test without assiciating view class, thus this method just check is there any thrown expcetion
     */
    @Test
    public void testSetReferenceCard() {
        try {
            controller.setReferenceCard(new NightmareReferenceCard(0));
        } catch (Exception e) {
            fail("Valid method call. Should not have thrown exception");
        }
    }

    /**
     * Test methods below are interacting with view objects, thus they are not tested in this class
     */
    @Test
    public void testUpdateView() {

    }
    @Test
    public void testDisplayView() {

    }

}