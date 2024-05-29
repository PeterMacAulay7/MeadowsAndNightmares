/**
 * JUnit test class for ScoreBoardController class
 * @author Dylan Kim
 * @version 0.5
 */
package controllertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controller.IScoreBoardController;
import controller.ScoreBoardController;
import model.ReferenceTile;
import view.ScoreBoardView;

public class ScoreBoardControllerTest {
    private IScoreBoardController controller;

    @BeforeEach
    public void setUp(){
        controller = new ScoreBoardController();
    }

    @Test
    public void testGetView() {
        assertNotNull(controller.getView());
        assertTrue(controller.getView() instanceof ScoreBoardView);
    }

    @Test
    public void testSetBoard() {

        // Act: Perform the action that should result in an exception
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            controller.parseModel();
        });

        // Assert: Check if the expected exception was thrown
        assertNotNull(exception);
        assertEquals("Board not set", exception.getMessage());
    }

    /**
     * Test method for setReferenceTile() and getReferenceTile() methods
     */
    @Test
    public void testSetGetReferenceTile(){
        assertNull(controller.getReferenceTile());
        controller.setReferenceTile(new ReferenceTile());
        assertNotNull(controller.getReferenceTile());
        assertTrue(controller.getReferenceTile() instanceof ReferenceTile);
    }

    /**
     * Test methods below are interacting with view objects, thus they are not tested in this class
     */


    @Test
    public void testParseModel() {

    }

    @Test
    public void testUpdateView() {

    }
    @Test
    public void testDisplayView() {

    }
}
