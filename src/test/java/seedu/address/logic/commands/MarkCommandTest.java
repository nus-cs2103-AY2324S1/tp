package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.attendance.AttendanceType;


class MarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private List<String> names = new ArrayList<>();
    @Test
    public void execute_markByName_validName() throws CommandException {
        AttendanceType attendanceType = AttendanceType.PRESENT;
        NameContainsKeywordsPredicate predicate = preparePredicate("Alice");
        MarkCommand markCommand = new MarkCommand(predicate, attendanceType);

        Person markedPerson = model.getFilteredPersonList().get(0);

        String expectedMessage = String.format(markCommand.MESSAGE_MARK_PERSON_SUCCESS,
                attendanceType.toString().toLowerCase(), markedPerson.getName());

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markByIndex_validIndex() throws CommandException {
        AttendanceType attendanceType = AttendanceType.PRESENT;

        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, attendanceType);

        Person markedPerson = model.getFilteredPersonList().get(0);

        String expectedMessage = String.format(markCommand.MESSAGE_MARK_PERSON_SUCCESS,
                attendanceType.toString().toLowerCase(), markedPerson.getName());

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        AttendanceType attendanceType = AttendanceType.PRESENT;
        AttendanceType attendanceType1 = AttendanceType.ABSENT;

        MarkCommand markFirstCommand = new MarkCommand(firstPredicate, attendanceType);
        MarkCommand markSecondCommand = new MarkCommand(secondPredicate, attendanceType);
        MarkCommand markThirdCommand = new MarkCommand(secondPredicate, attendanceType1);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same predicates & attendance type -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(firstPredicate, attendanceType);
        assertTrue(markFirstCommandCopy.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // same attendance type, different predicate -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));

        // same predicate, different attendance type -> returns false
        // assertFalse(markSecondCommand.equals(markThirdCommand));

        // different predicate, different attendance type -> returns false
        assertFalse(markFirstCommand.equals(markThirdCommand));
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        AttendanceType attendanceType = AttendanceType.PRESENT;
        MarkCommand markCommand = new MarkCommand(predicate, attendanceType);
        String expected = MarkCommand.class.getCanonicalName() + "{targetName=" + predicate + "}";
        assertEquals(expected, markCommand.toString());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
