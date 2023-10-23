package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCommand;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

public class TagCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_TAG_FAILED + TagCommand.MESSAGE_USAGE);
    private static final String MESSAGE_TAG_FAILURE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE);
    private final TagCommandParser parser = new TagCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no Student Number specified
        assertParseFailure(parser, TAG_EMPTY, MESSAGE_TAG_FAILURE);

        // no field specified
        assertParseFailure(parser, VALID_STUDENT_NUMBER_AMY, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_TAG_FAILURE);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // student number not starting with "A"
        assertParseFailure(parser, "B2103818N" + TAG_EMPTY, MESSAGE_TAG_FAILURE);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "A2103818N some random tag", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "A2103818N /t label", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, VALID_STUDENT_NUMBER_AMY
            + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAGS} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, VALID_STUDENT_NUMBER_AMY
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_STUDENT_NUMBER_AMY
                + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, VALID_STUDENT_NUMBER_AMY
                + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_STUDENT_NUMBER_AMY + TAG_DESC_HUSBAND + TAG_DESC_FRIEND;

        Student student = new StudentBuilder().withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        TagCommand expectedCommand = new TagCommand(
            new StudentNumber(VALID_STUDENT_NUMBER_AMY), student.getTags());

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        String userInput = VALID_STUDENT_NUMBER_AMY + TAG_EMPTY;

        TagCommand expectedCommand = new TagCommand(
            new StudentNumber(VALID_STUDENT_NUMBER_AMY), new HashSet<>());

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
