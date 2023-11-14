package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Deletes all persons in the contacts list.
 */
public class DeleteAllPersonCommand extends Command {

    public static final String COMMAND_WORD = "deleteAllPerson";
    public static final String SHORTENED_COMMAND_WORD = "dap";

    public static final String MESSAGE_DELETE_ALL_PERSON_SUCCESS = "Deleted all persons";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.deleteAllPerson();
        return new CommandResult(MESSAGE_DELETE_ALL_PERSON_SUCCESS);
    }
}
