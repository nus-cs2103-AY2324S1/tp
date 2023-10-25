package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

/**
 * Adds the default course for users in the address book.
 */
public class TeachCommand extends Command {
    public static final String COMMAND_WORD = "teach";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets up default module users are teaching.\n"
            + "Parameters: "
            + PREFIX_COURSE + "MODULE \n"
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_COURSE + "CS1231S ";

    public static final String MESSAGE_SUCCESS = "Default module successfully added.";

    /**
     * Sets module as the default.
     */
    private final Course module;

    /**
     * Creates TeachCommand object.
     * @param module
     */
    public TeachCommand(Course module) {
        requireNonNull(module);
        this.module = module;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setTeaching(module);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
