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
	

	/**
	 * Create the frame.
	 */
	public SkyWarsMainGUI(Grid map) {
		this.gameGrid = map;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		// Status bar
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
		
		
		//JPanel for the game grid
		int rows = 4;
		int columns = 4;
		JPanel grid = new JPanel();
		grid.setBackground(Color.WHITE);
		contentPane.add(grid, BorderLayout.CENTER);
		grid.setLayout(new GridLayout(rows, columns));
		
		for (int i = 0; i < rows; i++) {
		    for (int j = 0; j < columns; j++) {
		    	final int row = i;
		    	final int col = j;
		        JButton button = new JButton();
		        
		        // Add action listeners or customise the appearance of the button as needed.
		        button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
		        		Square square =  gameGrid.getMap()[row][col];
		        		
		        		JOptionPane.showMessageDialog(button, "Square at row " + row + ", column " + col + " clicked");
		        		
		        		ImageIcon imageIcon = new ImageIcon("master.png");
		        		
		        		
		        		Image masterShip = imageIcon.getImage();
		        		Image scaledMasterShip = masterShip.getScaledInstance(100, 100, masterShip.SCALE_SMOOTH);
		        		imageIcon = new ImageIcon(scaledMasterShip);
		        		
		        		button.setIcon(imageIcon);
		        	}
		        });
		        grid.add(button);
		    }// end inner for loop
		}//end for loop
		//END GRID

	}// end GUI
	
	// Method to update game status
	public void updateGameStatus(String status) {
		gameStatus.setText("Status: " + status);
	}
	
}// end class
