package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.course.Course;

public class ClearTeachCommand extends Command {

    public static final String COMMAND_WORD = "clearteach";
    public static final String MESSAGE_SUCCESS = "Default course has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the default course.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTeaching(Course.EMPTY_COURSE);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
