package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.addcommands.AddCommand;
import seedu.address.logic.commands.addcommands.AddCompanyCommand;
import seedu.address.logic.commands.addcommands.AddInternshipCommand;
import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.Company;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * The argument for adding companies.
     */
    public static final String ADD_COMPANIES_ARG_WORD = "c";

    /**
     * The argument for adding internships.
     */
    public static final String ADD_INTERNSHIPS_ARG_WORD = "i";

    /**
     * The argument for adding persons.
     */
    public static final String ADD_PERSONS_ARG_WORD = "p";

    /**
     * Regex used to confirm if the arguments are either c, i or p for add command.
     */
    private static final Pattern ARGUMENT_REGEX_PATTERN =
            Pattern.compile("(" + ADD_COMPANIES_ARG_WORD + "|" + ADD_INTERNSHIPS_ARG_WORD + "|"
                    + ADD_PERSONS_ARG_WORD + ")\\s+.*");

    private final Logger logger = LogsCenter.getLogger(AddCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        requireNonNull(args);

        // Trim only the start of args
        String trimmedArgs = args.trim();

        // Used to check if argument is either c, i or p.
        Matcher matcher = ARGUMENT_REGEX_PATTERN.matcher(trimmedArgs);

        // Throw an error, if argument is invalid.
        if (!matcher.matches()) {
            logger.info("Add command did not specify \"p\"/\"c\"/\"i\", or was empty after \"p\"/\"c\"/\"i\".");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String type = trimmedArgs.substring(0, 1);
        if (type.equals(ADD_PERSONS_ARG_WORD)) {
            logger.info("Adding person...");
            Person person = ParserUtil.parseAddPerson(trimmedArgs.substring(1));
            return new AddPersonCommand(person);
        } else if (type.equals(ADD_COMPANIES_ARG_WORD)) {
            logger.info("Adding company...");
            Company company = ParserUtil.parseAddCompany(trimmedArgs.substring(1));
            return new AddCompanyCommand(company);
        } else {
            logger.info("Adding internship...");
            // Since the index of the internship may change, it cannot be included as an attribute in the Internship
            // class. Since a method can only return one Object, we made the decision to parse the index here
            // instead of within ParserUtil.parseInternship.
            Index index = null;

            try {
                // Create a pattern that matches the first positive integer in the string between 2 spaces
                Pattern pattern = Pattern.compile("\\s(\\d+)(?=\\s.)");

                // Create a matcher to find the first integer
                Matcher digitMatcher = pattern.matcher(trimmedArgs);

                // Check if the matcher finds a integer and extract it
                if (digitMatcher.find()) {
                    String firstDigit = digitMatcher.group();
                    index = ParserUtil.parseIndex(firstDigit);
                }
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE), pe);
            }

            if (index == null) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddInternshipCommand.MESSAGE_USAGE));
            }

            Internship internship = ParserUtil.parseAddInternship(trimmedArgs.substring(1));
            return new AddInternshipCommand(index, internship);
        }
    }
}
