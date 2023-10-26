package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.CommandResult;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Represents the result of a command execution.
 */
public class CommandResultTest {

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("some result");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("some result")));
        assertTrue(commandResult.equals(new CommandResult("some result"
                , false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("some other result")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("some result"
                , true, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("some result", false
                , true, false)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("some result");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("some result").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("some other result").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("some result", true
                , false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("some result", false
                , true, false).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
//
//    private final String feedbackToUser;
//
//    /** Help information should be shown to the user. */
//    private final boolean showHelp;
//
//    /** The application should exit. */
//    private final boolean exit;
//
//    /** The application should switch theme. */
//    private final boolean switchTheme;
//
//    /**
//     * Constructs a {@code CommandResultTest} with the specified fields.
//     */
//    public CommandResultTest(String feedbackToUser, boolean showHelp, boolean exit, boolean switchTheme) {
//        this.feedbackToUser = requireNonNull(feedbackToUser);
//        this.showHelp = showHelp;
//        this.exit = exit;
//        this.switchTheme = switchTheme;
//    }
//
//    /**
//     * Constructs a {@code CommandResultTest} with the specified {@code feedbackToUser},
//     * and other fields set to their default value.
//     */
//    public CommandResultTest(String feedbackToUser) {
//        this(feedbackToUser, false, false, false);
//    }
//
//    public String getFeedbackToUser() {
//        return feedbackToUser;
//    }
//
//    public boolean isShowHelp() {
//        return showHelp;
//    }
//
//    public boolean isExit() {
//        return exit;
//    }
//
//    public boolean isSwitchTheme() {
//        return switchTheme;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof CommandResultTest)) {
//            return false;
//        }
//
//        CommandResultTest otherCommandResultTest = (CommandResultTest) other;
//        return feedbackToUser.equals(otherCommandResultTest.feedbackToUser)
//                && showHelp == otherCommandResultTest.showHelp
//                && exit == otherCommandResultTest.exit;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(feedbackToUser, showHelp, exit);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("feedbackToUser", feedbackToUser)
//                .add("showHelp", showHelp)
//                .add("exit", exit)
//                .toString();
//    }

}
