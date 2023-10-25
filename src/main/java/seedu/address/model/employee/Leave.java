package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Leave {

    public static final String MESSAGE_CONSTRAINTS = "Leave dates have to be of format dd-MM-yyyy!"
            + " Please ensure that the date is valid!";

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            .withResolverStyle(ResolverStyle.STRICT);

    public final LocalDate leaveDate;

    private Leave(String leaveDate) {
        requireNonNull(leaveDate);
        this.leaveDate = LocalDate.parse(leaveDate, DATE_FORMAT);
    }

    private Leave() {
        this.leaveDate = null;
    }

    @Override
    public String toString() {
        return leaveDate == null ? "no leave scheduled!" : leaveDate.format(DATE_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Leave)) {
            return false;
        }

        Leave otherDate = (Leave) other;
        return leaveDate.equals(otherDate.leaveDate);
    }

    @Override
    public int hashCode() {
        return leaveDate.hashCode();
    }

}
