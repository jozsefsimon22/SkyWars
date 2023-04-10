// SkyWarsController.java
package softwareDevelopment2Coursework;

import grid.Grid;
import grid.Square;
import ships.MasterShip;
import ships.Ship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SkyWarsController {
	private SkyWarsModel model;
	private SkyWarsMainGUI view;
	private JButton prevButton = null;
	private Ship masterShip = new MasterShip();

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
//		prevButton.setIcon(view.getMasterShipIcon());
		prevButton.setIcon(masterShip.getIcon());
	}

	public void newGame() {
		initializeMasterShip();
		view.setVisible(true);
	}

	private void handleGridButtonClick(int row, int col, JButton button) {
		Grid gameGrid = model.getGameGrid();

		if (model.isValidMove(row, col)) {
			model.moveMasterShip(row, col);
			button.setIcon(masterShip.getIcon());
			this.prevButton.setIcon(null);
			this.prevButton = button;
			model.newEnemyShip();
			this.displayEnemyShips();
		}

		else {
			model.notValidMove();
		}

	}

	// Sets the image of the GUI square to the image of the first ship in the
	// ArrayList
	public void displayEnemyShips() {
		for (int row = 0; row < model.getGameGrid().getWidth(); row++) {
			for (int col = 0; col < model.getGameGrid().getLength(); col++) {
				ArrayList<Ship> enemyShips = model.getGameGrid().getSquare(row, col).getEnemyShipsAtSquare();
				if (!enemyShips.isEmpty()) {
					Ship tempShip = enemyShips.get(0);
					String tempShipType = tempShip.getType();
					if (tempShipType.equals("Battle Star")) {
						JButton tempButton = view.getButtonAt(row, col);
						tempButton.setIcon(tempShip.getIcon());
					} else if (tempShipType.equals("Battle Shooter")) {
						JButton tempButton = view.getButtonAt(row, col);
						tempButton.setIcon(tempShip.getIcon());
					} else if (tempShipType.equals("Battle Cruiser")) {
						JButton tempButton = view.getButtonAt(row, col);
						tempButton.setIcon(tempShip.getIcon());
					}

				}
			}
		}
	}

}
