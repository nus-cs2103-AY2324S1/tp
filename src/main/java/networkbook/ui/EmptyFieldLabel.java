package networkbook.ui;

/**
 * A FieldLabel representing a null value.
 */
public class EmptyFieldLabel extends FieldLabel {

    private static final String EMPTY_FIELD_TEXT = "-";
    private static final String EMPTY_STYLE_CLASS = "empty";

    /**
     * Creates an {@code EmptyFieldLabel}.
     */
    public EmptyFieldLabel() {
        super(EMPTY_FIELD_TEXT);
        this.getStyleClass().add(EMPTY_STYLE_CLASS);
    }

}
