package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ImpossibleIndexException;
import seedu.address.logic.parser.exceptions.MissingIndexException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.month.DeleteMonth;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.LicencePlate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.policy.Company;
import seedu.address.model.policy.PolicyDate;
import seedu.address.model.policy.PolicyNumber;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (trimmedIndex.isEmpty()) {
            throw new MissingIndexException(Messages.MESSAGE_MISSING_INDEX);
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ImpossibleIndexException(Messages.MESSAGE_IMPOSSIBLE_INDEX);
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
     * Parses a {@code String nric} into an {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(trimmedNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
    }

    /**
     * Parses a {@code String licencePlate} into an {@code LicencePlate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code licencePlate} is invalid.
     */
    public static LicencePlate parseLicencePlate(String licencePlate) throws ParseException {
        requireNonNull(licencePlate);
        String trimmedLicencePlate = licencePlate.trim();
        if (!LicencePlate.isValidLicencePlate(trimmedLicencePlate)) {
            throw new ParseException(LicencePlate.MESSAGE_CONSTRAINTS);
        }
        return new LicencePlate(trimmedLicencePlate);
    }

    /**
     * Parses a {@code String policyNumber} into an {@code PolicyNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code policyNumber} is invalid.
     */
    public static PolicyNumber parsePolicyNumber(String policyNumber) throws ParseException {
        requireNonNull(policyNumber);
        String trimmedPolicyNumber = policyNumber.trim();
        if (!PolicyNumber.isValidPolicyNumber(trimmedPolicyNumber)) {
            throw new ParseException(PolicyNumber.MESSAGE_CONSTRAINTS);
        }
        return new PolicyNumber(trimmedPolicyNumber);
    }

    /**
     * Parses a {@code String policyIssueDate} into an {@code PolicyIssueDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code policyIssueDate} is invalid.
     */
    public static PolicyDate parsePolicyIssueDate(String policyIssueDate) throws ParseException {
        requireNonNull(policyIssueDate);
        String trimmedPolicyIssueDate = policyIssueDate.trim();
        if (!PolicyDate.isValidPolicyDate(trimmedPolicyIssueDate)) {
            throw new ParseException(PolicyDate.MESSAGE_CONSTRAINTS);
        }
        return new PolicyDate(trimmedPolicyIssueDate);
    }

    /**
     * Parses a {@code String policyExpiryDate} into an {@code PolicyExpiryDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code policyExpiryDate} is invalid.
     */
    public static PolicyDate parsePolicyExpiryDate(String policyExpiryDate) throws ParseException {
        requireNonNull(policyExpiryDate);
        String trimmedPolicyExpiryDate = policyExpiryDate.trim();
        if (!PolicyDate.isValidPolicyDate(trimmedPolicyExpiryDate)) {
            throw new ParseException(PolicyDate.MESSAGE_CONSTRAINTS);
        }
        return new PolicyDate(trimmedPolicyExpiryDate);
    }

    /**
     * Parses a {@code String company} into an {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        String trimmedCompany = company.trim();
        if (!Company.isValidCompany(trimmedCompany)) {
            throw new ParseException(Company.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompany);
    }

    /**
     * Parses a {@code String deleteMonth} into an {@code DeleteMonth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deleteMonth} is invalid.
     */
    public static DeleteMonth parseDeleteMonth(String deleteMonth) throws ParseException {
        requireNonNull(deleteMonth);
        String trimmedDeleteMonth = deleteMonth.trim();
        if (!DeleteMonth.isValidDeleteMonth(trimmedDeleteMonth)) {
            throw new ParseException(DeleteMonth.MESSAGE_CONSTRAINTS);
        }
        return new DeleteMonth(trimmedDeleteMonth);
    }
}
