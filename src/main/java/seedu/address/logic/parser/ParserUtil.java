package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.SortOrder;
import seedu.address.model.tag.Tag;

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
    public static Name parseName(CommandWarnings warn, String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        if (!Name.isAppropriateName(trimmedName)) {
            warn.addWarning(Name.MESSAGE_RECOMMENDATIONS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(CommandWarnings warn, String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        if (!Phone.isAppropriatePhone(trimmedPhone)) {
            warn.addWarning(Phone.MESSAGE_RECOMMENDATIONS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(CommandWarnings warn, String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        if (!Address.isAppropriateAddress(trimmedAddress)) {
            warn.addWarning(Address.MESSAGE_RECOMMENDATIONS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String HouseInfo} into a {@code HouseInfo}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code HouseInfo} is invalid.
     */
    public static HouseInfo parseHouseInfo(CommandWarnings warn, String info) throws ParseException {
        requireNonNull(info);
        String trimmedInfo = info.trim();
        if (!HouseInfo.isValidHouseInfo(trimmedInfo)) {
            throw new ParseException(HouseInfo.MESSAGE_CONSTRAINTS);
        }
        if (!HouseInfo.isAppropriateHouseInfo(trimmedInfo)) {
            warn.addWarning(HouseInfo.MESSAGE_RECOMMENDATIONS);
        }
        return new HouseInfo(trimmedInfo);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(CommandWarnings warn, String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        if (!Email.isAppropriateEmail(trimmedEmail)) {
            warn.addWarning(Email.MESSAGE_RECOMMENDATIONS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(CommandWarnings warn, String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        if (!Tag.isAppropriateTag(trimmedTag)) {
            warn.addWarning(Tag.MESSAGE_RECOMMENDATIONS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(CommandWarnings warn, Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(warn, tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String priority} into an {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(CommandWarnings warn, String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim().toLowerCase();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        if (!Priority.isAppropriatePriority(trimmedPriority)) {
            warn.addWarning(Priority.MESSAGE_RECOMMENDATIONS);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String sortOrder} into an {@code SortOrder}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortOrder} is invalid.
     */
    public static SortOrder parseSortOrder(CommandWarnings warn, String sortOrder) throws ParseException {
        requireNonNull(sortOrder);
        String trimmedSortOrder = sortOrder.trim();
        if (!SortOrder.isValidSortOrder(trimmedSortOrder)) {
            throw new ParseException(SortOrder.MESSAGE_CONSTRAINTS);
        }
        return new SortOrder(trimmedSortOrder);
    }
}
