// SkyWars.java
package softwareDevelopment2Coursework;

import ships.*;
import grid.*;

public class SkyWars {

    public static void main(String[] args) {
        Grid map = new Grid();

        SkyWarsModel model = new SkyWarsModel(map);
        SkyWarsMainGUI view = new SkyWarsMainGUI();
        SkyWarsController controller = new SkyWarsController(model, view);

        view.setVisible(true);
    }
}
