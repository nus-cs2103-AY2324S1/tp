package networkbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static networkbook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import networkbook.model.Model;

/**
 * Lists all persons in the network book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Here is your complete list of contacts:";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
