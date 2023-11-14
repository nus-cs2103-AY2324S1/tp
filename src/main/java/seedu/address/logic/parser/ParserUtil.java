package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Description;
import seedu.address.model.event.FromDate;
import seedu.address.model.event.Note;
import seedu.address.model.event.ToDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.rsvp.RsvpStatus;
import seedu.address.model.venue.Address;
import seedu.address.model.venue.Capacity;

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
     * Parses {@code Collection<String> indexes} into a {@code Set<Index>}.
     */
    public static Set<Index> parseIndexes(Collection<String> indexes) throws ParseException {
        requireNonNull(indexes);
        final Set<Index> indexSet = new HashSet<>();
        for (String index : indexes) {
            indexSet.add(parseIndex(index));
        }
        return indexSet;
    }

    // Persons

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

    // Events

    /**
     * Parses a {@code String name} into an {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static seedu.address.model.event.Name parseEventName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Description.isValidDesc(trimmedName)) {
            throw new ParseException(seedu.address.model.event.Name.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.event.Name(trimmedName);
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDesc(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String date} into an {@code FromDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static FromDate parseFromDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!FromDate.isValidDate(trimmedDate)) {
            throw new ParseException(FromDate.MESSAGE_CONSTRAINTS);
        }
        return new FromDate(trimmedDate);
    }

    /**
     * Parses a {@code String date} into an {@code ToDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static ToDate parseToDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!ToDate.isValidDate(trimmedDate)) {
            throw new ParseException(ToDate.MESSAGE_CONSTRAINTS);
        }
        return new ToDate(trimmedDate);
    }

    /**
     * Parses a {@code String note} into an {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        if (note == null) {
            return null;
        }
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }

    // Venues

    /**
     * Parses a {@code String name} into an {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static seedu.address.model.venue.Name parseVenueName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Description.isValidDesc(trimmedName)) {
            throw new ParseException(seedu.address.model.venue.Name.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.venue.Name(trimmedName);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseVenueAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String capacity} into an {@code Capacity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Capacity parseVenueCapacity(String capacity) throws ParseException {
        requireNonNull(capacity);
        String trimmedCapacity = capacity.trim();
        if (!Capacity.isValidCapacity(trimmedCapacity)) {
            throw new ParseException(Capacity.MESSAGE_CONSTRAINTS);
        }
        return new Capacity(trimmedCapacity);
    }

    /**
     * Parses a {@code String status} into an {@code RsvpStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static RsvpStatus parseRsvpStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        return RsvpStatus.getRsvpStatus(trimmedStatus);
    }

    // Vendors

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static seedu.address.model.vendor.Name parseVendorName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!seedu.address.model.vendor.Name.isValidName(trimmedName)) {
            throw new ParseException(seedu.address.model.vendor.Name.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.vendor.Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static seedu.address.model.vendor.Phone parseVendorPhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!seedu.address.model.vendor.Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(seedu.address.model.vendor.Phone.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.vendor.Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static seedu.address.model.vendor.Email parseVendorEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!seedu.address.model.vendor.Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(seedu.address.model.vendor.Email.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.vendor.Email(trimmedEmail);
    }
}
