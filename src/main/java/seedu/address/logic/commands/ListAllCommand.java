package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.model.Model;

/**
 * Lists all persons and tasks in CoordiMate to the user.
 */
public class ListAllCommand extends Command {

    public static final String COMMAND_WORD = "listAll";
    public static final String SHORTENED_COMMAND_WORD = "la";

    public static final String MESSAGE_SUCCESS = "Listed all persons and tasks!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
