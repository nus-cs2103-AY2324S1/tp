package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class RemoveClassCommand extends Command {

    public static final String COMMAND_WORD = "remove /c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class identified by the index number used in the displayed class list.\n"
            + "Parameters: CLASSNAME (Must be unique)\n"
            + "Example: " + COMMAND_WORD + " cs2103T";

    public static final String MESSAGE_REMOVE_CLASS_SUCCESS = "Deleted Class: %1$s";

    private final Class c;

    public RemoveClassCommand(Class c) {
        this.c = c;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Class classToRemove = this.c;
        model.removeClass(classToRemove);
        return new CommandResult(String.format(MESSAGE_REMOVE_CLASS_SUCCESS, Messages.formatClass(classToRemove)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveClassCommand)) {
            return false;
        }

        RemoveClassCommand otherRemoveClassCommand = (RemoveClassCommand) other;
        return this.c.equals(otherRemoveClassCommand.getClass());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("class", this.c)
                .toString();
    }
}
