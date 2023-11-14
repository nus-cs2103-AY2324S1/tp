package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ID_AMY = "A000010";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_ID_BOB = "A000011";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_DEPARTMENT_AMY = "Finance";
    public static final String VALID_DEPARTMENT_BOB = "Sales";
    public static final String VALID_ROLE_AMY = "Intern";
    public static final String VALID_ROLE_BOB = "Admin Staff";
    public static final String VALID_SALARY_AMY = "1000";
    public static final String VALID_SALARY_BOB = "2000";

    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String DEPARTMENT_DESC_AMY = " " + PREFIX_DEPARTMENT + VALID_DEPARTMENT_AMY;
    public static final String DEPARTMENT_DESC_BOB = " " + PREFIX_DEPARTMENT + VALID_DEPARTMENT_BOB;
    public static final String ROLE_DESC_AMY = " " + PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String INVALID_ID_DESC = " " + PREFIX_ID + "#000000"; // '#' not allowed in IDs
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_DEPARTMENT_DESC = " " + PREFIX_DEPARTMENT + "Finance&"; // '&' not allowed
    // in departments
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + "M@nager"; // '@' not allowed in roles
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY + "-5000"; // negative numbers not allowed in
    // salary

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withId(VALID_ID_AMY).withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withDepartment(VALID_DEPARTMENT_AMY).withRole(VALID_ROLE_AMY)
                .withSalary(VALID_SALARY_AMY)
                .build();
        DESC_BOB = new EditPersonDescriptorBuilder().withId(VALID_ID_BOB).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withDepartment(VALID_DEPARTMENT_BOB).withRole(VALID_ROLE_BOB)
                .withSalary(VALID_SALARY_BOB)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, new CommandHistory());
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command} with the {@code CommandHistory}, confirms that <br>
     * - the returned {@link CommandResult} matches a string {@code expectedCommandResult} <br>
     */
    public static void assertCommandSuccess(Command command, CommandHistory commandHistory,
                                            String expectedMessage) {
        try {
            CommandResult result = command.execute(new ModelManager(), commandHistory);
            CommandResult expectedCommandResult = new CommandResult(expectedMessage);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getSortedFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, new CommandHistory()));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getSortedFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getSortedFilteredPersonList().size());

        Person person = model.getSortedFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getSortedFilteredPersonList().size());
    }

    /**
     * Deletes the first employee from the {@code model}'s filtered and sorted list if there are employees' data
     * in EmployeeManager.
     */
    public static void deleteFirstPerson(Model model) {
        assertTrue(model.getSortedFilteredPersonList().size() > 0);
        Person personToDelete = model.getSortedFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(personToDelete);
        model.commitAddressBook();
    }
}
