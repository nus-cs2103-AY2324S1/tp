package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
public class GoalCommandTest {
    @Test
    public void execute_validGoal_success() throws CommandException {
        Model model = new ModelManager();
        GoalCommand goalCommand = new GoalCommand(5);
        CommandResult commandResult = goalCommand.execute(model);
        assertEquals(GoalCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(5, model.getGoal().getTarget());
    }

    @Test
    public void execute_negativeGoal_throwsCommandException() {
        Model model = new ModelManager();
        GoalCommand goalCommand = new GoalCommand(-1);
        assertThrows(CommandException.class, () -> goalCommand.execute(model));
    }

    @Test
    public void equals() {
        GoalCommand goalCommand = new GoalCommand(5);
        GoalCommand sameGoalCommand = new GoalCommand(5);
        GoalCommand differentGoalCommand = new GoalCommand(10);

        assertEquals(goalCommand, goalCommand); // Same object
        assertEquals(goalCommand, sameGoalCommand); // Same values
        assertEquals(goalCommand, new GoalCommand(5)); // Equal objects should have equal hash codes
        assertNotEquals(goalCommand, differentGoalCommand);
        assertNotEquals(goalCommand, null);
    }
}
