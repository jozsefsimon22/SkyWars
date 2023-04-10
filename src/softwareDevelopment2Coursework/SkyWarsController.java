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
		view.setController(this);
	}
	
	public void mainMenu() {
		MainMenu menu = new MainMenu();
        menu.setVisible(true);
        menu.setController(this);
	}

	public void initializeMasterShip() {
		model.allocateMasterShipRandomly();
		int row = model.getPrevRow();
		int col = model.getPrevCol();
		prevButton = view.getButtonAt(row, col);
		prevButton.setIcon(masterShip.getIcon());
	}

	public void resetGame() {
		model.resetGame();
		view.setVisible(true);
		displayEnemyShips();
		initializeMasterShip();
		view.setMoves(0);
		view.setScore(0);
		view.setEnemyShips(0);
	    view.setEnemyShipCountLabel();
	    view.setScoreLabel();
	    view.setMoveCountLabel();
	}

	private void handleGridButtonClick(int row, int col, JButton button) {
	    Grid gameGrid = model.getGameGrid();

	    if (model.isValidMove(row, col)) {
	        this.prevButton.setIcon(null);
	        this.prevButton = button;
	        model.enemyShipMove();
	        model.moveMasterShip(row, col);
	        boolean isGameOver = model.checkConflict(row, col, view.getMode());
	        if (isGameOver) {
	            int choice = model.gameOver();
	            if (choice == 0) {
	                returnToMainMenu();
	            } else if (choice == 1) {
	                System.exit(0);
	            }
	            return;
	        }
	        model.newEnemyShip();
	        this.displayEnemyShips();
	        view.setEnemyShipCountLabel();
	        view.setEnemyShips(model.getEnemyShipsInSky());
	        view.setMoves(view.getMoves() + 1);
	        view.setMoveCountLabel();
	        view.setScore(model.getScore());
	        view.setScoreLabel();
	        button.setIcon(masterShip.getIcon());
	    } else {
	        model.notValidMove();
	    }
	}

	
	public void returnToMainMenu() {
	    view.dispose();
	    mainMenu();
	}

	
	public int numberOfShips(int row, int col) {
		int numberOfShips;
		
		numberOfShips = model.noOfEnemyShipsAtSquare(row, col);
		
		return numberOfShips;
	}
	

	// Sets the image of the GUI square to the image of the first ship in the
	// ArrayList
	public void displayEnemyShips() {
		for (int row = 0; row < model.getGameGrid().getCol(); row++) {
			for (int col = 0; col < model.getGameGrid().getRow(); col++) {
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
				else {
					JButton tempButton = view.getButtonAt(row, col);
					tempButton.setIcon(null);
				}
			}
		}
	}
	
	public int confirmExit() {
		int output;
		output = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
		return output;
	}
	
	public int confirmRestart() {
		int output;
		output = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart the game?", "Restart", JOptionPane.YES_NO_OPTION);
		return output;
	}

	
	public void modeSwitcher() {
	    Mode currentMode = view.getMode();
	    Mode newMode = model.modeSwitcher(currentMode);
	    view.setMode(newMode);
	}
}
