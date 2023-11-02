package seedu.address.model.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.lead.Lead;

/**
 * Represents a Reminder
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public final class Reminder implements Comparable<LocalDate> {
    private final Person person;
    private final LocalDate followUpDate;

    /**
     * Represents a Reminder in the list of reminders
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Reminder(Person person) {
        this.person = person;
        //Person class should check if followUpDate is present before creating a Reminder
        assert person.getFollowUpDate().isPresent();
        this.followUpDate = person.getFollowUpDate().get();
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public boolean isAfter(LocalDate date) {
        return followUpDate.isAfter(date);
    }
    public boolean isAfterNow() {
        return followUpDate.isAfter(LocalDate.now());
    }

    public long getDueTime() {
        return ChronoUnit.MILLIS.between(LocalDateTime.now(), followUpDate.atStartOfDay());
    }
    public Name getName() {
        return person.getName();
    }

    public Lead getLead() {
        return person.getLead();
    }

    /**
     * Returns true if both reminders have the same identity and data fields.
     * This defines a stronger notion of equality between two reminders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Reminder otherReminder = (Reminder) other;
        return person.isSamePerson(otherReminder.person)
                && followUpDate.equals(otherReminder.followUpDate);
    }

    @Override
    public int compareTo(LocalDate date) {
        if (this.followUpDate.isEqual(date)) {
            return 0;
        }
        return this.followUpDate.isAfter(followUpDate) ? 1 : -1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person", person)
                .add("followUpDate", followUpDate)
                .toString();
    }
}
