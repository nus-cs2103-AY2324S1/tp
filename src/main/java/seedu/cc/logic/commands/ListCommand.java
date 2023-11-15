package seedu.cc.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.cc.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;

/**
 * Lists all persons in the clinic book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list-patients";

    public static final String MESSAGE_SUCCESS = "Listed all patients";
    private final SwitchCommand switchCommand = new SwitchCommand(Index.fromZeroBased(0));

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        switchCommand.execute(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
