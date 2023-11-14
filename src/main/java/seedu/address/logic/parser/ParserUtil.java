package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fields.ApplicationOutcome;
import seedu.address.model.fields.Cycle;
import seedu.address.model.fields.Date;
import seedu.address.model.fields.Description;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Location;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Pay;
import seedu.address.model.fields.Role;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.tag.Tag;

/**
 * Utility parser for assignments
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_DATE = "Enter date in yyyy-mm-dd HH:mm or yyyy-mm-dd format. "
            + "Date must be after current date.";

    public static final String MESSAGE_INVALID_NAME = "Name cannot be empty";

    public static final String MESSAGE_INVALID_LOCATION = "Location cannot be empty";

    public static final String MESSAGE_INVALID_ROLE = "Role cannot be empty";

    public static final String MESSAGE_INVALID_CYCLE = "Cycle cannot be empty";

    public static final String MESSAGE_INVALID_PAY = "Pay must be a non negative number, e.g. 0, 1600.50";

    public static final String MESSAGE_INVALID_STATUS = "Enter a valid status input: s/complete, s/incomplete";

    public static final String MESSAGE_INVALID_APP_OUTCOME = "Enter a valid outcome: o/follow-up, "
            + "ghosted, rejected, offered, accepted, awaiting";

    public static final String MESSAGE_INVALID_TASK_OUTCOME = "Enter a valid outcome: o/follow-up, "
            + "ghosted, rejected, offered, awaiting";

    /**
     * @param name the taskname
     * @return a name object
     * @throws ParseException
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidText(name)) {
            throw new ParseException(MESSAGE_INVALID_NAME);
        }
        return new Name(trimmedName);
    }

    /**
     * @param description the description of task
     * @return a description object
     * @throws ParseException
     */
    // Validation required? Currently, d/ is a valid input
    public static Description parseDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return new Description(trimmedDescription);
    }

    /**
     * @param status the completion status
     * @return a status object
     * @throws ParseException when input is not done or undone
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(status)) {
            throw new ParseException(MESSAGE_INVALID_STATUS);
        }
        return new Status(trimmedStatus.equals("complete"));
    }

    /**
     * @param date to be parsed
     * @return a date object
     * @throws ParseException when not in yyyy-mm-dd HH:mm format
     */
    public static Date parseDateForAdd(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();

        if (IsoDate.isValidDateNotBeforeToday(trimmedDate)) {
            return new IsoDate(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));
        }

        if (IsoDate.isValidIsoDateWithoutTimeAfterCurrent(trimmedDate)) {
            return new IsoDate(LocalDateTime.parse(date + " 23:59",
                    DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));
        }

        throw new ParseException(MESSAGE_INVALID_DATE);
    }

    /**
     * Parses {@code date} into a {@code Date} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param startDate to be parsed
     * @param endDate to be parsed
     * @return a date object
     * @throws ParseException when not in yyyy-mm-dd HH:mm format
     */
    public static Date parseDateForList(String startDate, String endDate) throws ParseException {
        requireNonNull(startDate, endDate);

        if (!IsoDate.isValidIsoDate(startDate) || !IsoDate.isValidIsoDate(endDate)
                || IsoDate.isDateBefore(endDate, startDate)) {
            throw new ParseException(IsoDate.MESSAGE_CONSTRAINTS_LIST);
        }

        return new IsoDate(LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * @param location of the internship
     * @return a Location object
     * @throws ParseException
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidText(trimmedLocation)) {
            throw new ParseException(MESSAGE_INVALID_LOCATION);
        }
        return new Location(trimmedLocation);
    }

    /**
     * @param role of the internship
     * @return a Role object
     * @throws ParseException
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidText(trimmedRole)) {
            throw new ParseException(MESSAGE_INVALID_ROLE);
        }
        return new Role(trimmedRole);
    }

    /**
     * @param cycle of the internship
     * @return a Cycle object
     * @throws ParseException
     */
    public static Cycle parseCycle(String cycle) throws ParseException {
        requireNonNull(cycle);
        String trimmedCycle = cycle.trim();
        if (!Cycle.isValidText(trimmedCycle)) {
            throw new ParseException(MESSAGE_INVALID_CYCLE);
        }
        return new Cycle(trimmedCycle);
    }

    /**
     * Pay a string to a Pay object
     * @param pay of the internship
     * @return a Pay object
     * @throws ParseException if not a valid pay
     */
    public static Pay parsePay(String pay) throws ParseException {
        requireNonNull(pay);
        String trimmedPay = pay.trim();
        if (!Pay.isValidPay(trimmedPay)) {
            throw new ParseException(MESSAGE_INVALID_PAY);
        }
        return new Pay(new BigDecimal(trimmedPay));
    }

    /**
     * Parse a string to an Outcome enum
     * @param outcome to be parsed
     * @return an Outcome
     * @throws ParseException if not a valid ApplicationOutcome
     */
    public static ApplicationOutcome parseApplicationOutcome(String outcome) throws ParseException {
        return Optional.of(outcome)
                .filter(ApplicationOutcome::isValidApplicationOutcome)
                .flatMap(Outcome::parseOutcome)
                .map(ApplicationOutcome::new)
                .orElseThrow(() -> new ParseException(MESSAGE_INVALID_APP_OUTCOME));
    }

    /**
     * Parse a string to an Outcome enum
     * @param outcome to be parsed
     * @return an Outcome
     * @throws ParseException if not a valid TaskOutcome
     */
    public static TaskOutcome parseTaskOutcome(String outcome) throws ParseException {
        return Optional.of(outcome)
                .filter(TaskOutcome::isValidTaskOutcome)
                .flatMap(Outcome::parseOutcome)
                .map(TaskOutcome::new)
                .orElseThrow(() -> new ParseException(MESSAGE_INVALID_TASK_OUTCOME));
    }
}
