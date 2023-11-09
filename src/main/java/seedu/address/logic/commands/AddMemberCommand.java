package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Member;

/**
 * Adds a member to the address book.
 */
public class AddMemberCommand extends Command {

    public static final String COMMAND_WORD = "addmember";
    public static final String COMMAND_ALIAS = "addm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_ALIAS
            + ": Adds a member to the member list. "
            + "\nParameters: "
            + PREFIX_NAME + " MEMBER_NAME "
            + PREFIX_PHONE + " PHONE_NUMBER "
            + PREFIX_EMAIL + " EMAIL "
            + PREFIX_TELEGRAM + " TELEGRAM_HANDLE "
            + "[" + PREFIX_TAG + " TAG]..."
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John Doe "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_EMAIL + " johnd@example.com "
            + PREFIX_TELEGRAM + " @johndoe "
            + PREFIX_TAG + " WelfareHead "
            + PREFIX_TAG + " Genius";

    public static final String MESSAGE_SUCCESS = "New member added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEMBER = "This member already exists in the member list";

    private final Member toAdd;

    /**
     * Creates an AddMemberCommand to add the specified {@code Member}
     *
     * @param member The member to add.
     */
    public AddMemberCommand(Member member) {
        requireNonNull(member);
        toAdd = member;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMember(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEMBER);
        }

        model.addMember(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMemberCommand)) {
            return false;
        }

        AddMemberCommand otherAddMemberCommand = (AddMemberCommand) other;
        return toAdd.equals(otherAddMemberCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
