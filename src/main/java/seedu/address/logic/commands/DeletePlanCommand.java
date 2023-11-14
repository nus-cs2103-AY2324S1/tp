package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Plan;

/**
 * Deletes a plan identified using it's displayed index from the address book.
 */
public class DeletePlanCommand extends Command {

    public static final String COMMAND_WORD = "delete-plan";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the plan identified by the index number used in the displayed plan list.\n"
            + "Parameters: INDEX (must be a positive non-zero integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PLAN_SUCCESS = "Deleted Plan: %1$s";

    private final Index targetIndex;

    public DeletePlanCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Plan> lastShownList = model.getFilteredPlanList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
        }

        Plan planToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePlan(planToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PLAN_SUCCESS, Messages.format(planToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePlanCommand)) {
            return false;
        }

        DeletePlanCommand otherDeleteCommand = (DeletePlanCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
