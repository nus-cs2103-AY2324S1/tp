package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.ui.HelpWindow;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_MISSING_SECONDARY_COMMAND =
            "Please be more specific. Type 'help %1$s' for more details.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_START_TIME_AFTER_END_TIME = "Start time %s is after the end time %s!\n";
    public static final String MESSAGE_INVALID_INTEGER_ARGUMENT =
            "The provided argument is not a valid integer! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String ALL_HELP_MESSAGE_HEADER = "Here are the list of all commands available:";
    public static final String ALL_HELP_MESSAGE_FOOTER = String.format(
        "Please type in \'help COMMAND_WORD\' to know more, or visit our website at %s", HelpWindow.USERGUIDE_URL);
    public static final String HELP_MESSAGE_PROPOSE_ALTERNATIVE_FORMAT =
        "Do you mean: %s? \n Type in \'help\' to see a list of the commands available";

    public static final String DUPLICATED_EVENTS = "\"Operation would result in duplicate events\"";

    public static final String EVENT_OVERLAP = "Event %s overlaps with event %s";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    public static String getHelpMessageForAll() {
        StringBuilder out = new StringBuilder();
        out.append(ALL_HELP_MESSAGE_HEADER);

        for (String s: CliSyntax.COMMAND_LIST) {
            out.append(String.format("\n - %s", s));
        }

        out.append("\n" + ALL_HELP_MESSAGE_FOOTER);
        return out.toString();
    }

    public static String getHelpMessageForRecognizableCommand(String command) throws CommandException {
        switch (command) {
        case AddCommand.COMMAND_WORD:
            return String.format("%s \n\n %s \n\n %s \n\n %s",
                AddEventCommand.MESSAGE_USAGE,
                AddNoteCommand.MESSAGE_USAGE,
                AddPersonCommand.MESSAGE_USAGE,
                AddTagCommand.MESSAGE_USAGE);

        case DeleteCommand.COMMAND_WORD:
            return String.format("%s \n\n %s \n\n %s \n\n %s",
                DeleteEventCommand.MESSAGE_USAGE,
                DeleteNoteCommand.MESSAGE_USAGE,
                DeletePersonCommand.MESSAGE_USAGE,
                DeleteTagCommand.MESSAGE_USAGE);

        case ClearCommand.COMMAND_WORD:
            return ClearCommand.MESSAGE_USAGE;

        case FindCommand.COMMAND_WORD:
            return FindCommand.MESSAGE_USAGE;

        case ListCommand.COMMAND_WORD:
            return String.format("%s \n\n %s",
                ListPersonCommand.MESSAGE_USAGE,
                ListEventCommand.MESSAGE_USAGE);

        default:
            throw new CommandException("Unexpected input");
        }
    }

    public static String getHelpMessageForUnrecognizableCommand(String toShow) {
        StringBuilder out = new StringBuilder(Messages.MESSAGE_UNKNOWN_COMMAND);
        out.append(toShow.isEmpty() ? "" : "\n" + String.format(HELP_MESSAGE_PROPOSE_ALTERNATIVE_FORMAT, toShow));
        return out.toString();
    }
}
