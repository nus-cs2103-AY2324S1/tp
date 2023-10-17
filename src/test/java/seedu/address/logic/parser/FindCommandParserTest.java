package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.LicenceContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.PolicyExpiryContainsKeywordsPredicate;
import seedu.address.model.person.PolicyIssueContainsKeywordsPredicate;
import seedu.address.model.person.PolicyNumberContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

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
                new FindCommand(new NameContainsKeywordsPredicate("Alice Bob"),
                        new LicenceContainsKeywordsPredicate(""), new NricContainsKeywordsPredicate(""),
                        new PhoneContainsKeywordsPredicate(""), new PolicyNumberContainsKeywordsPredicate(""),
                        new TagContainsKeywordsPredicate(""), new PolicyExpiryContainsKeywordsPredicate(""),
                        new EmailContainsKeywordsPredicate(""), new PolicyIssueContainsKeywordsPredicate(""));
        assertParseSuccess(parser, "find n/Alice Bob", expectedFindCommand);
    }

}
