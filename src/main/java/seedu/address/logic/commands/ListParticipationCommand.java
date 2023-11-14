package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;

/**
 * Class to List the participation records of an existing student.
 */
public class ListParticipationCommand extends Command {
    public static final String COMMAND_WORD = "listParticipation";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the participation of the student identified\n"
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "Example: " + COMMAND_WORD + " 1";
    private final Index index;

    /**
     * Constructor for ListParticipationCommand
     * @param index index of student to update
     */
    public ListParticipationCommand(Index index) {
        this.index = index;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person studentToEdit = lastShownList.get(index.getZeroBased());
        Attendance studentAtd = studentToEdit.getAttendance();
        return new CommandResult(studentAtd.listParticipation());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListParticipationCommand)) {
            return false;
        }

        ListParticipationCommand otherListParticipationCommand = (ListParticipationCommand) other;
        return index.equals(otherListParticipationCommand.index);
    }
}
