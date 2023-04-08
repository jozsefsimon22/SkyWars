// SkyWars.java
package softwareDevelopment2Coursework;

import ships.*;

import java.lang.ModuleLayer.Controller;

import grid.*;

public class SkyWars {

    public static void main(String[] args) {
        Grid sky = new Grid();

        SkyWarsModel model = new SkyWarsModel(sky);
        SkyWarsMainGUI view = new SkyWarsMainGUI();
        SkyWarsController controller = new SkyWarsController(model, view);
        controller.initializeMasterShip();
        
        view.setVisible(true);
    }
}
