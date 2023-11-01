package networkbook.ui;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import javafx.scene.control.Label;

/**
 * An UI component that displays text in a colored rectangle.
 */
public class FieldLabel extends Label {

    /**
     * Creates a {@code FieldLabel} with the given label text.
     */
    public FieldLabel(String labelText) {
        requireAllNonNull(labelText);
        this.setText(labelText);
        this.getStyleClass().add("cell_field");
        this.getStyleClass().add("cell_small_label");
    }

}
