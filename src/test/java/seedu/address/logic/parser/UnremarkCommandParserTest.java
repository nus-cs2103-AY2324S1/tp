package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_INDEX;
import static seedu.address.logic.Messages.MESSAGE_NON_INTEGER_INDEX;
import static seedu.address.logic.Messages.MESSAGE_NON_POSITIVE_INTEGER_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.company.Remark;

public class UnremarkCommandParserTest {
    private UnremarkCommandParser parser = new UnremarkCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput = targetIndex.getOneBased() + " ";
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_COMPANY, new Remark("No remarks"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidIndex_failure() {

        // no index
        assertParseFailure(parser, " ", MESSAGE_EMPTY_INDEX);

        // non-positive index
        assertParseFailure(parser, " 0 ", MESSAGE_NON_POSITIVE_INTEGER_INDEX);

        //invalid index
        assertParseFailure(parser, " 123** ", MESSAGE_NON_INTEGER_INDEX);


    }
}
