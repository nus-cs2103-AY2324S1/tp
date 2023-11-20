package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEY_MILESTONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Lead;

/**
 * Adds a lead to the address book.
 */
public class AddLeadCommand extends Command {

    public static final String COMMAND_WORD = "addlead";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lead to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_KEY_MILESTONE + "KEY_MILESTONE "
            + "[" + PREFIX_MEETING_TIME + "MEETING_TIME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_KEY_MILESTONE + "01/12/2023 "
            + PREFIX_MEETING_TIME + "10/10/2023 14:30 "
            + PREFIX_TAG + "classmate";

    public static final String MESSAGE_SUCCESS = "New lead added: %1$s";

    public static final String MESSAGE_DUPLICATE_LEAD = "This lead already exists in the address book";

    private final Lead toAdd;

    /**
     * Creates an AddLeadCommand to add the specified {@code Lead}
     */
    public AddLeadCommand(Lead lead) {
        requireNonNull(lead);
        this.toAdd = lead;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LEAD);
        }

        model.addLead(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLeadCommand)) {
            return false;
        }

        AddLeadCommand otherAddLeadCommand = (AddLeadCommand) other;
        return toAdd.equals(otherAddLeadCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
