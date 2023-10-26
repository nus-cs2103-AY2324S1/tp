package networkbook.ui;

import javafx.scene.control.Button;

/**
 * An UI component that displays text with an action performed on click.
 */
public class FieldHyperlink extends Button {

    private final Runnable action;

    /**
     * Creates a {@code FieldHyperlink} with the given label and action.
     */
    public FieldHyperlink(String labelText, Runnable action) {
        // Set button parameters
        this.setText(labelText);
        this.action = action;
        this.setOnAction(e -> action.run());
    }

}
