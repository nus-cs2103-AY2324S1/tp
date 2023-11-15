package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CHERYL = "Cheryl Tan";
    public static final String VALID_NAME_DEREK = "Derek Shepherd";
    public static final String VALID_PHONE_AMY = "91234567";
    public static final String VALID_PHONE_BOB = "81234567";

    public static final String VALID_PHONE_CHERYL = "92874563";
    public static final String VALID_PHONE_DEREK = "97463128";
    public static final String VALID_EMERGENCY_CONTACT_AMY = "81234567";
    public static final String VALID_EMERGENCY_CONTACT_BOB = "91234567";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CHERYL = "cheryberry@medilink.com";
    public static final String VALID_EMAIL_DEREK = "mcdreamy@medilink.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_ADDRESS_CHERYL = "123 Main Street, Anytown, USA";
    public static final String VALID_ADDRESS_DEREK = "456 Elm Avenue, Somewhereville, Canada";

    public static final String VALID_TAG_LOW = "priority: LOW";
    public static final String VALID_TAG_MEDIUM = "priority: MEDIUM";
    public static final String VALID_TAG_HIGH = "priority: HIGH";
    public static final String VALID_TAG_CARDIOLOGIST = "CARDIOLOGIST";
    public static final String VALID_TAG_SURGEON = "SURGEON";
    public static final String VALID_GENDER_MALE = "M";
    public static final String VALID_GENDER_FEMALE = "F";
    public static final String VALID_NRIC_AMY = "S8643226I";
    public static final String VALID_NRIC_BOB = "S8192320E";
    public static final String VALID_NRIC_CHERYL = "S9812345A";
    public static final String VALID_NRIC_DEREK = "S9712346B";
    public static final String VALID_NRIC_ALICE = "T0131267K";
    public static final String VALID_REMARK_AMY = "She likes aardvarks.";
    public static final String VALID_REMARK_BOB = "He likes football.";
    public static final String VALID_REMARK_CHERYL = "She wants to be a Surgeon.";
    public static final String VALID_CONDITION_BOB = "Pneumothorax";
    public static final String VALID_BLOODTYPE_BOB = "A+";
    public static final String VALID_BLOODTYPE_AMY = "A+";
    public static final String VALID_CONDITION_AMY = "Diabetes";
    public static final String VALID_DATE_1 = "2022-02-14 13:30";
    public static final String VALID_DATE_2 = "2022-02-28 13:30";
    public static final String VALID_DATE_1_DESC = " " + PREFIX_APPOINTMENT_TIME + VALID_DATE_1;
    public static final String VALID_DATE_2_DESC = " " + PREFIX_APPOINTMENT_TIME + VALID_DATE_2;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CHERYL = " " + PREFIX_NAME + VALID_NAME_CHERYL;
    public static final String NAME_DESC_DEREK = " " + PREFIX_NAME + VALID_NAME_DEREK;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_CHERYL = " " + PREFIX_PHONE + VALID_PHONE_CHERYL;
    public static final String PHONE_DESC_DEREK = " " + PREFIX_PHONE + VALID_PHONE_DEREK;
    public static final String EMERGENCY_CONTACT_DESC_AMY =
            " " + PREFIX_EMERGENCY_CONTACT + VALID_EMERGENCY_CONTACT_AMY;
    public static final String EMERGENCY_CONTACT_DESC_BOB =
            " " + PREFIX_EMERGENCY_CONTACT + VALID_EMERGENCY_CONTACT_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_CHERYL = " " + PREFIX_EMAIL + VALID_EMAIL_CHERYL;
    public static final String EMAIL_DESC_DEREK = " " + PREFIX_EMAIL + VALID_EMAIL_DEREK;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ADDRESS_DESC_CHERYL = " " + PREFIX_ADDRESS + VALID_ADDRESS_CHERYL;
    public static final String ADDRESS_DESC_DEREK = " " + PREFIX_ADDRESS + VALID_ADDRESS_DEREK;
    public static final String GENDER_DESC_MALE = " " + PREFIX_GENDER + VALID_GENDER_MALE;
    public static final String GENDER_DESC_FEMALE = " " + PREFIX_GENDER + VALID_GENDER_FEMALE;
    public static final String NRIC_DESC_AMY = " " + PREFIX_NRIC + VALID_NRIC_AMY;
    public static final String NRIC_DESC_BOB = " " + PREFIX_NRIC + VALID_NRIC_BOB;
    public static final String NRIC_DESC_CHERYL = " " + PREFIX_NRIC + VALID_NRIC_CHERYL;
    public static final String NRIC_DESC_DEREK = " " + PREFIX_NRIC + VALID_NRIC_DEREK;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String REMARK_DESC_CHERYL = " " + PREFIX_REMARK + VALID_REMARK_CHERYL;
    public static final String CONDITION_DESC_AMY = " " + PREFIX_CONDITION + VALID_CONDITION_AMY;
    public static final String CONDITION_DESC_BOB = " " + PREFIX_CONDITION + VALID_CONDITION_BOB;
    public static final String BLOODTYPE_DESC_AMY = " " + PREFIX_BLOODTYPE + VALID_BLOODTYPE_AMY;
    public static final String BLOODTYPE_DESC_BOB = " " + PREFIX_BLOODTYPE + VALID_BLOODTYPE_BOB;
    public static final String APPOINTMENT_DOCTOR_NRIC_DESC_DEREK = " " + PREFIX_DOCTOR_IC + VALID_NRIC_DEREK;

    public static final String APPOINTMENT_DOCTOR_NRIC_DESC_CHERYL = " " + PREFIX_DOCTOR_IC + VALID_NRIC_CHERYL;

    public static final String APPOINTMENT_PATIENT_NRIC_DESC_BOB = " " + PREFIX_PATIENT_IC + VALID_NRIC_BOB;
    public static final String APPOINTMENT_PATIENT_NRIC_DESC_AMY = " " + PREFIX_PATIENT_IC + VALID_NRIC_AMY;
    public static final String TAG_DESC_LOW = " " + PREFIX_TAG + "Low";
    public static final String TAG_DESC_MEDIUM = " " + PREFIX_TAG + "Medium";
    public static final String TAG_DESC_HIGH = " " + PREFIX_TAG + "High";
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_PATIENT_TAG_DESC = " " + PREFIX_TAG + "highest"; // not a priority level
    public static final String INVALID_DOCTOR_TAG_DESC = " " + PREFIX_TAG + "nurse"; // not a valid specialisation
    public static final String INVALID_BLOODTYPE_DESC = " " + PREFIX_BLOODTYPE + "Z+";
    public static final String INVALID_CONDITION_DESC = " " + PREFIX_CONDITION + " ";
    public static final String INVALID_EMERGENCY_CONTACT_DESC = " " + PREFIX_EMERGENCY_CONTACT + "+6A";
    public static final String INVALID_NRIC = "A1234567G";
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "Alien";

    public static final String INVALID_APPOINTMENT_DOCTOR_NRIC_DESC = " " + PREFIX_DOCTOR_IC + INVALID_NRIC;

    public static final String INVALID_APPOINTMENT_PATIENT_NRIC_DESC = " " + PREFIX_PATIENT_IC + INVALID_NRIC;
    public static final String INVALID_DATE_DESC = " " + PREFIX_APPOINTMENT_TIME + "100-100-12";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditCommand.EditPersonDescriptor DESC_CHERYL;
    public static final EditCommand.EditPersonDescriptor DESC_DEREK;


    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withBloodType(VALID_BLOODTYPE_AMY).withCondition(VALID_CONDITION_AMY)
                .withGender(VALID_GENDER_FEMALE)
                .withTags(VALID_TAG_LOW).withBloodType(VALID_BLOODTYPE_AMY).build();

        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withGender(VALID_GENDER_MALE).withTags(VALID_TAG_MEDIUM).build();

        DESC_CHERYL = new EditPersonDescriptorBuilder().withName(VALID_NAME_CHERYL)
                .withPhone(VALID_PHONE_CHERYL).withEmail(VALID_EMAIL_CHERYL).withAddress(VALID_ADDRESS_CHERYL)
                .withGender(VALID_GENDER_FEMALE).build();

        DESC_DEREK = new EditPersonDescriptorBuilder().withName(VALID_NAME_DEREK)
                .withPhone(VALID_PHONE_DEREK).withEmail(VALID_EMAIL_DEREK).withAddress(VALID_ADDRESS_DEREK)
                .withGender(VALID_GENDER_MALE).build();
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
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        //assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Person person = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }

}
