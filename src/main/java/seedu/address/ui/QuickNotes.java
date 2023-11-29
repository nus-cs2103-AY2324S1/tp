package seedu.address.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.storage.QuickNotesStorage;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class QuickNotes extends UiPart<Region> {

    private static final String FXML = "QuickNotes.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private final QuickNotesStorage quickNotesStorage = new QuickNotesStorage();

    @FXML
    private TextArea quickNotes;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;


    /**
     * Creates a {@code QuickNotes}.
     */
    public QuickNotes() {
        super(FXML);
        initialize();
    }

    private void initialize() {
        saveButton.setOnAction(this::handleSaveNotes);
        clearButton.setOnAction(this::handleClearNotes);
        loadNotesOnStartup();

        quickNotes.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!oldValue.equals(newValue)) {
                    getRoot().getStyleClass().remove("saved-notes");
                }
            }
        });
    }


    @FXML
    private void handleSaveNotes(ActionEvent event) {
        try {
            quickNotesStorage.saveNotes(quickNotes.getText());
            // Change the style of the TextArea after saving
            getRoot().getStyleClass().add("saved-notes");
            logger.info("Successfully saved note file.");
        } catch (IOException e) {
            logger.warning("Failed to save note file : " + StringUtil.getDetails(e));
        }

    }

    @FXML
    private void handleClearNotes(ActionEvent event) {
        quickNotes.clear();
        try {
            quickNotesStorage.clearNotes();
            logger.info("Successfully cleared note file.");
        } catch (IOException e) {
            logger.warning("Failed to clear note file : " + StringUtil.getDetails(e));
        }
    }

    private void loadNotesOnStartup() {
        try {
            String content = quickNotesStorage.loadNotes();
            if (content != null) {
                quickNotes.setText(content);
            }
            logger.info("Successfully loaded note file.");
        } catch (IOException e) {
            logger.warning("Failed to load note file : " + StringUtil.getDetails(e));
        }
    }

}
