package seedu.address.model.person;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's annual leave in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAnnualLeave(String)}
 */
public class AnnualLeave {
    public static final String MESSAGE_CONSTRAINTS =
            "Number of days of annual leave remaining should only contain numerical digits. "
                + "It should not contain dashes or spaces.";

    public static final String MESSAGE_LEAVE_CONSTRAINTS =
            "Number of days of annual leave taken should not exceed the total limit.";

    /*
     * The first character of the annual leave must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    public List<LocalDate> leaveList;

    /**
     * Constructs a {@code AnnualLeave}.
     *
     * @param annualLeave A valid annual leave.
     */
    public AnnualLeave(String annualLeave) {
        requireNonNull(annualLeave);
        checkArgument(isValidAnnualLeave(annualLeave), MESSAGE_CONSTRAINTS);
        value = annualLeave;
        this.leaveList = new ArrayList<>();
    }

    /**
     * Returns true if a given string is a valid number of days.
     */
    public static boolean isValidAnnualLeave(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        String result = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (LocalDate date : this.leaveList) {
            String formattedDate = date.format(formatter);
            result += formattedDate + " | ";
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnnualLeave)) {
            return false;
        }

        AnnualLeave otherAnnualLeave = (AnnualLeave) other;
        return value.equals(otherAnnualLeave.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public void addLeave(LocalDate startDate) {
        this.leaveList.add(startDate);
    }

    public void addLeave(LocalDate startDate, LocalDate endDate) {
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        for (int i = 0; i <= numOfDays; i++) {
            this.leaveList.add(startDate.plusDays(i));
        }
    }

    public boolean isValidLeave(LocalDate startDate, LocalDate endDate) {
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        int totalNumOfLeaves = parseInt(value);
        if (numOfDays + this.leaveList.size() <= totalNumOfLeaves) {
            return true;
        }
        return false;
    }

}
