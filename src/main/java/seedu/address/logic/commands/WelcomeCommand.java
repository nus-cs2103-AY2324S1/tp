package seedu.address.logic.commands;

import seedu.address.model.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Changes the remark of an existing person in the address book.
 */
public class WelcomeCommand extends Command {

    public static final String COMMAND_WORD = "welcome";

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String strDate = formatter.format(date);
    String welcomeMessage = "Hello from Linktree, Current date and time: " + strDate;
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(welcomeMessage);
    }
}
