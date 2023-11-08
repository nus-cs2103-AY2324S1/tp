package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.DATE_TIME_STRING_FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.task.exceptions.InvalidDeadlineException;

/**
 * An object representing the deadline of a task.
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS = "The deadline should "
            + "be in the format 'yyyy-MM-dd HH:mm' where:\n"
            + "    -'yyyy' is the year.\n"
            + "    -'MM' is the month.\n"
            + "    -'dd' is the day.\n"
            + "    -'HH:mm' is the time in 24-hour format.";

    public static final String ABSENT_DEADLINE = "-";


    private final Optional<LocalDateTime> deadline;

    /**
     * Constructs a Deadline object with String representation of a datetime.
     * If is String is null, instead creates an empty deadline.
     *
     * @param deadline the String representation of the deadline datetime.
     */
    public Deadline(String deadline) {
        if (deadline == null) {
            this.deadline = Optional.empty();
        } else {
            try {
                this.deadline = Optional.of(LocalDateTime.parse(deadline, DATE_TIME_STRING_FORMATTER));
            } catch (DateTimeParseException e) {
                throw new InvalidDeadlineException();
            }
        }
    }

    /**
     * Constructs a Deadline object with the provided LocalDateTime.
     * If the LocalDateTime is null, instead creates an empty deadline.
     *
     * @param deadline the LocalDateTime.
     */
    public Deadline(LocalDateTime deadline) {
        this.deadline = Optional.ofNullable(deadline);
    }

    /**
     * Checks if a deadline is present
     *
     * @return true if a deadline is present, false otherwise.
     */
    public boolean isPresent() {
        return deadline.isPresent();
    }

    public LocalDateTime getDeadline() {
        return deadline.orElse(null);
    }

    /**
     * Compares this deadline to another deadline. Deadlines that are present are prioritized above absent ones.
     *
     * @param other the object to be compared.
     * @return -1 if this deadline is before, 1 if deadline is after and 0 if the deadlines are both present and equal.
     */
    @Override
    public int compareTo(Deadline other) {
        requireNonNull(other);

        if (isPresent() && other.isPresent()) {
            return deadline.get().compareTo(other.deadline.get());
        } else if (isPresent()) {
            return -1;
        } else if (other.isPresent()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Gets the formatted string representation of the deadline.
     *
     * @return the string representation of "-" if no deadline is present.
     */
    public String getFormattedDeadline() {
        if (isPresent()) {
            return deadline.get().format(DATE_TIME_STRING_FORMATTER);
        }
        return ABSENT_DEADLINE;
    }

    @Override
    public boolean equals(Object other) {
        requireNonNull(other);
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        if (isPresent() && otherDeadline.isPresent()) {
            return deadline.get().equals(otherDeadline.deadline.get());
        }
        return isPresent() == otherDeadline.isPresent();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("deadline", deadline)
                .toString();
    }
}
