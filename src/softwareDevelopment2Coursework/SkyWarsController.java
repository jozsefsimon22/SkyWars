// SkyWarsController.java
package softwareDevelopment2Coursework;

import grid.Grid;
import grid.Square;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SkyWarsController {
	private SkyWarsModel model;
	private SkyWarsMainGUI view;
	private JButton prevButton = null;

	public SkyWarsController(SkyWarsModel model, SkyWarsMainGUI view) {
		this.model = model;
		this.view = view;
		view.setGridButtonListener(this::handleGridButtonClick);
	}

	public void initializeMasterShip() {
		model.allocateMasterShipRandomly();
		int row = model.getPrevRow();
		int col = model.getPrevCol();
		prevButton = view.getButtonAt(row, col);
		prevButton.setIcon(view.getMasterShipIcon());
	}

	private void handleGridButtonClick(int row, int col, JButton button) {
		Grid gameGrid = model.getGameGrid();

		if (model.isValidMove(row, col)) {
			model.moveMasterShip(row, col);
			button.setIcon(view.getMasterShipIcon());
			this.prevButton.setIcon(null);
			this.prevButton = button;
		}

		else {
			model.notValidMove();
		}

	}

}
