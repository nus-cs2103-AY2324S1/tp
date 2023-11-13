package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a shortcut from the address book.
 */
public class DeleteShortcutCommand extends Command {

    public static final String COMMAND_WORD = "delsc";
    public static final String MESSAGE_SUCCESS = "Shortcut removed: %1$s\n";
    public static final String MESSAGE_NONEXISTENT = "This shortcut was not previously registered: %1$s\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the previously registered shortcut(s).\n"
            + "Parameters: "
            + PREFIX_SHORTCUT + "SHORTCUT...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SHORTCUT + "del "
            + PREFIX_SHORTCUT + "li ";

    private final List<ShortcutAlias> shortcutAliasList;

    /**
     * Creates a DeleteShortcutCommand to remove the specified shortcut mapping.
     */
    public DeleteShortcutCommand(List<ShortcutAlias> shortcutAlias) {
        requireNonNull(shortcutAlias);
        this.shortcutAliasList = shortcutAlias;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder message = new StringBuilder();
        shortcutAliasList.forEach(shortcutAlias -> {
            String feedback = model.removeShortcut(shortcutAlias);
            if (feedback == null) {
                message.append(String.format(MESSAGE_NONEXISTENT, shortcutAlias));
            } else {
                message.append(String.format(MESSAGE_SUCCESS, shortcutAlias + " --> " + feedback));
            }
        });
        model.commit();
        return new CommandResult(message.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteShortcutCommand)) {
            return false;
        }

        DeleteShortcutCommand otherDeleteShortcutCommand = (DeleteShortcutCommand) other;
        return shortcutAliasList.equals(otherDeleteShortcutCommand.shortcutAliasList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("shortcutAliasList", shortcutAliasList)
                .toString();
    }
}
