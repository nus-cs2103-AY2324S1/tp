package seedu.address.model.appointment;

import java.time.LocalDate;

/**
 * Abstract class representing schedule item.
 * Subclasses should implement compareTo method to define how
 * schedule items are compared.
 */
public abstract class ScheduleItem implements Comparable<ScheduleItem> {
    public abstract boolean isSameDate(LocalDate date);
    @Override
    public abstract int compareTo(ScheduleItem scheduleItem);
}
