package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * A panel class for list panel classes that should appear on the lower list.
 */
abstract class BottomListPanel extends UiPart<Region> {
    /**
     * Creates a {@code BottomListPanel} with the given fxmlFileName.
     */
    public BottomListPanel(String fxmlFileName) {
        super(fxmlFileName);
    }
}
