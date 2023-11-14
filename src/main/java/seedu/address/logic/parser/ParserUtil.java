package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.policy.Policy;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
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
     * Parses a {@code String preferredMeetingRegion} into a
     * {@code PreferredMeetingRegion}.
     */
    public static seedu.address.model.person.PreferredMeetingRegion parsePreferredMeetingRegion(
            String preferredMeetingRegion) throws ParseException {
        requireNonNull(preferredMeetingRegion);
        String trimmedPreferredMeetingRegion = preferredMeetingRegion.trim();
        if (!seedu.address.model.person.PreferredMeetingRegion.isValidPreferredMeetingRegion(
                trimmedPreferredMeetingRegion)) {
            throw new ParseException(
                    seedu.address.model.person.PreferredMeetingRegion.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.person.PreferredMeetingRegion(trimmedPreferredMeetingRegion);
    }

    /**
     * Parses a {@code String preferredContact} into a {@code PreferredContact}.
     * @param policyName
     * @param description
     * @param policyValue
     * @param startDate
     * @param endDate
     * @return a set of policies
     * @throws ParseException
     */
    public static Set<Policy> parsePolicy(String policyName, String description, String policyValue, String startDate,
            String endDate) throws ParseException {
        requireNonNull(policyName);
        requireNonNull(description);
        requireNonNull(policyValue);
        requireNonNull(startDate);
        requireNonNull(endDate);
        final Set<Policy> policySet = new HashSet<>();

        double parsedPolicyValue;
        LocalDate parsedStartDate;
        LocalDate parsedEndDate;

        try {
            parsedPolicyValue = Double.parseDouble(policyValue);
        } catch (NumberFormatException e) {
            throw new ParseException(Policy.POLICY_VALUE_MESSAGE_CONSTRAINTS);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedStartDate = LocalDate.parse(startDate, formatter);
            parsedEndDate = LocalDate.parse(endDate, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(Policy.DATE_MESSAGE_CONSTRAINTS);
        }

        checkArgument(Policy.isValidPolicyName(policyName), Policy.POLICY_NAME_MESSAGE_CONSTRAINTS);
        checkArgument(Policy.isValidDescription(description), Policy.DESCRIPTION_MESSAGE_CONSTRAINTS);
        checkArgument(Policy.isValidPolicyValue(policyValue), Policy.POLICY_VALUE_MESSAGE_CONSTRAINTS);
        checkArgument(Policy.isValidDate(parsedStartDate), Policy.DATE_MESSAGE_CONSTRAINTS);
        checkArgument(Policy.isValidDate(parsedEndDate), Policy.DATE_MESSAGE_CONSTRAINTS);
        Policy policy = new Policy(policyName, description, parsedPolicyValue, parsedStartDate, parsedEndDate);
        policySet.add(policy);
        return policySet;
    }

}
