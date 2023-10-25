package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class AddShortcutCommand extends Command {
    public static final String COMMAND_WORD = "alias";
    public static final String MESSAGE_SUCCESS = "New shortcut added: %1$s";
    public static final String MESSAGE_REPLACED = "Old shortcut %1$s was removed as a result.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Registers the shortcut to the command keyword for future use.\n"
            + "Parameters: SHORTCUT KEYWORD\n"
            + "Example: " + COMMAND_WORD + " "
            + "del "
            + DeleteCommand.COMMAND_WORD;
    private final String alias;
    private final String command;
    public AddShortcutCommand(String alias, String commandWord) {
        this.alias = alias;
        this.command = commandWord;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String feedback = model.getShortcutSettings().registerShortcut(alias, command);
        if (feedback == null) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, alias + " --> " + command));
        } else {
            return new CommandResult(
                    String.format(MESSAGE_SUCCESS, alias + " --> " + command)
                            + "\n"
                            + String.format(MESSAGE_REPLACED, alias + " --> " + feedback)

            );
        }

    }
}
