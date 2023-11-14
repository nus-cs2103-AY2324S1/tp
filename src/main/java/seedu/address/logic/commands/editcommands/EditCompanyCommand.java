package seedu.address.logic.commands.editcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.logic.commands.editcommands.editdescriptors.EditCompanyDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyDescription;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing company in the address book.
 */
public class EditCompanyCommand extends EditCommand {

    public static final String MESSAGE_SUCCESS = "Edited Company: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_COMPANY = "This company already exists in the address book.";

    private final Index index;
    private final EditCompanyDescriptor editCompanyDescriptor;

    /**
     * @param index of the company in the filtered company list to edit
     * @param editCompanyDescriptor details to edit the company with
     */
    public EditCompanyCommand(Index index, EditCompanyDescriptor editCompanyDescriptor) {
        requireNonNull(index);
        requireNonNull(editCompanyDescriptor);

        this.index = index;
        this.editCompanyDescriptor = new EditCompanyDescriptor(editCompanyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToEdit = lastShownList.get(index.getZeroBased());
        Company editedCompany = createEditedCompany(companyToEdit, editCompanyDescriptor);

        if (!companyToEdit.isSameCompany(editedCompany) && model.hasCompany(editedCompany)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMPANY);
        }

        model.setCompany(companyToEdit, editedCompany);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        return new RegularCommandResult(String.format(MESSAGE_SUCCESS, Messages.formatCompany(editedCompany)));
    }

    /**
     * Creates and returns a {@code Company} with the details of {@code companyToEdit}
     * edited with {@code editCompanyDescriptor}.
     */
    private static Company createEditedCompany(Company companyToEdit, EditCompanyDescriptor editCompanyDescriptor) {
        assert companyToEdit != null;

        CompanyName updatedCompanyName =
                editCompanyDescriptor.getCompanyName().orElse(companyToEdit.getCompanyName());
        CompanyPhone updatedCompanyPhone =
                editCompanyDescriptor.getCompanyPhone().orElse(companyToEdit.getCompanyPhone());
        CompanyEmail updatedCompanyEmail =
                editCompanyDescriptor.getCompanyEmail().orElse(companyToEdit.getCompanyEmail());
        CompanyDescription updatedCompanyDescription =
                editCompanyDescriptor.getDescription().orElse(companyToEdit.getCompanyDescription());
        Set<Tag> updatedTags =
                editCompanyDescriptor.getTags().orElse(companyToEdit.getTags());
        List<Internship> updatedInternships = companyToEdit.getInternshipList();

        return new Company(updatedCompanyName, updatedCompanyPhone,
                updatedCompanyEmail, updatedCompanyDescription, updatedTags, updatedInternships);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCompanyCommand)) {
            return false;
        }

        EditCompanyCommand otherEditCompanyCommand = (EditCompanyCommand) other;
        return index.equals(otherEditCompanyCommand.index)
                && editCompanyDescriptor.equals(otherEditCompanyCommand.editCompanyDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editCompanyDescriptor", editCompanyDescriptor)
                .toString();
    }
}

