package seedu.address.model.interval;

import seedu.address.model.person.Begin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Interval's Begin in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBegin(String)}
 */

public class IntervalBegin extends Begin {

    /**
     * Constructs a {@code Begin}.
     *
     * @param begin A valid phone number.
     */
    public IntervalBegin(String begin) {
        super(begin);
    }

    public Date getTimes() throws ParseException {
        assert isValidBegin(value);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
        return dateFormat.parse(value);
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
        if (!(other instanceof seedu.address.model.interval.IntervalBegin)) {
            return false;
        }

        seedu.address.model.interval.IntervalBegin otherBegin = (seedu.address.model.interval.IntervalBegin) other;
        return value.equals(otherBegin.value);
    }
}
