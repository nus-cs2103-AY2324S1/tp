package seedu.address.logic.commands.editcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_COMPANIES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.commandresults.CommandResult;
import seedu.address.logic.commands.commandresults.DisplayableCommandResult;
import seedu.address.logic.commands.editcommands.editdescriptors.EditInternshipDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;

/**
 * Edits the details of an existing internship in the address book.
 */
public class EditInternshipCommand extends EditCommand {

    public static final String MESSAGE_SUCCESS = "Edited Internship: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the address book.";

    private final Index companyIndex;
    private final Index internshipIndex;
    private final EditInternshipDescriptor editInternshipDescriptor;

    /**
     * @param companyIndex Index of the company in the filtered {@code Company} list containing the {@code Internship}
     *     to edit.
     * @param internshipIndex Index of the internship to edit as shown in the list of internships in the selected
     *     {@code Company}.
     * @param editInternshipDescriptor Details to edit the selected {@code Internship} with.
     */
    public EditInternshipCommand(Index companyIndex, Index internshipIndex,
                                 EditInternshipDescriptor editInternshipDescriptor) {
        requireAllNonNull(companyIndex, internshipIndex, editInternshipDescriptor);

        this.companyIndex = companyIndex;
        this.internshipIndex = internshipIndex;
        this.editInternshipDescriptor = new EditInternshipDescriptor(editInternshipDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (companyIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }
        Company companyToEdit = lastShownList.get(companyIndex.getZeroBased());

        if (internshipIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }
        Internship internshipToEdit = companyToEdit.getInternshipAtIndex(internshipIndex);
        Internship editedInternship = createEditedInternship(internshipToEdit, editInternshipDescriptor);

        if (!internshipToEdit.getInternshipName().equals(editedInternship.getInternshipName())
                && companyToEdit.hasInternship(editedInternship)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        companyToEdit.setInternship(internshipToEdit, editedInternship);
        model.setCompany(companyToEdit, companyToEdit);

        companyToEdit.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        // This helps to "reset" the company list UI, otherwise the company card will not update.
        model.updateFilteredCompanyList(PREDICATE_SHOW_NO_COMPANIES);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);

        return new DisplayableCommandResult(String.format(MESSAGE_SUCCESS, Messages.formatInternship(editedInternship)),
                                            companyToEdit);
    }

    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToEdit}
     * edited with {@code editInternshipDescriptor}.
     */
    private static Internship createEditedInternship(Internship internshipToEdit,
                                         EditInternshipDescriptor editInternshipDescriptor) throws CommandException {
        Internship updatedInternship = null;
        InternshipName updatedInternshipName =
                editInternshipDescriptor.getInternshipName().orElse(internshipToEdit.getInternshipName());
        InternshipDescription updatedInternshipDescription =
                editInternshipDescriptor.getInternshipDescription().orElse(internshipToEdit.getInternshipDescription());
        Optional<InternshipInterviewDateTime> updatedInternshipDateTime;
        if (editInternshipDescriptor.getInternshipDateTime().isEmpty()) {
            if (internshipToEdit.getInternshipDateTime().isEmpty()) {
                updatedInternship = new Internship(updatedInternshipName, updatedInternshipDescription);
            } else {
                updatedInternshipDateTime = internshipToEdit.getInternshipDateTime();
                updatedInternship = new Internship(updatedInternshipName, updatedInternshipDescription,
                        updatedInternshipDateTime.get());
            }
        } else {
            updatedInternshipDateTime = editInternshipDescriptor.getInternshipDateTime();
            updatedInternship = new Internship(updatedInternshipName, updatedInternshipDescription,
                    updatedInternshipDateTime.get());
        }

        return updatedInternship;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipCommand)) {
            return false;
        }

        EditInternshipCommand otherEditInternshipCommand = (EditInternshipCommand) other;
        return companyIndex.equals(otherEditInternshipCommand.companyIndex)
                && internshipIndex.equals(otherEditInternshipCommand.internshipIndex)
                && editInternshipDescriptor.equals(otherEditInternshipCommand.editInternshipDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companyIndex", companyIndex)
                .add("internshipIndex", internshipIndex)
                .add("editInternshipDescriptor", editInternshipDescriptor)
                .toString();
    }
}
