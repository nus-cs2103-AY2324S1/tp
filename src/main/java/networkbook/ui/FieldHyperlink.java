package networkbook.ui;

import javafx.scene.control.Hyperlink;

/**
 * An UI component that displays text with an action performed on click.
 */
public class FieldHyperlink extends Hyperlink {

    /**
     * Creates a {@code FieldHyperlink} with the given label and action.
     */
    public FieldHyperlink(String labelText, Runnable action) {
        super(labelText);
        this.setOnAction(e -> action.run());
    }

}
