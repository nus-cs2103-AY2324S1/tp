package connectify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import connectify.commons.core.index.Index;
import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.company.Company;

/**
 * Deletes a company identified using it's displayed index from the address book.
 */
public class DeleteCompanyCommand extends Command {

    public static final String COMMAND_WORD = "deleteCompany";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the company identified by the index number used in the displayed company list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_COMPANY_SUCCESS = "Deleted Company: %1$s";

    private final Index targetIndex;

    public DeleteCompanyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCompany(companyToDelete);
        for (int i = 0; i < companyToDelete.getPersonList().size(); i++) {
            model.deletePerson(companyToDelete.getPersonList().get(i));
        }
        return new CommandResult(String.format(MESSAGE_DELETE_COMPANY_SUCCESS, Messages.format(companyToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCompanyCommand)) {
            return false;
        }

        DeleteCompanyCommand otherDeleteCommand = (DeleteCompanyCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
