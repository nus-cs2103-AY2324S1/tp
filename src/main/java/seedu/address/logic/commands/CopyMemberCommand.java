package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;

/**
 * Copies a member from the address book to the clipboard.
 */
public class CopyMemberCommand extends Command {

    public static final String COMMAND_WORD = "copymember";
    public static final String COMMAND_ALIAS = "cpm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Copies the details (name, phone number, email, telegram, and tags) of the member"
            + " at the specified index to clipboard\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_COPY_MEMBER_SUCCESS = "Copied details of member to clipboard:\n%1$s";
    private final Index memberIndex;

    /**
     * Creates a CopyMemberCommand to copy the {@code Member} at the specified {@code Index}.
     *
     * @param memberIndex The index of the member to copy.
     */
    public CopyMemberCommand(Index memberIndex) {
        this.memberIndex = memberIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownList = model.getFilteredMemberList();

        if (memberIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }

        Member memberToCopy = lastShownList.get(memberIndex.getZeroBased());
        String memberString = memberToCopy.detailsToCopy();

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(memberString), null);
        return new CommandResult(String.format(MESSAGE_COPY_MEMBER_SUCCESS, memberString));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CopyMemberCommand)) {
            return false;
        }

        CopyMemberCommand otherCopyCommand = (CopyMemberCommand) other;
        return memberIndex.equals(otherCopyCommand.memberIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("memberIndex", memberIndex)
                .toString();
    }
}
