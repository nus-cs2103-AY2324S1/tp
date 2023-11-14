package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.availability.FreeTime;
import seedu.address.model.availability.TimeInterval;
import seedu.address.model.course.Course;
import seedu.address.model.course.UniqueCourseList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Hour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

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
     * Parses a {@code String telegram} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedTelegram = telegram.trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
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
     * Parses a {@code String course} into a {@code Course}.
     */
    public static Course parseCourse(String course) throws ParseException {
        requireNonNull(course);
        String trimmedCourse = course.trim().toUpperCase();
        try {
            return UniqueCourseList.findByCourseCode(trimmedCourse);
        } catch (Exception e) {
            throw new ParseException(e.getMessage()); // If course is not found
        }
    }

    /**
     * Parses a {@code String course} into a {@code Course}.
     *
     * @throws ParseException if the given {@code course} is invalid.
     */
    public static Set<Course> parseCourses(Collection<String> courses) throws ParseException {
        requireNonNull(courses);
        final Set<Course> courseSet = new HashSet<>();
        for (String courseName : courses) {
            courseSet.add(parseCourse(courseName));
        }
        return courseSet;
    }

    /**
     * Parses a {@code String hour} into a {@code hour}.
     *
     * @throws ParseException if the given {@code hour} is invalid.
     */
    public static Hour parseHour(String hour) throws ParseException {
        requireNonNull(hour);
        String trimmedHour = hour.trim();
        try {
            Integer intHour = Integer.parseInt(trimmedHour);
            if (!Hour.isValidHour(intHour)) {
                throw new ParseException(Hour.MESSAGE_CONSTRAINTS);
            }
            return new Hour(intHour);
        } catch (NumberFormatException nfe) {
            throw new ParseException(Hour.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a collection of {@code String from} and {@code String to} into a {@code TimeInterval}.
     */
    public static TimeInterval parseTimeInterval(String from, String to) throws DateTimeParseException, ParseException {
        try {
            LocalTime fromTime = LocalTime.parse(from);
            LocalTime toTime = LocalTime.parse(to);
            if (!TimeInterval.isValidTimeInterval(fromTime, toTime)) {
                throw new ParseException(TimeInterval.MESSAGE_TO_BEFORE_FROM);
            }
            return new TimeInterval(fromTime, toTime);
        } catch (DateTimeParseException e) {
            throw new ParseException(FreeTime.MESSAGE_CONSTRAINTS);
        }
    }
}
