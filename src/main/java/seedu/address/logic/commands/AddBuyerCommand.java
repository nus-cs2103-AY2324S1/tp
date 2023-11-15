package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_POTENTIAL_DUPLICATE_SELLER;
import static seedu.address.logic.Messages.MESSAGE_SIMILAR_BUYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.displayable.buyer.Buyer;

/**
 * Adds a buyer to the address book.
 */
public class AddBuyerCommand extends Command {

    public static final String COMMAND_WORD = "buyer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a buyer to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_HOUSE_INFO + "INFO] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_PRIORITY + "PRIORITY] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_HOUSE_INFO + "Central Area 5 Room Condominium "
            + PREFIX_PRIORITY + "medium "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "Got it. I've added a buyer contact:\n%1$s";
    public static final String MESSAGE_DUPLICATE_BUYER = "This buyer already exists in the address book";
    private final Buyer toAdd;
    private final CommandWarnings commandWarnings;

    /**
     * Creates an AddBuyerCommand to add the specified {@code Buyer}
     */
    public AddBuyerCommand(Buyer buyer, CommandWarnings commandWarnings) {
        requireNonNull(buyer);
        toAdd = buyer;
        this.commandWarnings = commandWarnings;
    }
    /**
     * Creates an AddBuyerCommand to add the specified {@code Buyer}
     */
    public AddBuyerCommand(Buyer buyer) {
        this(buyer, new CommandWarnings());
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert toAdd != null;
        if (model.hasBuyer(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BUYER);
        }

        if (model.buyerHasSameSellerName(toAdd)) {
            commandWarnings.addWarning(MESSAGE_POTENTIAL_DUPLICATE_SELLER);
        }

        if (model.hasSimilarBuyer(toAdd)) {
            commandWarnings.addWarning(MESSAGE_SIMILAR_BUYER);
        }

        model.addBuyer(toAdd);
        model.commitAddressBook();

        if (commandWarnings.containsWarnings()) {
            return new CommandResult(commandWarnings.getWarningMessage());
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddBuyerCommand)) {
            return false;
        }

        AddBuyerCommand otherAddCommand = (AddBuyerCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
