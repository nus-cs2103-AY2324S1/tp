package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all applicants in the address book to the user.
 */
public class ViewApplicantsCommand extends Command {
    public static final String COMMAND_WORD = "viewApplicants";

    public static final String MESSAGE_SUCCESS = "Listed all applicants";

    @Override
    public CommandResult execute(Model model) {
        //TODO implement this
        requireNonNull(model);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
