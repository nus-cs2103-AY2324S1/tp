package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Applicant;

/**
 * Copies an applicant from the address book to the clipboard.
 */
public class CopyApplicantCommand extends Command {

    public static final String COMMAND_WORD = "copyApplicant";
    public static final String COMMAND_ALIAS = "cpa";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies the details of the applicant identified by the index number used"
            + " in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Copied details of applicant to clipboard:\n%1$s";
    private final Index applicantIndex;

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
        return new CommandResult(String.format(MESSAGE_SUCCESS, applicantString));
    }
}
