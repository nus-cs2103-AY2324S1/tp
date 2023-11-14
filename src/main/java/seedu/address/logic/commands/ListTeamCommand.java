package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEAMS;

import seedu.address.model.Model;

/**
 * Lists all Teams in the address book to the user.
 */
public class ListTeamCommand extends Command {

    public static final String COMMAND_WORD = "listt";

    public static final String MESSAGE_SUCCESS = "Listed all teams";


    /**
     * Create a CommandResult instance, which the Ui will operate base on.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult instance, which the Ui will operate base on.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false, false, false, false);
    }
}
