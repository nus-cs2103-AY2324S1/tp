package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.DuplicatedTaskException;
import seedu.address.model.Model;
import seedu.address.model.member.Member;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Note;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;

/**
 * Adds a task to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the address book. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Submit User Guide";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "Oh no! This task already exists in the task list!";

    private final AddTaskDescriptor desc;
    /**
     * Creates an AddCommand with the specified {@code AddTaskDescriptor}
     * @param desc the {@code AddTaskDescriptor} with the necessary details to instantiate the task
     */
    public AddCommand(AddTaskDescriptor desc) {
        requireNonNull(desc);

        this.desc = desc;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Task test = createAddedTask(desc);

        if (model.hasTask(test)) {
            throw new DuplicatedTaskException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(test);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(test)));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createAddedTask(AddCommand.AddTaskDescriptor addTaskDescriptor) {

        assert addTaskDescriptor != null;

        Description updatedDescription = addTaskDescriptor.getDescription().get();
        Deadline updatedDeadline = addTaskDescriptor.getDeadline().orElse(Deadline.ofNull());
        Priority updatedPriority = addTaskDescriptor.getPriority().orElse(Priority.NONE);
        Status status = new Status();
        Note note = new Note("");
        Set<Member> members = addTaskDescriptor.getMembers().orElse(new HashSet<>());

        return new Task(updatedDescription, status, note, updatedDeadline, updatedPriority, members);
    }

    public AddTaskDescriptor getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return desc.equals(otherAddCommand.desc);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("desc", desc)
                .toString();
    }

    /**
     * Stores the details to create a new task with.
     */
    public static class AddTaskDescriptor {
        private Description description;
        private Deadline deadline;
        private Priority priority;
        private Set<Member> members;

        /**
         * Basic constructor.
         * Priority, Note and Members are initialised with default, non-null values
         */
        public AddTaskDescriptor() {
            this.priority = Priority.NONE;
            this.members = new HashSet<>();
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }
        public void setPriority(Priority priority) {
            this.priority = priority;
        }
        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        /**
         * Sets {@code members} to this object's {@code members}.
         * A defensive copy of {@code members} is used internally.
         * @param members A set of members to be added to the task.
         */
        public void setMembers(Set<Member> members) {
            this.members = (members != null) ? new HashSet<>(members) : null;
        }

        /**
         * Returns an unmodifiable member set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * @return An {@code Optional#empty()} if {@code members} is null.
         */
        public Optional<Set<Member>> getMembers() {
            return Optional.ofNullable(members).map(x -> Collections.unmodifiableSet(members));
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddCommand.AddTaskDescriptor)) {
                return false;
            }

            AddCommand.AddTaskDescriptor otherAddTaskDescriptor = (AddCommand.AddTaskDescriptor) other;
            return Objects.equals(description, otherAddTaskDescriptor.description)
                    && Objects.equals(members, otherAddTaskDescriptor.members)
                    && Objects.equals(deadline, otherAddTaskDescriptor.deadline)
                    && Objects.equals(priority, otherAddTaskDescriptor.priority);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("description", description)
                    .add("members", members)
                    .add("deadline", deadline)
                    .add("priority", priority)
                    .toString();
        }
    }
}
