package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.model.gradedtest.GradedTest.DEFAULT_VALUE;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditGradedTestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gradedtest.Finals;
import seedu.address.model.gradedtest.MidTerms;
import seedu.address.model.gradedtest.PracticalExam;
import seedu.address.model.gradedtest.ReadingAssessment1;
import seedu.address.model.gradedtest.ReadingAssessment2;


public class EditGradedTestCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGradedTestCommand.MESSAGE_USAGE);
    private EditGradedTestCommandParser parser = new EditGradedTestCommandParser();

    @Test
    public void parse_validArgs_returnsEditGradedTestCommand() throws ParseException {
        // Valid input with all fields
        String input = "1 ra1/10 ra2/20 mt/70 f/100 pe/100";
        EditGradedTestCommand expectedCommand = new EditGradedTestCommand(
                Index.fromOneBased(1),
                Optional.of(new ReadingAssessment1("10")),
                Optional.of(new ReadingAssessment2("20")),
                Optional.of(new MidTerms("70")),
                Optional.of(new Finals("100")),
                Optional.of(new PracticalExam("100"))
        );
        assertEquals(expectedCommand, parser.parse(input));

        // Valid input with only some fields
        input = "1 ra1/10 f/100";
        expectedCommand = new EditGradedTestCommand(
                Index.fromOneBased(1),
                Optional.of(new ReadingAssessment1("10")),
                Optional.empty(),
                Optional.empty(),
                Optional.of(new Finals("100")),
                Optional.empty()
        );
        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Missing index
        assertThrows(ParseException.class, () -> parser.parse("ra1/10 ra2/20 mt/70 f/100 pe/100"));

        // Missing fields
        assertThrows(ParseException.class, () -> parser.parse("1"));

        // Missing value for a field
        assertThrows(ParseException.class, () -> parser.parse("1 ra1/ ra2/20 mt/70 f/100 pe/100"));

        // Unknown prefix
        assertThrows(ParseException.class, () -> parser.parse("1 ra1/10 xyz/20 mt/70 f/100 pe/100"));

        // Singular wrong regex for scores
        assertThrows(ParseException.class, () -> parser.parse("1 ra1/FAKEJARVIS"));

        // Singular wrong Multiple for scores
        assertThrows(ParseException.class, () -> parser.parse("1 ra1/fefe ra2/fefef mt/dfef"));

        // Singular wrong Multiple (Full House) for scores
        assertThrows(ParseException.class, () -> parser.parse("1 ra1/fefe ra2/fefef mt/dfef f/22 pe/11"));

        // Bad symbols
        assertThrows(ParseException.class, () -> parser.parse("1 ra1/* ra2/%$ mt/70 f/100 pe/100"));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInputWithAllFields_returnsEditGradedTestCommand() throws ParseException {
        String input = "1 ra1/10 ra2/20 mt/70 f/100 pe/100";
        EditGradedTestCommand expectedCommand = new EditGradedTestCommand(
                Index.fromOneBased(1),
                Optional.of(new ReadingAssessment1("10")),
                Optional.of(new ReadingAssessment2("20")),
                Optional.of(new MidTerms("70")),
                Optional.of(new Finals("100")),
                Optional.of(new PracticalExam("100"))
        );
        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void parse_validInputWithSomeFields_returnsEditGradedTestCommand() throws ParseException {
        String input = "1 ra1/10 f/100";
        EditGradedTestCommand expectedCommand = new EditGradedTestCommand(
                Index.fromOneBased(1),
                Optional.of(new ReadingAssessment1("10")),
                Optional.empty(),
                Optional.empty(),
                Optional.of(new Finals("100")),
                Optional.empty()
        );
        assertEquals(expectedCommand, parser.parse(input));
    }

    @Test
    public void parse_validInputWithDefaultValues_returnsEditGradedTestCommand1() throws ParseException {
        String input1 = "1 ra1/10 ra2/- mt/- f/- pe/-";
        EditGradedTestCommand expectedCommand1 = new EditGradedTestCommand(
                Index.fromOneBased(1),
                Optional.of(new ReadingAssessment1("10")),
                Optional.of(new ReadingAssessment2(DEFAULT_VALUE)),
                Optional.of(new MidTerms(DEFAULT_VALUE)),
                Optional.of(new Finals(DEFAULT_VALUE)),
                Optional.of(new PracticalExam(DEFAULT_VALUE))
        );
        assertEquals(expectedCommand1, parser.parse(input1));
    }

    @Test
    public void parse_validInputWithDefaultValues_returnsEditGradedTestCommand2() throws ParseException {
        String input2 = "1 pe/10000000";
        EditGradedTestCommand expectedCommand2 = new EditGradedTestCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(new PracticalExam("10000000"))
        );
        assertEquals(expectedCommand2, parser.parse(input2));
    }



    @Test
    public void parse_validInputWithDifferentScoreFormats_returnsEditGradedTestCommand() throws ParseException {
        String input = "1 ra1/1.5 ra2/2.0 mt/3 f/4.0 pe/5";
        EditGradedTestCommand expectedCommand = new EditGradedTestCommand(
                Index.fromOneBased(1),
                Optional.of(new ReadingAssessment1("1.5")),
                Optional.of(new ReadingAssessment2("2.0")),
                Optional.of(new MidTerms("3")),
                Optional.of(new Finals("4.0")),
                Optional.of(new PracticalExam("5"))
        );
        assertEquals(expectedCommand, parser.parse(input));
    }
}
