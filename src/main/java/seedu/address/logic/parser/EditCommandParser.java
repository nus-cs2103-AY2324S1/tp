package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.editcommands.EditCommand;
import seedu.address.logic.commands.editcommands.EditCompanyCommand;
import seedu.address.logic.commands.editcommands.EditInternshipCommand;
import seedu.address.logic.commands.editcommands.EditPersonCommand;
import seedu.address.logic.commands.editcommands.editdescriptors.EditCompanyDescriptor;
import seedu.address.logic.commands.editcommands.editdescriptors.EditInternshipDescriptor;
import seedu.address.logic.commands.editcommands.editdescriptors.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * The argument for editing companies.
     */
    public static final String EDIT_COMPANIES_ARG_WORD = "c";

    /**
     * The argument for editing internships.
     */
    public static final String EDIT_INTERNSHIPS_ARG_WORD = "i";

    /**
     * The argument for editing persons.
     */
    public static final String EDIT_PERSONS_ARG_WORD = "p";

    /**
     * Regex used to confirm if the arguments are either c, i or p for edit command.
     */
    private static final Pattern ARGUMENT_REGEX_PATTERN =
            Pattern.compile("\\s*(" + EDIT_COMPANIES_ARG_WORD + "|" + EDIT_INTERNSHIPS_ARG_WORD + "|"
                    + EDIT_PERSONS_ARG_WORD + ")\\s+.*");

    private final Logger logger = LogsCenter.getLogger(EditCommandParser.class);


    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();

        // Used to check if argument is either c, i or p.
        Matcher matcher = ARGUMENT_REGEX_PATTERN.matcher(trimmedArgs);

        // Throw an error, if argument is invalid (i.e. not c, i or p).
        if (!matcher.matches()) {
            logger.info("Edit command did not specify \"p\", \"i\" or \"c\", was empty after \"p\","
                    + "\"i\" or \"c\"");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        String type = trimmedArgs.substring(0, 1);

        if (type.equals(EDIT_PERSONS_ARG_WORD)) {
            logger.info("Editing person...");
            Index index = parseIndexToEdit(trimmedArgs);
            if (index == null) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditPersonCommand.MESSAGE_USAGE));
            }

            EditPersonDescriptor editPersonDescriptor = ParserUtil.parseEditPerson(trimmedArgs.substring(1));
            return new EditPersonCommand(index, editPersonDescriptor);
        } else if (type.equals(EDIT_COMPANIES_ARG_WORD)) {
            logger.info("Editing company...");
            Index index = parseIndexToEdit(trimmedArgs);
            if (index == null) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditCompanyCommand.MESSAGE_USAGE));
            }

            EditCompanyDescriptor editCompanyDescriptor = ParserUtil.parseEditCompany(trimmedArgs
                    .substring(1));
            return new EditCompanyCommand(index, editCompanyDescriptor);
        } else {
            logger.info("Editing internship...");
            Index companyIndex = null;
            Index internshipIndex = null;

            try {
                // Create a pattern that matches the digits in the string
                Pattern pattern = Pattern.compile("\\s*i\\s*c/\\s*(\\d+)\\s*i/\\s*(\\d+)");

                // Create a matcher to find the digits
                Matcher digitMatcher = pattern.matcher(args);

                // Check if the matcher finds the digits and extract them
                if (digitMatcher.find()) {
                    String companyIndexString = digitMatcher.group(1);
                    companyIndex = ParserUtil.parseIndex(companyIndexString);
                    String internshipIndexString = digitMatcher.group(2);

                    if (internshipIndexString == null) {
                        throw new ParseException(
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInternshipCommand.MESSAGE_USAGE));
                    }
                    internshipIndex = ParserUtil.parseIndex(internshipIndexString);
                } else {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInternshipCommand.MESSAGE_USAGE));
                }
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInternshipCommand.MESSAGE_USAGE), pe);
            }

            EditInternshipDescriptor editInternshipDescriptor = ParserUtil.parseEditInternship(trimmedArgs
                    .substring(1));

            return new EditInternshipCommand(companyIndex, internshipIndex, editInternshipDescriptor);
        }
    }

    /**
     * Parses index for the company or person to edit from the user's input.
     * @param args User inputs for editing.
     * @return Index of company or person to edit.
     * @throws ParseException If user input does not contain an index.
     */
    public Index parseIndexToEdit(String args) throws ParseException {
        Index index = null;

        try {
            String[] splitArgs = args.split(" ", 3);
            index = ParserUtil.parseIndex(splitArgs[1]);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPersonCommand.MESSAGE_USAGE), pe);
        }

        return index;
    }
}
