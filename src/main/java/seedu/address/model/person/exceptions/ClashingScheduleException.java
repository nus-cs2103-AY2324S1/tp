package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in clashing schedules
 */
public class ClashingScheduleException extends RuntimeException{
    public ClashingScheduleException() {
        super("Operation would result in clashing schedules");
    }
}
