package seedu.address.model.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.person.lead.Lead;
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
    private final List<Interaction> interactions;

    private final LocalDate followUpDate;

    /**
     * Represents a Reminder in the list of reminders
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Reminder(Name name, Phone phone, Set<Tag> tags,
        List<Interaction> interactions, Lead lead, LocalDate currentDate) {
        this.name = name;
        this.phone = phone;
        this.tags.addAll(tags);
        this.interactions = interactions;
        assert lead != null;
        this.lead = lead;
        this.followUpDate = this.getFollowUpDate(interactions, currentDate, lead);
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

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    private LocalDate getFollowUpDate(
            List<Interaction> interacitons, LocalDate currentDate, Lead lead) {
        //Creating a copy of the interactions list is not necssary as we are sorting by dates
        //However it is still defeinsive to do so to prevent side effects
        List<Interaction> interactionsCopy = new ArrayList<>(interactions);
        interactionsCopy.sort((a, b) -> b.getDate().compareTo(a.getDate()));
        LocalDate lastInteractionDate = interactionsCopy.get(0).getDate();
        int followUpPeriod = lead.getFollowUpPeriod();
        return lastInteractionDate.plusWeeks(followUpPeriod);
    }

    public long getDueTime() {
        // return followUpDate.toEpochDay() - LocalDate.now().toEpochDay();
        return ChronoUnit.MILLIS.between(LocalDateTime.now(), followUpDate.atStartOfDay());
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
                && followUpDate.equals(otherReminder.followUpDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("tags", tags)
                .add("lead", lead)
                .add("followUpDate", followUpDate)
                .toString();
    }
}
