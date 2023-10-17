package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_COUNT;

import seedu.address.model.Model;
import seedu.address.model.student.ClassDetails;

public class ConfigCommand extends Command {

    public static final String COMMAND_WORD = "config";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Configures Class Manager with the module information.\n"
            + "Parameters: "
            + PREFIX_TUTORIAL_COUNT + "TUTORIAL_COUNT "
            + PREFIX_ASSIGNMENT_COUNT + "ASSIGNMENT_COUNT\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_TUTORIAL_COUNT + "13 "
            + PREFIX_ASSIGNMENT_COUNT + "4";
    public static final String MESSAGE_CONFIG_SUCCESS = "Class Manager has been configured with the following "
            + "information:\n"
            + "Tutorial Count: %1$d\n"
            + "Assignment Count: %2$d\n";
    public static final String MESSAGE_CONFIG_FAILED = "Class Manager has failed to be configured.\n"
            + "Please try entering the config command again.\n";

    private final int tutorialCount;
    private final int assignmentCount;

    public ConfigCommand(int tutorialCount, int assignmentCount) {
        this.tutorialCount = tutorialCount;
        this.assignmentCount = assignmentCount;
    }

    /**
     * Configures {@code ClassDetails} with module information.
     * @param model {@code Model} which the command should operate on.
     * @return
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ClassDetails.TUTORIAL_COUNT = tutorialCount;
        ClassDetails.ASSIGNMENT_COUNT = assignmentCount;
        model.setConfigured();
        return new CommandResult(String.format(MESSAGE_CONFIG_SUCCESS, tutorialCount, assignmentCount));
    }
}
