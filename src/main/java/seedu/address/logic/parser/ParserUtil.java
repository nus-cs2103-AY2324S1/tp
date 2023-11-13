package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.AppointmentDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_DUPLICATE_INDEX = "Index is not a non-zero unsigned integer.";
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
     * Parses a space-separated list of index strings and returns a list of Index objects.
     * @param indexesString The space-separated index strings.
     * @return List of Index objects.
     * @throws ParseException if any of the specified indexes is invalid (not a non-zero unsigned integer).
     */
    public static List<Index> parseIndexes(String indexesString) throws ParseException {
        String[] indexStrings = indexesString.trim().split("\\s+");
        List<Index> indexes = new ArrayList<>();
        List<Integer> seenIndexes = new ArrayList<>();

        for (String indexStr : indexStrings) {
            String trimmedIndex = indexStr.trim();
            if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }

            int currentIndex = Integer.parseInt(trimmedIndex);

            // Check for duplicate indexes
            if (seenIndexes.contains(currentIndex)) {
                throw new ParseException(MESSAGE_DUPLICATE_INDEX);
            }

            seenIndexes.add(currentIndex);

            indexes.add(Index.fromOneBased(currentIndex));
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
     * Parses a {@code String occupation} into a {@code Occupation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code occupation} is invalid.
     */
    public static Occupation parseOccupation(String occupation) throws ParseException {
        requireNonNull(occupation);
        String trimmedOccupation = occupation.trim();
        if (!Occupation.isValidOccupation(trimmedOccupation)) {
            throw new ParseException(Occupation.MESSAGE_CONSTRAINTS);
        }
        return new Occupation(trimmedOccupation);
    }

    /**
     * Parses a {@code String date} into a {@code AppointmentDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static AppointmentDate parseAppointmentDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (date.isEmpty()) {
            return new AppointmentDate("");
        }
        if (!AppointmentDate.isValidFormat(trimmedDate)) {
            throw new ParseException(AppointmentDate.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (!AppointmentDate.isValidCurrentDate(trimmedDate)) {
            throw new ParseException(AppointmentDate.MESSAGE_CONSTRAINTS_CURRENTDATE);
        }
        return new AppointmentDate(DateTimeParser.convertDate(date));
    }

    /**
     * Parses {@code Collection<String> appointmentDate} into a {@code AppointmentDate}.
     */
    public static AppointmentDate parseAppointmentDates(Collection<String> appt) throws ParseException {
        requireNonNull(appt);
        final Set<AppointmentDate> appointmentDateSet = new HashSet<>();
        for (String tagName : appt) {
            return parseAppointmentDate(tagName);
        }

        return parseAppointmentDate("");
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
}
