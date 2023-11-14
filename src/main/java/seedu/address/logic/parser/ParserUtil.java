package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.Date;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.StartTime;
import seedu.address.model.schedule.Status;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses {@code String time} into a {@code StartTime}.
     */
    public static StartTime parseStartTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();

        if (!StartTime.isValidStartTime(trimmedTime)) {
            throw new ParseException(StartTime.MESSAGE_CONSTRAINTS);
        }

        return new StartTime(LocalDateTime.parse(trimmedTime));
    }

    /**
     * Parses {@code String time} into a {@code EndTime}.
     */
    public static EndTime parseEndTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();

        if (!EndTime.isValidEndTime(trimmedTime)) {
            throw new ParseException(EndTime.MESSAGE_CONSTRAINTS);
        }

        return new EndTime(LocalDateTime.parse(trimmedTime));
    }

    /**
     * Parses {@code String status} into a {@code status}
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();

        if (trimmedStatus.equals("0")) {
            return Status.MISSED;
        } else if (trimmedStatus.equals("1")) {
            return Status.COMPLETED;
        } else {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses {@code String time} into a {@code StartTime}.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();

        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }

        return new Date(LocalDate.parse(trimmedDate));
    }
}
