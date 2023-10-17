package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICAL_HISTORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SPECIALTY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MEDICAL_HISTORY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALTY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_THIRTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_DERMATOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICALHISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Specialty;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.SpecialistBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresentPatient_success() {
        Person expectedPatient = new PatientBuilder(AMY).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseComplexSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_FRIEND + AGE_DESC_AMY + MEDICAL_HISTORY_DESC_AMY,
                new AddCommand(expectedPatient), PersonType.PATIENT);

        // multiple tags - all accepted
        Person expectedPatientMultipleTags = new PatientBuilder(AMY).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseComplexSuccess(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + AGE_DESC_AMY + MEDICAL_HISTORY_DESC_AMY,
                new AddCommand(expectedPatientMultipleTags), PersonType.PATIENT);
    }
    @Test
    public void parse_allFieldsPresentSpecialist_success() {
        Person expectedSpecialist = new SpecialistBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseComplexSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + LOCATION_DESC_BOB + TAG_DESC_FRIEND + SPECIALTY_DESC_BOB,
                new AddCommand(expectedSpecialist),
                PersonType.SPECIALIST);

        // multiple tags - all accepted
        Person expectedSpecialistMultipleTags = new SpecialistBuilder(BOB).withTags(VALID_TAG_FRIEND,
                        VALID_TAG_HUSBAND)
                .build();
        assertParseComplexSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + LOCATION_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SPECIALTY_DESC_BOB,
                new AddCommand(expectedSpecialistMultipleTags), PersonType.SPECIALIST);
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + LOCATION_DESC_BOB + TAG_DESC_FRIEND;

        String validExpectedSpecialistString = validExpectedPersonString + SPECIALTY_DESC_BOB;

        String validExpectedPatientString = validExpectedPersonString + AGE_DESC_AMY + MEDICAL_HISTORY_DESC_AMY;

        // multiple names
        assertParseComplexFailure(parser, NAME_DESC_AMY + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME), PersonType.SPECIALIST);

        // multiple phones
        assertParseComplexFailure(parser, PHONE_DESC_AMY + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE), PersonType.SPECIALIST);

        // multiple emails
        assertParseComplexFailure(parser, EMAIL_DESC_AMY + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL), PersonType.SPECIALIST);

        // multiple addresses
        assertParseComplexFailure(parser, LOCATION_DESC_AMY + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION), PersonType.SPECIALIST);

        // multiple ages
        assertParseComplexFailure(parser, AGE_DESC_AMY + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE), PersonType.PATIENT);

        // multiple fields repeated
        assertParseComplexFailure(parser,
                validExpectedSpecialistString + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + NAME_DESC_AMY + LOCATION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_LOCATION,
                        PREFIX_EMAIL, PREFIX_PHONE),
                PersonType.SPECIALIST);

        // invalid value followed by valid value

        // invalid name
        assertParseComplexFailure(parser, INVALID_NAME_DESC + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME), PersonType.SPECIALIST);

        // invalid email
        assertParseComplexFailure(parser, INVALID_EMAIL_DESC + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL), PersonType.SPECIALIST);

        // invalid phone
        assertParseComplexFailure(parser, INVALID_PHONE_DESC + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE), PersonType.SPECIALIST);

        // invalid address
        assertParseComplexFailure(parser, INVALID_LOCATION_DESC + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION), PersonType.SPECIALIST);

        // invalid specialty
        assertParseComplexFailure(parser, INVALID_SPECIALTY_DESC + validExpectedSpecialistString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SPECIALTY), PersonType.SPECIALIST);

        // invalid medical history
        assertParseComplexFailure(parser, INVALID_MEDICAL_HISTORY_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MEDICALHISTORY), PersonType.PATIENT);

        // invalid age
        assertParseComplexFailure(parser, INVALID_AGE_DESC + validExpectedPatientString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE), PersonType.PATIENT);
        // valid value followed by invalid value

        // invalid name
        assertParseComplexFailure(parser, validExpectedSpecialistString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME), PersonType.SPECIALIST);

        // invalid email
        assertParseComplexFailure(parser, validExpectedSpecialistString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL), PersonType.SPECIALIST);

        // invalid phone
        assertParseComplexFailure(parser, validExpectedSpecialistString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE), PersonType.SPECIALIST);

        // invalid address
        assertParseComplexFailure(parser, validExpectedSpecialistString + INVALID_LOCATION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION), PersonType.SPECIALIST);

        // invalid specialty
        assertParseComplexFailure(parser, validExpectedSpecialistString + INVALID_SPECIALTY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SPECIALTY), PersonType.SPECIALIST);

        // invalid medical history
        assertParseComplexFailure(parser, validExpectedPatientString + INVALID_MEDICAL_HISTORY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MEDICALHISTORY), PersonType.PATIENT);

        // invalid age
        assertParseComplexFailure(parser, validExpectedPatientString + INVALID_AGE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AGE), PersonType.PATIENT);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PatientBuilder(AMY).withTags().build();
        assertParseComplexSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + AGE_DESC_AMY + MEDICAL_HISTORY_DESC_AMY,
                new AddCommand(expectedPerson), PersonType.PATIENT);
    }

    @Test
     public void parse_compulsoryFieldMissing_failure() {
        String expectedMessageSpecialist = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE_SPECIALIST);

        String expectedMessagePatient = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE_PATIENT);

        // missing name prefix
        assertParseComplexFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + LOCATION_DESC_BOB + SPECIALTY_DESC_BOB, expectedMessageSpecialist, PersonType.SPECIALIST);

        // missing phone prefix
        assertParseComplexFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                        + LOCATION_DESC_BOB + SPECIALTY_DESC_BOB, expectedMessageSpecialist, PersonType.SPECIALIST);

        // missing email prefix
        assertParseComplexFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                        + LOCATION_DESC_BOB + SPECIALTY_DESC_BOB, expectedMessageSpecialist, PersonType.SPECIALIST);

        // missing address prefix
        assertParseComplexFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + VALID_LOCATION_BOB + SPECIALTY_DESC_BOB, expectedMessageSpecialist, PersonType.SPECIALIST);

        // missing specialty prefix
        assertParseComplexFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + LOCATION_DESC_BOB + VALID_SPECIALTY_DERMATOLOGY, expectedMessageSpecialist, PersonType.SPECIALIST);

        // missing age prefix
        assertParseComplexFailure(parser, VALID_AGE_THIRTY + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + LOCATION_DESC_AMY, expectedMessagePatient, PersonType.PATIENT);

        // all prefixes missing
        assertParseComplexFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_LOCATION_BOB, expectedMessagePatient, PersonType.PATIENT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseComplexFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SPECIALTY_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS, PersonType.SPECIALIST);

        // invalid phone
        assertParseComplexFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                        + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SPECIALTY_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS, PersonType.SPECIALIST);

        // invalid email
        assertParseComplexFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SPECIALTY_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS, PersonType.SPECIALIST);

        // invalid address
        assertParseComplexFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_LOCATION_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SPECIALTY_DESC_BOB,
                Location.MESSAGE_CONSTRAINTS, PersonType.SPECIALIST);

        // invalid tag
        assertParseComplexFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + LOCATION_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND + SPECIALTY_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS, PersonType.SPECIALIST);

        // invalid specialty
        assertParseComplexFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_SPECIALTY_DESC,
                Specialty.MESSAGE_CONSTRAINTS, PersonType.SPECIALIST);

        // invalid age
        assertParseComplexFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_AGE_DESC
                        + MEDICAL_HISTORY_DESC_AMY,
                Age.MESSAGE_CONSTRAINTS, PersonType.PATIENT);

        // invalid age
        assertParseComplexFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + AGE_DESC_AMY
                        + INVALID_MEDICAL_HISTORY_DESC,
                MedicalHistory.MESSAGE_CONSTRAINTS, PersonType.PATIENT);

        // two invalid values, only first invalid value reported
        assertParseComplexFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_LOCATION_DESC + SPECIALTY_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS, PersonType.SPECIALIST);

        // non-empty preamble
        assertParseComplexFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + LOCATION_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                        + SPECIALTY_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_SPECIALIST),
                PersonType.SPECIALIST);
    }
}
