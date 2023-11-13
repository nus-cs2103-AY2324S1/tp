package seedu.address.model.person;

import static seedu.address.commons.util.DateTimeUtil.format;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code LastContactedTime} matches the given time.
 */
public class LastContactTimeContainsPredicate implements Predicate<Person> {
    private final LocalDateTime time;

    /**
     * Constructs a predicate with the given time.
     * @param time time to be checked.
     */
    public LastContactTimeContainsPredicate(LocalDateTime time) {
        this.time = time;
        format(this.time);
        LastContactedTime.isValidLastContactedTime(time);
    }

    @Override
    public boolean test(Person person) {
        if (time.equals(LocalDateTime.MIN)) {
            return true;
        }
        return person.getLastContactedTime().equals(time);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LastContactTimeContainsPredicate)) {
            return false;
        }

        LastContactTimeContainsPredicate otherLastContactTimeContainsPredicate =
                (LastContactTimeContainsPredicate) other;
        return this.time.equals(otherLastContactTimeContainsPredicate.time);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("time", time).toString();
    }
}
