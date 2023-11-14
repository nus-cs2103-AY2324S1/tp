package seedu.address.logic.commands.musician;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.musician.Musician;

/**
 * Adds a musician to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a musician to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_INSTRUMENT + "INSTRUMENT]... "
            + "[" + PREFIX_GENRE + "GENRE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TAG + "available "
            + PREFIX_INSTRUMENT + "piano "
            + PREFIX_GENRE + "jazz";

    public static final String MESSAGE_SUCCESS = "New musician added: %1$s";
    public static final String MESSAGE_DUPLICATE_MUSICIAN = "This musician already exists in your contact list";
    public static final String MESSAGE_DUPLICATE_INFO = "Phone number or email already exists in your contact list!";

    private final Musician toAdd;

    /**
     * Creates an {@code AddCommand} to add the specified {@code Musician}.
     */
    public AddCommand(Musician musician) {
        requireNonNull(musician);
        toAdd = musician;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMusician(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MUSICIAN);
        }

        if (model.hasDuplicateInfo(null, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INFO);
        }

        model.addMusician(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
