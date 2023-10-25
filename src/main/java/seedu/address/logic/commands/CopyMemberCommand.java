package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;

/**
 * Copies a member from the address book to the clipboard.
 */
public class CopyMemberCommand extends Command {

    public static final String COMMAND_WORD = "copyMember";
    public static final String COMMAND_ALIAS = "cpm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies the details of the member identified by the index number used"
            + " in the displayed member list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Copied details member to clipboard: %1$s";
    private final Index memberIndex;

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
        String memberString = memberToCopy.toString();

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(memberString), null);
        return new CommandResult(String.format(MESSAGE_SUCCESS, memberString));
    }
}
