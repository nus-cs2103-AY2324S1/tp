package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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
import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Specialist;

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
            "(?<commandWord>\\S+)\\s(?<personType>-(pa|sp))(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    private final Model model;

    /**
     * Constructs an AddressBookParser with a reference to the {@code Model}
     */
    public AddressBookParser(Model model) {
        requireNonNull(model);
        this.model = model;
    }

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
            final String commandWord = model.getShortcut(matcherPersonType.group("commandWord"));
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

            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(personType, arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand(personType);

            case EditCommand.COMMAND_WORD:
                if (model.getSelectedPerson() instanceof Patient) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_PATIENT));
                }
                if (model.getSelectedPerson() instanceof Specialist) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_SPECIALIST));
                }
                break;

            case DeleteCommand.COMMAND_WORD:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

            case UndoCommand.COMMAND_WORD:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));

            case RedoCommand.COMMAND_WORD:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE));

            case AddShortcutCommand.COMMAND_WORD:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));

            case DeleteShortcutCommand.COMMAND_WORD:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteShortcutCommand.MESSAGE_USAGE));

            case ViewCommand.COMMAND_WORD:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

            case ThemeCommand.COMMAND_WORD:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));

            default:
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        } else if (matcherBasic.matches()) {
            final String commandWord = model.getShortcut(matcherBasic.group("commandWord"));
            final String arguments = matcherBasic.group("arguments");

            // Note to developers: Change the log level in config.json to enable lower level
            // (i.e., FINE, FINER and lower) log messages such as the one below.
            // Lower level log messages are used sparingly to minimize noise in the code.
            logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

            switch (commandWord) {
            case EditCommand.COMMAND_WORD:
                if (model.getSelectedPerson() instanceof Patient) {
                    return new EditCommandParser().parse(PersonType.PATIENT, arguments);
                }
                if (model.getSelectedPerson() instanceof Specialist) {
                    return new EditCommandParser().parse(PersonType.SPECIALIST, arguments);
                }
                break;

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommandParser().parse(arguments);

            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case AddShortcutCommand.COMMAND_WORD:
                return new AddShortcutCommandParser().parse(arguments);

            case DeleteShortcutCommand.COMMAND_WORD:
                return new DeleteShortcutCommandParser().parse(arguments);

            case ViewCommand.COMMAND_WORD:
                return new ViewCommandParser().parse(arguments);

            case UndoCommand.COMMAND_WORD:
                return new UndoCommand();

            case RedoCommand.COMMAND_WORD:
                return new RedoCommand();

            case ThemeCommand.COMMAND_WORD:
                return new ThemeCommandParser().parse(arguments);

            case AddCommand.COMMAND_WORD:
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
