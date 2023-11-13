package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Applicant;

/**
 * Copies an applicant from the address book to the clipboard.
 */
public class CopyApplicantCommand extends Command {

    public static final String COMMAND_WORD = "copyapplicant";
    public static final String COMMAND_ALIAS = "cpa";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Copies the name and phone number of the applicant at the specified index to clipboard.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_COPY_APPLICANT_SUCCESS = "Copied details of applicant to clipboard:\n%1$s";
    private final Index applicantIndex;

    /**
     * Creates a CopyApplicantCommand to copy the {@code Applicant} at the specified {@code Index}.
     *
     * @param applicantIndex The index of the applicant to copy.
     */
    public CopyApplicantCommand(Index applicantIndex) {
        this.applicantIndex = applicantIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (applicantIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Applicant applicantToCopy = lastShownList.get(applicantIndex.getZeroBased());
        String applicantString = applicantToCopy.detailsToCopy();

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(applicantString), null);
        return new CommandResult(String.format(MESSAGE_COPY_APPLICANT_SUCCESS, applicantString));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CopyApplicantCommand)) {
            return false;
        }

        CopyApplicantCommand otherCopyCommand = (CopyApplicantCommand) other;
        return applicantIndex.equals(otherCopyCommand.applicantIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("applicantIndex", applicantIndex)
                .toString();
    }
}
