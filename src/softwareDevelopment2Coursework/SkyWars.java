// SkyWars.java
package softwareDevelopment2Coursework;

import grid.*;


public class SkyWars {
	public static MainMenu menu;

    public static void main(String[] args) {
    	Grid sky = new Grid();
        SkyWarsModel model = new SkyWarsModel(sky);
        SkyWarsMainGUI view = new SkyWarsMainGUI();
        SkyWarsController controller = new SkyWarsController(model, view);
        
        controller.mainMenu();
    }
}
