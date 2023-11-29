package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DENTIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALIZATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.testutil.EditDentistDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    // Input Fields - Appointment Specific
    public static final String INPUT_DENTIST_ID_APPOINTMENT = " " + PREFIX_DENTIST + "1";
    public static final String INPUT_INVALID_DENTIST_ID_APPOINTMENT = " " + PREFIX_DENTIST + "0L";
    public static final String INPUT_PATIENT_ID_APPOINTMENT = " " + PREFIX_PATIENT + "1";
    public static final String INPUT_INVALID_PATIENT_ID_APPOINTMENT = " " + PREFIX_PATIENT + "0L";
    public static final String INPUT_START_APPOINTMENT = " " + PREFIX_START + "2023-10-19 16:00";
    public static final String INPUT_INVALID_START_APPOINTMENT = " " + PREFIX_START + "2023-10-1916:00";

    public static final String INPUT_TREATMENT_APPOINTMENT = " " + PREFIX_TREATMENT + "Braces";

    // Valid Fields - Patient Specific
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_BIRTHDATE_BOB = "01-01-1990";
    public static final String VALID_REMARK_BOB = "Allergy to anaesthesia";
    public static final String VALID_TREATMENT_BOB = "Braces";


    // Valid Fields - Dentist Specific
    public static final String VALID_SPECIALIZATION_AMY = "ORTHODONTICS";
    public static final String VALID_SPECIALIZATION_BOB = "ENDODONTICS";
    public static final String VALID_YOE_AMY = "12";
    public static final String VALID_YOE_BOB = "4";
    public static final String VALID_DENTIST_ID_AMY = "1";
    public static final String VALID_DENTIST_ID_BOB = "2";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;

    // Input Fields - Dentist Specific
    public static final String SPECIALIZATION_DESC_AMY = " " + PREFIX_SPECIALIZATION + VALID_SPECIALIZATION_AMY;
    public static final String SPECIALIZATION_DESC_BOB = " " + PREFIX_SPECIALIZATION + VALID_SPECIALIZATION_BOB;
    public static final String YOE_DESC_AMY = " " + PREFIX_YOE + VALID_YOE_AMY;
    public static final String YOE_DESC_BOB = " " + PREFIX_YOE + VALID_YOE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses

    // Invalid Fields - Dentist Specific
    public static final String INVALID_SPECIALIZATION_DESC = " " + PREFIX_SPECIALIZATION + "GP&";
    public static final String INVALID_YOE_DESC = " " + PREFIX_YOE + "129037"; // YOE maximum 2 digits
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditDentistCommand.EditDentistDescriptor DESC_AMY_DENTIST;
    public static final EditDentistCommand.EditDentistDescriptor DESC_BOB_DENTIST;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    static {
        DESC_AMY_DENTIST = new EditDentistDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSpecialization(VALID_SPECIALIZATION_AMY)
                .withYoe(VALID_YOE_AMY)
                .withId(VALID_DENTIST_ID_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB_DENTIST = new EditDentistDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withSpecialization(VALID_SPECIALIZATION_BOB)
                .withYoe(VALID_YOE_BOB)
                .withId(VALID_DENTIST_ID_BOB)
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
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

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
     * Updates {@code model}'s filtered list to show only the person at the given {@code dentistId} in the
     * {@code model}'s address book.
     */
    public static void showDentistWithId(Model model, long dentistId) {
        assertTrue(dentistId < model.getFilteredDentistList().size());

        Dentist dentist = model.getDentistById(dentistId);
        final String[] splitName = dentist.getName().fullName.split("\\s+");
        model.updateFilteredDentistList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredDentistList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code patientId} in the
     * {@code model}'s address book.
     */
    public static void showPatientWithId(Model model, long patientId) {
        int zeroBasedIndex = (int) patientId - 1;

        assertTrue(zeroBasedIndex < model.getFilteredPatientList().size() && zeroBasedIndex >= 0);

        Predicate<Patient> patientIdPredicate = patient -> patient.getId() == patientId;
        model.updateFilteredPatientList(patientIdPredicate);

        assertEquals(1, model.getFilteredPatientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the appointment at the given {@code appointmentId} in the
     * {@code model}'s address book.
     */
    public static void showAppointmentWithId(Model model, long appointmentId) {
        int zeroBasedIndex = (int) appointmentId - 1;

        assertTrue(zeroBasedIndex < model.getFilteredAppointmentList().size() && zeroBasedIndex >= 0);

        Predicate<Appointment> appointmentIdPredicate = appointment -> appointment.getId() == appointmentId;
        model.updateFilteredAppointmentList(appointmentIdPredicate);
        assertEquals(1, model.getFilteredAppointmentList().size());
    }
}
