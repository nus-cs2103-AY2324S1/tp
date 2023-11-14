package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;

/**
 * Adds a Property to the address book.
 */
public class AddPropertyCommand extends Command {

    public static final String COMMAND_WORD = "addprop";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to the PropertyMatch. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_PHONE + "PHONE_NUMBER "
            + PREFIX_PRICE + "PRICE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Kuantan Regency "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_PRICE + "100000000 "
            + PREFIX_TAG + "bright "
            + PREFIX_TAG + "sunny";

    public static final String MESSAGE_SUCCESS = "New property added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in the PropertyMatch";

    private final Property toAdd;

    /**
     * Creates an AddPropertyCommand to add the specified {@code Property}
     */
    public AddPropertyCommand(Property property) {
        requireNonNull(property);
        toAdd = property;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProperty(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        model.addProperty(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPropertyCommand)) {
            return false;
        }

        AddPropertyCommand otherAddPropertyCommand = (AddPropertyCommand) other;
        return toAdd.equals(otherAddPropertyCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
