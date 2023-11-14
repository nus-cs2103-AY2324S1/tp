package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NATIONALITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MAJOR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MAJOR_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NATIONALITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NATIONALITY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NATIONALITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SM_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SM_LINKEDIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUT_FIRST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUT_SECOND_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Year;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB)
                .withSocialMediaLinks(VALID_SM_LINKEDIN_BOB, VALID_SM_GITHUB_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB
                        + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                        + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple tutorials - all accepted
        Person expectedPersonMultipleTutorials = new PersonBuilder(BOB)
                .withTutorials(VALID_TUT_FIRST_BOB, VALID_TUT_SECOND_BOB).build();
        assertParseSuccess(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                        + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                        + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                new AddCommand(expectedPersonMultipleTutorials));

        // multiple social media links - all accepted
        Person expectedPersonMultipleSocialMediaLinks = new PersonBuilder(BOB)
                .withSocialMediaLinks(VALID_SM_LINKEDIN_BOB, VALID_SM_GITHUB_BOB).build();
        assertParseSuccess(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                        + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                        + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                new AddCommand(expectedPersonMultipleSocialMediaLinks));
    }

    @Test
    public void parse_repeatedNonSocialMediaLinkValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB + NATIONALITY_DESC_BOB + GENDER_DESC_BOB;
        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple majors
        assertParseFailure(parser, MAJOR_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MAJOR));

        // multiple years
        assertParseFailure(parser, YEAR_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_YEAR));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple descriptions
        assertParseFailure(parser, DESCRIPTION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // multiple nationalities
        assertParseFailure(parser, NATIONALITY_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NATIONALITY));

        // multiple genders
        assertParseFailure(parser, GENDER_DESC_AMY + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + MAJOR_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY
                        + YEAR_DESC_AMY + DESCRIPTION_DESC_AMY + NATIONALITY_DESC_BOB
                        + GENDER_DESC_BOB + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_MAJOR, PREFIX_YEAR,
                        PREFIX_EMAIL, PREFIX_DESCRIPTION, PREFIX_NATIONALITY, PREFIX_GENDER));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid major
        assertParseFailure(parser, INVALID_MAJOR_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MAJOR));

        // invalid year
        assertParseFailure(parser, INVALID_YEAR_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_YEAR));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // invalid nationality
        assertParseFailure(parser, INVALID_NATIONALITY_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NATIONALITY));

        // invalid gender
        assertParseFailure(parser, INVALID_GENDER_DESC + validExpectedPersonString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid major
        assertParseFailure(parser, validExpectedPersonString + INVALID_MAJOR_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_MAJOR));

        // invalid year
        assertParseFailure(parser, validExpectedPersonString + INVALID_YEAR_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_YEAR));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid description
        assertParseFailure(parser, validExpectedPersonString + INVALID_DESCRIPTION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DESCRIPTION));

        // invalid nationality
        assertParseFailure(parser, validExpectedPersonString + INVALID_NATIONALITY_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NATIONALITY));

        // invalid gender
        assertParseFailure(parser, validExpectedPersonString + INVALID_GENDER_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero social media links
        Person expectedPerson = new PersonBuilder(AMY).withTutorials().withSocialMediaLinks().build();
        assertParseSuccess(parser, NAME_DESC_AMY + MAJOR_DESC_AMY + YEAR_DESC_AMY
                        + EMAIL_DESC_AMY + DESCRIPTION_DESC_AMY + NATIONALITY_DESC_AMY + GENDER_DESC_AMY,
                        new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                        + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                        + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                expectedMessage);

        // missing major prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_MAJOR_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                        + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                        + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                expectedMessage);

        // missing year prefix
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + VALID_YEAR_BOB + EMAIL_DESC_BOB
                        + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                        + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + VALID_EMAIL_BOB
                        + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                        + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                        + VALID_DESCRIPTION_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                        + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                + NATIONALITY_DESC_BOB + VALID_GENDER_BOB,
            expectedMessage);

        // missing nationality prefix
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                + VALID_NATIONALITY_BOB + GENDER_DESC_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_MAJOR_BOB + VALID_YEAR_BOB + VALID_EMAIL_BOB
                        + VALID_DESCRIPTION_BOB + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid major
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_MAJOR_DESC + YEAR_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB, Major.MESSAGE_CONSTRAINTS);

        // invalid year
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + INVALID_YEAR_DESC + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB, Year.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + INVALID_EMAIL_DESC
                + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_DESCRIPTION_DESC + TUTORIAL_DESC_BOB + SM_DESC_BOB
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB, Description.MESSAGE_CONSTRAINTS);

        // invalid tutorial
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + INVALID_TUTORIAL_DESC + SM_DESC_BOB
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB, Tutorial.MESSAGE_CONSTRAINTS);

        // invalid social media
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + INVALID_SM_DESC
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB, SocialMediaLink.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
            + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
            + NATIONALITY_DESC_BOB + INVALID_GENDER_DESC, Gender.MESSAGE_CONSTRAINTS);

        // invalid nationality
        assertParseFailure(parser, NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
            + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
            + INVALID_NATIONALITY_DESC + GENDER_DESC_BOB, Nationality.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + MAJOR_DESC_BOB + YEAR_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_DESCRIPTION_DESC + TUTORIAL_DESC_BOB + SM_DESC_BOB
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + MAJOR_DESC_BOB + YEAR_DESC_BOB
                        + EMAIL_DESC_BOB + DESCRIPTION_DESC_BOB + TUTORIAL_DESC_BOB + SM_DESC_BOB
                           + NATIONALITY_DESC_BOB + GENDER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
