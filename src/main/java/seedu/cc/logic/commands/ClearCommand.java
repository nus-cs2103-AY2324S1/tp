package seedu.cc.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.cc.model.ClinicBook;
import seedu.cc.model.Model;

/**
 * Clears the clinic book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Clinic book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClinicBook(new ClinicBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
