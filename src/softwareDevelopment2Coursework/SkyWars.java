package softwareDevelopment2Coursework;

import ships.*;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import grid.*;
import grid.Square;

public class SkyWars {

	public static void main(String[] args) {
		
		ImageIcon imageIcon = new ImageIcon("master.png");
		
		
		Image masterShip = imageIcon.getImage();
		Image scaledMasterShip = masterShip.getScaledInstance(20, 20, masterShip.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledMasterShip);
		
		
		
		
		
        
        
//        button.setIcon(imageIcon);
		
		ShipGenerator ship = new ShipGenerator();
		
		Grid map = new Grid();
		
		SkyWarsMainGUI gameWindow = new SkyWarsMainGUI(map);
		gameWindow.setVisible(true);
		
		String test = JOptionPane.showInputDialog("Enter a name");
		
		gameWindow.updateGameStatus(test);
		
	}
}