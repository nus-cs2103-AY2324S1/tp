package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Status;
import seedu.address.model.schedule.StatusPredicate;
import seedu.address.model.schedule.TutorIndexPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListScheduleCommand extends Command {

    public static final String COMMAND_WORD = "list-s";

    public static final String MESSAGE_SUCCESS = "Listed all schedules";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": List the schedule identified by the index number used in the displayed tutor list.\n"
        + "Parameters: INDEX (must be a positive integer, optional to add)\n"
        + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;
    private final Status status;

    /**
     * Creates an ListScheduleCommand to list the specified {@code Schedule}
     */
    public ListScheduleCommand(Index targetIndex, Status status) {
        this.targetIndex = targetIndex;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<String> nameList = null;
        if (targetIndex != null) {
            List<Person> lastShownList = model.getFilteredPersonList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            String fullName = lastShownList.get(targetIndex.getZeroBased()).getName().toString();
            nameList = new ArrayList<>(Arrays.asList(fullName.split(" ")));
            TutorIndexPredicate indexPredicate = new TutorIndexPredicate(nameList);
            model.updateFilteredScheduleList(indexPredicate);
        }

        if (status != null) {
            StatusPredicate statusPredicate = new StatusPredicate(
                Collections.singletonList(status.toString()), nameList);
            model.updateFilteredScheduleList(statusPredicate);
        }

        if (targetIndex == null && status == null) {
            model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
            return new CommandResult(MESSAGE_SUCCESS);
        }

        return new CommandResult(String.format(Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW,
            model.getFilteredScheduleList().size()));
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
