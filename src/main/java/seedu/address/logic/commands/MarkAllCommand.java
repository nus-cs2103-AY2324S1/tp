package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.grades.exceptions.InvalidTutorialIndexException;

/**
 * Marks all displayed students' attendance.
 */
public class MarkAllCommand extends Command {
    public static final String COMMAND_WORD = "markall";
    public static final String MESSAGE_MARK_SUCCESS = "Successfully mark attendance.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks all the students displayed.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    private final Index index;

    /**
     * Constructs a MarkAllCommand object.
     *
     * @param index of the class.
     */
    public MarkAllCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();
        try {
            for (Student s : lastShownList) {
                model.setStudent(s, s.markPresent(index));
            }
        } catch (InvalidTutorialIndexException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(MESSAGE_MARK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAllCommand)) {
            return false;
        }

        MarkAllCommand e = (MarkAllCommand) other;
        return index.equals(e.index);
    }
}
