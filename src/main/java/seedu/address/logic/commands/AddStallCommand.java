package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.stall.Stall;

/**
 * Adds a stall to FoodNotes.
 */
public class AddStallCommand extends Command {

    public static final String COMMAND_WORD = "add-stall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a stall to FoodNotes. "
            + "Parameters: "
            + PREFIX_NAME + "STALL_NAME "
            + PREFIX_LOCATION + "STALL_LOCATION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Japanese Stall "
            + PREFIX_LOCATION + "Deck";

    public static final String MESSAGE_SUCCESS = "New stall added: %1$s";
    public static final String MESSAGE_DUPLICATE_STALL = "This stall already exists in FoodNotes";

    private final Stall toAdd;

    /**
     * Creates an AddStallCommand to add the specified {@code Stall}
     */
    public AddStallCommand(Stall stall) {
        requireNonNull(stall);
        toAdd = stall;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStall(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STALL);
        }

        model.addStall(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStallCommand)) {
            return false;
        }

        AddStallCommand otherAddStallCommand = (AddStallCommand) other;
        return toAdd.equals(otherAddStallCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
