// SkyWars.java
package softwareDevelopment2Coursework;

import ships.*;

import java.awt.EventQueue;
import java.lang.ModuleLayer.Controller;

import grid.*;

public class SkyWars {

    public static void main(String[] args) {
    	Grid sky = new Grid();
        SkyWarsModel model = new SkyWarsModel(sky);
        SkyWarsMainGUI view = new SkyWarsMainGUI();
        SkyWarsController controller = new SkyWarsController(model, view);
      
    	        EventQueue.invokeLater(new Runnable() {
    	            public void run() {
    	                try {
    	                    MainMenu menu = new MainMenu();
    	                    menu.setVisible(true);
    	                    menu.setController(controller);
    	                } catch (Exception e) {
    	                    e.printStackTrace();
    	                }
    	            }
    	        });



        
    }
}
