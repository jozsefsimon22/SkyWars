package softwareDevelopment2Coursework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grid.Grid;

class SkyWarsModelTest {
	private Grid gameGrid;
    private SkyWarsModel model;

    @BeforeEach
    void setUp() {
    	gameGrid = new Grid();
        model = new SkyWarsModel(gameGrid);
    }

    @Test
    void testGridInitialization() {
        assertNotNull(gameGrid);
        assertEquals(4, gameGrid.getRow());
        assertEquals(4, gameGrid.getCol());
    }

    @Test
    void testInitialScore() {
        int score = model.getScore();
        assertEquals(0, score);
    }

    @Test
    void testInitialEnemyShipsInSky() {
        int enemyShipsInSky = model.getEnemyShipsInSky();
        assertEquals(0, enemyShipsInSky);
    }
}
