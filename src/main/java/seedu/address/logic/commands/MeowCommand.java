package seedu.address.logic.commands;

import seedu.address.model.Model;

public class MeowCommand extends Command {
    public static final String COMMAND_WORD = "meow";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Meow!";

    public static final String MESSAGE_SUCCESS = "Meow!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String toString() {
        return "Meow!";
    }
}
