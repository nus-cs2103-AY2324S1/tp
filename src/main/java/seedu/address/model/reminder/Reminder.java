package seedu.address.model.reminder;

import java.time.LocalDate;

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

    public Name getName() {
        return person.getName();
    }

    public Lead getLead() {
        return person.getLead();
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public boolean isAfterYesterday() {
        return this.compareTo(LocalDate.now().plusDays(-1)) > 0;
    }

    @Override
    public int compareTo(LocalDate date) {
        if (this.followUpDate.isEqual(date)) {
            return 0;
        }
        return this.followUpDate.isAfter(date) ? 1 : -1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person", person)
                .add("followUpDate", followUpDate)
                .toString();
    }
}
