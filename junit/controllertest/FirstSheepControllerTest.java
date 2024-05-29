/**
 * JUnit test class for FirstSheepController class
 * @author Dylan Kim
 * @version 0.5
 */
package controllertest;

import controller.FirstSheepController;
import controller.IFirstSheepController;
import model.FirstSheep;
import view.FirstSheepView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FirstSheepControllerTest {
    private IFirstSheepController controller;
    
    @BeforeEach
    public void setUp(){
        controller = new FirstSheepController();
    }

    @Test
    public void testGetView() {
        assertNotNull(controller.getView());
        assertTrue(controller.getView() instanceof FirstSheepView);
    }

    /**
     * Test method for setFirstSheep() and getFirstSheep() methods
     */
    @Test
    public void testSetGetFirstSheep() {
        assertNull(controller.getFirstSheep());
        controller.setFirstSheep(new FirstSheep());
        assertNotNull(controller.getFirstSheep());
        assertTrue(controller.getFirstSheep() instanceof FirstSheep);
    }

    /**
     * Since these methods are just calling corresponding method from FirstSheep, it will be tested there
     * 
     */
    @Test
    public void testParseModel() {

    }
    @Test
    public void testpassPlayersInfo() {
        
    }
    @Test
    public void testDecideFirstSheep(){

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
