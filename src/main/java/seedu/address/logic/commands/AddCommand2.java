package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages2;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model2;
import seedu.address.model.person.Card;




/**
 * Adds a person to the address book.
 */
public class AddCommand2 extends Command2 {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a card to the deck. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER ";

    public static final String MESSAGE_SUCCESS = "New Card added: %1$s";
    public static final String MESSAGE_DUPLICATE_CARD = "This Card already exists in the Deck";

    private final Card toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand2(Card card) {
        requireNonNull(card);
        toAdd = card;
    }

    @Override
    public CommandResult execute(Model2 model) throws CommandException {
        requireNonNull(model);

        if (model.hasCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }

        model.addCard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages2.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand2)) {
            return false;
        }

        AddCommand2 otherAddCommand = (AddCommand2) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
