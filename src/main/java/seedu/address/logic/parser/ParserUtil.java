package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.RuntimeParseException;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.event.exceptions.InvalidEventPeriodException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String NUMBER_INDEX_INVALID_INDEX = "Number of arguments provided is invalid.";
    public static final DateTimeFormatter DATE_TIME_STRING_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm");
    private static final String SPLIT_SPACE_DELIMITER = " ";
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
     * Parses given string and returns an array consisting of 2 Indexes. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static ArrayList<Index> parseDualIndexes(String oneBasedIndex) throws ParseException {
        final int maxParseIndexes = 2;

        ArrayList<Index> arrayList = new ArrayList<>();
        String[] indexArray = oneBasedIndex.split(SPLIT_SPACE_DELIMITER);

        if (indexArray.length != maxParseIndexes) {
            throw new ParseException(NUMBER_INDEX_INVALID_INDEX);
        }

        String trimmedIndex1 = indexArray[0].trim();
        String trimmedIndex2 = indexArray[1].trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex1)
                || !StringUtil.isNonZeroUnsignedInteger(trimmedIndex2)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        arrayList.add(Index.fromOneBased(Integer.parseInt(trimmedIndex1)));
        arrayList.add(Index.fromOneBased(Integer.parseInt(trimmedIndex2)));
        return arrayList;
    }

    /**
     * Wrapped version of parseIndex that throws a RuntimeException instead.
     *
     * @param oneBasedIndex index integer to be converted into Index object.
     * @return Index object that is parsed from the input index integer.
     * @throws RuntimeException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndexSafe(String oneBasedIndex) throws RuntimeParseException {
        try {
            return parseIndex(oneBasedIndex);
        } catch (ParseException pe) {
            throw new RuntimeParseException();
        }
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
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    public static Optional<Set<Tag>> parseTagsForUse(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses the event description String into a EventDescription object.
     *
     * @param description description String.
     * @return EventDescription object with the given description String.
     * @throws ParseException if the description is empty.
     */
    public static EventDescription parseEventDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!EventDescription.isValid(description)) {
            throw new ParseException(EventDescription.MESSAGE_CONSTRAINTS);
        }
        return new EventDescription(trimmedDescription);
    }

    /**
     * Parses given start date string and end date string into an EventPeriod object.
     *
     * @param startDate start date string in 'yyyy-MM-dd HH:mm' format.
     * @param endDate end date string in 'yyyy-MM-dd HH:mm' format.
     * @return EventPeriod object describing the time period between startDate and endDate.
     * @throws ParseException if the startDate or endDate strings are in improper format.
     */
    public static EventPeriod parseEventPeriod(String startDate, String endDate) throws ParseException {
        requireAllNonNull(startDate, endDate);
        String trimmedStartDate = startDate.trim();
        String trimmedEndDate = endDate.trim();
        try {
            EventPeriod.isValidPeriod(trimmedStartDate, trimmedEndDate);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new ParseException(EventPeriod.MESSAGE_CONSTRAINTS);
        } catch (InvalidEventPeriodException invalidEventPeriodException) {
            throw new ParseException(EventPeriod.PERIOD_INVALID);
        }
        return new EventPeriod(trimmedStartDate, trimmedEndDate);
    }

    /**
     * Parses given DateTime String into a LocalDateTime object.
     *
     * @param dateTime a date time string in 'yyyy-MM-dd HH:mm' format.
     * @return LocalDateTime object describing the time and date.
     * @throws ParseException if the string is not in the stated format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        return LocalDateTime.parse(trimmedDateTime, DATE_TIME_STRING_FORMATTER);
    }
}
