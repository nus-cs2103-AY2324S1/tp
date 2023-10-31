package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

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
            return new CommandResult(Messages.getHelpMessageForAll());
        }

        if (!isRecognizable) {
            return new CommandResult(Messages.getHelpMessageForUnrecognizableCommand(toShow));
        }

        return new CommandResult(Messages.getHelpMessageForRecognizableCommand(toShow));
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
