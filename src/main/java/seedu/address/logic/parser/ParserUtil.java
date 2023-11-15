package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.Indices;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
     * Parses a string of {@code oneBasedIndices} into {@code Indices} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if at least one specified index is invalid (not non-zero unsigned integer).
     */
    public static Indices parseIndices(String oneBasedIndices) throws ParseException {
        String[] indices = oneBasedIndices.trim().split("\\s+");
        int size = indices.length;
        int[] trimmedIndices = new int[size];

        for (int i = 0; i < size; i++) {
            if (!StringUtil.isNonZeroUnsignedInteger(indices[i])) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            trimmedIndices[i] = Integer.parseInt(indices[i]);
        }
        return Indices.fromOneBased(trimmedIndices);
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
     * Parses a {@code String availability} into an {@code Availability}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code availability} is invalid.
     */
    public static Availability parseAvailability(String availability) throws ParseException {
        requireNonNull(availability);
        String trimmedAvailability = availability.trim();
        if (!Availability.isValidAvailability(trimmedAvailability)) {
            throw new ParseException(Availability.MESSAGE_CONSTRAINTS);
        }
        return new Availability(trimmedAvailability);
    }

    /**
     * Parses a {@code String animalType} into an {@code AnimalType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code animalType} is invalid.
     */
    public static AnimalType parseAnimalType(String animalType, Availability availability) throws ParseException {
        requireNonNull(animalType);
        requireNonNull(availability);

        String trimmedAnimalType = animalType.trim();
        boolean isValidType = false;

        switch (availability.value) {
        case "Available":
            isValidType = AnimalType.isValidAnimalType(trimmedAnimalType, AnimalType.VALIDATION_REGEX_AVAILABLE);
            break;
        case "NotAvailable":
            isValidType = AnimalType.isValidAnimalType(trimmedAnimalType, AnimalType.VALIDATION_REGEX_NOT_AVAILABLE);
            break;
        case Person.NIL_WORD:
            isValidType = AnimalType.isValidAnimalType(trimmedAnimalType, AnimalType.VALIDATION_REGEX_NIL);
            break;
        default:
            // no specific action is needed.
            break;
        }

        if (!isValidType) {
            throw new ParseException(AnimalType.MESSAGE_CONSTRAINTS);
        }

        return new AnimalType(trimmedAnimalType, availability);
    }

    /**
     * Parses a {@code String housing} into an {@code Housing}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code housing} is invalid.
     */
    public static Housing parseHousing(String housing) throws ParseException {
        requireNonNull(housing);
        String trimmedHousing = housing.trim();
        if (!Housing.isValidHousing(trimmedHousing)) {
            throw new ParseException(Housing.MESSAGE_CONSTRAINTS);
        }
        return new Housing(trimmedHousing);
    }

    public static String parseSimpleString(String confirmation) throws ParseException {
        return confirmation.trim();
    }
}
