package softwareDevelopment2Coursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SkyWarsMainGUITest {
    private SkyWarsMainGUI mainGUI;

    @BeforeEach
    void setUp() {
        mainGUI = new SkyWarsMainGUI();
    }

    @Test
    void testInitialMode() {
        Mode initialMode = mainGUI.getMode();
        assertEquals(Mode.DEFENSIVE, initialMode);
    }

    @Test
    void testInitialScore() {
        int score = mainGUI.getScore();
        assertEquals(0, score);
    }

    @Test
    void testInitialMoves() {
        int moves = mainGUI.getMoves();
        assertEquals(0, moves);
    }

    @Test
    void testInitialEnemyShips() {
        int enemyShips = mainGUI.getEnemyShips();
        assertEquals(0, enemyShips);
    }
}

