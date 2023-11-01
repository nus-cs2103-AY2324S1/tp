package networkbook.ui;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import networkbook.model.person.Priority;

/**
 * A FieldLabel that displays priority level.
 */
public class PriorityFieldLabel extends FieldLabel {

    private static final String PRIORITY_STYLE_CLASS_PREFIX = "priority_";

    /**
     * Creates a {@code PriorityFieldLabel} with the given priority.
     */
    public PriorityFieldLabel(Priority priority) {
        super("");
        requireAllNonNull(priority);
        this.setText(priority.toString());
        this.getStyleClass().add(PRIORITY_STYLE_CLASS_PREFIX + priority.toString());
    }

}
