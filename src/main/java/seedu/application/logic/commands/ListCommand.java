package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.model.Model.PREDICATE_SHOW_ALL_JOBS;

import seedu.application.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all %d jobs!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredJobList().size()),
            false, false, true, -1);
    }
}
