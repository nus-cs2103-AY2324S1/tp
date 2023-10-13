package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

public class ListApplicantCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applicants";

    @Override
    public CommandResult execute(Model model) {
        //TODO implement this
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
