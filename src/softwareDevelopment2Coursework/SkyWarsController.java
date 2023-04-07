// SkyWarsController.java
package softwareDevelopment2Coursework;

import grid.Grid;
import grid.Square;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SkyWarsController {
    private SkyWarsModel model;
    private SkyWarsMainGUI view;
    private int prevRow = -1;
    private int prevCol = -1;
    private JButton prevButton = null;

    public SkyWarsController(SkyWarsModel model, SkyWarsMainGUI view) {
        this.model = model;
        this.view = view;
        view.setGridButtonListener(this::handleGridButtonClick);
    }

    private void handleGridButtonClick(int row, int col, JButton button) {
        Grid gameGrid = model.getGameGrid();

        // If there was a previous button clicked, remove the master ship from that square
        if (prevCol != -1 && prevRow != -1) {
            Square prevSquare = gameGrid.getSquare(prevRow, prevCol);
            prevSquare.removeMasterShipAtSquare();
            prevButton.setIcon(null);
        }

        // Set the master ship at the new square
        Square square = gameGrid.getSquare(row, col);
        square.setMasterShipAtSquare();

        button.setIcon(view.getMasterShipIcon());

        // Update the previous row, column, and button
        prevRow = row;
        prevCol = col;
        prevButton = button;
    }

}
