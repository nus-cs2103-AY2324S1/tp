package networkbook.ui;

/**
 * An UI component that displays text with an action performed on click.
 * It is styled differently to represent that it will open an external application.
 */
public class ExternalFieldHyperlink extends FieldHyperlink {

    /**
     * Creates an {@code ExternalFieldHyperlink} with the given label and action.
     */
    public ExternalFieldHyperlink(String labelText, Runnable action) {
        super(labelText, action);
        this.setText(labelText);
        this.getStyleClass().add("external");
        this.getStyleClass().add("cell_field");
        this.getStyleClass().add("cell_small_label");
        this.setOnAction(e -> action.run());
    }

}
