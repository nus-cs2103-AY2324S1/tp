package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.group.exceptions.TaskException;
import seedu.address.model.group.tasks.TaskInitializer;
import seedu.address.model.group.tasks.TaskList;

/**
 * Lists out all tasks for a specific group.
 */
public class TasksCommand extends Command {

    public static final String COMMAND_WORD = "tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Lists out all tasks for a specific group.\n"
        + "Parameters: GROUP_NUMBER\n"
        + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_SUCCESS = "Listing out tasks for group %1$s";
    public static final String MESSAGE_TASK_GROUP_NOT_FOUND = "Group with the provided group number not found.";

    private final int groupId;
    private final GroupContainsKeywordsPredicate predicate;


    /**
     * Creates a TasksCommand to list out all tasks for a specific group.
     *
     * @param groupId The group ID for which tasks should be listed.
     */
    public TasksCommand(int groupId, GroupContainsKeywordsPredicate predicate) {
        this.groupId = groupId;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // Retrieve the group using the groupId
        Optional<Group> optionalGroup = model.getGroupWithNumber(groupId);
        if (optionalGroup.isEmpty()) {
            throw new CommandException(MESSAGE_TASK_GROUP_NOT_FOUND);
        }

        Group group = optionalGroup.get();

        TaskList taskList = group.getTasks();

        if (taskList.isEmpty()) {
            try {
                taskList = TaskInitializer.initializeTasks();
            } catch (TaskException e) {
                throw new RuntimeException(e);
            }
            group.addTasks(taskList);
        }

        String displayedTasks = taskList.toString();
        requireNonNull(model);
        model.updateFilteredGroupList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, groupId) + "\n" + displayedTasks,
            false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        // basic checks
        if (other == this) {
            return true;
        }
        if (!(other instanceof TasksCommand)) {
            return false;
        }

        // property checks
        TasksCommand otherTasksCommand = (TasksCommand) other;
        return groupId == otherTasksCommand.groupId;
    }
}
