package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESCRIPTION_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESCRIPTION_DESC_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_EMAIL_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_EMAIL_DESC_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_PHONE_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_PHONE_DESC_ORACLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_DESCRIPTION_DESC_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_DESCRIPTION_DESC_SOFTWARE_ENGINEER_WITH_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_INTERVIEW_DATE_TIME_DESC_DATA_ANALYST;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_INTERVIEW_DATE_TIME_DESC_SOFTWARE_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_NAME_DESC_DATA_ANALYST;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_NAME_DESC_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INTERNSHIP_NAME_DESC_SOFTWARE_ENGINEER_WITH_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERNSHIP_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERNSHIP_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERNSHIP_INTERVIEW_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INTERNSHIP_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INNOVATIVE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TECH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_DESCRIPTION_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_EMAIL_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_PHONE_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_DATA_ANALYST_INTERVIEW_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_DESCRIPTION_DATA_ANALYST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INNOVATIVE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCompanies.APPLE;
import static seedu.address.testutil.TypicalCompanies.ORACLE;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITHOUT_DATETIME;
import static seedu.address.testutil.TypicalInternships.SOFTWARE_ENGINEER_WITH_DATETIME;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.addcommands.AddCommand;
import seedu.address.logic.commands.addcommands.AddCompanyCommand;
import seedu.address.logic.commands.addcommands.AddInternshipCommand;
import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyDescription;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_invalidTypeArgument_failure() {
        assertParseFailure(parser, " d ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parsePerson_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, " p " + PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddPersonCommand(expectedPerson));


        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser,
                " p " + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddPersonCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parsePerson_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, " p " + NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, " p " + PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, " p " + EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, " p " + ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple fields repeated
        assertParseFailure(parser,
                " p " + validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, " p " + INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, " p " + INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, " p " + INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, " p " + INVALID_ADDRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, " p " + validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, " p " + validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, " p " + validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, " p " + validExpectedPersonString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    }

    @Test
    public void parsePerson_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, " p " + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddPersonCommand(expectedPerson));
    }

    @Test
    public void parsePerson_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, " p " + VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, " p " + NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, " p " + NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, " p " + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " p " + VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parsePerson_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, " p " + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, " p " + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, " p " + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, " p " + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, " p " + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, " p " + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, " p " + PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseCompany_allFieldsPresent_success() {
        Company expectedCompany = new CompanyBuilder(ORACLE).withTags(VALID_TAG_TECH).build();

        // whitespace only preamble
        assertParseSuccess(parser, " c " + PREAMBLE_WHITESPACE + COMPANY_NAME_DESC_ORACLE
                + COMPANY_PHONE_DESC_ORACLE + COMPANY_EMAIL_DESC_ORACLE
                + COMPANY_DESCRIPTION_DESC_ORACLE + TAG_DESC_TECH, new AddCompanyCommand(expectedCompany));


        // multiple tags - all accepted
        Company expectedCompanyMultipleTags = new CompanyBuilder(ORACLE).withTags(VALID_TAG_TECH,
                VALID_TAG_INNOVATIVE).build();
        assertParseSuccess(parser,
                " c " + COMPANY_NAME_DESC_ORACLE
                        + COMPANY_PHONE_DESC_ORACLE + COMPANY_EMAIL_DESC_ORACLE
                        + COMPANY_DESCRIPTION_DESC_ORACLE + TAG_DESC_TECH + TAG_DESC_INNOVATIVE,
                new AddCompanyCommand(expectedCompanyMultipleTags));
    }

    @Test
    public void parseCompany_optionalFieldsMissing_success() {
        // zero tags
        Company expectedCompany = new CompanyBuilder(APPLE).withTags().withoutInternships().build();
        assertParseSuccess(parser,
                " c " + COMPANY_NAME_DESC_APPLE + COMPANY_PHONE_DESC_APPLE
                        + COMPANY_EMAIL_DESC_APPLE + COMPANY_DESCRIPTION_DESC_APPLE,
                new AddCompanyCommand(expectedCompany));
    }

    @Test
    public void parseCompany_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, " c " + VALID_COMPANY_NAME_APPLE + COMPANY_PHONE_DESC_APPLE
                        + COMPANY_EMAIL_DESC_APPLE + COMPANY_DESCRIPTION_DESC_APPLE,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, " c " + COMPANY_NAME_DESC_APPLE + VALID_COMPANY_PHONE_APPLE
                        + COMPANY_EMAIL_DESC_APPLE + COMPANY_DESCRIPTION_DESC_APPLE,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, " c " + COMPANY_NAME_DESC_APPLE + VALID_COMPANY_PHONE_APPLE
                        + VALID_COMPANY_EMAIL_APPLE + COMPANY_DESCRIPTION_DESC_APPLE,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, " c " + COMPANY_NAME_DESC_APPLE + VALID_COMPANY_PHONE_APPLE
                        + VALID_COMPANY_EMAIL_APPLE + VALID_COMPANY_DESCRIPTION_APPLE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " c " + VALID_COMPANY_NAME_APPLE + VALID_COMPANY_PHONE_APPLE
                        + VALID_COMPANY_EMAIL_APPLE + VALID_COMPANY_DESCRIPTION_APPLE,
                expectedMessage);
    }

    @Test
    public void parseCompany_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, " c " + INVALID_COMPANY_NAME_DESC + COMPANY_PHONE_DESC_APPLE
                + COMPANY_EMAIL_DESC_APPLE + COMPANY_DESCRIPTION_DESC_APPLE
                + TAG_DESC_TECH + TAG_DESC_INNOVATIVE, CompanyName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, " c " + COMPANY_NAME_DESC_APPLE + INVALID_COMPANY_PHONE_DESC
                + COMPANY_EMAIL_DESC_APPLE + COMPANY_DESCRIPTION_DESC_APPLE
                + TAG_DESC_TECH + TAG_DESC_INNOVATIVE, CompanyPhone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, " c " + COMPANY_NAME_DESC_APPLE + COMPANY_PHONE_DESC_APPLE
                + INVALID_COMPANY_EMAIL_DESC + COMPANY_DESCRIPTION_DESC_APPLE
                + TAG_DESC_TECH + TAG_DESC_INNOVATIVE, CompanyEmail.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, " c " + COMPANY_NAME_DESC_APPLE + COMPANY_PHONE_DESC_APPLE
                + COMPANY_EMAIL_DESC_APPLE + INVALID_COMPANY_DESCRIPTION_DESC
                + TAG_DESC_TECH + TAG_DESC_INNOVATIVE, CompanyDescription.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, " c " + COMPANY_NAME_DESC_APPLE + COMPANY_PHONE_DESC_APPLE
                + COMPANY_EMAIL_DESC_APPLE + COMPANY_DESCRIPTION_DESC_APPLE
                + INVALID_TAG_DESC + TAG_DESC_INNOVATIVE, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, " c " + INVALID_COMPANY_NAME_DESC + COMPANY_PHONE_DESC_APPLE
                        + COMPANY_EMAIL_DESC_APPLE + INVALID_COMPANY_DESCRIPTION_DESC,
                CompanyName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, " c " + PREAMBLE_NON_EMPTY + COMPANY_NAME_DESC_APPLE
                        + COMPANY_PHONE_DESC_APPLE + COMPANY_EMAIL_DESC_APPLE + COMPANY_DESCRIPTION_DESC_APPLE
                        + TAG_DESC_TECH + TAG_DESC_INNOVATIVE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseInternship_allFieldsPresent_success() {
        Index expectedIndex = Index.fromOneBased(1);
        Internship expectedInternship = new InternshipBuilder(SOFTWARE_ENGINEER_WITH_DATETIME).build();

        // whitespace only preamble
        assertParseSuccess(parser, " i " + PREAMBLE_WHITESPACE + INTERNSHIP_INDEX_DESC
                + INTERNSHIP_NAME_DESC_SOFTWARE_ENGINEER_WITH_DATE_TIME
                + INTERNSHIP_DESCRIPTION_DESC_SOFTWARE_ENGINEER_WITH_DATE_TIME
                + INTERNSHIP_INTERVIEW_DATE_TIME_DESC_SOFTWARE_ENGINEER,
                new AddInternshipCommand(expectedIndex, expectedInternship));
    }

    @Test
    public void parseInternship_optionalFieldsMissing_success() {
        Index expectedIndex = Index.fromOneBased(1);
        Internship expectedInternship = new InternshipBuilder(SOFTWARE_ENGINEER_WITHOUT_DATETIME).build();

        // no scheduled interview date time
        assertParseSuccess(parser, " i " + PREAMBLE_WHITESPACE + INTERNSHIP_INDEX_DESC
                + INTERNSHIP_NAME_DESC_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME
                + INTERNSHIP_DESCRIPTION_DESC_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME,
                new AddInternshipCommand(expectedIndex, expectedInternship));
    }

    @Test
    public void parseInternship_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, " i " + INTERNSHIP_NAME_DESC_DATA_ANALYST
                        + INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST + INTERNSHIP_INTERVIEW_DATE_TIME_DESC_DATA_ANALYST,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, " i " + INTERNSHIP_INDEX_DESC + VALID_INTERNSHIP_DESCRIPTION_DATA_ANALYST
                        + INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST + INTERNSHIP_INTERVIEW_DATE_TIME_DESC_DATA_ANALYST,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, " i " + INTERNSHIP_INDEX_DESC + INTERNSHIP_NAME_DESC_DATA_ANALYST
                        + VALID_INTERNSHIP_DESCRIPTION_DATA_ANALYST + INTERNSHIP_INTERVIEW_DATE_TIME_DESC_DATA_ANALYST,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " i " + VALID_INTERNSHIP_DESCRIPTION_DATA_ANALYST
                        + VALID_INTERNSHIP_DESCRIPTION_DATA_ANALYST + VALID_INTERNSHIP_DATA_ANALYST_INTERVIEW_DATE_TIME,
                expectedMessage);
    }

    @Test
    public void parseInternship_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE);

        // no integer in preamble without date time
        assertParseFailure(parser, " i " + INTERNSHIP_NAME_DESC_DATA_ANALYST
                        + INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST, expectedMessage);

        // no integer in preamble with date time
        assertParseFailure(parser, " i " + INTERNSHIP_NAME_DESC_DATA_ANALYST
                        + INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST + INTERNSHIP_INTERVIEW_DATE_TIME_DESC_DATA_ANALYST,
                        expectedMessage);

        // invalid index
        assertParseFailure(parser, " i " + INVALID_INTERNSHIP_INDEX_DESC
                        + INTERNSHIP_NAME_DESC_SOFTWARE_ENGINEER_WITHOUT_DATE_TIME,
                        expectedMessage);

        // invalid name
        assertParseFailure(parser, " i " + INTERNSHIP_INDEX_DESC + INVALID_INTERNSHIP_NAME_DESC
                        + INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST + INTERNSHIP_INTERVIEW_DATE_TIME_DESC_DATA_ANALYST,
                        InternshipName.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, " i " + INTERNSHIP_INDEX_DESC + INTERNSHIP_NAME_DESC_DATA_ANALYST
                        + INVALID_INTERNSHIP_DESCRIPTION_DESC + INTERNSHIP_INTERVIEW_DATE_TIME_DESC_DATA_ANALYST,
                        InternshipDescription.MESSAGE_CONSTRAINTS);

        // invalid interview date time
        assertParseFailure(parser, " i " + INTERNSHIP_INDEX_DESC + INTERNSHIP_NAME_DESC_DATA_ANALYST
                        + INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST + INVALID_INTERNSHIP_INTERVIEW_DATE_TIME_DESC,
                        InternshipInterviewDateTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, " i " + INTERNSHIP_INDEX_DESC + INVALID_INTERNSHIP_NAME_DESC
                        + INTERNSHIP_DESCRIPTION_DESC_DATA_ANALYST + INVALID_INTERNSHIP_INTERVIEW_DATE_TIME_DESC,
                        InternshipName.MESSAGE_CONSTRAINTS);
    }
}
