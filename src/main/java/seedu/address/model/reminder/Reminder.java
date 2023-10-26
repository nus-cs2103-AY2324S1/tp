package seedu.address.model.reminder;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.lead.Lead;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a Reminder
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder {
    private static final int DAYS_IN_WEEK = 7;

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Lead lead;

    private final Date currentDate;
    private final Date followUpDate;

    /**
     * Represents a Reminder in the list of reminders
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Reminder(Name name, Phone phone, Set<Tag> tags, Lead lead, Date currentDate) {
        this.name = name;
        this.phone = phone;
        this.tags.addAll(tags);
        this.lead = lead;
        this.currentDate = currentDate;
        this.followUpDate = this.getFollowUpDate(currentDate, lead);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Lead getLead() {
        return lead;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public Date getFollowUpDate() {
        return followUpDate;
    }

    private Date getFollowUpDate(Date currentDate, Lead lead) {
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, lead.getFollowUpPeriod() * DAYS_IN_WEEK);
        return cal.getTime();
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
        return name.equals(otherReminder.name)
                && phone.equals(otherReminder.phone)
                && tags.equals(otherReminder.tags)
                && lead.equals(otherReminder.lead)
                && currentDate.equals(otherReminder.currentDate)
                && followUpDate.equals(otherReminder.followUpDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("tags", tags)
                .add("lead", lead)
                .add("currentDate", currentDate)
                .add("followUpDate", followUpDate)
                .toString();
    }
}
