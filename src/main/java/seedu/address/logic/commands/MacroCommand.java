package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
/**
 * Command as a combination of multiple commands
 */
public class MacroCommand extends Command {
    private final Command[] commands;
    /**
     * Creates a Command as a combination of multiple commands
     */
    public MacroCommand(Command... commands) {
        assert commands.length > 0;
        this.commands = commands;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        StringBuilder result = new StringBuilder();
        String commandName = "";
        CommandResult temp;
        try {
            for (Command command : commands) {
                commandName = command.getClass().getSimpleName();
                temp = command.execute(model);
                if (!temp.getFeedbackToUser().isEmpty()) {
                    result.append(commandName).append(": ").append(temp.getFeedbackToUser()).append("\n");
                }
            }
        } catch (CommandException e) {
            throw new CommandException(result + commandName + " failed: " + e.getMessage());
        }
        return new CommandResult(result.toString());
    }
}
