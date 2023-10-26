package networkbook.ui;

import static java.util.Objects.requireNonNull;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import networkbook.model.person.Graduation;
import networkbook.model.person.Person;
import networkbook.model.person.Priority;

/**
 * An UI component that displays text with an action performed on click.
 */
public class FieldHyperlink extends Button {

    private final Runnable action;

    /**
     * Creates a {@code FieldHyperlink} with the given label and action.
     */
    public FieldHyperlink(String labelText, Runnable action) {
        // super(FXML);
        this.setText(labelText);
        this.action = action;
        this.setOnAction(e -> action.run());
    }

}
