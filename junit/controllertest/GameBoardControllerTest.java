/**
 * JUnit test class for GameBoardController class
 * @author Dylan Kim
 * @version 0.5
 */

package controllertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controller.GameBoardController;
import controller.IGameBoardController;
import view.GameBoardView;

public class GameBoardControllerTest {
    IGameBoardController controller;
    @BeforeEach
    public void setUp(){
        controller = new GameBoardController();
    }

    /**
     * Test method for getView()
     */
    @Test
    public void testGetView() {
        assertNotNull(controller.getView());
        assertTrue(controller.getView() instanceof GameBoardView);
    } 

    /**
     * Test method for setBoard()
     */
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
     * Test methods below are interacting with view objects, thus they are not tested in this class
     */
    
    /**
     * @LRicker: I am not sure about should testRevealMarket() and testParseModel() methods be in a controller because their 
     * functions look more suitable for view class
     */
    @Test
    public void testRevealMarket(){

    }
    @Test
    public void testParseModel() {

    }

    @Test
    public void testDisplayView() {

    }
    @Test
    public void testUpdateView() {

    }
}
