package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;

/**
 * Deletes a member from the address book.
 */
public class DeleteMemberCommand extends Command {

    public static final String COMMAND_WORD = "deletemember";
    public static final String COMMAND_ALIAS = "delm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Deletes the member identified by the index number used in the displayed member list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MEMBER_SUCCESS = "Deleted member: %1$s";

    private final Index memberIndex;

    /**
     * Creates a DeleteMemberCommand to delete the specified {@code Member}
     *
     * @param memberIndex The index of the member to delete.
     */
    public DeleteMemberCommand(Index memberIndex) {
        this.memberIndex = memberIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();

        if (memberIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Member memberToDelete = lastShownList.get(memberIndex.getZeroBased());
        model.deleteMember(memberToDelete);
        model.clearTaskList();
        return new CommandResult(String.format(MESSAGE_DELETE_MEMBER_SUCCESS, Messages.format(memberToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMemberCommand)) {
            return false;
        }

        DeleteMemberCommand otherDeleteCommand = (DeleteMemberCommand) other;
        return memberIndex.equals(otherDeleteCommand.memberIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("memberIndex", memberIndex)
                .toString();
    }
}
