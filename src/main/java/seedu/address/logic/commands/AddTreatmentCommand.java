package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.treatment.Treatment;

/**
 * Adds a treatment to the address book.
 */
public class AddTreatmentCommand extends Command {

    public static final String COMMAND_WORD = "add-treatment";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Adds a treatment to the address book. "
            + "Parameters: "
            + PREFIX_TREATMENT + "NAME "
            + PREFIX_COST + "COST "
            + PREFIX_TIME + "TIME...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TREATMENT + "Braces "
            + PREFIX_COST + "1080 "
            + PREFIX_TIME + "10:30\n";

    public static final String MESSAGE_SUCCESS = "New Treatment added: %1$s";
    public static final String MESSAGE_DUPLICATE_TREATMENT = "This Treatment already exists in ToothTracker";

    private final Treatment toAdd;

    /**
     * Creates an AddTreatmentCommand to add the specified {@code Treatment}
     */
    public AddTreatmentCommand(Treatment treatment) {
        requireNonNull(treatment);
        toAdd = treatment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTreatment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TREATMENT);
        }

        model.addTreatment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTreatmentCommand)) {
            return false;
        }

        AddTreatmentCommand otherAddCommand = (AddTreatmentCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("toAdd", toAdd)
            .toString();
    }
}
