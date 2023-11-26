package swe.context.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import swe.context.commons.core.index.Index;
import swe.context.commons.util.StringUtil;
import swe.context.logic.Messages;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INDEX_NOT_POSITIVE =
            "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(ParserUtil.MESSAGE_INDEX_NOT_POSITIVE);
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
        if (!Name.isValid(trimmedName)) {
            throw new ParseException(Messages.NAME_INVALID);
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
        if (!Phone.isValid(trimmedPhone)) {
            throw new ParseException(Messages.PHONE_INVALID);
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
        if (!Email.isValid(trimmedEmail)) {
            throw new ParseException(Messages.EMAIL_INVALID);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a string into a Note.
     *
     * Trims the specified string.
     */
    public static Note parseNote(String noteString) {
        requireNonNull(noteString);

        return new Note(
            noteString.trim()
        );
    }

    /**
     * Attempts to parse the specified string as a {@link Tag}.
     *
     * Trims the specified string as part of parsing.
     *
     * @param tagName Tag name.
     * @throws ParseException If the specified string is not a valid tag.
     */
    public static Tag parseTag(String tagName) throws ParseException {
        String trimmed = tagName.trim();

        if (!Tag.isValid(trimmed)) {
            throw new ParseException(
                Messages.tagInvalid(trimmed)
            );
        }

        return new Tag(trimmed);
    }

    /**
     * Attempts to parse the specified string as a {@link AlternateContact}.
     *
     * Trims the specified string as part of parsing.
     *
     * @throws ParseException if the specified string is not a valid alternate contact.
     */
    public static AlternateContact parseAlternate(String alternateContact) throws ParseException {
        String trimmed = alternateContact.trim();

        if (!AlternateContact.isValid(trimmed)) {
            throw new ParseException(
                    Messages.alternateContactInvalid(trimmed)
            );
        }

        return new AlternateContact(trimmed);
    }

    /**
     * Attempts to parse the specified strings as {@link Tag}s.
     *
     * @param tagNames Tag names.
     */
    public static Set<Tag> parseTags(Collection<String> tagNames) throws ParseException {
        Set<Tag> tags = new HashSet<>();
        for (String tagName : tagNames) {
            tags.add(
                parseTag(tagName)
            );
        }
        return tags;
    }

    /**
     * Attempts to parse the specified strings as {@link AlternateContact}s.
     *
     * @param alternateContactNames AlternateContact names.
     */
    public static Set<AlternateContact> parseAlternates(Collection<String> alternateContactNames)
            throws ParseException {
        Set<AlternateContact> alternateContacts = new HashSet<>();
        for (String alternateContactName : alternateContactNames) {
            alternateContacts.add(parseAlternate(alternateContactName));
        }
        return alternateContacts;
    }
}
