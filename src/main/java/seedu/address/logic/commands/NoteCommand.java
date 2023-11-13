package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Note;
import seedu.address.model.task.Task;

/**
 * Marks a task as completed.
 */
public class NoteCommand extends Command {
    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add additional information about a task.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NOTE + "NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NOTE + "Remember to clarify with the tutor first";

    public static final String MESSAGE_NOTE_TASK_SUCCESS = "Added Note to Task: %1$s";

    private final Index targetIndex;
    private final Note note;

    /**
     * Creates a NoteCommand to add the specified {@code Note} to the {@code Task} at the specified {@code Index}.
     * @param targetIndex Index of the task in the task list to add the note to.
     * @param note Note to be added to the task.
     */
    public NoteCommand(Index targetIndex, Note note) {
        requireNonNull(targetIndex);
        requireNonNull(note);

        this.targetIndex = targetIndex;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToNote = lastShownList.get(targetIndex.getZeroBased());

        Task taskWithNote = new Task(taskToNote.getDescription(),
                taskToNote.getStatus(), note, taskToNote.getDeadline(), taskToNote.getPriority(),
                taskToNote.getMembers());
        model.setTask(taskToNote, taskWithNote);


        return new CommandResult(String.format(MESSAGE_NOTE_TASK_SUCCESS, Messages.format(taskToNote)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }

        NoteCommand otherNoteCommand = (NoteCommand) other;
        return targetIndex.equals(otherNoteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
