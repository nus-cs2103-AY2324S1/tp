package seedu.lovebook.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;

/**
 * The UI component responsible for helping the user with preset commands.
 */
public class PresetsBar extends UiPart<Region> {
    private static final String FXML = "PresetsBar.fxml";

    private CommandBox commandBox;

    /**
     * Creates a {@code PresetsBar} with the given {@code CommandBox}.
     * @param commandBox The command box to be used.
     */
    public PresetsBar(CommandBox commandBox) {
        super(FXML);
        this.commandBox = commandBox;
    }


    /**
     * Handles the Add button pressed event.
     */
    @FXML
    public void handleClear() {
        commandBox.setCommandTextField("");
    }

    /**
     * Handles the Add button pressed event.
     */
    @FXML
    public void handleAddD() {
        commandBox.setCommandTextField("add name/NAME age/AGE gender/GENDER "
                + "height/HEIGHT income/INCOME horoscope/HOROSCOPE");
    }

    /**
     * Handles the Delete button pressed event.
     */
    @FXML
    public void handleDeleteD() {
        commandBox.setCommandTextField("delete INDEX");
    }

    /**
     * Handles the Edit button pressed event.
     */
    @FXML
    public void handleEditD() {
        commandBox.setCommandTextField("edit INDEX age/AGE gender/GENDER "
                + "height/HEIGHT income/INCOME horoscope/HOROSCOPE");
    }

    /**
     * Handles the Filter button pressed event.
     */
    @FXML
    public void handleSetPref() {
        commandBox.setCommandTextField("setP age/AGE gender/GENDER "
                + "height/HEIGHT income/INCOME horoscope/HOROSCOPE");
    }

    /**
     * Handles the Filter button pressed event.
     */
    @FXML
    public void handleShowPref() {
        commandBox.setCommandTextField("showP");
    }

}



