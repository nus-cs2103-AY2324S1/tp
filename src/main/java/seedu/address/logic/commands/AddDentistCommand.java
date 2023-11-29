package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALIZATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.dentist.Dentist;

/**
 * Adds a dentist to ToothTracker address book.
 */
public class AddDentistCommand extends Command {
    public static final String COMMAND_WORD = "add-dentist";
    public static final String MESSAGE_DUPLICATE_DENTIST = "This dentist already exists in ToothTracker address book!";
    public static final String MESSAGE_SUCCESS = "New Dentist added: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a dentist to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + PREFIX_SPECIALIZATION + "SPECIALIZATION "
            + PREFIX_YOE + "YEARS OF EXPERIENCE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "My Dental Clinics Serangoon Road S123456 "
            + PREFIX_SPECIALIZATION + "Orthodontics "
            + PREFIX_YOE + "5 "
            + PREFIX_TAG + "Braces";

    private final Dentist toAdd;

    /**
     * Creates an AddDentistCommand to add the specified {@code Dentist}
     */
    public AddDentistCommand(Dentist dentist) {
        requireNonNull(dentist);
        toAdd = dentist;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDentist(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DENTIST);
        }

        model.addDentist(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                toAdd);
    }

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDentistCommand // instanceof handles nulls
                && toAdd.equals(((AddDentistCommand) other).toAdd));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
