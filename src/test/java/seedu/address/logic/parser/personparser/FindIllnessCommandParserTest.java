package seedu.address.logic.parser.personparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.personcommands.FindIllnessCommand;
import seedu.address.model.person.IllnessContainsKeywordsPredicate;


public class FindIllnessCommandParserTest {

    private FindIllnessCommandParser parser = new FindIllnessCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindIllnessCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindIllnessCommand expectedFindIllnessCommand =
                new FindIllnessCommand(new IllnessContainsKeywordsPredicate(Arrays.asList("Fever", "Headache")));
        assertParseSuccess(parser, "Fever Headache", expectedFindIllnessCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Fever \n \t Headache  \t", expectedFindIllnessCommand);
    }
}
