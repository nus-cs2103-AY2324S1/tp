package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

//@@author peasantbird-reused
//Reused from Address Book (Level4) with minor modifications

/**
 * Redoes the last command that was undone.
 * Only affects commands that changes a buyer or seller in the buyer/seller lists.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "The next command was redone.";
    public static final String MESSAGE_FAILURE = "No commands to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoAddressBook();
        model.updateFilteredBuyerList(Model.PREDICATE_SHOW_BUYERS);
        model.updateFilteredSellerList(Model.PREDICATE_SHOW_SELLERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
//@@author
