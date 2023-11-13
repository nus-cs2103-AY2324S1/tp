package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons and events in the address book to the user.
 */
public class ListAllCommand extends Command {

    public static final String COMMAND_WORD = "list_all";

    public static final String MESSAGE_SUCCESS = "Listed all persons and events";


    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @return result message of the command execution.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
