// SkyWarsMainGUI.java
package softwareDevelopment2Coursework;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import grid.Grid;
import ships.Ship;
import ships.Square;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;



public class SkyWarsMainGUI extends JFrame {
    private JPanel contentPane;
    private JLabel gameStatus;
    private SkyWarsController controller;
    private Mode mode;
    private int score;
    private int moves;
    private int enemyShips;
    JLabel moveCount = new JLabel();
    JLabel scoreLabel = new JLabel();
    JLabel enemyShipCount = new JLabel();
    
    public void setController(SkyWarsController controller) {
		this.controller = controller;
	}

	public SkyWarsMainGUI() {
        initialise();
    }

    private void initialise() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        mode = Mode.DEFENSIVE;
        setScore(0);
        setMoves(0);
        setEnemyShips(0);

        createStatusBar();
        createGrid();
        createMenu();
    }// end initialise 

    private JButton createGridButton(int row, int col) {
        JButton button = new JButton();
        button.putClientProperty("row", row);
        button.putClientProperty("col", col);
        return button;
    }

    private void createGrid() {
        int rows = 4;
        int columns = 4;

        JPanel grid = new JPanel();
        grid.setBackground(Color.WHITE);
        contentPane.add(grid, BorderLayout.CENTER);
        grid.setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid.add(createGridButton(i, j));
            }
        }
    }

    public void setGridButtonListener(TriConsumer<Integer, Integer, JButton> listener) {
        Component[] components = ((JPanel) contentPane.getComponent(1)).getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row = (int) button.getClientProperty("row");
                        int col = (int) button.getClientProperty("col");
                        listener.accept(row, col, button);
                        
                    }
                });
                button.addMouseMotionListener((MouseMotionListener) new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        int row = (int) button.getClientProperty("row");
                        int col = (int) button.getClientProperty("col");
                        button.setToolTipText("There are " + controller.numberOfShips(row, col) + " at this square.");
                    }
                });
            }
        }
    }
    
	private void createStatusBar() {
	    int preferredWidth = 300;
	    JPanel statusbar = new JPanel();
	    statusbar.setBackground(Color.WHITE);
	    statusbar.setLayout(new BoxLayout(statusbar, BoxLayout.Y_AXIS));
	    statusbar.setPreferredSize(new Dimension(preferredWidth, statusbar.getPreferredSize().height));

	    // Add a border around the status bar for better separation
	    statusbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    gameStatus = new JLabel("Status: VARIABLE");
	    statusbar.add(gameStatus);

	    // Add a vertical strut for spacing between components
	    statusbar.add(Box.createVerticalStrut(20));

	    JLabel masterSpaceShipMode = new JLabel("Master Space Ship Mode: " + mode);
	    statusbar.add(masterSpaceShipMode);
	    statusbar.add(Box.createVerticalStrut(5));
	    JToggleButton tglbtnMode = new JToggleButton("Switch Mode");
	    tglbtnMode.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		controller.modeSwitcher();
	    		masterSpaceShipMode.setText("Master Space Ship Mode: " + mode);
	    	}
	    });
	    statusbar.add(tglbtnMode);

	    statusbar.add(Box.createVerticalStrut(20));

	    setEnemyShipCountLabel();
	    statusbar.add(enemyShipCount);

	    statusbar.add(Box.createVerticalStrut(20));

	    setScoreLabel();
	    statusbar.add(scoreLabel);

	    statusbar.add(Box.createVerticalStrut(20));

	    setMoveCountLabel();
	    statusbar.add(moveCount);
	 
	    // Add a glue component to push the reset button to the bottom of the status bar
	    statusbar.add(Box.createVerticalGlue());

	    // Add the status bar to the main content pane
	    contentPane.add(statusbar, BorderLayout.EAST);
	}
	
	public void createMenu() {
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        //Main menu button
        JButton mainMenuButton = new JButton("Main Menu");
	    mainMenuButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(controller.confirmExit() == 0) {
	    			dispose();
	    			controller.mainMenu();        	    			
	    		};
	    	}
	    });
        panel.add(mainMenuButton);
        
        //Save game button
        JButton saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name = JOptionPane.showInputDialog("Please enter a name: ");
        		controller.saveGame(name);
    			dispose();
    			controller.mainMenu(); 
        	}
        });
        panel.add(saveGameButton);
        
        //Restart button
	    JButton restartButton = new JButton("Restart");
	    restartButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(controller.confirmRestart() == 0) {        	    			
	    			dispose();
	    			controller.resetGame();
	    		}
	    	}
	    });
	    panel.add(restartButton);

	}

	public JButton getButtonAt(int row, int col) {
		Component[] components = ((JPanel) contentPane.getComponent(1)).getComponents();
		for (Component component : components) {
			if (component instanceof JButton) {
				JButton button = (JButton) component;
				int buttonRow = (int) button.getClientProperty("row");
				int buttonCol = (int) button.getClientProperty("col");
				if (row == buttonRow && col == buttonCol) {
					return button;
				}
			}
		}
		return null;
	}
	
	public void updateGameGrid(Grid grid) {
	    int rows = grid.getRow();
	    int columns = grid.getCol();

	    for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < columns; col++) {
	            grid.Square square = grid.getSquare(row, col);
	            JButton button = getButtonAt(row, col);

	            // Clear the current button state
	            button.setIcon(null);
	            button.setBackground(Color.WHITE);

	            // If there is a master ship in the square, set the button's icon to the master ship's icon
	            if (square.isMasterShipAtSquare()) {
	                Ship masterShip = controller.loadMasterShip();
	                button.setIcon(masterShip.getIcon());
	            } else {
	                // Check for the presence of enemy ships in the square
	                ArrayList<Ship> enemyShips = square.getEnemyShipsAtSquare();
	                if (!enemyShips.isEmpty()) {
	                    // In this example, we set the button's icon to the first enemy ship in the list
	                    button.setIcon(enemyShips.get(0).getIcon());
	                }
	            }
	        }
	    }
	}

	
    public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setScoreLabel() {
		scoreLabel.setText("Score: " + score);
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}
	
	public void setMoveCountLabel() {
		moveCount.setText("Moves: " + moves);
	}

	public int getEnemyShips() {
		return enemyShips;
	}

	public void setEnemyShips(int enemyShips) {
		this.enemyShips = enemyShips;
	}

	public void setEnemyShipCountLabel() {
		enemyShipCount.setText("Enemy Ships: " + enemyShips);
	}
    

}
