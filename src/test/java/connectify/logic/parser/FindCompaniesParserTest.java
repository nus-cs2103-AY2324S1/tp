package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static connectify.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import connectify.logic.commands.FindCompaniesCommand;
import connectify.model.company.CompanyNameContainsKeywordsPredicate;


public class FindCompaniesParserTest {

    private FindCompaniesCommandParser parser = new FindCompaniesCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCompaniesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCompaniesCommand expectedFindCompaniesCommand =
                new FindCompaniesCommand(new CompanyNameContainsKeywordsPredicate(Arrays.asList("Facebook", "Meta")));
        assertParseSuccess(parser, "Facebook Meta", expectedFindCompaniesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Facebook \n \t Meta  \t", expectedFindCompaniesCommand);
    }

}
