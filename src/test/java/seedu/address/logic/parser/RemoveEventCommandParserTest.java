package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveEventCommand;

public class RemoveEventCommandParserTest {
    private RemoveEventCommandParser parser = new RemoveEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // dated event in user
        assertParseSuccess(parser, "user en/CS2103 Meeting",
                new RemoveEventCommand("CS2103 MEETING", null));

        // dated event in friend
        assertParseSuccess(parser, "1 en/CS2103 Meeting",
                new RemoveEventCommand("CS2103 MEETING", Index.fromOneBased(1)));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validCommand = "user en/CS2103 Meeting";

        // multiple event name
        assertParseFailure(parser, validCommand + " en/CS2103 Meeting",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: en/ ");

    }

    @Test
    public void parse_fieldMissing_failure() {
        // missing event name prefix
        assertParseFailure(parser, "user",
                "Missing prefix(es) for en/ !\n" + "Message Usage:\n" + RemoveEventCommand.MESSAGE_USAGE);

        // all prefixes missing
        assertParseFailure(parser, "user CS2103 Meeting",
                "Missing prefix(es) for en/ !\n" + "Message Usage:\n"
                        + RemoveEventCommand.MESSAGE_USAGE);

        //wrong index
        assertParseFailure(parser, "wrong en/CS2103 Meeting",
                String.format("Invalid index!\n"
                        + "Index can only be 'user' or a positive integer! \n"));
    }

}
