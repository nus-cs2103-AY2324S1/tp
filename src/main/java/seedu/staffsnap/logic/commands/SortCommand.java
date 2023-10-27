package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_DESCRIPTOR;

import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.applicant.Descriptor;


/**
 * Sorts all Applicants in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorted all Applicants. "
            + "Parameters: "
            + PREFIX_DESCRIPTOR + "DESCRIPTOR ";
    public static final String MESSAGE_SUCCESS = "Sorted all Applicants";
    public static final String MESSAGE_FAILURE = "Please add a descriptor with d/ [name/phone].";

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
}
