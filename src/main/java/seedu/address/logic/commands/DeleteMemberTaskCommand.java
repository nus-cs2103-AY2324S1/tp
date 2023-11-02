package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;
import seedu.address.model.task.Task;

public class DeleteMemberTaskCommand extends Command {

    public static final String COMMAND_WORD = "deleteTask";
    public static final String COMMAND_ALIAS = "delt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from the specified member "
            + "by the index number used in the displayed member list and the task index. \n"
            + "Parameters: MEMBER_INDEX (must be a positive integer) "
            + PREFIX_TASK + "TASK_INDEX";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Task deleted from member %1$s";

    private final Index memberIndex;
    private final Index taskIndex;
    /**
     * Creates a DeleteTaskCommand to delete a task from the specified {@code Member}.
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

        if (taskIndex.getZeroBased() < 0 || taskIndex.getZeroBased() >= memberToDeleteTaskFrom.getTasks().size()) {
            throw new CommandException("Index out of bounds");
        }

        Task taskToDelete = memberToDeleteTaskFrom.getTasks().get(taskIndex.getZeroBased());

        Member taskDeletedMember = removeTaskFromMember(memberToDeleteTaskFrom, taskToDelete);

        model.setMember(memberToDeleteTaskFrom, taskDeletedMember);
        model.updateFilteredMemberList(PREDICATE_SHOW_ALL_PERSONS);
        model.setTaskListForMember(taskDeletedMember);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, memberToDeleteTaskFrom));
    }


    private static Member removeTaskFromMember(Member member, Task taskToDelete) {
        assert member != null;

        List<Task> updatedTasks = new ArrayList<>(member.getTasks());
        updatedTasks.remove(taskToDelete);

        return new Member(member.getName(), member.getPhone(), member.getEmail(),
                member.getTelegram(), member.getTags(), updatedTasks);
    }
}