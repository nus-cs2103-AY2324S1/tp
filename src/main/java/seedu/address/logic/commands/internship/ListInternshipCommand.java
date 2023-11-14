package seedu.address.logic.commands.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIP_ROLES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERNSHIP_TASKS;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;


/**
 * Lists all persons in the address book to the user.
 */
public class ListInternshipCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "list-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists internship roles and tasks";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setView(correctViewNeeded);
        model.updateFilteredInternshipRoleList(PREDICATE_SHOW_ALL_INTERNSHIP_ROLES);
        model.updateFilteredInternshipTaskList(PREDICATE_SHOW_ALL_INTERNSHIP_TASKS);

        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipRoleList().size()
                                + model.getFilteredInternshipTaskList().size()));
    }
}
