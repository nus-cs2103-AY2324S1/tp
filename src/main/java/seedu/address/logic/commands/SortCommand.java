package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user, sorted by
 * alphabetical order of names.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "List sorted in alphabetical order of names";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortByName();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
