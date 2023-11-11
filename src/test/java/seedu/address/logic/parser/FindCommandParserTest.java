package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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
