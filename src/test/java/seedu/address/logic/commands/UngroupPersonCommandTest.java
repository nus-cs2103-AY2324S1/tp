package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

public class UngroupPersonCommandTest {
    private static final String PERSON_NAME_ALEX_YEOH_EXAMPLE = "Alex Yeoh";
    private static final String PERSON_NAME_BERNICE_EXAMPLE = "Bernice Yu";
    private static final String GROUP_NAME_CS2103T_EXAMPLE = "CS2103T";
    private static final String GROUP_NAME_ST2334_EXAMPLE = "ST2334";
    private static final String PERSON_NAME_INCOMPLETE_EXAMPLE = "Alex";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void validName_validGroup_success() throws CommandException {
        Pair<Person, Group> pairToUngroup = model.ungroupPerson(PERSON_NAME_ALEX_YEOH_EXAMPLE,
                GROUP_NAME_CS2103T_EXAMPLE);
        Person personToUngroup = pairToUngroup.getKey();
        Group groupToUngroup = pairToUngroup.getValue();
        String expectedMessage = String.format(UngroupPersonCommand.MESSAGE_SUCCESS,
                personToUngroup.getName().fullName);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.ungroupPerson(personToUngroup.getName().fullName, groupToUngroup.getGroupName());

        UngroupPersonCommand ungroupPersonCommand = new UngroupPersonCommand(PERSON_NAME_ALEX_YEOH_EXAMPLE,
                GROUP_NAME_CS2103T_EXAMPLE);

        assertCommandSuccess(ungroupPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void invalidName_throwsCommandException() throws CommandException {
        UngroupPersonCommand ungroupPersonCommand =
                new UngroupPersonCommand(PERSON_NAME_INCOMPLETE_EXAMPLE, GROUP_NAME_CS2103T_EXAMPLE);

        assertCommandFailure(ungroupPersonCommand, model, Messages.MESSAGE_NO_PERSON_WITH_NAME_FOUND);
    }

    @Test
    public void equals() {
        UngroupPersonCommand ungroupPersonFirstCommand =
                new UngroupPersonCommand(PERSON_NAME_ALEX_YEOH_EXAMPLE, GROUP_NAME_CS2103T_EXAMPLE);
        UngroupPersonCommand ungroupPersonSecondCommand =
                new UngroupPersonCommand(PERSON_NAME_BERNICE_EXAMPLE, GROUP_NAME_CS2103T_EXAMPLE);
        UngroupPersonCommand ungroupPersonThirdCommand =
                new UngroupPersonCommand(PERSON_NAME_ALEX_YEOH_EXAMPLE, GROUP_NAME_ST2334_EXAMPLE);


        // same object -> returns true
        assertTrue(ungroupPersonFirstCommand.equals(ungroupPersonFirstCommand));

        // same values -> returns true
        UngroupPersonCommand ungroupPersonFirstCommandCopy =
                new UngroupPersonCommand(PERSON_NAME_ALEX_YEOH_EXAMPLE, GROUP_NAME_CS2103T_EXAMPLE);
        assertTrue(ungroupPersonFirstCommand.equals(ungroupPersonFirstCommandCopy));

        // different types -> returns false
        assertFalse(ungroupPersonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(ungroupPersonFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(ungroupPersonFirstCommand.equals(ungroupPersonSecondCommand));

        // different group -> returns false
        assertFalse(ungroupPersonFirstCommand.equals(ungroupPersonThirdCommand));
    }

    @Test
    public void toStringMethod() {
        UngroupPersonCommand ungroupPersonCommand = new UngroupPersonCommand(PERSON_NAME_ALEX_YEOH_EXAMPLE,
                GROUP_NAME_CS2103T_EXAMPLE);
        String expected = UngroupPersonCommand.class.getCanonicalName()
                + "{personName=" + PERSON_NAME_ALEX_YEOH_EXAMPLE + ", "
                + "groupName=" + GROUP_NAME_CS2103T_EXAMPLE + "}";
        assertEquals(expected, ungroupPersonCommand.toString());
    }
}
