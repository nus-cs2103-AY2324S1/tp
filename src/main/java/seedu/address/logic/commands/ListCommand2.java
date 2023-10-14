package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.Model2;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model2.PREDICATE_SHOW_ALL_CARDS;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand2 extends Command2 {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all cards";


    @Override
    public CommandResult execute(Model2 model) {
        requireNonNull(model);
        model.updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
