package seedu.modulight.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.modulight.commons.core.index.Index;
import seedu.modulight.commons.util.ToStringBuilder;
import seedu.modulight.logic.Messages;
import seedu.modulight.logic.commands.exceptions.CommandException;
import seedu.modulight.model.Model;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;
import seedu.modulight.model.student.Student;
import seedu.modulight.model.student.model.StudentBook;
import seedu.modulight.model.studentscore.StudentScore;
import seedu.modulight.model.studentscore.model.StudentScoreBook;

/**
 * Deletes a gradedComponent identified using it's displayed index from the address book.
 */
public class DeleteGradedComponentCommand extends Command {

    public static final String COMMAND_WORD = "deleteComp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the gradedComponent identified by the index number used "
            + "in the displayed gradedComponent list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_GC_SUCCESS = "Deleted graded component: %1$s";

    private final Index targetIndex;

    public DeleteGradedComponentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        GradedComponentBook gradedComponentBook = model.getGradedComponentBook();
        StudentBook studentBook = model.getStudentBook();
        StudentScoreBook studentScoreBook = model.getStudentScoreBook();
        List<GradedComponent> lastShownList = model.getFilteredGradedComponentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        GradedComponent gradedComponentToDelete = lastShownList.get(targetIndex.getZeroBased());
        gradedComponentBook.removeGradedComponent(gradedComponentToDelete);
        List<StudentScore> studentScoreList = studentScoreBook.getStudentScoreList();
        for (int i = studentScoreList.size() - 1; i >= 0; i--) {
            StudentScore curScore = studentScoreList.get(i);
            if (curScore.getGcName().equals(gradedComponentToDelete.getName())) {
                Student student = studentBook.getStudentById(curScore.getStudentId());
                student.deleteScore(curScore);
                studentBook.setStudent(student, student);
                studentScoreBook.removeStudentScore(curScore);
            }
        }
        return new CommandResult(String.format(MESSAGE_DELETE_GC_SUCCESS,
                Messages.formatGradedComponent(gradedComponentToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteGradedComponentCommand)) {
            return false;
        }

        DeleteGradedComponentCommand otherDeleteCommand = (DeleteGradedComponentCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

