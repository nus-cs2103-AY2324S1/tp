package seedu.address.ui;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.address.model.module.Module;

/**
 * A UI component that displays information of a {@code Module}.
 */
public class ModuleBox extends UiPart<Region> {

    private static final String FXML = "ModuleBox.fxml";

    private List<Module> moduleList;

    @FXML
    private HBox modulePane;

    @FXML
    private Text modulesTaken;

    /**
     * Creates a {@code ModuleBox} with the given {@code Module} to display.
     */
    public ModuleBox(ObservableList<Module> modules) {
        super(FXML);
        this.moduleList = modules;
        // modulesTaken.setText("Modules taken: ");
        setDisplayText();
        modulesTaken.setStroke(Color.WHITE);
    }

    public void setDisplayText() {
        String toBeDisplayed = "Modules taken: ";
        for (Module module : moduleList) {
            toBeDisplayed += (module + " ");
        }
        modulesTaken.setText(toBeDisplayed);
    }
}
