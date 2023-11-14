package networkbook.ui;

/**
 * An UI component that displays text with an action performed on click.
 */
public class TagHyperlink extends FieldHyperlink {

    /**
     * Creates a {@code TagHyperlink} with the given label and action.
     */
    public TagHyperlink(String labelText, Runnable action) {
        super(labelText, action);
        this.setText(labelText);
        this.getStyleClass().add("tag");
        this.getStyleClass().add("cell_field");
        this.getStyleClass().add("cell_small_label");
        this.setOnAction(e -> action.run());
    }

}
