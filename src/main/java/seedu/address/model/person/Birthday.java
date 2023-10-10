package seedu.address.model.person;

import java.time.LocalDate;
import java.time.MonthDay;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable;
 */
public class Birthday {
    public final MonthDay birthday;

    public Birthday(MonthDay birthday) {
        requireNonNull(birthday);
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return birthday.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return birthday.equals(otherBirthday.birthday);
    }

    @Override
    public int hashCode() {
        return birthday.hashCode();
    }

}
