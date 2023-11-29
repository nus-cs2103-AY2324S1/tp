package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DENTISTS;

import seedu.address.model.Model;

/**
 * Lists all dentists in the address book to the user.
 */
public class ListDentistCommand extends Command {

    public static final String COMMAND_WORD = "list-dentist";

    public static final String MESSAGE_SUCCESS = "Listed all dentists!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDentistList(PREDICATE_SHOW_ALL_DENTISTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
