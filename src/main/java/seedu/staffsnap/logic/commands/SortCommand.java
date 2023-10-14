package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_DESCRIPTOR;
import static seedu.staffsnap.model.Model.PREDICATE_HIDE_ALL_APPLICANTS;
import static seedu.staffsnap.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

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

    private final Descriptor descriptor;

    /**
     * Creates a SortCommand to add the specified {@code descriptor}
     */
    public SortCommand(Descriptor descriptor) {
        requireNonNull(descriptor);
        this.descriptor = descriptor;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedApplicantList(descriptor);
        model.updateFilteredApplicantList(PREDICATE_HIDE_ALL_APPLICANTS);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
