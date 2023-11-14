package seedu.address.logic.commands.viewcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.DisplayableCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;

/**
 * Views the contact with the specified index from the companies contact list.
 */
public class ViewCompanyCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Company with index %d displayed!";
    private final Index targetIndex;

    public ViewCompanyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Index getTargetIndex() {
        return targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (this.targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToView = lastShownList.get(this.targetIndex.getZeroBased());
        return new DisplayableCommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased()), companyToView);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ViewCompanyCommand)) {
            return false;
        }
        ViewCompanyCommand otherViewCompanyCommand = (ViewCompanyCommand) other;
        return this.targetIndex.equals(otherViewCompanyCommand.getTargetIndex());
    }
}
