package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;
import seedu.address.model.task.Task;

/**
 * Deletes a task from the Member in the address book.
 */
public class DeleteMemberTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";
    public static final String COMMAND_ALIAS = "delt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Deletes a specified task from the specified member "
            + "by the index number used in the displayed member list "
            + "and the task index used in the displayed task list. \n"
            + "Parameters: MEMBER_INDEX (must be a positive integer) "
            + PREFIX_TASK + " TASK_INDEX"
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK + " 1 ";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Task %1$s deleted from member %2$s";
    private final Index memberIndex;
    private final Index taskIndex;

    /**
     * Creates an DeleteMemberTaskCommand to delete the specified {@code Task}
     *
     * @param memberIndex The index of the member to delete the task from.
     * @param taskIndex   The index of the task to delete.
     */
    public DeleteMemberTaskCommand(Index memberIndex, Index taskIndex) {
        requireNonNull(memberIndex);
        requireNonNull(taskIndex);

        this.memberIndex = memberIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();

        if (memberIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Member memberToDeleteTaskFrom = lastShownList.get(memberIndex.getZeroBased());

        if (taskIndex.getZeroBased() >= memberToDeleteTaskFrom.getTasks().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = memberToDeleteTaskFrom.getTasks().get(taskIndex.getZeroBased());

        Member taskDeletedMember = removeTaskFromMember(memberToDeleteTaskFrom, taskToDelete);

        model.setMember(memberToDeleteTaskFrom, taskDeletedMember);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_PERSONS);
        model.setTaskListForMember(taskDeletedMember);

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete,
                Messages.format(memberToDeleteTaskFrom)));
    }

    private static Member removeTaskFromMember(Member member, Task taskToDelete) {
        assert member != null;

        List<Task> updatedTasks = new ArrayList<>(member.getTasks());
        updatedTasks.remove(taskToDelete);

        return new Member(member.getName(), member.getPhone(), member.getEmail(),
                member.getTelegram(), member.getTags(), updatedTasks);
    }
}
