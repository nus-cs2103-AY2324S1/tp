package connectify.logic.commands;

import static connectify.commons.util.CollectionUtil.requireAllNonNull;
import static connectify.logic.parser.CliSyntax.PREFIX_NOTE;
import static connectify.model.Model.PREDICATE_SHOW_ALL_COMPANIES;

import java.util.List;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.company.Company;
import connectify.model.company.CompanyNote;

/**
 * Changes the note of an existing person in the address book.
 */
public class CompanyNoteCommand extends Command {
    public static final String COMMAND_WORD = "noteCompany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the note of the company identified "
            + "by the index number used in the last company listing. "
            + "Existing note will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NOTE + "[NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "Looking for aspiring frontend developers.";

    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Company: %1$s";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Company: %1$s";

    private final Index index;

    private final CompanyNote note;

    /**
     * @param index of the company in the filtered company list to edit the note
     * @param note of the company to be updated to
     */
    public CompanyNoteCommand(Index index, CompanyNote note) {
        requireAllNonNull(index, note);
        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToEdit = lastShownList.get(index.getZeroBased());
        Company editedCompany = new Company(companyToEdit.getName(), companyToEdit.getIndustry(),
                companyToEdit.getLocation(),
                companyToEdit.getDescription(),
                companyToEdit.getWebsite(),
                companyToEdit.getEmail(),
                companyToEdit.getPhone(),
                companyToEdit.getAddress(),
                note,
                companyToEdit.getPersonList());

        model.setCompany(companyToEdit, editedCompany);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);

        return new CommandResult(generateSuccessMessage(editedCompany));
    }

    /**
     * Generates a command execution success message based on whether the note is added to or removed from
     * {@code companyToEdit}.
     */
    private String generateSuccessMessage(Company companyToEdit) {
        String message = !note.getContent().isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_SUCCESS;
        return String.format(message, Messages.format(companyToEdit));
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyNoteCommand)) {
            return false;
        }

        // state check
        CompanyNoteCommand e = (CompanyNoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
}
