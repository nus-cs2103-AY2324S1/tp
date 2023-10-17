package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_TYPE;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PATIENT_TAG;
import static seedu.address.logic.parser.CliSyntax.SPECIALIST_TAG;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonType;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args. Basic commands do not require a person type tag.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\S+)(?<arguments>.*)");
    /**
     * Used for initial separation of command word, person type and args. Complex commands require a person type tag.
     */
    private static final Pattern COMPLEX_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\S+)\\s(?<personType>-\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcherBasic = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        final Matcher matcherPersonType = COMPLEX_COMMAND_FORMAT.matcher(userInput.trim());

        if (matcherPersonType.matches()) {
            final String commandWord = matcherPersonType.group("commandWord");
            final String personTypeWord = matcherPersonType.group("personType");
            final String arguments = matcherPersonType.group("arguments");

            // Note to developers: Change the log level in config.json to enable lower level
            // (i.e., FINE, FINER and lower) log messages such as the one below.
            // Lower level log messages are used sparingly to minimize noise in the code.
            logger.fine(
                    "Command word: " + commandWord + "; Person type: " + personTypeWord + "; Arguments: " + arguments);

            PersonType personType;
            if (personTypeWord.equals(PATIENT_TAG)) {
                personType = PersonType.PATIENT;
            } else if (personTypeWord.equals(SPECIALIST_TAG)) {
                personType = PersonType.SPECIALIST;
            } else {
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException(MESSAGE_INVALID_PERSON_TYPE);
            }

            switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return new AddCommandParser().parse(personType, arguments);

            case EditCommand.COMMAND_WORD:
                return new EditCommandParser().parse(personType, arguments);

            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(personType, arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand(personType);

            default:
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

            }
        } else if (matcherBasic.matches()) {
            final String commandWord = matcherBasic.group("commandWord");
            final String arguments = matcherBasic.group("arguments");

            // Note to developers: Change the log level in config.json to enable lower level
            // (i.e., FINE, FINER and lower) log messages such as the one below.
            // Lower level log messages are used sparingly to minimize noise in the code.
            logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

            switch (commandWord) {

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommandParser().parse(arguments);

            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case AddCommand.COMMAND_WORD:
            case EditCommand.COMMAND_WORD:
            case FindCommand.COMMAND_WORD:
            case ListCommand.COMMAND_WORD:
                throw new ParseException(MESSAGE_INVALID_PERSON_TYPE);

            default:
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

}
