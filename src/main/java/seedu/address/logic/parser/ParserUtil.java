package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANGE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANGE_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeParserUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.addcommands.AddCompanyCommand;
import seedu.address.logic.commands.addcommands.AddInternshipCommand;
import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.logic.commands.editcommands.EditCompanyCommand;
import seedu.address.logic.commands.editcommands.EditPersonCommand;
import seedu.address.logic.commands.editcommands.editdescriptors.EditCompanyDescriptor;
import seedu.address.logic.commands.editcommands.editdescriptors.EditInternshipDescriptor;
import seedu.address.logic.commands.editcommands.editdescriptors.EditPersonDescriptor;
import seedu.address.logic.commands.sortcommands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyDescription;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
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
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private static Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses a {@code addPersonString} into an {@code Person}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Person parseAddPerson(String addPersonString) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(addPersonString,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!argMultimap.arePrefixesPresent(
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new Person(name, phone, email, address, tagList);
    }

    /**
     * Parses a {@code editPersonString} into an {@code Person}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static EditPersonDescriptor parseEditPerson(String editPersonString) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(editPersonString,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        Pattern r = Pattern.compile("^\\d+$");
        Matcher matcher = r.matcher(argMultimap.getPreamble());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCompanyCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        return editPersonDescriptor;
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
     * Parses a {@code addCompanyString} into an {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Company parseAddCompany(String addCompanyString) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(addCompanyString,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION, PREFIX_TAG);

        if (!argMultimap.arePrefixesPresent(
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION);
        CompanyName name = ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_NAME).get());
        CompanyPhone phone = ParserUtil.parseCompanyPhone(argMultimap.getValue(PREFIX_PHONE).get());
        CompanyEmail email = ParserUtil.parseCompanyEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        CompanyDescription description = ParserUtil.parseCompanyDescription(
                argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new Company(name, phone, email, description, tagList);
    }

    /**
     * Parses a {@code editCompanyString} into an {@code EditCompanyDescriptor}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static EditCompanyDescriptor parseEditCompany(String editCompanyString) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(editCompanyString,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION);

        Pattern r = Pattern.compile("^\\d+$");
        Matcher matcher = r.matcher(argMultimap.getPreamble());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCompanyCommand.MESSAGE_USAGE));
        }

        EditCompanyDescriptor editCompanyDescriptor = new EditCompanyDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editCompanyDescriptor.setCompanyName(
                    ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editCompanyDescriptor.setCompanyPhone(
                    ParserUtil.parseCompanyPhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editCompanyDescriptor.setCompanyEmail(
                    ParserUtil.parseCompanyEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editCompanyDescriptor.setDescription(
                    ParserUtil.parseCompanyDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        parseTagsForEdit(
                argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editCompanyDescriptor::setTags);

        if (!editCompanyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCompanyCommand.MESSAGE_NOT_EDITED);
        }

        return editCompanyDescriptor;
    }


    /**
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedCompanyName = companyName.trim();
        if (!CompanyName.isValidName(trimmedCompanyName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedCompanyName);
    }

    /**
     * Parses a {@code String companyPhone} into a {@code CompanyPhone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyPhone} is invalid.
     */
    public static CompanyPhone parseCompanyPhone(String companyPhone) throws ParseException {
        requireNonNull(companyPhone);
        String trimmedCompanyPhone = companyPhone.trim();
        if (!Phone.isValidPhone(trimmedCompanyPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new CompanyPhone(trimmedCompanyPhone);
    }

    /**
     * Parses a {@code String companyDescription} into an {@code CompanyDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyDescription} is invalid.
     */
    public static CompanyDescription parseCompanyDescription(String companyDescription) throws ParseException {
        requireNonNull(companyDescription);
        String trimmedDescription = companyDescription.trim();
        if (!CompanyDescription.isValidCompanyDescription(trimmedDescription)) {
            throw new ParseException(CompanyDescription.MESSAGE_CONSTRAINTS);
        }
        return new CompanyDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String companyEmail} into an {@code CompanyEmail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyEmail} is invalid.
     */
    public static CompanyEmail parseCompanyEmail(String companyEmail) throws ParseException {
        requireNonNull(companyEmail);
        String trimmedEmail = companyEmail.trim();
        if (!CompanyEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(CompanyEmail.MESSAGE_CONSTRAINTS);
        }
        return new CompanyEmail(trimmedEmail);
    }

    /**
     * Parses a {@code addInternshipString} into an {@code Internship}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Internship parseAddInternship(String addInternshipString) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(addInternshipString,
                        PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_SCHEDULED);

        if (!argMultimap.arePrefixesPresent(PREFIX_NAME, PREFIX_DESCRIPTION) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_DESCRIPTION);
        InternshipName name = ParserUtil.parseInternshipName(argMultimap.getValue(PREFIX_NAME).get());
        InternshipDescription description = ParserUtil.parseInternshipDescription(
                argMultimap.getValue(PREFIX_DESCRIPTION).get());

        if (argMultimap.getValue(PREFIX_SCHEDULED).isPresent()) {
            InternshipInterviewDateTime interviewDateTime = ParserUtil.parseInternshipInterviewDateTime(
                    argMultimap.getValue(PREFIX_SCHEDULED).get());
            return new Internship(name, description, interviewDateTime);
        }

        return new Internship(name, description);
    }

    /**
     * Parses a {@code editInternshipString} into an {@code EditInternshipDescriptor}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static EditInternshipDescriptor parseEditInternship(String editInternshipString) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(editInternshipString.substring(2),
                        PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_SCHEDULED);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_SCHEDULED);

        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editInternshipDescriptor.setInternshipName(
                    ParserUtil.parseInternshipName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editInternshipDescriptor.setInternshipDescription(
                    ParserUtil.parseInternshipDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_SCHEDULED).isPresent()) {
            editInternshipDescriptor.setInternshipDateTime(
                    ParserUtil.parseInternshipInterviewDateTime(argMultimap.getValue(PREFIX_SCHEDULED).get()));
        }

        if (!editInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCompanyCommand.MESSAGE_NOT_EDITED);
        }

        return editInternshipDescriptor;
    }

    /**
     * Parses a {@code String internshipName} into a {@code InternshipName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code internshipName} is invalid.
     */
    public static InternshipName parseInternshipName(String internshipName) throws ParseException {
        requireNonNull(internshipName);
        String trimmedInternshipName = internshipName.trim();
        if (!InternshipName.isValidName(trimmedInternshipName)) {
            throw new ParseException(InternshipName.MESSAGE_CONSTRAINTS);
        }
        return new InternshipName(trimmedInternshipName);
    }

    /**
     * Parses a {@code String internshipDescription} into an {@code InternshipDescription}.
     * Leading and tailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code internshipDescription} is invalid.
     */
    public static InternshipDescription parseInternshipDescription(String internshipDescription) throws ParseException {
        requireNonNull(internshipDescription);
        String trimmedDescription = internshipDescription.trim();
        if (!InternshipDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(CompanyDescription.MESSAGE_CONSTRAINTS);
        }
        return new InternshipDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String interviewDateTime} into a {@code InternshipInterviewDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewDateTime} is invalid.
     */
    public static InternshipInterviewDateTime parseInternshipInterviewDateTime(
            String interviewDateTime) throws ParseException {
        requireNonNull(interviewDateTime);
        String trimmedInterviewDateTime = interviewDateTime.trim();
        if (!InternshipInterviewDateTime.isValidInterviewDateTime(trimmedInterviewDateTime)) {
            throw new ParseException(InternshipInterviewDateTime.MESSAGE_CONSTRAINTS);
        }

        LocalDateTime dateTime = DateTimeParserUtil.parseStringToDateTime(trimmedInterviewDateTime);
        return new InternshipInterviewDateTime(dateTime);
    }

    /**
     * Parses a {@code String sortInterval} into a {@code Pair<Optional<InternshipInterviewDateTime>,
     * Optional<InternshipInterviewDateTime>>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortInterval} is invalid.
     */
    public static Pair<Optional<InternshipInterviewDateTime>, Optional<InternshipInterviewDateTime>> parseSortInterval(
        String sortInterval) throws ParseException {
        requireNonNull(sortInterval);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(sortInterval, PREFIX_RANGE_START, PREFIX_RANGE_END);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_RANGE_START, PREFIX_RANGE_END);

        // Such cases where there is no start nor end should not happen
        // as the case is handled in the command parser.
        try {
            if (!(argMultimap.arePrefixesPresent(PREFIX_RANGE_START)
                ||
                argMultimap.arePrefixesPresent(PREFIX_RANGE_END))) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
            // Handle when there is only a start date
            if (argMultimap.arePrefixesPresent(PREFIX_RANGE_START)
                &&
                !argMultimap.arePrefixesPresent(PREFIX_RANGE_END)) {
                LocalDateTime startDateTime = DateTimeParserUtil.parseStringToDateTimeThrow(
                    argMultimap.getValue(PREFIX_RANGE_START).get());
                return new Pair<>(Optional.of(new InternshipInterviewDateTime(startDateTime)), Optional.empty());
            }
            // Handle when there is only an end date
            if (!argMultimap.arePrefixesPresent(PREFIX_RANGE_START)
                &&
                argMultimap.arePrefixesPresent(PREFIX_RANGE_END)) {
                LocalDateTime endDateTime = DateTimeParserUtil.parseStringToDateTimeThrow(
                    argMultimap.getValue(PREFIX_RANGE_END).get());
                return new Pair<>(Optional.empty(), Optional.of(new InternshipInterviewDateTime(endDateTime)));
            }
            // Handle when there is both a start and end date
            if (argMultimap.arePrefixesPresent(PREFIX_RANGE_START)
                &&
                argMultimap.arePrefixesPresent(PREFIX_RANGE_END)) {
                LocalDateTime startDateTime = DateTimeParserUtil.parseStringToDateTimeThrow(
                    argMultimap.getValue(PREFIX_RANGE_START).get());
                LocalDateTime endDateTime = DateTimeParserUtil.parseStringToDateTimeThrow(
                    argMultimap.getValue(PREFIX_RANGE_END).get());
                if (startDateTime.isAfter(endDateTime)) {
                    //throw new ParseException(MESSAGE_INVALID_END_TIME);
                    throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
                }
                return new Pair<>(Optional.of(new InternshipInterviewDateTime(startDateTime)),
                    Optional.of(new InternshipInterviewDateTime(endDateTime)));
            }
        } catch (DateTimeParseException e) {
            //throw new ParseException(MESSAGE_INVALID_DATETIME_FORMAT);
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        // If we reach here, something went wrong
        assert false;
        return null;
    }
}
