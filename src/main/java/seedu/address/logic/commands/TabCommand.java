package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Switches to the tab identified using zero based index on UI.
 */
public class TabCommand extends Command {
    public static final String COMMAND_WORD = "tab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches tab based on index.\n"
            + "Parameters: TAB_INDEX (ranging from 1 for Students to 5 for Consultations)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Switched to tab %1$s";
    public static final int VALID_TAB_INDEX_LOWER_BOUND = 1;
    public static final int VALID_TAB_INDEX_UPPER_BOUND = 5;

    public static final CommandType COMMAND_TYPE = CommandType.TAB;
    private final Index targetIndex;

    public TabCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int index = targetIndex.getOneBased();

        if (index < VALID_TAB_INDEX_LOWER_BOUND || index > VALID_TAB_INDEX_UPPER_BOUND) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAB_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, index), COMMAND_TYPE, targetIndex);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TabCommand)) {
            return false;
        }

        return true;
    }
}
