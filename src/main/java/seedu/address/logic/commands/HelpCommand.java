package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.ui.HelpWindow;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public static final String SHOWING_ALL_MESSAGE_HEADER = "Here are the list of all commands available:";

    public static final String SHOWING_ALL_MESSAGE_FOOTER = String.format(
        "Please type in \'help COMMAND_WORD\' to know more, or visit our website at %s", HelpWindow.USERGUIDE_URL);

    public static final String PROPOSE_ALTERNATIVE_MESSAGE_FORMAT =
        "Do you mean: %s? \n Type in \'help\' to see a list of the commands available";

    private boolean willShowAll;
    private boolean isRecognizable;
    private String toShow;

    private HelpCommand(boolean willShowAll, boolean isRecognizable, String toShow) {
        this.willShowAll = willShowAll;
        this.isRecognizable = isRecognizable;
        this.toShow = toShow;
    }

    public HelpCommand(boolean willShowAll) {
        this(willShowAll, false, "");
    }

    public HelpCommand(boolean isRecognizable, String toShow) {
        this(false, isRecognizable, toShow);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (willShowAll) {
            StringBuilder out = new StringBuilder();
            out.append(SHOWING_ALL_MESSAGE_HEADER);

            for (String s: CliSyntax.COMMAND_LIST) {
                out.append(String.format("\n - %s", s));
            }

            out.append("\n" + SHOWING_ALL_MESSAGE_FOOTER);
            return new CommandResult(out.toString());
        }

        if (!isRecognizable) {
            StringBuilder out = new StringBuilder(Messages.MESSAGE_UNKNOWN_COMMAND);
            out.append(toShow.isEmpty() ? "" : "\n" + String.format(PROPOSE_ALTERNATIVE_MESSAGE_FORMAT, toShow));
            return new CommandResult(out.toString());
        }

        switch (toShow) {
        case AddCommand.COMMAND_WORD:
            return new CommandResult(String.format("%s \n\n %s \n\n %s",
                AddEventCommand.MESSAGE_USAGE,
                AddNoteCommand.MESSAGE_USAGE,
                AddPersonCommand.MESSAGE_USAGE));

        case EditCommand.COMMAND_WORD:
            return new CommandResult(EditCommand.MESSAGE_USAGE);

        case DeleteCommand.COMMAND_WORD:
            return new CommandResult(String.format("%s \n\n %s \n\n %s",
                DeleteEventCommand.MESSAGE_USAGE,
                DeleteNoteCommand.MESSAGE_USAGE,
                DeletePersonCommand.MESSAGE_USAGE));

        case ClearCommand.COMMAND_WORD:
            return new CommandResult(ClearCommand.MESSAGE_USAGE);

        case FindCommand.COMMAND_WORD:
            return new CommandResult(FindCommand.MESSAGE_USAGE);

        case ListCommand.COMMAND_WORD:
            return new CommandResult(String.format("%s \n\n %s \n\n %s",
                ListEventCommand.MESSAGE_USAGE,
                ListNoteCommand.MESSAGE_USAGE,
                ListPersonCommand.MESSAGE_USAGE));

        default:
            throw new CommandException("Unexpected input");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherHelpCommand = (HelpCommand) other;

        boolean showAllIsEqual = willShowAll == otherHelpCommand.willShowAll;
        boolean isRecognizableIsEqual = isRecognizable == otherHelpCommand.isRecognizable;
        boolean toShowIsEqual = toShow.equals(otherHelpCommand.toShow);
        return showAllIsEqual && isRecognizableIsEqual && toShowIsEqual;
    }
}
