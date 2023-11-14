package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Deletes a tag from a task identified using its displayed index from the address book.
 */
public class DeleteTagTaskCommand extends Command {

    public static final String COMMAND_WORD = "deleteTagTask";
    public static final String SHORTENED_COMMAND_WORD = "dtagt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (alias: " + SHORTENED_COMMAND_WORD + ")"
            + ": Deletes one or more tags from a task identified using its displayed index from the address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG "
            + "[" + PREFIX_TAG + "MORE TAGS]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "catering "
            + PREFIX_TAG + "budget";

    public static final String MESSAGE_DELETE_TAG_TASK_SUCCESS = "Deleted tag(s) from task: %1$s\n";
    public static final String MESSAGE_DELETE_TAG_TASK_MATCHING_TAGS = "Successfully removed these tags: %1$s\n";
    public static final String MESSAGE_DELETE_TAG_TASK_MISSING_TAGS = "These tags did not seem to exist: %1$s\n";

    private final Index targetIndex;
    private final Set<Tag> tagsToDelete;

    /**
     * @param targetIndex of the task in the filtered task list to edit
     * @param tagsToDelete tags to delete from the task
     */
    public DeleteTagTaskCommand(Index targetIndex, Set<Tag> tagsToDelete) {
        this.targetIndex = targetIndex;
        this.tagsToDelete = tagsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(targetIndex.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, tagsToDelete);
        model.setTask(taskToEdit, editedTask);
        return new CommandResult(formatResultMessage(taskToEdit, editedTask, tagsToDelete));
    }

    private static Task createEditedTask(Task taskToEdit, Set<Tag> tagsToDelete) {
        requireNonNull(taskToEdit);
        requireNonNull(tagsToDelete);

        Set<Tag> newTags = new HashSet<>(taskToEdit.getTags());
        newTags.removeAll(tagsToDelete);
        return new Task(
                taskToEdit.getTitle(),
                taskToEdit.getNote(),
                newTags);
    }

    /**
     * Determines what the CommandResult string should be.
     * Handles cases of missing tags.
     */
    public static String formatResultMessage(Task taskToEdit, Task editedTask, Set<Tag> tagsToDelete) {
        Set<Tag> matchingTags = new HashSet<>(taskToEdit.getTags());
        matchingTags.retainAll(tagsToDelete);

        Set<Tag> missingTags = new HashSet<>(tagsToDelete);
        missingTags.removeAll(matchingTags);

        String res = String.format(MESSAGE_DELETE_TAG_TASK_SUCCESS, Messages.format(editedTask));
        if (matchingTags.size() > 0) {
            String matchingTagsString = matchingTags.stream().map(Tag::toString).collect(Collectors.joining(", "));
            res += String.format(MESSAGE_DELETE_TAG_TASK_MATCHING_TAGS, matchingTagsString);
        }

        if (missingTags.size() > 0) {
            String missingTagsString = missingTags.stream().map(Tag::toString).collect(Collectors.joining(", "));
            res += String.format(MESSAGE_DELETE_TAG_TASK_MISSING_TAGS, missingTagsString);
        }
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTagTaskCommand)) {
            return false;
        }

        DeleteTagTaskCommand otherDeleteCommand = (DeleteTagTaskCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex)
                && tagsToDelete.equals(otherDeleteCommand.tagsToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("tagsToDelete", tagsToDelete)
                .toString();
    }
}
