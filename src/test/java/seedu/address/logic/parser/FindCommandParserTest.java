package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalApplicants.AMY;
import static seedu.address.testutil.TypicalApplicants.BOB;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.applicant.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.applicant.predicate.ApplicantPredicate;
import seedu.address.model.applicant.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.applicant.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.applicant.predicate.PhoneContainsNumberPredicate;
import seedu.address.model.applicant.predicate.TagsContainKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(
                        new ApplicantPredicate(
                                Arrays.asList(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")))));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_repeatedPrefix_failure() {
        String validExpectedApplicantString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        //multiple tags
        assertParseFailure(parser, TAG_DESC_FRIEND + validExpectedApplicantString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TAG));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedApplicantString
                        + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY + TAG_DESC_FRIEND,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS,
                        PREFIX_EMAIL, PREFIX_PHONE, PREFIX_TAG));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        ApplicantPredicate namePredicate =
                new ApplicantPredicate(Arrays.asList(
                        new NameContainsKeywordsPredicate(Arrays.asList(AMY.getName().fullName.split("\\s+")))));
        ApplicantPredicate phonePredicate =
                new ApplicantPredicate(Arrays.asList(
                        new PhoneContainsNumberPredicate(AMY.getPhone().value)));

        // Only name
        assertParseSuccess(parser, NAME_DESC_AMY, new FindCommand(namePredicate));

        // only phone
        assertParseSuccess(parser, PHONE_DESC_AMY, new FindCommand(phonePredicate));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PHONE_DESC_BOB + TAG_DESC_FRIEND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY;

        ApplicantPredicate predicate = new ApplicantPredicate(Arrays.asList(
                new NameContainsKeywordsPredicate(Arrays.asList(AMY.getName().fullName.split("[,\\s]+"))),
                new PhoneContainsNumberPredicate(BOB.getPhone().value),
                new EmailContainsKeywordsPredicate(Arrays.asList(AMY.getEmail().toString())),
                new AddressContainsKeywordsPredicate(Arrays.asList(AMY.getAddress().value.split("[,\\s]+"))),
                new TagsContainKeywordsPredicate(Arrays.asList(VALID_TAG_FRIEND))
        ));

        FindCommand expectedCommand = new FindCommand(predicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
