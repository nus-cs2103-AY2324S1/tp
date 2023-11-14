package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import seedu.address.model.Model;

/**
 * Lists all customers in the address book to the user.
 */
public class ListPropertyCommand extends Command {

    public static final String COMMAND_WORD = "listprop";

    public static final String MESSAGE_SUCCESS = "Listed all properties";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
