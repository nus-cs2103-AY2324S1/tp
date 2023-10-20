package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.band.Band;

/**
 * Adds a band to the address book.
 */
public class AddBandCommand extends Command {
    public static final String COMMAND_WORD = "addb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a band to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "My garage band";

    public static final String MESSAGE_SUCCESS = "New band added: %1$s";
    public static final String MESSAGE_DUPLICATE_BAND = "This band already exists in the address book";

    private final Band toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddBandCommand(Band band) {
        requireNonNull(band);
        toAdd = band;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBand(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BAND);
        }

        model.addBand(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddBandCommand)) {
            return false;
        }

        AddBandCommand otherAddCommand = (AddBandCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
