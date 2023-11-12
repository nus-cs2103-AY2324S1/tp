package seedu.address.model.interview.exceptions;

/**
 * Signals that the operation will result in interview clash.
 * (Interviews are considered clashes interviews have overlapping start time and end time
 * - As checked by isClashingWith).
 */
public class InterviewClashException extends RuntimeException {
    public InterviewClashException() {
        super("Operation would result in interview clash");
    }
}
