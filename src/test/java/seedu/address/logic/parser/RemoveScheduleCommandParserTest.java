package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveScheduleCommand;

public class RemoveScheduleCommandParserTest {
    private final RemoveScheduleCommandParser parser = new RemoveScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // cca in user
        assertParseSuccess(parser, "user type/cca en/Basketball",
                new RemoveScheduleCommand("BASKETBALL", "cca"));

        // cca in friend
        assertParseSuccess(parser, "1 type/cca en/Basketball",
                new RemoveScheduleCommand("BASKETBALL", "cca", Index.fromOneBased(1)));

        // module in user
        assertParseSuccess(parser, "user type/module en/CS2103",
                new RemoveScheduleCommand("CS2103", "module"));

        // module in friend
        assertParseSuccess(parser, "1 type/module en/CS2103",
                new RemoveScheduleCommand("CS2103", "module", Index.fromOneBased(1)));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validCommand = "user type/cca en/Basketball";

        // multiple event type
        assertParseFailure(parser, validCommand + " type/cca",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: type/ ");

        // multiple event name
        assertParseFailure(parser, validCommand + " en/Basketball",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: en/ ");

        // multiple fields repeated
        assertParseFailure(parser, validCommand + " type/cca" + " en/Basketball",
                "You can only have 1 of each prefix!\n" + "Duplicated prefixes are: en/ type/ ");
    }

    @Test
    public void parse_fieldMissing_failure() {

        // missing schedule name prefix
        assertParseFailure(parser, "user type/cca",
                "Missing prefix(es) for en/ !\n" + "Message Usage:\n"
                        + RemoveScheduleCommand.MESSAGE_USAGE);

        // missing schedule type prefix
        assertParseFailure(parser, "user en/Basketball",
                "Missing prefix(es) for type/ !\n" + "Message Usage:\n"
                        + RemoveScheduleCommand.MESSAGE_USAGE);

        // all prefixes missing
        assertParseFailure(parser, "user cca Basketball",
                "Missing prefix(es) for en/ type/ !\n" + "Message Usage:\n"
                        + RemoveScheduleCommand.MESSAGE_USAGE);

        // wrong index
        assertParseFailure(parser, "wrong type/cca en/Basketball",
                "Invalid index!\n" + "Index can only be 'user' or a positive integer! \n");
    }

}
