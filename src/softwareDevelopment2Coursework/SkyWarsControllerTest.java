package softwareDevelopment2Coursework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grid.Grid;

class SkyWarsControllerTest {
	private Grid gameGrid;
    private SkyWarsModel model;
    private SkyWarsMainGUI view;
    private SkyWarsController controller;

    @BeforeEach
    void setUp() {
    	gameGrid = new Grid();
        model = new SkyWarsModel(gameGrid);
        view = new SkyWarsMainGUI();
        controller = new SkyWarsController(model, view);
    }

    @Test
    void testModeSwitcher() {
        Mode initialMode = view.getMode();
        controller.modeSwitcher();
        Mode newMode = view.getMode();
        assertNotEquals(initialMode, newMode);
    }

    @Test
    void testNumberOfShips() {
        int ships = controller.numberOfShips(0, 0);
        assertEquals(0, ships);
    }

    @Test
    void testInitialViewScore() {
        int score = view.getScore();
        assertEquals(0, score);
    }

}