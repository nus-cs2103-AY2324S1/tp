package seedu.address.model.appointment;

/**
 * Abstract class representing schedule item.
 * Subclasses should implement compareTo method to define how
 * schedule items are compared.
 */
public abstract class ScheduleItem implements Comparable<ScheduleItem> {
    @Override
    public abstract int compareTo(ScheduleItem scheduleItem);
}
