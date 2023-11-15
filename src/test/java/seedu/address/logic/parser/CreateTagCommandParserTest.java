package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

public class CreateTagCommandParserTest {

    private CreateTagCommandParser parser = new CreateTagCommandParser();
    private UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCreateTagCommand() {
        String[] tagParams = {"role developer", " dept software"};
        CreateTagCommand expectedCreateTagCommand = new CreateTagCommand(tagParams);

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " t/role developer t/dept software", expectedCreateTagCommand);

    }

    @Test
    public void parse_invalidArgsEmptyTag_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " t/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateTagCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_invalidArgsInvalidTagName_throwsParseException() {
        // no leading and trailing whitespaces
        assertParseFailure(parser, " t/category invalidTagName@", Tag.MESSAGE_CONSTRAINTS);

        // multiple whitespaces between keywords
        assertParseFailure(parser, "    t/category \n \t  invalidTagName@ \n", Tag.MESSAGE_CONSTRAINTS);
    }


    @Test
    public void parse_invalidArgsWithNoCategory_throwsParseException() {
        assertParseFailure(parser, " t/tagname", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateTagCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " t/  \n    \t tagname", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateTagCommand.MESSAGE_USAGE));
    }
}
