package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Selects and displays an Application identified using it's displayed index from the intern tracker.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects and displays the intern application identified by the index number used in the "
            + "displayed "
            + "application list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_INTERN_APPLICATION_SUCCESS = "Viewed intern application";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternApplication> lastShownList = model.getFilteredInternApplicationList();
        assert lastShownList != null;

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToView = lastShownList.get(targetIndex.getZeroBased());
        assert internApplicationToView != null;
        model.setCurrentInternApplication(internApplicationToView);
        return new CommandResult(MESSAGE_VIEW_INTERN_APPLICATION_SUCCESS,
                internApplicationToView);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return targetIndex.equals(otherViewCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
