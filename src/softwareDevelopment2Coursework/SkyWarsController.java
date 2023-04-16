// SkyWarsController.java
package softwareDevelopment2Coursework;

import ships.MasterShip;
import ships.Ship;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SkyWarsController {
	private SkyWarsModel model;
	private SkyWarsMainGUI view;
	private JButton prevButton = null;
	private Ship masterShip = new MasterShip();

	// Constructor
	public SkyWarsController(SkyWarsModel model, SkyWarsMainGUI view) {
		this.model = model;
		this.view = view;
		// Set up button listener and controller for the view
		view.setGridButtonListener(this::handleGridButtonClick);
		view.setController(this);
	}

	// Displays the main menu
	public void mainMenu() {
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
		menu.setController(this);
	}

	// Initialises the master ship
	public Ship initializeMasterShip() {
		model.allocateMasterShipRandomly();
		int row = model.getPrevRow();
		int col = model.getPrevCol();
		prevButton = view.getButtonAt(row, col);
		prevButton.setIcon(masterShip.getIcon());
		return masterShip;
	}

	// Loads the master ship
	public Ship loadMasterShip() {
		int row = model.getPrevRow();
		int col = model.getPrevCol();
		prevButton = view.getButtonAt(row, col);
		prevButton.setIcon(masterShip.getIcon());
		return masterShip;
	}

	// Resets the game
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

	// Handles button click event
	private void handleGridButtonClick(int row, int col, JButton button) {
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

	// Returns to the main menu
	public void returnToMainMenu() {
		view.dispose();
		mainMenu();
	}

	// Returns the number of ships at a given square
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

				} else {
					JButton tempButton = view.getButtonAt(row, col);
					tempButton.setIcon(null);
				}
			}
		}
	}

	// Displays a confirmation dialog asking the user if they want to exit the game.
	public int confirmExit() {
		int output;
		output = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
				JOptionPane.YES_NO_OPTION);
		return output;
	}

	// Displays a confirmation dialog asking the user if they want to restart the
	// game.
	public int confirmRestart() {
		int output;
		output = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart the game?", "Restart",
				JOptionPane.YES_NO_OPTION);
		return output;
	}

	// Switches the current game mode to the next mode.
	public void modeSwitcher() {
		Mode currentMode = view.getMode();
		Mode newMode = model.modeSwitcher(currentMode);
		view.setMode(newMode);
	}

	// Saves the current game with a unique file name based on the current date and
	// time.
	public void saveGame(String fileName) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String date = dateFormat.format(new Date());
		String newFileName = "SAVE_GAME_" + fileName + "_" + date;
		model.saveGame(newFileName);
		JOptionPane.showMessageDialog(null, "Game Saved");
	}

	// Loads a saved game from a file and updates the model and view accordingly.
	public void loadGame(String fileName) {
		SkyWarsModel loadedModel = SkyWarsModel.loadGame(fileName);
		if (loadedModel != null) {
			this.prevButton = null;
			model = loadedModel;
			view.updateGameGrid(model.getGameGrid());
			view.setVisible(true);
		}
	}

}
