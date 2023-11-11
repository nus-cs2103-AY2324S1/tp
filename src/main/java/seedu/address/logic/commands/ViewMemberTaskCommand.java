package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;

/**
 * Views tasks assigned to the Member in the address book.
 */
public class ViewMemberTaskCommand extends Command {

    public static final String COMMAND_WORD = "viewtask";
    public static final String COMMAND_ALIAS = "viewt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Views all tasks assigned to the specified member "
            + "by the index number used in the displayed member list. \n"
            + "Parameters: INDEX (must be a positive integer) ";

    public static final String MESSAGE_ADD_TODO_SUCCESS = "Listed tasks for member %1$s.";

    private final Index index;


    /**
     * Creates an AddMemberCommand to add the specified {@code Member}
     *
     * @param index The index of the member to view.
     */
    public ViewMemberTaskCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Member memberToView = lastShownList.get(index.getZeroBased());

        model.setTaskListForMember(memberToView);
        return new CommandResult(String.format(MESSAGE_ADD_TODO_SUCCESS, Messages.format(memberToView)));
    }
}
