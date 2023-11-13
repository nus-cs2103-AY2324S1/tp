package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_BLANK_ARGUMENTS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SPECIALTY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_DERMATOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditSpecialistDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String NAME_EMPTY = " " + PREFIX_NAME;
    private static final String PHONE_EMPTY = " " + PREFIX_PHONE;
    private static final String AGE_EMPTY = " " + PREFIX_AGE;
    private static final String LOCATION_EMPTY = " " + PREFIX_LOCATION;


    private static final String MESSAGE_INVALID_SPECIALIST =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_SPECIALIST);

    private static final String MESSAGE_INVALID_PATIENT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_PATIENT);

    private EditCommandParser parser = new EditCommandParser();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseComplexFailure(parser, "",
                EditCommand.MESSAGE_NOT_EDITED + "\n" + EditCommand.MESSAGE_USAGE_PATIENT, PersonType.PATIENT);
        assertParseComplexFailure(parser, "",
                EditCommand.MESSAGE_NOT_EDITED + "\n" + EditCommand.MESSAGE_USAGE_SPECIALIST, PersonType.SPECIALIST);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseComplexFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_SPECIALIST, PersonType.SPECIALIST);

        // zero index
        assertParseComplexFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_SPECIALIST, PersonType.SPECIALIST);

        // invalid arguments being parsed as preamble
        assertParseComplexFailure(parser, "1 some random string", MESSAGE_INVALID_SPECIALIST, PersonType.SPECIALIST);

        // invalid prefix being parsed as preamble
        assertParseComplexFailure(parser, "1 i/ string", MESSAGE_INVALID_SPECIALIST, PersonType.SPECIALIST);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseComplexFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS,
                PersonType.PATIENT); // invalid name
        assertParseComplexFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS,
                PersonType.PATIENT); // invalid phone
        assertParseComplexFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS,
                PersonType.PATIENT); // invalid email
        assertParseComplexFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS,
                PersonType.PATIENT); // invalid tag

        // invalid phone followed by valid email
        assertParseComplexFailure(parser, INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS,
                PersonType.PATIENT);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseComplexFailure(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS, PersonType.PATIENT);
        assertParseComplexFailure(parser, TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS, PersonType.PATIENT);
        assertParseComplexFailure(parser, TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS, PersonType.PATIENT);

        // multiple invalid values, but only the first invalid value is captured
        assertParseComplexFailure(parser,
                INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS, PersonType.PATIENT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + LOCATION_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND + SPECIALTY_DESC_BOB;

        EditPersonDescriptor descriptor = new EditSpecialistDescriptorBuilder()
                .withSpecialty(VALID_SPECIALTY_DERMATOLOGY).withLocation(VALID_LOCATION_AMY)
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseComplexSuccess(parser, userInput, expectedCommand, PersonType.SPECIALIST);
    }

    @Test
    public void parse_somePersonFieldsSpecified_success() {
        // Specialists are used in this test to check Person Fields.
        String userInputArgs = PHONE_DESC_BOB + EMAIL_DESC_BOB;
        EditPersonDescriptor descriptor = new EditCommand.EditSpecialistDescriptor();
        descriptor.setPhone(new Phone(VALID_PHONE_BOB));
        descriptor.setEmail(new Email(VALID_EMAIL_BOB));
        EditCommand expectedCommand = new EditCommand(descriptor);

        assertParseComplexSuccess(parser, userInputArgs, expectedCommand, PersonType.SPECIALIST);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditCommand.EditPatientDescriptor();
        descriptor.setName(new Name(VALID_NAME_AMY));
        EditCommand expectedCommand = new EditCommand(descriptor);
        assertParseComplexSuccess(parser, userInput, expectedCommand, PersonType.PATIENT);

        // phone
        userInput = PHONE_DESC_AMY;
        descriptor = new EditCommand.EditPatientDescriptor();
        descriptor.setPhone(new Phone(VALID_PHONE_AMY));
        expectedCommand = new EditCommand(descriptor);
        assertParseComplexSuccess(parser, userInput, expectedCommand, PersonType.PATIENT);

        // email
        userInput = EMAIL_DESC_AMY;
        descriptor = new EditCommand.EditPatientDescriptor();
        descriptor.setEmail(new Email(VALID_EMAIL_AMY));
        expectedCommand = new EditCommand(descriptor);
        assertParseComplexSuccess(parser, userInput, expectedCommand, PersonType.PATIENT);

        // tags
        userInput = TAG_DESC_FRIEND;
        descriptor = new EditCommand.EditPatientDescriptor();
        Set<Tag> testTags = new HashSet<>();
        testTags.add(new Tag(VALID_TAG_FRIEND));
        descriptor.setTags(testTags);
        expectedCommand = new EditCommand(descriptor);
        assertParseComplexSuccess(parser, userInput, expectedCommand, PersonType.PATIENT);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        String userInput = INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseComplexFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE),
                PersonType.SPECIALIST);

        // invalid followed by valid
        userInput = PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseComplexFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE),
                PersonType.SPECIALIST);

        // mulltiple valid fields repeated
        userInput = PHONE_DESC_AMY + LOCATION_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + LOCATION_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + LOCATION_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseComplexFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LOCATION),
                PersonType.SPECIALIST);

        // multiple invalid values
        userInput = INVALID_PHONE_DESC + INVALID_EMAIL_DESC + INVALID_PHONE_DESC + INVALID_EMAIL_DESC;

        assertParseComplexFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL),
                PersonType.SPECIALIST);
    }

    @Test
    public void parse_resetTags_success() {
        Patient firstPerson = (Patient) model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.updateSelectedPerson(firstPerson);

        String userInputArgs = TAG_EMPTY;

        // Expected edit command for clearing tags
        EditCommand.EditPatientDescriptor descriptor = new EditCommand.EditPatientDescriptor();
        descriptor.setTags(new HashSet<Tag>());
        EditCommand expectedCommand = new EditCommand(descriptor);

        PersonType personType = model.getSelectedPerson() instanceof Patient
                ? PersonType.PATIENT
                : PersonType.SPECIALIST;

        assertParseComplexSuccess(parser, userInputArgs, expectedCommand, personType);
    }

    @Test
    public void parse_emptyPrefixArguments_failure() {
        String userInputArgs = NAME_EMPTY + PHONE_EMPTY;
        String expectedMessage = String.format(MESSAGE_BLANK_ARGUMENTS,
                EditCommand.MESSAGE_USAGE_PATIENT);
        assertParseComplexFailure(parser, userInputArgs, expectedMessage, PersonType.PATIENT);

        userInputArgs = AGE_EMPTY;
        assertParseComplexFailure(parser, userInputArgs, expectedMessage, PersonType.PATIENT);

        expectedMessage = String.format(MESSAGE_BLANK_ARGUMENTS,
                EditCommand.MESSAGE_USAGE_SPECIALIST);
        userInputArgs = LOCATION_EMPTY;
        assertParseComplexFailure(parser, userInputArgs, expectedMessage, PersonType.SPECIALIST);
    }
}
