package seedu.address.logic.commands;


import seedu.address.model.Model2;

/**
 * Terminates the program.
 */
public class ExitCommand2 extends Command2 {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting lesSON as requested ...";

    @Override
    public CommandResult execute(Model2 model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
