//@@author zannloo
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_PATH_NOT_FOUND;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ChildOperation;
import seedu.address.model.Model;
import seedu.address.model.TaskOperation;
import seedu.address.model.path.AbsolutePath;
import seedu.address.model.profbook.Group;
import seedu.address.model.profbook.Student;
import seedu.address.model.task.ToDo;

/**
 * Adds a Todo task for a one or more {@code Student} or {@code Group}.
 */
public class CreateTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_SUCCESS_STUDENT = "New Todo task added to student: %1$s\n%2$s";

    public static final String MESSAGE_SUCCESS_GROUP = "New Todo task added to group: %1$s\n%2$s";

    public static final String MESSAGE_SUCCESS_ALL_STUDENTS =
            "New ToDo task added to all students in group: %1$s";

    public static final String MESSAGE_SUCCESS_ALL_STUDENTS_FOR_ROOT =
            "New ToDo task added to all students in root directory.";

    public static final String MESSAGE_SUCCESS_ALL_STUDENTS_WITH_WARNING =
            "Warning: Some student(s) already have the task. \n"
            + "New ToDo task has been added to the rest.";

    public static final String MESSAGE_SUCCESS_ALL_GROUPS =
            "New ToDo task added to all groups in root directory.";

    public static final String MESSAGE_SUCCESS_ALL_GROUPS_WITH_WARNING =
            "Warning: Some group(s) already have the task. \n"
            + "New ToDo task has been added to the rest.";

    public static final String MESSAGE_DUPLICATE_TODO_TASK = "This Todo task has already been allocated";

    public static final String MESSAGE_TASK_CREATION_FOR_ROOT = "Unable to create task for root directory.";

    public static final String MESSAGE_INVALID_PATH_FOR_ALL_STU = "AllStu flag is only allowed for root and group path";

    public static final String MESSAGE_INVALID_PATH_FOR_ALL_GROUP = "AllGrp flag is only allowed for root path";

    public static final String MESSAGE_ALL_CHILDREN_HAVE_TASK = "All %1$ss already have the task.";

    public static final String MESSAGE_USAGE =
            "Usage: " + COMMAND_WORD + " [path]" + " -d <desc>" + " [OPTION]... \n"
            + "\n"
            + "Create todo task to the path path (the current directory by default).\n"
            + "\n"
            + "Argument: \n"
            + "    -d, --desc           Description of the todo task\n"
            + "\n"
            + "Option: \n"
            + "    path                 Valid path to group or student\n"
            + "    -al, --all           Bulk task assignment\n"
            + "                         Possible value: allStu, allGrp\n"
            + "    -h, --help           Show this help menu\n"
            + "\n"
            + "Examples: \n"
            + "todo grp-001/0001Y -d Homework \n"
            + "todo . -d Homework -al allGrp \n"
            + "todo ./grp-001 -d Homework -al allStu";

    public static final CreateTodoCommand HELP_MESSAGE = new CreateTodoCommand() {
        @Override
        public CommandResult execute(Model model) {
            return new CommandResult(MESSAGE_USAGE);
        }
    };

    private static final Logger logger = LogsCenter.getLogger(CreateTodoCommand.class);

    private final AbsolutePath path;

    private final ToDo todo;

    private final Category category;

    /**
     * Constructs a {@code CreateTodoCommand} with the specified absolute path and Todo task details.
     *
     * @param path   The absolute path to the group where the Todo task will be added.
     * @param todo     The details of the Todo task to be created.
     * @param category The specific category of people to add Todo task to each.
     */
    public CreateTodoCommand(AbsolutePath path, ToDo todo, Category category) {
        requireAllNonNull(path, todo, category);
        this.path = path;
        this.todo = todo;
        this.category = category;
    }

    private CreateTodoCommand() {
        this.path = null;
        this.todo = null;
        this.category = null;
    }

    /**
     * Executes a CreateTodoCommand to allocate a {@code Todo} task to one or more
     * {@code Group} or {@code Student}.
     *
     * @return A CommandResult indicating the outcome of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert model != null : "Model should not be null";

        // Check path exists in ProfBook
        if (!model.hasPath(path)) {
            logger.warning("Path does not exist. Aborting create todo task command.");
            throw new CommandException(String.format(MESSAGE_PATH_NOT_FOUND, path));
        }

        switch(category) {
        case NONE:
            return handleNone(model);
        case ALLSTU:
            return handleAllStu(model);
        default:
            return handleAllGrp(model);
        }
    }

    /**
     * Allocates a {@code Todo} task to a {@code Group} or {@code Student}.
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     */
    private CommandResult handleNone(Model model) throws CommandException {
        // Check if target path is root
        if (path.isRootDirectory()) {
            throw new CommandException(MESSAGE_TASK_CREATION_FOR_ROOT);
        }

        TaskOperation taskOperation = model.taskOperation(path);
        if (taskOperation.hasTask(this.todo)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO_TASK);
        }

        logger.finer("Created a todo task for a single group or student");
        taskOperation.addTask(this.todo);
        model.updateList();

        if (path.isGroupDirectory()) {
            return new CommandResult(String.format(MESSAGE_SUCCESS_GROUP, path.getGroupId().get(), this.todo));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS_STUDENT, path.getStudentId().get(), this.todo));
    }

    /**
     * Handles the situation where a {@code Todo} task is allocated to all {@code Student}
     * in {@code Group} or {@code Root}.
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     */
    private CommandResult handleAllStu(Model model) throws CommandException {
        if (path.isStudentDirectory()) {
            throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_STU);
        }

        if (path.isGroupDirectory()) {
            return addTaskToAllStuInGrp(model);
        }

        return addTaskToAllStuInRoot(model);
    }

    /**
     * Adds a {@code Todo} task to all {@code Student} in a {@code Group}.
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     */
    private CommandResult addTaskToAllStuInGrp(Model model) throws CommandException {
        ChildOperation<Student> groupOper = model.groupChildOperation(path);

        // Check whether all children already have the task
        if (groupOper.doAllChildrenHaveTasks(todo, 1)) {
            throw new CommandException(String.format(MESSAGE_ALL_CHILDREN_HAVE_TASK, "student"));
        }

        // Check whether at least one of the children has the task
        boolean warning = groupOper.doAnyChildrenHaveTasks(todo, 1);

        logger.finer("Created a todo task for all students under a group");
        groupOper.addTaskToAllChildren(todo, 1);
        model.updateList();
        return new CommandResult(
                warning ? MESSAGE_SUCCESS_ALL_STUDENTS_WITH_WARNING
                        : String.format(MESSAGE_SUCCESS_ALL_STUDENTS, path.getGroupId().get()));
    }

    /**
     * Adds a {@code Todo} task to all {@code Student} in a {@code Root}.
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     */
    private CommandResult addTaskToAllStuInRoot(Model model) throws CommandException {
        ChildOperation<Group> operation = model.rootChildOperation();

        // Check whether all children already have the task
        if (operation.doAllChildrenHaveTasks(todo, 2)) {
            throw new CommandException(String.format(MESSAGE_ALL_CHILDREN_HAVE_TASK, "student"));
        }

        // Check whether at least one of the children has the task
        boolean warning = operation.doAnyChildrenHaveTasks(todo, 2);

        logger.finer("Created a todo task for all students under ProfBook");
        operation.addTaskToAllChildren(todo, 2);
        model.updateList();
        return new CommandResult(
                warning ? MESSAGE_SUCCESS_ALL_STUDENTS_WITH_WARNING
                        : MESSAGE_SUCCESS_ALL_STUDENTS_FOR_ROOT);
    }

    /**
     * Handles the situation where a {@code Todo} task is allocated to all {@code Group} in a {@code Root}.
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     */
    private CommandResult handleAllGrp(Model model) throws CommandException {
        if (!path.isRootDirectory()) {
            throw new CommandException(MESSAGE_INVALID_PATH_FOR_ALL_GROUP);
        }

        return addTaskToAllGrpInRoot(model);
    }

    /**
     * Adds a {@code Todo} task to all {@code Group} in a {@code Root}.
     *
     * @return Command result which represents the outcome of the command execution.
     * @throws CommandException Exception thrown when error occurs during command execution.
     */
    private CommandResult addTaskToAllGrpInRoot(Model model) throws CommandException {
        ChildOperation<Group> rootOper = model.rootChildOperation();

        // Check whether all children already have the task
        if (rootOper.doAllChildrenHaveTasks(todo, 1)) {
            throw new CommandException(String.format(MESSAGE_ALL_CHILDREN_HAVE_TASK, "group"));
        }

        // Check whether at least one of the children has the task
        boolean warning = rootOper.doAnyChildrenHaveTasks(todo, 1);

        logger.finer("Created a todo task for all groups under ProfBook");
        rootOper.addTaskToAllChildren(todo, 1);
        model.updateList();
        return new CommandResult(warning ? MESSAGE_SUCCESS_ALL_GROUPS_WITH_WARNING : MESSAGE_SUCCESS_ALL_GROUPS);
    }

    /**
     * Checks if this {@code CreateTodoCommand} is equal to another object.
     *
     * @param other The other object to compare against this {@code CreateTodoCommand}.
     * @return True if the object is same as {@code CreateTodoCommand} and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateTodoCommand)) {
            return false;
        }

        CreateTodoCommand otherCreateTodoCommand = (CreateTodoCommand) other;
        return this.path.equals(otherCreateTodoCommand.path)
                && this.todo.equals(otherCreateTodoCommand.todo);
    }

    /**
     * Returns a string representation of this CreateTodoCommand.
     *
     * @return String representation of the CreateTodoCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateTodo", this.todo)
                .toString();
    }
}
