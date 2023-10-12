package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Encapsulates details of notifications.
 */
public class Notification {
    private final String title;
    private final String description;

    /**
     * Creates a notification.
     * @param title Title of notification.
     * @param description Description of notification.
     */
    public Notification(String title, String description) {
        requireAllNonNull(title, description);
        this.title = title;
        this.description = description;
    }

    /**
     * Gets title.
     * @return Title of notification.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets description.
     * @return Description of notification.
     */
    public String getDescription() {
        return description;
    }
}
