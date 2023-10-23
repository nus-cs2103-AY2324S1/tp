package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DeleteInterviewCommandParserTest {
    @Test
    public void parse_nullArguments_throwsNullPointerException() {
        DeleteInterviewCommandParser deleteInterviewCommandParser = new DeleteInterviewCommandParser();
        assertThrows(NullPointerException.class, () -> deleteInterviewCommandParser.parse(null));
    }
}
