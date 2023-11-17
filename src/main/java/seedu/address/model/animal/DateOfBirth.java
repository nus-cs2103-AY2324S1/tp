package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Objects;

import seedu.address.commons.util.TimeUtil;

/**
 * Represents the Date of Birth of the {@link Animal}.
 */
public class DateOfBirth {
    public static final String MESSAGE_CONSTRAINTS = String.format(
        "Date Of Birth should be in the following format:%n%s%n", TimeUtil.getValidDateFormats());

    public static final String MESSAGE_DATE_CONSTRAINTS_FORMAT = "Date Of Birth: %s is in the future!";
    public static final String AGE_FORMAT = "%s Year(s), %s Month(s), %s Day(s)";
    private final LocalDate dateOfBirth;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param date a valid date.
     */
    public DateOfBirth(String date) {
        requireNonNull(date);
        checkArgument(TimeUtil.isValidDate(date), MESSAGE_CONSTRAINTS);

        LocalDate parsedDate = TimeUtil.parseDateString(date);
        checkArgument(!TimeUtil.isFutureDate(parsedDate), String.format(MESSAGE_DATE_CONSTRAINTS_FORMAT, parsedDate));

        this.dateOfBirth = parsedDate;
    }

    public String getAge() {
        // Current date in Singapore time zone
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Singapore"));

        // Calculate the age using Period class
        Period age = Period.between(dateOfBirth, currentDate);

        // Construct and return the result
        return String.format(AGE_FORMAT, age.getYears(), age.getMonths(), age.getDays());
    }


    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return TimeUtil.isValidDate(test);
    }

    /**
     * Returns the {@link LocalDate} object representing this DateOfBirth.
     *
     * @return the LocalDate representing this DateOfBirth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DateOfBirth that = (DateOfBirth) o;
        return Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth);
    }

    @Override
    public String toString() {
        return dateOfBirth.toString();
    }
}
