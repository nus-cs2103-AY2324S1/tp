package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a shortcut to the address book.
 */
public class AddShortcutCommand extends Command {
    public static final String COMMAND_WORD = "addsc";
    public static final String MESSAGE_SUCCESS = "New shortcut added: %1$s";
    public static final String MESSAGE_DUPLICATE = "That shortcut already exists: %1$s;";
    public static final String MESSAGE_REPLACED = "Old shortcut %1$s was removed as a result.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Registers the shortcut to the valid command keyword for future use.\n"
            + "Parameters: "
            + PREFIX_SHORTCUT + "SHORTCUT "
            + PREFIX_COMMAND_WORD + "KEYWORD\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SHORTCUT + "del "
            + PREFIX_COMMAND_WORD + DeleteCommand.COMMAND_WORD;
    private final ShortcutAlias shortcutAlias;
    private final CommandWord command;

    /**
     * Creates an AddShortcutCommand to add the specified shortcut mapping.
     */
    public AddShortcutCommand(ShortcutAlias shortcutAlias, CommandWord commandWord) {
        requireAllNonNull(shortcutAlias, commandWord);
        this.shortcutAlias = shortcutAlias;
        this.command = commandWord;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String feedback = model.registerShortcut(shortcutAlias, command);
        if (feedback == null) {
            // Completely new mapping
            model.commit();
            return new CommandResult(String.format(MESSAGE_SUCCESS, shortcutAlias + " --> " + command));
        } else if (feedback.equals(command.keyword)) {
            // A duplicate mapping was replaced
            return new CommandResult(String.format(MESSAGE_DUPLICATE, shortcutAlias + " --> " + command));
        } else {
            // A non-duplicate mapping was replaced
            model.commit();
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS, shortcutAlias + " --> " + command)
                            + "\n"
                            + String.format(MESSAGE_REPLACED, shortcutAlias + " --> " + feedback)

            );
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddShortcutCommand)) {
            return false;
        }

        AddShortcutCommand otherAddShortcutCommand = (AddShortcutCommand) other;
        return shortcutAlias.equals(otherAddShortcutCommand.shortcutAlias)
                && command.equals(otherAddShortcutCommand.command);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("shortcutAlias", shortcutAlias)
                .add("command", command)
                .toString();
    }
}
