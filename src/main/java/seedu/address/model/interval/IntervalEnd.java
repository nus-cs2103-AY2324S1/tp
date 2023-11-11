package seedu.address.model.interval;

import seedu.address.model.person.End;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Interval's End in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEnd(String)}
 */

public class IntervalEnd extends End {

    /**
     * Constructs a {@code End}.
     *
     * @param end A valid phone number.
     */
    public IntervalEnd(String end) {
        super(end);
    }

    public Date getTimes() throws ParseException {
        assert isValidEnd(value);
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
        if (!(other instanceof seedu.address.model.interval.IntervalEnd)) {
            return false;
        }

        seedu.address.model.interval.IntervalEnd otherEnd = (seedu.address.model.interval.IntervalEnd) other;
        return value.equals(otherEnd.value);
    }
}
