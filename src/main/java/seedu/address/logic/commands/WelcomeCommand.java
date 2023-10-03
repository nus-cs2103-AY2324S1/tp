package seedu.address.logic.commands;

import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.address.model.Model;

/**
 * Changes the remark of an existing person in the address book.
 */
public class WelcomeCommand extends Command {

    public static final String COMMAND_WORD = "welcome";

    @Override
    public CommandResult execute(Model model) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String strDate = formatter.format(date);
        String welcomeMessage = "Hello from Linktree, Current date and time: " + strDate;
        return new CommandResult(welcomeMessage);
    }
}
