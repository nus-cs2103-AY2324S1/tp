package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.TutorPredicate;

/**
 * Lists all schedules in the address book to the user.
 */
public class ListScheduleCommand extends Command {

    public static final String COMMAND_WORD = "list-s";

    public static final String MESSAGE_SUCCESS = "Listed all schedules";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": List the schedules in the address book. If index is specified, it lists the schedule"
        + "identified by that index number in the displayed tutor list.\n"
        + "Parameters: \nINDEX (must be a positive integer, optional to add) \n"
        + "Example: \n"
        + COMMAND_WORD + " 1, \n";

    private Index targetIndex;

    public ListScheduleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex == null) {
            model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person tutor = lastShownList.get(targetIndex.getZeroBased());
            TutorPredicate indexPredicate = new TutorPredicate(tutor);
            model.updateFilteredScheduleList(indexPredicate);

            return new CommandResult(
                String.format(Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW, model.getFilteredScheduleList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListScheduleCommand)) {
            return false;
        }

        if (targetIndex != null) {
            ListScheduleCommand otherFindCommand = (ListScheduleCommand) other;
            return targetIndex.equals(otherFindCommand.targetIndex);
        } else {
            return true;
        }
    }


}
