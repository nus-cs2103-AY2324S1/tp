package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.staffsnap.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.commands.SortCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.Descriptor;

class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_descriptorOnly_success() throws ParseException {
        SortCommand expectedStatusCommand = new SortCommand(Descriptor.PHONE, false);
        assertParseSuccess(parser, " d/phone", expectedStatusCommand);
    }

    @Test
    public void parse_descriptorOnly_failure() {
        assertThrows(ParseException.class, () -> parser.parse(" d/help"));
    }

    @Test
    public void parse_descriptorAndAscending_success() {
        SortCommand expectedStatusCommand = new SortCommand(Descriptor.PHONE, false);
        assertParseSuccess(parser, " d/phone asc/", expectedStatusCommand);
    }

    @Test
    public void parse_descriptorAndAscending_failure() {
        assertThrows(ParseException.class, () -> parser.parse(" d/help asc/"));
    }

    @Test
    public void parse_descriptorAndDescending_success() {
        SortCommand expectedStatusCommand = new SortCommand(Descriptor.PHONE, true);
        assertParseSuccess(parser, " d/phone dsc/", expectedStatusCommand);
    }

    @Test
    public void parse_descriptorAndDescending_failure() {
        assertThrows(ParseException.class, () -> parser.parse(" d/help dsc/"));
    }
}
