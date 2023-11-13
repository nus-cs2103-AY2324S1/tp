package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_COMPLETE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.CompleteCommandParser;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class CompleteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CompleteCommandParser commandParser = new CompleteCommandParser();

    @Test
    public void testCompleteByIndexSubclass() {
        //valid Index
        Person editedPerson = new PersonBuilder(BENSON).withNullAppointment().build();
        String expectedMessage = MESSAGE_COMPLETE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), editedPerson);

        CompleteCommand completeCommand = new CompleteByIndex(INDEX_SECOND_PERSON);

        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void testCompleteByDateSubclass() {
        Person editedPerson1 = new PersonBuilder(BENSON).withNullAppointment().build();
        Person editedPerson2 = new PersonBuilder(CARL).withNullAppointment().build();
        String expectedMessage = MESSAGE_COMPLETE_SUCCESS;

        LocalDate validDate = LocalDate.of(2023, 05, 01);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), editedPerson1);
        expectedModel.setPerson(model.getFilteredPersonList().get(2), editedPerson2);

        CompleteCommand completeCommand = new CompleteByDate(validDate);

        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void equals() {
        LocalDate validDate = LocalDate.of(2023, 05, 01);

        CompleteCommand completeCommandByIndex = new CompleteByIndex(INDEX_FIRST_PERSON);
        CompleteCommand completeCommandByDate = new CompleteByDate(validDate);

        //different commands
        assertFalse(completeCommandByIndex.equals(completeCommandByDate));

        //same objects
        assertTrue(completeCommandByIndex.equals(completeCommandByIndex));
        assertTrue(completeCommandByIndex.equals(new CompleteByIndex(INDEX_FIRST_PERSON)));

        assertTrue(completeCommandByDate.equals(completeCommandByDate));
        assertTrue(completeCommandByDate.equals(new CompleteByDate(validDate)));
    }
}
