package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.finance.Finance;

/**
 * Deletes a Finance identified using its displayed index.
 */
public class DeleteFinanceCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the finance entry identified by the index number used in the displayed finance list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_FINANCE_SUCCESS = "Deleted finance entry: %1$s";

    private final Index targetIndex;

    public DeleteFinanceCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Finance> financeList = model.getFilteredFinanceList();

        if (targetIndex.getZeroBased() >= financeList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
        }

        Finance financeToDelete = financeList.get(targetIndex.getZeroBased());
        model.deleteFinance(financeToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_FINANCE_SUCCESS,
                Messages.formatFinance(financeToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteFinanceCommand)) {
            return false;
        }

        DeleteFinanceCommand otherDeleteFinanceCommand = (DeleteFinanceCommand) other;
        return targetIndex.equals(otherDeleteFinanceCommand.targetIndex);
    }

}
