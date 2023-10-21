package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class EditInterviewCommandParserTest {
    @Test
    public void parse_nullArguments_throwsNullPointerException() {
        EditInterviewCommandParser editInterviewCommandParser = new EditInterviewCommandParser();
        assertThrows(NullPointerException.class, () -> editInterviewCommandParser.parse(null));
    }
}
