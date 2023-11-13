package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_PREAMBLE_DETECTED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.CompanyContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NricContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyExpiryContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyIssueContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PolicyNumberContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_argHasPreamble_throwsParseException() {
        assertParseFailure(parser, " abc n/Alice Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_PREAMBLE_DETECTED));
    }

    @Test
    public void parse_singlePrefixGivenNoValue_throwsParseException() {
        // Single prefix no value
        assertParseFailure(parser, " n/",
                String.format(MESSAGE_EMPTY_FIELDS, "n/"));


        assertParseFailure(parser, " l/",
                String.format(MESSAGE_EMPTY_FIELDS, "l/"));


        assertParseFailure(parser, " i/",
                String.format(MESSAGE_EMPTY_FIELDS, "i/"));


        assertParseFailure(parser, " p/",
                String.format(MESSAGE_EMPTY_FIELDS, "p/"));


        assertParseFailure(parser, " t/",
                String.format(MESSAGE_EMPTY_FIELDS, "t/"));


        assertParseFailure(parser, " e/",
                String.format(MESSAGE_EMPTY_FIELDS, "e/"));


        assertParseFailure(parser, " c/",
                String.format(MESSAGE_EMPTY_FIELDS, "c/"));


        assertParseFailure(parser, " pn/",
                String.format(MESSAGE_EMPTY_FIELDS, "pn/"));


        assertParseFailure(parser, " pi/",
                String.format(MESSAGE_EMPTY_FIELDS, "pi/"));


        assertParseFailure(parser, " pe/",
                String.format(MESSAGE_EMPTY_FIELDS, "pe/"));
    }

    @Test
    public void parse_multiplePrefixGivenAllNoValue_throwsParseException() {
        // All prefixes no value
        assertParseFailure(parser, " n/ l/",
                String.format(MESSAGE_EMPTY_FIELDS, "n/ l/"));

        assertParseFailure(parser, " n/ c/",
                String.format(MESSAGE_EMPTY_FIELDS, "n/ c/"));

        assertParseFailure(parser, " i/ p/",
                String.format(MESSAGE_EMPTY_FIELDS, "i/ p/"));
    }

    @Test
    public void parse_multiplePrefixSomeHasValue_throwsParseException() {
        // Some prefix has value
        assertParseFailure(parser, " n/Alice l/",
                String.format(MESSAGE_EMPTY_FIELDS, "l/"));

        assertParseFailure(parser, " n/ c/Google",
                String.format(MESSAGE_EMPTY_FIELDS, "n/"));

        assertParseFailure(parser, " i/589J p/ pi/01-01-2024",
                String.format(MESSAGE_EMPTY_FIELDS, "p/"));

        assertParseFailure(parser, " p/98765432 l/SLA e/ n/",
                String.format(MESSAGE_EMPTY_FIELDS, "n/ e/"));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate("Alice Bob"),
                        new LicenceContainsKeywordsPredicate(""), new NricContainsKeywordsPredicate(""),
                        new PhoneContainsKeywordsPredicate(""), new PolicyNumberContainsKeywordsPredicate(""),
                        new TagContainsKeywordsPredicate(""), new PolicyExpiryContainsKeywordsPredicate(""),
                        new EmailContainsKeywordsPredicate(""), new PolicyIssueContainsKeywordsPredicate(""),
                        new CompanyContainsKeywordsPredicate(""));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);
    }

}
