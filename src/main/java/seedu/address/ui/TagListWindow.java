package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * The window for displaying the list of tags.
 */
public class TagListWindow extends UiPart<Stage> {
    private static final String FXML = "TagListWindow.fxml";

    private static final Logger logger = LogsCenter.getLogger(TagListWindow.class);

    private TagListPanel tagListPanel;

    @FXML
    private StackPane tagListPanelPlaceholder;

    /**
     * Initialises an TagListWindow object
     */
    public TagListWindow(Stage root, Logic logic) {
        super(FXML, root);
        tagListPanel = new TagListPanel(logic.getFilteredTagsList());
        tagListPanelPlaceholder.getChildren().add(tagListPanel.getRoot());
    }

    /**
     * Shows the tag list window.
     */
    public void show() {
        logger.fine("Showing tag list page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void focus() {
        getRoot().requestFocus();
    }
}
