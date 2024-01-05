package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskProgress;

/**
 * Updates the progress of the identified task.
 */
public class UpdateTaskProgressCommand extends Command {

    public static final String COMMAND_WORD = "updateprogress";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the task's progress identified by the index number used in the displayed task list.\n"
            + "Parameters: "
            + "TASK_INDEX (must be a positive integer) "
            + PREFIX_TASK_PROGRESS + "PROGRESS\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TASK_PROGRESS + "PENDING";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Updated Task: %1$s";

    public static final String MESSAGE_NOT_EDITED = "Progress must be updated";

    public static final String MESSAGE_DUPLICATE_TASK = "Task already exists in the task list";

    public static final CommandType COMMAND_TYPE = CommandType.UPDATE_TASK_PROGRESS;

    private final Index targetIndex;

    private final EditProgressDescriptor descriptor;

    /**
     * @param targetIndex of the task in the filtered task list to edit
     * @param descriptor progress details to edit the task with
     */
    public UpdateTaskProgressCommand(Index targetIndex, EditProgressDescriptor descriptor) {
        requireAllNonNull(targetIndex, descriptor);
        this.targetIndex = targetIndex;
        this.descriptor = descriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(targetIndex.getZeroBased());
        Task editedTask = createTask(taskToEdit, descriptor);

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, Messages.format(editedTask)), COMMAND_TYPE);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateTaskProgressCommand)) {
            return false;
        }

        UpdateTaskProgressCommand otherUpdateTaskProgressCommand = (UpdateTaskProgressCommand) other;
        return targetIndex.equals(otherUpdateTaskProgressCommand.targetIndex)
                && descriptor.equals(otherUpdateTaskProgressCommand.descriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("editProgressDescriptor", descriptor)
                .toString();
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code descriptor}.
     */
    private static Task createTask(Task taskToEdit, EditProgressDescriptor descriptor) {
        assert taskToEdit != null;

        TaskProgress updatedProgress = descriptor.getProgress().orElse(taskToEdit.getProgress());

        return new Task(taskToEdit.getName(), taskToEdit.getDescription(),
                taskToEdit.getPriority(), taskToEdit.getDate(), updatedProgress);
    }

    /**
     * Stores the progress to edit the task with.
     * Progress will replace the corresponding field in Task.
     */
    public static class EditProgressDescriptor {

        private TaskProgress progress;

        public EditProgressDescriptor() {}

        public EditProgressDescriptor(EditProgressDescriptor toCopy) {
            setProgress(toCopy.progress);
        }

        public void setProgress(TaskProgress progress) {
            this.progress = progress;
        }

        public boolean isUpdated() {
            return progress != null;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditProgressDescriptor)) {
                return false;
            }

            EditProgressDescriptor otherDescriptor = (EditProgressDescriptor) other;
            return progress.equals(otherDescriptor.progress);
        }

        public Optional<TaskProgress> getProgress() {
            return Optional.ofNullable(progress);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("taskProgress", progress)
                    .toString();
        }
    }
}
