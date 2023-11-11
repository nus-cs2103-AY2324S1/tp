package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Time;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        System.out.println(name);
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseGroupName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String groupName} into an {@code Group}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code groupName} is invalid.
     */
    public static Group parseGroup(String groupName) throws ParseException {
        requireNonNull(groupName);
        String trimmedGroupName = groupName.trim();
        if (!Group.isValidGroupName(trimmedGroupName)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }
        Group group = new Group(trimmedGroupName);
        return group;
    }

    /**
     * Parses a {@code String groupName} into a {@code Group}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code groupName} is invalid.
     */
    public static Group parseSingleGroup(String groupName) throws ParseException {
        requireNonNull(groupName);
        String trimmedGroup = groupName.trim();
        if (!Group.isValidGroupName(trimmedGroup)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(trimmedGroup);
    }

    /**
     * Parses a {@code String timeString} into a {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeString} is invalid.
     */
    public static Time parseTime(String timeString) throws ParseException {
        requireNonNull(timeString);
        String trimmedTimeString = timeString.trim();
        if (!Time.isValidTime(trimmedTimeString)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        String dayString = trimmedTimeString.substring(0, timeString.indexOf(" "));
        String time = trimmedTimeString.substring(timeString.indexOf(" ") + 1);
        DayOfWeek day = Time.decodeDay(dayString);
        LocalTime hour = LocalTime.parse(time.substring(0, 2) + ":" + time.substring(2, 4));
        return new Time(day, hour);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code ArrayList<TimeInterval>}.
     */
    public static ArrayList<TimeInterval> parseInterval(Collection<String> timeIntervals) throws ParseException {
        requireNonNull(timeIntervals);
        ArrayList<TimeInterval> timeIntervalsAl = new ArrayList<>();
        for (String intString: timeIntervals) {
            timeIntervalsAl.add(parseEachInterval(intString));
        }
        return timeIntervalsAl;
    }

    /**
     * Parses a {@code String interval} into a {@code TimeInterval}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interval} is invalid.
     */
    public static TimeInterval parseEachInterval(String interval) throws ParseException {
        requireNonNull(interval);
        String trimmedInterval = interval.trim();
        if (!TimeInterval.isValidTimeIntervalSyntax(trimmedInterval)) {
            throw new ParseException(TimeInterval.MESSAGE_CONSTRAINTS_SYNTAX);
        }
        String start = interval.substring(0, interval.indexOf("-")).trim();
        Time startTime = parseTime(start);
        String end = interval.substring(interval.indexOf('-') + 1).trim();
        Time endTime = parseTime(end);
        if (!TimeInterval.isValidTimeIntervalLogic(startTime, endTime)) {
            throw new ParseException(TimeInterval.MESSAGE_CONSTRAINTS_LOGIC);
        }
        return new TimeInterval(startTime, endTime);
    }
}
