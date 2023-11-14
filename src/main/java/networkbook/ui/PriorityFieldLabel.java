package networkbook.ui;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import networkbook.model.person.Priority;

/**
 * A FieldLabel that displays priority level.
 */
public class PriorityFieldLabel extends FieldLabel {

    private static final String PRIORITY_STYLE_CLASS_PREFIX = "priority_";
    private static final String PRIORITY_CHAR = "★";

    /**
     * Creates a {@code PriorityFieldLabel} with the given priority.
     */
    public PriorityFieldLabel(Priority priority) {
        super("");
        requireAllNonNull(priority);
        StringBuilder labelText = new StringBuilder("");
        switch (priority.getPriorityLevel()) {
        case HIGH:
            labelText.append(PRIORITY_CHAR);
            // Fallthrough
        case MEDIUM:
            labelText.append(PRIORITY_CHAR);
            // Fallthrough
        case LOW:
            labelText.append(PRIORITY_CHAR);
            // Fallthrough
        default:
            break;
        }
        this.setText(labelText.toString());
        this.getStyleClass().add(PRIORITY_STYLE_CLASS_PREFIX + priority.toString());
    }

}
