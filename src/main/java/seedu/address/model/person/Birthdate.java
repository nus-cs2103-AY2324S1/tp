package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

/**
 * Represents a Person's birthdate in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthdate(String)}
 */
public class Birthdate implements Comparable<Birthdate> {
    public static final String MESSAGE_CONSTRAINTS =
            "Birthdates should be of the form YYYY/MM/DD, and must be a valid date.\n"
            + "* Note: Date indicated must be YYYY/MM/DD"
            + " (i.e. 2th Jan 2020 must be input as 2021/01/02 instead of 2020-01-02).\n"
            + "E.g. birthdate=2023/10/20";

    private static final DateTimeFormatter FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd")
            .parseDefaulting(ChronoField.ERA, 1)
            .toFormatter()
            .withChronology(IsoChronology.INSTANCE)
            .withResolverStyle(ResolverStyle.STRICT);

    public final String value;
    public final LocalDate birthdate;

    /**
     * Constructs an {@code Birthdate}.
     *
     * @param birthdate A valid birthdate.
     */
    public Birthdate(String birthdate) {
        requireNonNull(birthdate);
        checkArgument(isValidBirthdate(birthdate), MESSAGE_CONSTRAINTS);
        this.value = birthdate;
        this.birthdate = LocalDate.parse(birthdate, FORMAT);
    }

    /**
     * Returns if a given string is a valid birthdate.
     */
    public static boolean isValidBirthdate(String test) {
        try {
            LocalDate.parse(test, FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthdate // instanceof handles nulls
                && value.equals(((Birthdate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Birthdate birthdate) {
        return this.birthdate.compareTo(birthdate.birthdate);
    }
}
