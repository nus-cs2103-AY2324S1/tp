package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
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
     * Parses a {@code String ic} into an {@code Ic}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ic} is invalid.
     */
    public static Ic parseIc(String ic) throws ParseException {
        requireNonNull(ic);
        String trimmedIc = ic.trim().toUpperCase();
        if (!Ic.isValidIc(trimmedIc)) {
            throw new ParseException(Ic.MESSAGE_CONSTRAINTS);
        }
        return new Ic(trimmedIc);
    }

    /**
     * Parses a {@code String gender} into an {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a string to create a {@code Tag} object after processing the input string.
     * The input string is trimmed and converted to uppercase. If the tag name is a valid
     * patient tag name, it is prefixed with "priority: " before being used to create the tag.
     *
     * @param tag The string to be parsed into a Tag object.
     * @return A Tag object representing the input string.
     * @throws NullPointerException if the tag parameter is null.
     */
    public static Tag parseTag(String tag) {
        requireNonNull(tag);
        String trimmedTag = tag.trim().toUpperCase();
        if (Tag.isValidPatientTagName(trimmedTag)) {
            trimmedTag = "priority: " + trimmedTag;
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a collection of strings to create a set of {@code Tag} objects.
     * Each string in the collection is processed to create a Tag. If a duplicate tag is found,
     * a ParseException is thrown.
     *
     * @param tags The collection of strings to be parsed into Tag objects.
     * @return A set of Tag objects.
     * @throws ParseException if a duplicate tag is detected.
     * @throws NullPointerException if the tags parameter is null.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            Tag toAdd = parseTag(tagName);
            if (tagSet.contains(toAdd)) {
                throw new ParseException(Tag.DUPLICATE_TAG);
            }
            tagSet.add(toAdd);
        }
        return tagSet;
    }

    /**
     * Parses a string to create a {@code Tag} object representing a patient tag.
     * The tag name is validated to be a valid patient tag name, prefixed with "priority: ".
     * If the tag name is not valid, a ParseException is thrown.
     *
     * @param tag The string to be parsed into a patient Tag object.
     * @return A Tag object representing the patient tag.
     * @throws ParseException if the tag name is not a valid patient tag name.
     * @throws NullPointerException if the tag parameter is null.
     */
    public static Tag parsePatientTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim().toUpperCase();
        if (!Tag.isValidPatientTagName(trimmedTag)) {
            throw new ParseException(Tag.INVALID_PATIENT_TAG);
        }
        return new Tag("priority: " + trimmedTag);
    }

    /**
     * Parses a collection of strings to create a set of {@code Tag} objects representing patient tags.
     * If more than one tag is provided, a ParseException is thrown since patients should only have one tag.
     * Each tag is validated to be a proper patient tag.
     *
     * @param tags The collection of strings to be parsed into patient Tag objects.
     * @return A set of Tag objects representing the patient tags.
     * @throws ParseException if the collection contains more than one tag or if the tag is not valid.
     * @throws NullPointerException if the tags parameter is null.
     */
    public static Set<Tag> parsePatientTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        if (tags.size() > 1) {
            throw new ParseException(Tag.EXTRA_PATIENT_TAG);
        }
        for (String tagName : tags) {
            tagSet.add(parsePatientTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a string to create a {@code Tag} object representing a doctor tag.
     * The tag name is validated to be a valid doctor tag name. If the tag name is not valid,
     * a ParseException is thrown.
     *
     * @param tag The string to be parsed into a doctor Tag object.
     * @return A Tag object representing the doctor tag.
     * @throws ParseException if the tag name is not a valid doctor tag name.
     * @throws NullPointerException if the tag parameter is null.
     */
    public static Tag parseDoctorTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim().toUpperCase();
        if (!Tag.isValidDoctorTagName(trimmedTag)) {
            throw new ParseException(Tag.INVALID_DOCTOR_TAG);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses a collection of strings to create a set of {@code Tag} objects representing doctor tags.
     * Duplicate tags are not allowed, and if found, a ParseException is thrown.
     * Each tag is validated to be a proper doctor tag.
     *
     * @param tags The collection of strings to be parsed into doctor Tag objects.
     * @return A set of Tag objects representing the doctor tags.
     * @throws ParseException if a duplicate tag is detected or if the tag is not valid.
     * @throws NullPointerException if the tags parameter is null.
     */
    public static Set<Tag> parseDoctorTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            Tag toAdd = parseDoctorTag(tagName);
            if (tagSet.contains(toAdd)) {
                throw new ParseException(Tag.DUPLICATE_TAG);
            }
            tagSet.add(toAdd);
        }
        return tagSet;
    }

    /**
     * Parses a {@code String bloodtype} into a {@code BloodType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bloodtype} is invalid.
     */
    public static BloodType parseBloodType(String bloodType) throws ParseException {
        requireNonNull(bloodType);
        String trimmedTag = bloodType.trim();
        if (!BloodType.isValidBloodType(trimmedTag)) {
            throw new ParseException(BloodType.MESSAGE_CONSTRAINTS);
        }
        return new BloodType(trimmedTag);
    }

    /**
     * Parses a {@code String condition} into a {@code Condition}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code condition} is invalid.
     */
    public static Condition parseCondition(String condition) throws ParseException {
        requireNonNull(condition);
        String trimmedCondition = condition.trim();
        if (!Condition.isValidCondition(trimmedCondition)) {
            throw new ParseException(Condition.MESSAGE_CONSTRAINTS);
        }
        return new Condition(trimmedCondition);
    }


    /**
     * Parses {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Remark parseRemark(String remark) {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }

    /**
     * Parses {@code String appointmentTime} into a {@code Appointment}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static AppointmentTime parseAppointmentTime(String appointmentTime) throws ParseException {
        requireNonNull(appointmentTime);
        String trimmedAppointmentTime = appointmentTime.trim();
        if (!AppointmentTime.isValidAppointmentTime(trimmedAppointmentTime)) {
            throw new ParseException(AppointmentTime.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentTime(trimmedAppointmentTime);
    }
}
