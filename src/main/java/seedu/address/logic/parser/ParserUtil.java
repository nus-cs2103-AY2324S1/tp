package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointments.AppointmentDate;
import seedu.address.model.appointments.AppointmentTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.dentist.Specialization;
import seedu.address.model.person.dentist.Yoe;
import seedu.address.model.tag.Tag;
import seedu.address.model.treatment.TreatmentCost;
import seedu.address.model.treatment.TreatmentName;
import seedu.address.model.treatment.TreatmentTime;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing
     * whitespaces will be trimmed.
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
     * Parses a {@code String name} into a {@code Name}. Leading and trailing whitespaces will be
     * trimmed.
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
     * Parses a {@code String phone} into a {@code Phone}. Leading and trailing whitespaces will be
     * trimmed.
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
     * Parses a {@code String address} into an {@code Address}. Leading and trailing whitespaces
     * will be trimmed.
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
     * Parses a {@code String birthdate} into an {@code birthdate}. Leading and trailing whitespaces
     * will be trimmed.
     *
     * @throws ParseException if the given {@code Birthdate} is invalid.
     */
    public static Birthdate parseBirthdate(String birthdate) throws ParseException {
        requireNonNull(birthdate);
        String trimmedbirthdate = birthdate.trim();
        if (!Birthdate.isValidDate(trimmedbirthdate)) {
            throw new ParseException(Birthdate.MESSAGE_CONSTRAINTS);
        }
        return new Birthdate(trimmedbirthdate);
    }

    /**
     * Parses a {@code String gender} into an {@code gender}. Leading and trailing whitespaces will
     * be trimmed.
     *
     * @throws ParseException if the given {@code Gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedgender = gender.trim();
        if (!Gender.isValidGender(trimmedgender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedgender);
    }

    /**
     * Parses a {@code String Remark} into an {@code Remark }. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses the given start time and duration strings to create an AppointmentTime object.
     *
     * @param startTime The string representation of the appointment start time.
     * @param duration  The string representation of the appointment duration.
     * @return An AppointmentTime object representing the parsed start time and duration.
     * @throws ParseException       if the start time or duration is in an invalid format.
     * @throws NullPointerException if either startTime or duration is null.
     */
    public static AppointmentTime parseAppointmentTime(String startTime, String duration)
            throws ParseException {
        requireNonNull(startTime);
        requireNonNull(duration);
        String trimmedStartTime = startTime.trim();
        String trimmedDuration = duration.trim();
        if (!AppointmentDate.isValidDate(trimmedStartTime)) {
            throw new ParseException(AppointmentDate.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentTime(trimmedStartTime, trimmedDuration);
    }

    /**
     * Parses a {@code String email} into an {@code Email}. Leading and trailing whitespaces will be
     * trimmed.
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
     * Parses a {@code String tag} into a {@code Tag}. Leading and trailing whitespaces will be
     * trimmed.
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

    // Parse Methods for Dentists

    /**
     * Parses a {@code String specialization} into an {@code Specialization}. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code specialization} is invalid.
     */
    public static Specialization parseSpecialization(String specialization) throws ParseException {
        requireNonNull(specialization);
        String trimmedSpecialization = specialization.trim();
        if (!Specialization.isValidSpecialization(trimmedSpecialization)) {
            throw new ParseException(Specialization.MESSAGE_CONSTRAINTS);
        }
        return new Specialization(trimmedSpecialization);
    }

    /**
     * Parses a {@code String yoe} into an {@code String}. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the given {@code yoe} is invalid.
     */
    public static Yoe parseYoe(String yoe) throws ParseException {
        requireNonNull(yoe);
        String trimmedYoe = yoe.trim();
        if (!Yoe.isValidYoe(trimmedYoe)) {
            throw new ParseException(Yoe.MESSAGE_CONSTRAINTS);
        }
        return new Yoe(trimmedYoe);
    }

    /**
     * Parses a {@code String treatment cost} into an {@code String}. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cost} is invalid.
     */
    public static TreatmentCost parseTreatmentCost(String cost) throws ParseException {
        requireNonNull(cost);
        String trimmedcost = cost.trim();
        if (!TreatmentCost.isValidCost(trimmedcost)) {
            throw new ParseException(TreatmentCost.MESSAGE_CONSTRAINTS);
        }
        return new TreatmentCost(trimmedcost);
    }

    /**
     * Parses a {@code String TreatmentTime} into an {@code String}. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static TreatmentTime parseTreatmentTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedtime = time.trim();
        if (!TreatmentTime.isValidTime(trimmedtime)) {
            throw new ParseException(TreatmentTime.MESSAGE_CONSTRAINTS);
        }
        return new TreatmentTime(trimmedtime);
    }

    /**
     * Parses a {@code String TreatmentName} into an {@code String}. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TreatmentName parseTreatmentName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedname = name.trim();
        if (!TreatmentName.isValidName(trimmedname)) {
            throw new ParseException(TreatmentName.MESSAGE_CONSTRAINTS);
        }
        return new TreatmentName(trimmedname);
    }



}
