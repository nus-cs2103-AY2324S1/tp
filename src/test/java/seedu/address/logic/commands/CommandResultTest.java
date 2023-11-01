package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", null)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different CommandType (HELP) -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.HELP)));

        // different CommandType (EXIT) -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.EXIT)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different CommandType (HELP) -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.HELP).hashCode());

        // different CommandType (EXIT) -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.EXIT).hashCode());
    }

    @Test
    public void toStringMethodWithoutPerson() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser()
                + ", person=" + commandResult.getPersonToView()
                + ", targetIndex=" + commandResult.getTargetIndex()
                + ", commandType=" + commandResult.getCommandType()
                + ", isFostererEdited=" + commandResult.getIsFostererEdited() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void toStringMethodWithPerson() {
        Person alice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();

        CommandResult commandResult = new CommandResult("feedback",
                alice,
                null,
                null,
                false
        );

        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser()
                + ", person=" + commandResult.getPersonToView()
                + ", targetIndex=" + commandResult.getTargetIndex()
                + ", commandType=" + commandResult.getCommandType()
                + ", isFostererEdited=" + commandResult.getIsFostererEdited() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
