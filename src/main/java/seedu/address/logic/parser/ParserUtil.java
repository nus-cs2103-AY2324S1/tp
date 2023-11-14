package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.address.Address;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.date.Date;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskDescription;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_CONTAINS_MORE_THAN_ONE_WHITE_SPACE =
            "%1$s contains more than one white space.";

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
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        if (isContainsMoreThanOneWhiteSpace(trimmedName)) {
            throw new ParseException(String.format(MESSAGE_CONTAINS_MORE_THAN_ONE_WHITE_SPACE, "Name"));
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
        if (isContainsMoreThanOneWhiteSpace(trimmedAddress)) {
            throw new ParseException(String.format(MESSAGE_CONTAINS_MORE_THAN_ONE_WHITE_SPACE, "Address"));
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
        String trimmedTag = tag.trim().toLowerCase();
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
     * Parses a {@code String Date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Date} is invalid.
     */
    public static Date parseDate(String eventDate) throws ParseException {
        requireNonNull(eventDate);
        String trimmedEventDate = eventDate.trim();
        if (!Date.isValidDate(trimmedEventDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedEventDate);
    }


    /**
     * Parses {@code Collection<String> contactNames} into a {@code Set<Name>}.
     */
    public static Set<Name> parseContactNames(Collection<String> contactNames) throws ParseException {
        requireNonNull(contactNames);
        final Set<Name> contactNameSet = new HashSet<>();
        for (String contactName : contactNames) {
            contactNameSet.add(parseName(contactName));
        }
        return contactNameSet;
    }

    /**
     * Parses a {@code String taskDescription} into an {@code TaskDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code taskDescription} is invalid.
     */
    public static TaskDescription parseTaskDescription(String taskDescription) throws ParseException {
        requireNonNull(taskDescription);
        String trimmedTaskDescription = taskDescription.trim();
        if (!TaskDescription.isValidDescription(trimmedTaskDescription)) {
            throw new ParseException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        if (isContainsMoreThanOneWhiteSpace(trimmedTaskDescription)) {
            throw new ParseException(String.format(MESSAGE_CONTAINS_MORE_THAN_ONE_WHITE_SPACE, "Task Description"));
        }
        return new TaskDescription(trimmedTaskDescription);
    }

    /**
     * Parses input to check if the input contains more than one white space.
     * @param input Input string to check.
     * @return true if input contains more than one white space, false otherwise.
     */
    public static boolean isContainsMoreThanOneWhiteSpace(String input) {
        return input.matches(".*\\s{2,}.*");
    }
}
