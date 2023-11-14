package seedu.codesphere.logic.commands;

import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.logic.stagemanager.Stages;
import seedu.codesphere.model.Model;

/**
 * Represents a command to return to the home page in the application.
 * It allows users to navigate back to the main home screen that shows a list of courses.
 */
public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": returns back to the home page\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Returned to home page.";

    public static final String MESSAGE_HOME_ALREADY = "You are already at the home page.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StageManager currStageManager = StageManager.getInstance();

        if (currStageManager.getStage() == Stages.HOME) {
            return new CommandResult(MESSAGE_HOME_ALREADY);
        }

        currStageManager.getSelectedCourse().resetFilteredStudentList();
        currStageManager.setHomeStage();

        return new CommandResult(MESSAGE_SUCCESS, Stages.HOME);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        return other instanceof HomeCommand;
    }
}
