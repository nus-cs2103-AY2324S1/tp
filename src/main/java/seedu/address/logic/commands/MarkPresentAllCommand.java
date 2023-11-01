package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.grades.exceptions.InvalidTutorialIndexException;

/**
 * Marks all displayed students' attendance as present.
 */
public class MarkPresentAllCommand extends Command {
    public static final String COMMAND_WORD = "mark-pre-all";
    public static final String MESSAGE_MARK_SUCCESS = "Successfully mark attendance as present.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks all the students displayed as present.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    private final Index index;

    /**
     * Constructs a MarkPresentAllCommand object.
     *
     * @param index of the class.
     */
    public MarkPresentAllCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();
        try {
            for (Student s : lastShownList) {
                model.setStudent(s, s.markPresent(index));
                if (model.isSelectedStudent(s)) {
                    model.setSelectedStudent(s);
                }
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
        if (!(other instanceof MarkPresentAllCommand)) {
            return false;
        }

        MarkPresentAllCommand e = (MarkPresentAllCommand) other;
        return index.equals(e.index);
    }
}
