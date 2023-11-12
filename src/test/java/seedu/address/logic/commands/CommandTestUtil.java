package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.CliSyntax.PATIENT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.SPECIALIST_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.EditSpecialistDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_LOCATION_AMY = "Block 312, Amy Street 1";
    public static final String VALID_LOCATION_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_AGE_THIRTY = "30";
    public static final String VALID_MEDICAL_HISTORY_OSTEOPOROSIS = "Osteoporosis";
    public static final String VALID_MEDICAL_HISTORY_ANEMIA = "Anemia";
    public static final String VALID_SPECIALTY_DERMATOLOGY = "Dermatology";
    public static final String VALID_SPECIALTY_ORTHOPAEDIC = "orthopaedic";

    public static final String PERSON_TYPE_AMY = " " + PATIENT_TAG;
    public static final String PERSON_TYPE_BOB = " " + SPECIALIST_TAG;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String LOCATION_DESC_AMY = " " + PREFIX_LOCATION + VALID_LOCATION_AMY;
    public static final String LOCATION_DESC_BOB = " " + PREFIX_LOCATION + VALID_LOCATION_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_THIRTY;
    public static final String MEDICAL_HISTORY_DESC_AMY = " " + PREFIX_MEDICALHISTORY
            + VALID_MEDICAL_HISTORY_ANEMIA;
    public static final String SPECIALTY_DESC_BOB = " " + PREFIX_SPECIALTY + VALID_SPECIALTY_DERMATOLOGY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // empty string not allowed for addresses
    public static final String INVALID_SPECIALTY_DESC = " " + PREFIX_SPECIALTY; // empty string invalid for specialty
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "67Y"; // 'Y' not allowed in ages
    public static final String INVALID_MEDICAL_HISTORY_DESC = " "
            + PREFIX_MEDICALHISTORY; // empty string invalid for medical history

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPatientDescriptor DESC_AMY;
    public static final EditCommand.EditSpecialistDescriptor DESC_BOB;

    public static final ShortcutAlias SHORTCUT_ALIAS_1 = new ShortcutAlias("del");
    public static final ShortcutAlias SHORTCUT_ALIAS_2 = new ShortcutAlias("li");
    public static final CommandWord COMMAND_WORD_1 = new CommandWord(DeleteCommand.COMMAND_WORD);
    public static final CommandWord COMMAND_WORD_2 = new CommandWord(ListCommand.COMMAND_WORD);

    public static final String SHORTCUT_DESC_VALID = " " + PREFIX_SHORTCUT + SHORTCUT_ALIAS_1;
    public static final String SHORTCUT_DESC_INVALID = " " + PREFIX_SHORTCUT + COMMAND_WORD_1;
    public static final String COMMANDWORD_DESC_VALID = " " + PREFIX_COMMAND_WORD + COMMAND_WORD_1;
    public static final String COMMANDWORD_DESC_INVALID = " " + PREFIX_COMMAND_WORD + SHORTCUT_ALIAS_1;


    static {
        DESC_AMY = (EditCommand.EditPatientDescriptor) new EditPatientDescriptorBuilder()
                .withMedicalHistory(VALID_MEDICAL_HISTORY_ANEMIA)
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = (EditCommand.EditSpecialistDescriptor) new EditSpecialistDescriptorBuilder()
                .withLocation(VALID_LOCATION_BOB)
                .withSpecialty(VALID_SPECIALTY_DERMATOLOGY)
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
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
    @SuppressWarnings("checkstyle:EmptyCatchBlock")
    public static void assertCommandFailure(Command command, Model actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Executes the given {@code input} from {@code logicManager}, confirms that <br>
     * actual command result and {@code expectedCommandResult} are equal.
     * @param logicManager
     * @param input
     * @param expectedException
     */
    public static void assertExceptionExecutedFromLogicManager(LogicManager logicManager, String input,
                                                                  Exception expectedException) {
        CommandResult commandResult;
        try {
            commandResult = logicManager.execute(input);
        } catch (Exception e) {
            assertEquals(expectedException, e);
            return;
        }

        fail("Expected an exception but no exception was thrown. Command result : " + commandResult);
    }

    /**
     * Executes the given {@code input} from {@code logicManager}, confirms that <br>
     * actual command result and {@code expectedCommandResult} are equal.
     * @param logicManager
     * @param input
     * @param expectedCommandResult
     */
    public static void assertCommandResultExecutedFromLogicManager(LogicManager logicManager, String input,
                                                               CommandResult expectedCommandResult) {
        CommandResult commandResult;
        try {
            commandResult = logicManager.execute(input);
        } catch (Exception e) {
            fail("Expected CommandResult but exception was thrown : " + e + " : " + e.getMessage());
            return;
        }
        assertEquals(expectedCommandResult, commandResult);
    }
}
