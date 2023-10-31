package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.Model;

/**
 * Clears the applicant book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "yes";
    public static final String MESSAGE_FAILURE = "Clear is cancelled!";
    public static final String MESSAGE_SUCCESS = "Staff-Snap has been successfully cleared!";

    public final Boolean isSuccess;


    /**
     * Constructor for the command
     * @param args whether to clear
     */
    public ClearCommand(String args) {
        System.out.println(args);
        if (args.equals(COMMAND_WORD)) {
            this.isSuccess = true;
        } else {
            this.isSuccess = false;
        }
        System.out.println(isSuccess);
    }
    @Override
    public CommandResult execute(Model model) {
        if (this.isSuccess) {
            requireNonNull(model);
            assert(model != null);
            model.setApplicantBook(new ApplicantBook());
            return new CommandResult(MESSAGE_SUCCESS);
        }
        return new CommandResult(MESSAGE_FAILURE);
    }

}
