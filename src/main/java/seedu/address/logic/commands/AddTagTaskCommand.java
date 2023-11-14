package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

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
import seedu.address.model.task.Note;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Adds tags to an existing task in the address book.
 */
public class AddTagTaskCommand extends Command {

    public static final String COMMAND_WORD = "addTagTask";
    public static final String SHORTENED_COMMAND_WORD = "atagt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (alias: " + SHORTENED_COMMAND_WORD + ")"
            + ": Adds new tags to the task identified "
            + "by the index number used in the displayed task list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "class " + PREFIX_TAG + "finance";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s \n" + "Successfully added these "
            + "tags: %2$s \n" + "These tags were not added as they already exists: %3$s";

    // newTags stores those from tagsToAdd that are not already in taskToEdit's tags
    private static Set<Tag> newTags;
    // oldTags stores those from tagsToAdd that are already in taskToEdit's tags
    private static Set<Tag> oldTags;
    private final Set<Tag> tagsToAdd;
    private final Index index;

    /**
     * @param index of the task in the filtered task list to edit
     * @param tagsToAdd tags to add to the task
     */
    public AddTagTaskCommand(Index index, Set<Tag> tagsToAdd) {
        requireNonNull(index);
        requireNonNull(tagsToAdd);

        this.index = index;
        this.tagsToAdd = tagsToAdd;
        newTags = new HashSet<>();
        oldTags = new HashSet<>();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownTaskList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, tagsToAdd);

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, Messages.format(editedTask),
                setToString(newTags), setToString(oldTags)));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}.
     */
    private static Task createEditedTask(Task taskToEdit, Set<Tag> tagsToAdd) {
        assert taskToEdit != null;

        Title title = taskToEdit.getTitle();
        Note note = taskToEdit.getNote();
        Status taskStatus = taskToEdit.getStatus();
        Set<Tag> existingTags = taskToEdit.getTags();

        Set<Tag> commonTags = new HashSet<>(existingTags);
        commonTags.retainAll(tagsToAdd);

        oldTags.addAll(commonTags);

        newTags.addAll(tagsToAdd);
        newTags.removeAll(existingTags);

        Set<Tag> updatedTags = new HashSet<>(existingTags);
        updatedTags.addAll(tagsToAdd);

        return new Task(title, note, taskStatus, updatedTags);
    }

    public static String setToString(Set<Tag> tags) {

        if (tags.isEmpty()) {
            return "-";
        } else {
            return tags.stream()
                    .map(Tag::toString)
                    .collect(Collectors.joining(", "));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagTaskCommand)) {
            return false;
        }

        AddTagTaskCommand otherAddTagTaskCommand = (AddTagTaskCommand) other;
        return index.equals(otherAddTagTaskCommand.index)
                && tagsToAdd.equals(otherAddTagTaskCommand.tagsToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("tagsToAdd", tagsToAdd)
                .toString();
    }
}
