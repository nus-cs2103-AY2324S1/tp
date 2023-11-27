package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all bookings";

    /**
     * Executes the list command to display all bookings to the user.
     *
     * @param model The current model.
     * @return A CommandResult indicating the result of the list operation and the success message.
     */
    @Override
    public CommandResult execute(Model model) {
        assert model != null;
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
