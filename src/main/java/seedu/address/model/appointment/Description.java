package seedu.address.model.appointment;

/**
 * Represents a description of an appointment.
 */
public class Description {
    private String description;

    /**
     * Constructs a Description object with the specified description text.
     *
     * @param description The text description of the appointment.
     */
    public Description(String description) {
        this.description = description;
    }

    /**
     * Retrieves the text description of the appointment.
     *
     * @return The text description of the appointment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the text description of the appointment.
     *
     * @param description The new text description for the appointment.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
