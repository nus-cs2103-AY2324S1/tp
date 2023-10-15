package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.Model;

/**
 * Clears the applicant book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Staff-Snap has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setApplicantBook(new ApplicantBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
