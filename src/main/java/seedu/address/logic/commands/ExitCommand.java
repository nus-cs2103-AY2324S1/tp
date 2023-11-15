package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting BayMeds v.2103 as requested ...";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits application.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, COMMAND_WORD);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExitCommand;
    }

}
