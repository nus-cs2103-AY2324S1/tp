package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.Messages.MESSAGE_TOO_MANY_INDEXES;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.Title;
import seedu.address.model.person.Email;
import seedu.address.model.person.LastContactedTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    /**
     * Checks if the arguments are empty.
     * @throws ParseException if arguments is not empty.
     */
    public static void verifyNoArgs(String args) throws ParseException {
        if (!args.isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_FIELDS);
        }
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (trimmedIndex.equals("")) {
            throw new ParseException(MESSAGE_MISSING_INDEX);
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX_FORMAT);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Splits multiple {@code oneBasedIndexes} by whitespace and parses them into an
     * ArrayList of {@code Indexes} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     *
     * @throws ParseException if any indexes are invalid (not non-zero unsigned integer)
     *                        or if there are too many indexes provided.
     */
    public static List<Index> parseIndexes(String oneBasedIndexes, int expectedIndexes) throws ParseException {
        assert expectedIndexes > 0 : "Expected indexes must be positive";

        String[] indexStrings = oneBasedIndexes.trim().split("\\s+");
        List<Index> indexes = new ArrayList<>();

        for (String indexString : indexStrings) {
            if (indexes.size() >= expectedIndexes) { // check if size overflows
                if (StringUtil.isNumeric(indexString)) { // if it is another index, throw too many index exception
                    throw new ParseException(MESSAGE_TOO_MANY_INDEXES);
                } else { // otherwise, throw invalid field exception
                    throw new ParseException(MESSAGE_INVALID_FIELDS);
                }
            }

            indexes.add(parseIndex(indexString));
        }

        if (indexes.size() < expectedIndexes) {
            throw new ParseException(MESSAGE_MISSING_INDEX);
        }

        return indexes;
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
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
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
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
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
     * Parses a {@code String start} into an {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code start} is invalid.
     */
    public static LocalDateTime parseContactTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedStart = time.trim();
        //set last contacted to LocalDateTime.MIN if last contacted field is not specified
        if (trimmedStart.isEmpty()) {
            return LocalDateTime.MIN;
        }
        try {
            LocalDateTime preppedTime = DateTimeUtil.parse(trimmedStart);
            if (!LastContactedTime.isValidLastContactedTime(preppedTime)) {
                throw new ParseException(LastContactedTime.MESSAGE_CONSTRAINTS);
            }
            if (checkCorrectDay(preppedTime, time)) {
                return preppedTime;
            } else {
                throw new ParseException(preppedTime.getMonth().toString()
                        + " does not have " + time.substring(0, 2) + " days.");
            }
        } catch (DateTimeParseException e) {
            throw new ParseException(LastContactedTime.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String start} into an {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code start} is invalid.
     */
    public static LocalDateTime parseMeetingTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedStart = time.trim();
        try {
            LocalDateTime result = DateTimeUtil.parse(trimmedStart);
            if (checkCorrectDay(result, time)) {
                return result;
            } else {
                throw new ParseException(result.getMonth().toString()
                        + "does not have " + time.substring(0, 2) + " days.");
            }
        } catch (DateTimeParseException e) {
            throw new ParseException(MeetingTime.MESSAGE_CONSTRAINTS);
        }
    }

    private static boolean checkCorrectDay(LocalDateTime localDateTime, String string) {
        if (Year.isLeap(localDateTime.getYear())
                && (localDateTime.getMonth() == Month.FEBRUARY && string.startsWith("29"))) {
            return true;
        }
        Month thisMonth = localDateTime.getMonth();
        if (!has31Days(thisMonth) && string.startsWith("31")) {
            return false;
        }
        return thisMonth != Month.FEBRUARY
                || (!string.startsWith("29") && !string.startsWith("30"));
    }

    private static boolean has31Days(Month month) {
        requireNonNull(month);
        switch (month) {
        case FEBRUARY: case APRIL: case JUNE: case SEPTEMBER: case NOVEMBER:
            return false;
        default:
            return true;
        }
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
        return Tag.of(trimmedTag);
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
     * Parses a {@code String attendee} into a {@code Attendee}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attendee} is invalid.
     */
    public static Attendee parseAttendee(String attendee) throws ParseException {
        requireNonNull(attendee);
        String trimmedAttendee = attendee.trim();
        if (!Attendee.isValidAttendee(trimmedAttendee)) {
            throw new ParseException(Attendee.MESSAGE_CONSTRAINTS);
        }
        return new Attendee(trimmedAttendee);
    }

    /**
     * Parses {@code Collection<String> attendees} into a {@code Set<Attendee>}.
     */
    public static Set<Attendee> parseAttendees(Collection<String> attendees) throws ParseException {
        requireNonNull(attendees);
        final Set<Attendee> attendeeSet = new HashSet<>();
        for (String person : attendees) {
            attendeeSet.add(parseAttendee(person));
        }
        return attendeeSet;
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String status} into an {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }
}
