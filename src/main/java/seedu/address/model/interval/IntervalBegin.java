package seedu.address.model.interval;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents a Interval's Begin in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBegin(String)}
 */

public class IntervalBegin {

    public static final String MESSAGE_CONSTRAINTS = "Begin has a format of HHMM";
    public static final String VALIDATION_REGEX = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$";
    public final String value;

    /**
     * Constructs a {@code Begin}.
     *
     * @param begin A valid phone number.
     */
    public IntervalBegin(String begin) {
        requireNonNull(begin);
        checkArgument(isValidBegin(begin), MESSAGE_CONSTRAINTS);
        value = begin;
    }

    public static boolean isValidBegin(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private Date parse(String test) throws ParseException {
        assert isValidBegin(test);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
        return dateFormat.parse(test);
    }

    public Date getTime() throws ParseException {
        return parse(value);
    }

    public int toInt() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.person.Begin)) {
            return false;
        }

        seedu.address.model.person.Begin otherBegin = (seedu.address.model.person.Begin) other;
        return value.equals(otherBegin.value);
    }
}
