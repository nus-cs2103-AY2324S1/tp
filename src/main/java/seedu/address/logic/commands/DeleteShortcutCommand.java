package seedu.address.logic.commands;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;
public class DeleteShortcutCommand extends Command {

    public static final String COMMAND_WORD = "delsc";
    public static final String MESSAGE_SUCCESS = "Shortcut removed: %1$s";
    public static final String MESSAGE_NONEXISTENT = "This shortcut was not previously registered: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the previously registered shortcut(s).\n"
            + "Parameters: "
            + PREFIX_SHORTCUT + "SHORTCUT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SHORTCUT + "del "
            + PREFIX_SHORTCUT + "li ";

    private final List<ShortcutAlias> shortcutAliasList;
    public DeleteShortcutCommand(List<ShortcutAlias> shortcutAlias) {
        this.shortcutAliasList = shortcutAlias;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder message = new StringBuilder();
        shortcutAliasList.forEach(shortcutAlias -> {
            String feedback = model.getShortcutSettings().removeShortcut(shortcutAlias);
            if (feedback == null) {
                message.append(String.format(MESSAGE_NONEXISTENT, shortcutAlias)).append("\n");
            } else {
                message.append(String.format(MESSAGE_SUCCESS, shortcutAlias + " --> " + feedback)).append("\n");
            }
        });
       return new CommandResult(message.toString());
    }

}
