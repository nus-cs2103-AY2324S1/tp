//@@author phiphi-tan
package seedu.codesphere.ui;

import javafx.scene.layout.Region;

/**
 * A UI for the splash window
 */
public class SplashPanel extends UiComponent<Region> {

    private static final String FXML = "SplashPanel.fxml";

    public SplashPanel() {
        super(FXML);
    }
}
