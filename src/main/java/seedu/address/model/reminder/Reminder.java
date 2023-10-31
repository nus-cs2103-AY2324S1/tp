package seedu.address.model.reminder;

import java.util.Calendar;
import java.util.Date;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.lead.Lead;

/**
 * Represents a Reminder
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder implements Comparable<Date> {
    private static final int DAYS_IN_WEEK = 7;

    private final Person person;
    private final Date followUpDate;

    /**
     * Represents a Reminder in the list of reminders
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Reminder(Person person, Date currentDate) {
        this.person = person;
        this.followUpDate = this.setFollowUpDate(currentDate, person.getLead());
    }

    public Date getFollowUpDate() {
        return followUpDate;
    }

    private Date setFollowUpDate(Date currentDate, Lead lead) {
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
        return person.isSamePerson(otherReminder.person)
                && followUpDate.equals(otherReminder.followUpDate);
    }

    @Override
    public int compareTo(Date date) {
        return this.followUpDate.after(date) ? 1 : -1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person", person)
                .add("followUpDate", followUpDate)
                .toString();
    }
}
