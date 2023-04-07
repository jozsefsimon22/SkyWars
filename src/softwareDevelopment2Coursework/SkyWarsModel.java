// SkyWarsModel.java
package softwareDevelopment2Coursework;

import grid.Grid;

public class SkyWarsModel {
    private Grid gameGrid;

    public SkyWarsModel(Grid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public Grid getGameGrid() {
        return gameGrid;
    }
}
