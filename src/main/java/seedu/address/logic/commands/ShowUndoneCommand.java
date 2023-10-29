package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.Model;
import seedu.address.model.interview.InterviewNotDonePredicate;

/**
 * Lists all undone interviews in the address book to the user.
 */
public class ShowUndoneCommand extends Command {
    public static final String COMMAND_WORD = "show-undone";

    public static final String MESSAGE_SUCCESS = "Showing undone interviews.\nUse list-i to show all interviews.";
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    @Override
    public CommandResult execute(Model model) {
        logger.fine("Executing listing undone interviews");
        requireNonNull(model);
        model.updateFilteredInterviewList(new InterviewNotDonePredicate());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
