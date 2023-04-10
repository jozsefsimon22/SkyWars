// SkyWarsMainGUI.java
package softwareDevelopment2Coursework;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SkyWarsMainGUI extends JFrame {
    private JPanel contentPane;
    private JLabel gameStatus;
    private ImageIcon masterShipIcon;
    private ImageIcon battleStarIcon;

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

        createStatusBar();
        createGrid();

        // Create ImageIcon for the master ship
        ImageIcon originalIcon = new ImageIcon("master.png");
        Image masterShip = originalIcon.getImage();
        Image scaledMasterShip = masterShip.getScaledInstance(100, 100, masterShip.SCALE_SMOOTH);
        masterShipIcon = new ImageIcon(scaledMasterShip);
        
        ImageIcon originalIconTwo = new ImageIcon("ship_one.png");
        Image battleStar = originalIconTwo.getImage();
        Image scaledBattleStar = battleStar.getScaledInstance(100, 100, masterShip.SCALE_SMOOTH);
        battleStarIcon = new ImageIcon(scaledBattleStar);
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

	    JLabel masterSpaceShipMode = new JLabel("Master Space Ship Mode: VARIABLE");
	    statusbar.add(masterSpaceShipMode);

	    statusbar.add(Box.createVerticalStrut(20));

	    JLabel enemyShipCount = new JLabel("Enemy Ships: VARIABLE");
	    statusbar.add(enemyShipCount);

	    statusbar.add(Box.createVerticalStrut(20));

	    JLabel score = new JLabel("Score: VARIABLE");
	    statusbar.add(score);

	    statusbar.add(Box.createVerticalStrut(20));

	    JLabel moveCount = new JLabel("Moves: VARIABLE");
	    statusbar.add(moveCount);

	    // Add a glue component to push the reset button to the bottom of the status bar
	    statusbar.add(Box.createVerticalGlue());

	    JButton resetButton = new JButton("Restart");
	    resetButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    statusbar.add(resetButton);

	    // Add the status bar to the main content pane
	    contentPane.add(statusbar, BorderLayout.EAST);
	}
	
    public ImageIcon getMasterShipIcon() {
        return masterShipIcon;
    }
    
    public ImageIcon getbattleStarIcon() {
        return battleStarIcon;
    }

    public void updateGameStatus(String status) {
        gameStatus.setText("Status: " + status);
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

}
