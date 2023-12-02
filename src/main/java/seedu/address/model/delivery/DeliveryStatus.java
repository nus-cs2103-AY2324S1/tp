package seedu.address.model.delivery;

/**
 * Represents the status of a delivery.
 */
public enum DeliveryStatus {
    CREATED,
    SHIPPED,
    COMPLETED,
    CANCELLED;

    public static final String MESSAGE_CONSTRAINTS = "Delivery Status should be one of "
        + "CREATED, SHIPPED, COMPLETED, CANCELLED";

    /**
     * Checks if string given is a valid status.
     *
     * @param test string to be tested.
     * @return true if is a valid status, false otherwise.
     */
    public static boolean isValidStatus(String test) {
        for (DeliveryStatus s : DeliveryStatus.values()) {
            if (s.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}


