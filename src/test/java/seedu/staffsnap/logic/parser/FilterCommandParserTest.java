package seedu.staffsnap.logic.parser;


import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.staffsnap.logic.commands.FilterCommand;
import seedu.staffsnap.model.applicant.CustomFilterPredicate;
import seedu.staffsnap.model.applicant.Email;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;

class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FilterCommand.MESSAGE_FAILURE);

    @Test
    void parse_missingParts_failure() {
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_nameOnly_success() {
        assertParseSuccess(parser, " n/ Name", new FilterCommand(new CustomFilterPredicate(new Name("Name"), null,
                null, null, null)));
    }

    @Test
    void parse_emailOnly_success() {
        assertParseSuccess(parser, " e/ test@test.com", new FilterCommand(new CustomFilterPredicate(null, null,
                new Email("test@test.com"), null, null)));
    }

    @Test
    void parse_positionOnly_success() {
        assertParseSuccess(parser, " p/ Software Engineer", new FilterCommand(new CustomFilterPredicate(null, null,
                null, new Position("Software Engineer"), null)));
    }

    @Test
    void parse_phoneOnly_success() {
        assertParseSuccess(parser, " hp/ 98765432", new FilterCommand(new CustomFilterPredicate(null, new Phone(
                "98765432"), null, null, null)));
    }
}