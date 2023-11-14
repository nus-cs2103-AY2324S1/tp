package seedu.address.logic.parser.tag;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tag.FilterByTagCommand;
import seedu.address.model.contact.ContactIsTaggedPredicate;
import seedu.address.model.tag.Tag;

public class FilterByTagCommandParserTest {
    private FilterByTagCommandParser parser = new FilterByTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterByTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        Tag firstTag = new Tag("friends");
        Tag secondTag = new Tag("owesMoney");

        List<Tag> tagList = new ArrayList<>();
        tagList.add(firstTag);
        tagList.add(secondTag);

        ContactIsTaggedPredicate predicate = new ContactIsTaggedPredicate(tagList);
        FilterByTagCommand expectedFilterCommand = new FilterByTagCommand(tagList, predicate);


        // no leading and trailing whitespaces
        assertParseSuccess(parser, "friends owesmoney", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " friends   owesmoney", expectedFilterCommand);

        // include both upper and lower case
        assertParseSuccess(parser, "frieNds owesMoney", expectedFilterCommand);
    }
}
