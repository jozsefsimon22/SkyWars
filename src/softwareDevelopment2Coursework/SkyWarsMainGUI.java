package softwareDevelopment2Coursework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import grid.*;

public class SkyWarsMainGUI extends JFrame {

	private JPanel contentPane;
	private JLabel gameStatus;
	private Grid gameGrid;
	private int prevRow = -1;
	private int prevCol = -1;
	private JButton prevButton = null;
	private ImageIcon imageIcon;

	public SkyWarsMainGUI(Grid map) {
		this.gameGrid = map;
		initialize();
	}

	private void initialize() {
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
		imageIcon = new ImageIcon(scaledMasterShip);
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

	    JButton resetButton = new JButton("Reset");
	    statusbar.add(resetButton);

	    // Add the status bar to the main content pane
	    contentPane.add(statusbar, BorderLayout.EAST);
	}


	private JButton createGridButton(int row, int col) {
	    JButton button = new JButton();
	    button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // If there was a previous button clicked, remove the master ship from that square
	            if(prevCol != -1 && prevRow != -1) {
	                Square prevSquare = gameGrid.getSquare(prevRow, prevCol);
	                prevSquare.removeMasterShipAtSquare();
	                prevButton.setIcon(null);
	            }

	            // Set the master ship at the new square
	            Square square = gameGrid.getSquare(row, col);
	            square.setMasterShipAtSquare();

	            button.setIcon(imageIcon);

	            // Update the previous row, column, and button
	            prevRow = row;
	            prevCol = col;
	            prevButton = (JButton) e.getSource();
	        }
	    });

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

	public void updateGameStatus(String status) {
		gameStatus.setText("Status: " + status);
	}
}