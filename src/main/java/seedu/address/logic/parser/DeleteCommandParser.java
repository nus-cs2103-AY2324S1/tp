package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.LogicManager;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.deletecommands.DeleteCommand;
import seedu.address.logic.commands.deletecommands.DeleteCompanyCommand;
import seedu.address.logic.commands.deletecommands.DeleteInternshipCommand;
import seedu.address.logic.commands.deletecommands.DeletePersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    /**
     * The argument for deleting company.
     */
    public static final String DELETE_COMPANY_ARG_WORD = "c";

    /**
     * The argument for deleting persons.
     */
    public static final String DELETE_PERSON_ARG_WORD = "p";
    /**
     * The argument for deleting internship.
     */
    public static final String DELETE_INTERNSHIP_ARG_WORD = "i";

    /**
     * Regex used to confirm if the arguments are either c or p for delete command.
     */
    private static final Pattern ARGUMENT_REGEX_PATTERN =
            Pattern.compile("^(" + DELETE_COMPANY_ARG_WORD + "|"
                    + DELETE_PERSON_ARG_WORD + ")$");

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] commandParts = trimmedArgs.split("\\s+");

        if (isDeleteInternshipCommand(commandParts)) {
            return createDeleteInternshipCommand(commandParts);
        } else if (isDeletePersonOrCompanyCommand(commandParts)) {
            return createDeletePersonOrCompanyCommand(commandParts);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Checks if the given command parts represent a DeleteInternshipCommand.
     *
     * @param commandParts The parts of the command to check.
     * @return True if it's a DeleteInternshipCommand, otherwise false.
     */
    private boolean isDeleteInternshipCommand(String[] commandParts) {
        return commandParts.length == 3
                && commandParts[0].equals(DELETE_INTERNSHIP_ARG_WORD)
                && commandParts[1].startsWith("c/")
                && commandParts[2].startsWith("i/");
    }

    /**
     * Creates a DeleteInternshipCommand from the given command parts.
     *
     * @param commandParts The parts of the command to create from.
     * @return The DeleteInternshipCommand.
     * @throws ParseException If there's an issue with parsing the command.
     */
    private DeleteCommand createDeleteInternshipCommand(String[] commandParts) throws ParseException {
        Index targetCompanyIndex = null;
        try {
            targetCompanyIndex = ParserUtil.parseIndex(commandParts[1].substring(2));
        } catch (ParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }
        try {
            Index targetInternshipIndex = ParserUtil.parseIndex(commandParts[2].substring(2));
            return new DeleteInternshipCommand(targetCompanyIndex, targetInternshipIndex);
        } catch (ParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }
    }

    /**
     * Checks if the given command parts represent a DeletePerson or DeleteCompanyCommand.
     *
     * @param commandParts The parts of the command to check.
     * @return True if it's a DeletePerson or DeleteCompanyCommand, otherwise false.
     */
    private boolean isDeletePersonOrCompanyCommand(String[] commandParts) {
        return commandParts.length == 2 && ARGUMENT_REGEX_PATTERN.matcher(commandParts[0]).matches();
    }

    /**
     * Creates a DeletePersonCommand or DeleteCompanyCommand from the given command parts.
     *
     * @param commandParts The parts of the command to create from.
     * @return The DeletePersonCommand or DeleteCompanyCommand.
     * @throws ParseException If there's an issue with parsing the command.
     */
    private DeleteCommand createDeletePersonOrCompanyCommand(String[] commandParts) throws ParseException {
        if (commandParts[0].equals(DELETE_PERSON_ARG_WORD)) {
            try {
                Index index = ParserUtil.parseIndex(commandParts[1]);
                return new DeletePersonCommand(index);
            } catch (ParseException e) {
                throw new ParseException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        } else {
            try {
                Index index = ParserUtil.parseIndex(commandParts[1]);
                return new DeleteCompanyCommand(index);
            } catch (ParseException e) {
                throw new ParseException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
            }
        }
    }
}

