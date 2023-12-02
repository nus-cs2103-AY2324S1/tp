package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_EMPTY_INDEX;
import static seedu.address.logic.Messages.MESSAGE_NON_INTEGER_INDEX;
import static seedu.address.logic.Messages.MESSAGE_NON_POSITIVE_INTEGER_INDEX;

import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Remark;
import seedu.address.model.company.Role;

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
        if (trimmedIndex.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_INDEX);
        }
        if (!StringUtil.isInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_NON_INTEGER_INDEX);
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_NON_POSITIVE_INTEGER_INDEX);
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
        String trimmedName = name.trim().replaceAll("\\s+", " ");
        if (trimmedName.isEmpty()) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS_NON_EMPTY);
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS_INVALID_REGEX);
        }
        if (!Name.isValidNameLength(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS_INVALID_LENGTH);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading, trailing and in-between whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim().replaceAll("\\s+", "");
        if (trimmedPhone.isEmpty()) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS_NON_EMPTY);
        }
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS_VALID_REGEX);
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
        if (trimmedEmail.isEmpty()) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS_NON_EMPTY);
        }
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS_VALID_REGEX);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String role} into an {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim().replaceAll("\\s+", " ");
        if (trimmedRole.isEmpty()) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS_NON_EMPTY);
        }
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS_INVALID_REGEX);
        }
        if (!Role.isValidRoleLength(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS_INVALID_LENGTH);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String deadline} into an {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (trimmedDeadline.isEmpty()) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS_NON_EMPTY);
        }
        if (!Deadline.isValidFormat(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS_WRONG_FORMAT);
        }
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS_INVALID_DEADLINE);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String status} into an {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static ApplicationStatus parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (trimmedStatus.isEmpty()) {
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS_NON_EMPTY);
        }
        if (!ApplicationStatus.isValidApplicationStatus(trimmedStatus)) {
            Logger.getGlobal().warning("CAUGHT HERE");
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS_VALID_STATUS);
        }
        return new ApplicationStatus(trimmedStatus);
    }

    /**
     * Parses a {@code String recruiterName} into an {@code RecruiterName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code recruiterName} is invalid.
     */
    public static RecruiterName parseRecruiterName(String recruiterName) throws ParseException {
        requireNonNull(recruiterName);
        String trimmedRecruiterName = recruiterName.trim().replaceAll("\\s+", " ");;
        if (trimmedRecruiterName.isEmpty()) {
            throw new ParseException(RecruiterName.MESSAGE_CONSTRAINTS_NON_EMPTY);
        }
        if (!RecruiterName.isValidName(trimmedRecruiterName)) {
            throw new ParseException(RecruiterName.MESSAGE_CONSTRAINTS_INVALID_REGEX);
        }
        if (!RecruiterName.isValidNameLength(trimmedRecruiterName)) {
            throw new ParseException(RecruiterName.MESSAGE_CONSTRAINTS_INVALID_LENGTH);
        }
        return new RecruiterName(trimmedRecruiterName);
    }

    /**
     * Parses a {@code String priority} into an {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim().toUpperCase();
        if (trimmedPriority.isEmpty()) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS_NON_EMPTY);
        }
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS_VALID_REGEX);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }
}
