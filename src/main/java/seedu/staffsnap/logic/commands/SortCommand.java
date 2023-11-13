package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_DESCRIPTOR;

import java.util.Objects;

import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Descriptor;


/**
 * Sorts all Applicants in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the applicant list based on the descriptor "
            + "provided.\n"
            + "Parameters: "
            + PREFIX_DESCRIPTOR + "DESCRIPTOR\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DESCRIPTOR + "name";
    public static final String MESSAGE_SUCCESS = "Sorted all Applicants";

    private final Descriptor descriptor;
    private final Boolean isDescendingOrder;

    /**
     * Creates a SortCommand to add the specified {@code descriptor}
     */
    public SortCommand(Descriptor descriptor, Boolean isDescendingOrder) {
        requireNonNull(descriptor);
        this.descriptor = descriptor;

        if (isDescendingOrder == null) {
            this.isDescendingOrder = false;
        } else {
            this.isDescendingOrder = isDescendingOrder;
        }
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedApplicantList(descriptor, isDescendingOrder);
        model.refreshApplicantList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortCommand that = (SortCommand) o;
        return descriptor == that.descriptor && Objects.equals(isDescendingOrder, that.isDescendingOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptor, isDescendingOrder);
    }
}
